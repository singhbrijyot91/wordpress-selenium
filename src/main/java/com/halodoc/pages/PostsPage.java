package com.halodoc.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.halodoc.common.Utils.*;

public class PostsPage {
    WebDriver driver;

    public PostsPage(WebDriver driver){
        this.driver = driver;
    }

    By addNewPostButton = By.xpath("//*[@class='button post-type-list__add-post is-compact is-primary']");

    By publishedTab = By.xpath("//*[text()='Published' and contains(@class,'section-nav-tab__text')]");

    By scheduledTab = By.xpath("//*[text()='Scheduled' and contains(@class,'section-nav-tab__text')]");

    //By allPostsInScheduledTab = By.xpath("//*[@class='post-item__title']/a");
    By allPostsInScheduledTab = By.xpath("//*[@class='post-item__panel']");
    By allPostsTitleInScheduledTab = By.xpath("//h1[@class='post-item__title']");
    By allPostsButton = By.xpath("//button[@class='button ellipsis-menu__toggle is-borderless']");
    By binButtonInPost = By.xpath("//*[@class='popover__menu-item'][4]");

    By scheduledTabCount = By.xpath("//span[@class='section-nav-tab__text' and text()='Scheduled']/span");

    public boolean verifyPostsPageIsDisplayed(){
        if(waitIsPresent(driver, publishedTab)) return true;
        return false;
    }

    public PostsPage clickAddNewPostButton(){
        if(verifyPostsPageIsDisplayed())
            waitAndClick(driver, addNewPostButton);
            //driver.findElement(addNewPostButton).click();
        return this;
    }

    public PostsPage clickScheduledTab(){
        waitAndClick(driver, scheduledTab);
        return this;
    }

    public boolean createdPostIsDisplayed(String expectedPostTitle) throws InterruptedException {
        Thread.sleep(5000);
        List<WebElement> allPosts = driver.findElements(allPostsInScheduledTab);
        System.out.println(allPosts.size());
        for(WebElement post : allPosts){
            if(post.findElement(allPostsTitleInScheduledTab).getText()
                    .equals(expectedPostTitle)){
                post.findElement(allPostsButton).click();
                return true;
            }
        }
        return false;
    }

    public PostsPage deletePost(){
        waitAndClick(driver, binButtonInPost);
        return this;
    }

    public int getNumberOfPostsInScheduledTab(){
        return Integer.valueOf(waitAndGetText(driver, scheduledTabCount));
    }
}
