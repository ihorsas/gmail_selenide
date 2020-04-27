package com.igor.page.widget;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.igor.page.BasePage;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class AlertDialogWidget extends BasePage {
    private List<SelenideElement> alertDialogs(){
        return $$(By.xpath("//div[@role='alertdialog']"));
    }
    private SelenideElement buttonOk(){
        return $(By.xpath("//button[@name='ok']"));
    }

    public boolean alertDialogIsEnable() {
        return alertDialogs().size() != 0;
    }

    public void clickToButtonOk() {
        buttonOk().waitUntil(Condition.enabled, wait).click();
    }
}
