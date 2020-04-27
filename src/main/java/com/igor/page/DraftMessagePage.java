package com.igor.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class DraftMessagePage extends BasePage {
    private SelenideElement draftLetter() {
        return $(By.xpath("//div[@role='main']//tbody/tr[1]/td[6]//span[@class='bog']/span"));
    }

    public String getLetter() {
        draftLetter().waitUntil(Condition.not(Condition.empty), wait);
        return draftLetter().getText().isEmpty() ? "" : draftLetter().getText();
    }
}
