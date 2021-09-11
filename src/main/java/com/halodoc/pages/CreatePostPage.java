package com.halodoc.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.time.LocalDate;

import static com.halodoc.common.Utils.*;

public class CreatePostPage {
    WebDriver driver;

    public CreatePostPage(WebDriver driver){
        this.driver = driver;
    }

//    By title = By.xpath("//*[@class='wp-block wp-block-post-title block-editor-block-list__block editor-post-title editor-post-title__input rich-text']");
//    //By title = By.xpath("//*[@class='edit-post-visual-editor__post-title-wrapper']/h1/span");
//
//    By label = By.id("block-c47d8451-04da-46a5-9170-e10957f06042");
//
//    By plusSign = By.id("id-5afiha-1");
//
//    By addBlockButton = By.xpath("//*[@class='components-button block-editor-inserter__toggle has-icon']");
//
//    By welcomeHeader= By.xpath("//*[@class='welcome-tour-card__heading']");
//
//    By skipButtonInWelcomeHeader = By.xpath("//*[@class='components-button is-tertiary']");
    By createNewPostIframe = By.className("is-loaded");
    By title = By.xpath("//*[@class='edit-post-visual-editor__post-title-wrapper']/h1");
    By skipButtonInWelcomeModal = By.xpath("//*[@class='components-button is-tertiary']");
    By body = By.xpath("//*[@class='is-root-container block-editor-block-list__layout']/p");
    By body1 = By.xpath("//*[@class='is-root-container block-editor-block-list__layout']/p[2]");
    By body2 = By.xpath("//*[@class='is-root-container block-editor-block-list__layout']/p[3]");
    By block = By.xpath("//*[@class='block-editor-block-list__empty-block-inserter']//button");
    By imageButtonInsideBlock = By.xpath("//button[@class='components-button block-editor-block-types-list__item editor-block-list-item-image']");
    By uploadButton = By.xpath("//*[@class='components-form-file-upload']/button");
    By insertFromUrl = By.xpath("//button[@class='components-button block-editor-media-placeholder__button is-tertiary']");
    By insertFromUrlText = By.xpath("//input[@class='block-editor-media-placeholder__url-input-field']");
    By applyInInsertFromUrlText = By.xpath("//button[@class='components-button block-editor-media-placeholder__url-input-submit-button has-icon']");
    By publishButton = By.xpath("//button[@class='components-button editor-post-publish-panel__toggle editor-post-publish-button__button is-primary']");
    By scheduleButton = By.xpath("//button[@class='components-button editor-post-publish-button editor-post-publish-button__button is-primary']");
    //By publishCalendar = By.xpath("//button[@class='components-button components-panel__body-toggle']");
    //By publishCalendar = By.xpath("//*[@class='components-panel__body-title']/button");
    By publishCalendar = By.xpath("//*[text()='Publish:']");
    By publishDateInput = By.xpath("//input[@class='components-datetime__time-field-day-input']");
    By copyButton = By.xpath("//*[@class='post-publish-panel__postpublish-post-address__copy-button-wrap']/button");

    public CreatePostPage switchToFrame(){
        switchToIframe(driver, createNewPostIframe, 20);
        return this;
    }

    public CreatePostPage skipModalIfPresent(){
        if(waitIsPresent(driver, skipButtonInWelcomeModal, 5))
            driver.findElement(skipButtonInWelcomeModal).click();
        return this;
    }

    public CreatePostPage addTitle(String titleText){
        waitAndEnterText(driver, title, titleText);
        waitAndEnterText(driver, title, Keys.ENTER +"");
        waitAndEnterText(driver, title, Keys.ENTER +"");
        waitAndEnterText(driver, title, Keys.ENTER +"");
        return this;
    }

    public CreatePostPage addBody(String bodyText){
        waitAndEnterText(driver, body, bodyText);
        return this;
    }

    public CreatePostPage uploadImage(String imageName) throws InterruptedException, AWTException {
        //moving to p2 and then hovering mouse to p1 for block element to be visible
        waitAndClick(driver, body1);
        moveToElement(driver, body);
        //clicking on block element
        waitAndClick(driver, block);
        //clicking on image inside block and Upload button after it
        waitAndClick(driver, imageButtonInsideBlock);
        waitAndClick(driver, uploadButton);
        copyFileToClipboard(imageName);
        //sleep to provide sync between upload window dialog and pasting through keyboard
        Thread.sleep(3000);
        pasteClipboardDataUsingKeyboard();
        return this;
    }

    public CreatePostPage insertImageFromURL(String url){
        waitAndClick(driver, body1);
        waitAndClick(driver, block);
        waitAndClick(driver, imageButtonInsideBlock);
        waitAndClick(driver, insertFromUrl);
        waitAndEnterText(driver, insertFromUrlText, url);
        waitAndClick(driver, applyInInsertFromUrlText);
        return this;
    }

    public CreatePostPage schedulePost(){
        //publish
        waitAndClick(driver, publishButton);

        //change publish date
        waitAndClick(driver, publishCalendar);

        //select today + 5 date
        int date = getScheduledDate(5);
        waitAndClick(driver, publishDateInput);
        //clear data
        clearTextInInputUsingKeyboard(driver, publishDateInput);
        waitAndEnterText(driver, publishDateInput, String.valueOf(date));
        //click on schedule
        waitAndClick(driver, scheduleButton);
        return this;
    }

    public CreatePostPage copyPostLink() throws IOException, UnsupportedFlavorException {
        //copy the link
        waitAndClick(driver, copyButton);
        return this;
    }

    public CreatePostPage moveOutOfCreatePostPage(){
        moveOutOfFrame(driver);
        return this;
    }



}
