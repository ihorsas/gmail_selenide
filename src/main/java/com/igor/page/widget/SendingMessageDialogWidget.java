package com.igor.page.widget;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.igor.page.BasePage;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class SendingMessageDialogWidget extends BasePage {
    private SelenideElement undoButton(){
        return $(By.xpath("//*[@role='alert']//*[@role='link' and @id='link_undo']"));
    }

    public void waitWhileMessageSending(){
        undoButton().waitUntil(Condition.disappear, wait);
    }
}