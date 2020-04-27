package com.igor.page;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.igor.logger.AllureLogger;
import com.igor.utils.url.Url;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.testng.Assert.assertTrue;

public class LogInPage extends BasePage {
    private SelenideElement loginWithGoogle() {
        return $(By.xpath("//*[@id='openid-buttons']/button[1]"));
    }

    private SelenideElement usernameField() {
        return $("#identifierId");
    }

    private SelenideElement passwordField() {
        return $("#password input");
    }

    private SelenideElement errorMessageUsername() {
        return $(By.xpath("//div[@aria-atomic='true']//span/.."));
    }

    private SelenideElement errorMessagePassword() {
        return $(By.xpath("//*[@id=\"view_container\"]/div/div/div[2]/div/div[1]/div/form/span/section/div/div/div[1]/div[2]/div[2]/span/text()"));
    }

    @Step("Enter username {0} running method: {method} step")
    public LogInPage enterUsername(String username) {
        AllureLogger.info("Set username");
        usernameField().setValue(username).pressEnter();
        return this;
    }

    @Step("Go to gmail method: {method} step")
    public LogInPage goToGmail() {
        AllureLogger.info("Go to gmail");
        loginWithGoogle().click();
        return this;
    }

    @Step("Enter password: {0} running method: {method} step")
    public LogInPage enterPassword(String password) {
        AllureLogger.info("Set password");
        passwordField().setValue(password).pressEnter();
        return this;
    }

    @Step("Proceed to inbox running method: {method} step")
    public MainPage proceedToInbox() {
        new WebDriverWait(WebDriverRunner.getWebDriver(), wait).until((driver) -> driver.getCurrentUrl().contains("stackoverflow"));
        open(new Url().getUrl());
        waitUntilPagesIsLoaded();
        return new MainPage();
    }

    @Step("Log in with username: {0} and password: {1} running method: {method} step")
    public MainPage logIn(String username, String password) {
        return this.goToGmail()
                .enterUsername(username)
                .enterPassword(password)
                .proceedToInbox();
    }

    public void verifyErrorMessageUsername() {
        waitUntilPagesIsLoaded();
        assertTrue(errorMessageUsername().isDisplayed());
    }

    public void verifyErrorMessagePassword() {
        waitUntilPagesIsLoaded();
        assertTrue(errorMessagePassword().isDisplayed());
    }
}
