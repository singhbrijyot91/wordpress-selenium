package com.halodoc.common;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.time.LocalDate;

public class Utils {

    public static boolean waitIsPresent(WebDriver driver, By element){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        }catch(Exception e){
            return false;
        }
        return true;
    }

    public static boolean waitIsPresent(WebDriver driver, By element, int timeout){
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        }catch(Exception e){
            return false;
        }
        return true;
    }

    public static void clearText(WebDriver driver, By bylocator){
        if(waitIsPresent(driver, bylocator)){
            driver.findElement(bylocator).clear();
        }else
            throw new NoSuchElementException("Element not found : "+ bylocator);
    }

    public static void waitAndClick(WebDriver driver, By bylocator){
        if(waitIsPresent(driver, bylocator)){
            driver.findElement(bylocator).click();
        }else
            throw new NoSuchElementException("Element not found : "+ bylocator);
    }

    public static String waitAndGetText(WebDriver driver, By bylocator){
        if(waitIsPresent(driver, bylocator))
            return driver.findElement(bylocator).getText();
        else
            throw new NoSuchElementException("Element not found : "+ bylocator);
    }

    public static int getScheduledDate(int plusDays){
        return LocalDate.now().plusDays(plusDays).getDayOfMonth();

    }

    public static boolean checkAlertIsPresent(WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver, 10 );
        try{
            wait.until(ExpectedConditions.alertIsPresent());
        }catch (Exception e){
            return false;
        }
        return true;
//        if(wait.until(ExpectedConditions.alertIsPresent())==null)
//            return false;
//        else
//            return true;
    }

    public static void assertElementIsPresent(WebElement element){
    }

     public static void waitAndEnterText(WebDriver driver, By bylocator, String text){
         if(waitIsPresent(driver, bylocator)){
             driver.findElement(bylocator).sendKeys(text);
         }else
             throw new NoSuchElementException("Element not found : "+ bylocator);
     }                                                                            

    public static void moveToElement(WebDriver driver, By bylocator){
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(bylocator)).perform();
    }

    public static void switchToIframe(WebDriver driver, By bylocator, int timeout){
        new WebDriverWait(driver, timeout).until
                (ExpectedConditions.frameToBeAvailableAndSwitchToIt
                        (bylocator));
    }

    public static void copyFileToClipboard(String fileName){
        StringSelection str = new StringSelection(System.getProperty("user.dir") + fileName);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(str, null);
    }

    public static void pasteClipboardDataUsingKeyboard() throws AWTException, InterruptedException {
        Robot rb = new Robot();
        // press Contol+V for pasting
        rb.keyPress(KeyEvent.VK_CONTROL);
        Thread.sleep(500);
        rb.keyPress(KeyEvent.VK_V);

        // release Contol+V for pasting
        Thread.sleep(500);
        rb.keyRelease(KeyEvent.VK_V);
        Thread.sleep(500);
        rb.keyRelease(KeyEvent.VK_CONTROL);

        // for pressing and releasing Enter
        Thread.sleep(500);
        rb.keyPress(KeyEvent.VK_ENTER);
        Thread.sleep(500);
        rb.keyRelease(KeyEvent.VK_ENTER);
    }

    public static String getDataFromClipboard() throws IOException, UnsupportedFlavorException {
        return (String)Toolkit.getDefaultToolkit().getSystemClipboard().
                getData(DataFlavor.stringFlavor);
    }

    public static void moveOutOfFrame(WebDriver driver){
        driver.switchTo().defaultContent();
    }

    public static void clearTextInInputUsingKeyboard(WebDriver driver, By bylocator){
        waitAndEnterText(driver, bylocator, Keys.CONTROL + "a");
        waitAndEnterText(driver, bylocator, Keys.DELETE + "");
//        if(waitIsPresent(driver, bylocator)){
//            driver.findElement(bylocator).sendKeys(Keys.CONTROL + "a");
//            driver.findElement(bylocator).sendKeys(Keys.DELETE);
//        }else
//            throw new NoSuchElementException("Element not found : "+ bylocator);
    }

    public static void enterTextWithJavaScriptExecutor(WebDriver driver, WebElement element, String text){
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        //TODO - take value from parameter
        jse.executeScript("arguments[0].value='halodoc'", element);
    }

    public static void clickWithJavaScriptExecutor(WebDriver driver, By bylocator){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(bylocator));
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
    }
}
