package com.halodoc.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.halodoc.common.Utils.waitAndClick;
import static com.halodoc.common.Utils.waitAndEnterText;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    By emailText = By.id("usernameOrEmail");

    By continueButton = By.xpath("//*[contains(@class,'button form-button is-primary')]");

    By passwordText = By.id("password");

    public LoginPage login(String username, String password){
        waitAndEnterText(driver, emailText, username);
        waitAndClick(driver, continueButton);
        waitAndEnterText(driver, passwordText, password);
        waitAndClick(driver, continueButton);
        return this;
    }

}
