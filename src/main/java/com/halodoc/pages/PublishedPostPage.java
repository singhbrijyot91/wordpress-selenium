package com.halodoc.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import static com.halodoc.common.Utils.waitAndGetText;
import static com.halodoc.common.Utils.waitIsPresent;

public class PublishedPostPage {
    WebDriver driver;

    public PublishedPostPage(WebDriver driver){
        this.driver = driver;
    }

    By titleTextDisplayed = By.xpath("//*[@class='entry-title']");
    By bodyDisplayed = By.xpath("//*[@class='entry-content']/p");
    By imagesDisplayed = By.xpath("//*[@class='entry-content']/figure");
    By scheduledDate = By.xpath("//*[@class='entry-footer responsive-max-width']//time[@class='entry-date published']");

    public String getTitleText(){
        return waitAndGetText(driver, titleTextDisplayed);
    }

    public String getBodyText(){
        return waitAndGetText(driver, bodyDisplayed);
    }

    public int getNumberOfImagesDisplayed(){
        if(waitIsPresent(driver, imagesDisplayed)){
            return driver.findElements(imagesDisplayed).size();
        }else
            throw new NoSuchElementException("Element not found : "+ imagesDisplayed);
    }

    public String getScheduledDate(){
        return waitAndGetText(driver, scheduledDate);
    }
}
