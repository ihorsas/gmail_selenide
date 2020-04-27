package com.igor;

import com.codeborne.selenide.testng.ScreenShooter;
import com.igor.listener.CustomListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static com.igor.driver.DriverManager.initializeDriver;

@Listeners({CustomListener.class, ScreenShooter.class})
public abstract class GmailTest {
    @BeforeMethod()
    public void setStartedPage() {
        initializeDriver();
    }

    @AfterMethod
    public void tearDown() {
        closeWebDriver();
    }
}
