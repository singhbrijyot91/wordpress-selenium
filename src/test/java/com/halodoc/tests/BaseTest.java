package com.halodoc.tests;

import com.halodoc.pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    WebDriver driver;
    public final String chromeDriverExeFile = "C:\\Users\\Brijyot\\Downloads\\chromedriver_win32\\chromedriver.exe";
    LoginPage loginPage;
    HomePage homePage;
    PostsPage postsPage;
    CreatePostPage createPostPage;
    PublishedPostPage publishedPostPage;
    public final String website = "https://wordpress.com/home/dummytest122131488.wordpress.com";
    public static final String USERNAME = "testdummyapp";
    public static final String PASSWORD = "!testdummyapp!";


    @BeforeSuite
    public void setupTests(){
        setupSelenium();
        driver.manage().window().maximize();
        //driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get(website);
        initializePages(driver);
        loginPage.login(USERNAME, PASSWORD);
        homePage.goToPostsPage();
    }

    @AfterSuite
    public void tearDown(){
        driver.quit();
    }

    public void setupSelenium(){
        System.setProperty("webdriver.chrome.driver", chromeDriverExeFile);
        //driver = new ChromeDriver();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-popup-blocking");
        driver = new ChromeDriver(options);
    }

    public void initializePages(final WebDriver driver){
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        postsPage = new PostsPage(driver);
        createPostPage = new CreatePostPage(driver);
        publishedPostPage = new PublishedPostPage(driver);
    }
}
