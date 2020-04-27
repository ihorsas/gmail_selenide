package com.igor.listener;

import com.codeborne.selenide.junit.ScreenShooter;
import com.igor.driver.DriverManager;
import io.qameta.allure.Attachment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.util.Objects;

public class CustomListener extends TestListenerAdapter {

    private Logger logger = LogManager.getLogger(CustomListener.class);

    public void onTestStart(ITestResult result){
        logger.info("Test started: " + result.getName());
    }

    public void onTestSuccess(ITestResult result){
        logger.info("Test success: " + result.getName());
    }

    public void onTestFailure(ITestResult result){
        logger.info("Test failed: " + result.getName());
        if(Objects.nonNull(result.getThrowable())){
            result.getThrowable().printStackTrace();
        }
    }
}
