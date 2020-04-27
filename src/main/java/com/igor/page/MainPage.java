package com.igor.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

public class MainPage extends BasePage {

    private SelenideElement signOutButton() {
        return $(By.xpath("//a[contains(@href, 'SignOut')]"));
    }

    private SelenideElement logOutButton() {
        return $(By.xpath("//a[contains(@href, 'Logout')]"));
    }

    private SelenideElement composeButton() {
        return $(By.xpath("//div[@role='complementary']/..//div[@role='button']"));
    }

    private SelenideElement searchField() {
        return $(By.xpath("//form[@role='search']//input"));
    }

    private SelenideElement searchButton() {
        return $(By.xpath("//form[@role='search']/button[4]"));
    }

    private SelenideElement draftMessagesButton(){
        return $( "[role='navigation'] a[href$='#drafts'][draggable]");
    }


    public void clickToComposeButton() {
        waitUntilPagesIsLoaded();
        composeButton().waitUntil(Condition.enabled, wait).click();
    }

    public void proceedToSentPage() {
        searchField().setValue("in:sent");
        searchButton().waitUntil(Condition.enabled, wait)
                .click();
        new WebDriverWait(WebDriverRunner.getWebDriver(), 40)
                .until(ExpectedConditions.urlContains("sent"));
    }

    public void proceedToDraftMessages(){
        draftMessagesButton().waitUntil(Condition.enabled, wait).click();
        waitUntilPagesIsLoaded();
    }

    public void logOut(){
        signOutButton().waitUntil(Condition.enabled, wait).click();
        waitUntilPagesIsLoaded();
        logOutButton().waitUntil(Condition.enabled, wait).click();
        waitUntilLogOut();
    }

    public void verifyInboxOpen() {
        assertTrue(WebDriverRunner.getWebDriver().getCurrentUrl().contains("inbox"), "Inbox wasn't opened");
    }
}
