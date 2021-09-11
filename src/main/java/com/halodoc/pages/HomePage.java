package com.halodoc.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.halodoc.common.Utils.waitAndClick;
import static com.halodoc.common.Utils.waitIsPresent;

public class HomePage {

    WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    By postsSideBar = By.xpath("//*[text()='Posts']");

    By myHomeLabel = By.xpath("//h1[@class='formatted-header__title wp-brand-font']");

    By homeIcon = By.id("wp-admin-bar-blog");

    public boolean verifyHomePage(){
        if(waitIsPresent(driver, myHomeLabel)) return true;
        return false;
//        if(driver.findElement(myHomeLabel).isDisplayed())
//            return true;
//        //TODO : Use Assert if not on home page
//        return false;
    }

    public HomePage goToPostsPage(){
        if(verifyHomePage())
            waitAndClick(driver, postsSideBar);
            //driver.findElement(postsSideBar).click();
        return this;
    }

    public HomePage goToHomePage(){
        waitAndClick(driver, homeIcon);
        return this;
    }


}
