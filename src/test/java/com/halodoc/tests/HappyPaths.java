package com.halodoc.tests;

import org.openqa.selenium.*;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.time.LocalDate;

import static com.halodoc.common.Utils.*;
import static org.assertj.core.api.Assertions.*;

public class HappyPaths extends BaseTest{

    public static final String TITLE_TEXT = "HaloDoc";
    private static final String BODY_TEXT = "This is body text.";
    private static final String IMAGE_NAME = "\\TajMahal.PNG";
    private static final String URL = "https://www.w3schools.com/images/w3schools_green.jpg";

    @Test
    public void testPostsCreation() throws InterruptedException, AWTException, IOException, UnsupportedFlavorException {
        postsPage.clickAddNewPostButton();
        createPostPage.switchToFrame().skipModalIfPresent()
                .addTitle(TITLE_TEXT).addBody(BODY_TEXT).
                uploadImage(IMAGE_NAME).insertImageFromURL(URL)
                .schedulePost().copyPostLink()
                .moveOutOfCreatePostPage();
        //open the copied url
        driver.get(getDataFromClipboard());
        //accept any alert
        if(checkAlertIsPresent(driver)) {
            driver.switchTo().alert().accept();
            System.out.println("Alert is present and accepted");
        }
        //assert title, body and number of images
        assertThat(publishedPostPage.getTitleText()).isEqualTo(TITLE_TEXT);
        assertThat(publishedPostPage.getBodyText()).isEqualTo(BODY_TEXT);
        assertThat(publishedPostPage.getNumberOfImagesDisplayed()).isEqualTo(2);

        //validate post in scheduled tab
        homePage.goToHomePage().goToPostsPage();
        postsPage.clickScheduledTab();
        assertThat(postsPage.getNumberOfPostsInScheduledTab()).isEqualTo(1);
        assertThat(postsPage.createdPostIsDisplayed(TITLE_TEXT)).isTrue();
        postsPage.deletePost();
        assertThat(postsPage.getNumberOfPostsInScheduledTab()).isEqualTo(0);
    }
}