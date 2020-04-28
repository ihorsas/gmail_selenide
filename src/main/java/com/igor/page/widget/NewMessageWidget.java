package com.igor.page.widget;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.igor.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;

public class NewMessageWidget extends BasePage {
    private SelenideElement receiverField() {
        return $(By.xpath("//textarea[@name='to']"));
    }

    private SelenideElement titleField() {
        return $(By.xpath("//input[@name='subjectbox']"));
    }

    private SelenideElement messageField() {
        return $(By.xpath("//input[@name='composeid']/../../table//div[@role='textbox']"));
    }

    private SelenideElement sendButton() {
        return $(By.xpath("//table[@role='group']/tbody/tr/td[1]/div/div/div[1]"));
    }

    private SelenideElement saveAndCloseFormMessage(){
        return $("*[class='Ha'][src='images/cleardot.gif']");
    }


    public void setReceiverField(String receiver) {
        receiverField().waitUntil(Condition.visible, wait).setValue(receiver);
    }

    public void setTitleField(String title) {
        titleField().setValue(title);
    }

    public void setMessageField(String message) {
        messageField().setValue(message);
    }

    public void clickToSendButton() {
        sendButton().waitUntil(Condition.enabled, wait).click();
    }

    public void clickOnButtonSaveAndCloseFormMessage() {
        saveAndCloseFormMessage().click();
        waitUntilPagesIsLoaded();
    }
}
