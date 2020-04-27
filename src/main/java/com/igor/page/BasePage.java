package com.igor.page;

import static com.codeborne.selenide.Condition.disappears;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.igor.utils.constant.Constants.*;
import static com.igor.utils.parser.PropertyLoader.getValue;

public abstract class BasePage {
    protected final static long wait = Long.parseLong(getValue(WAIT_TIMERS_PROPERTIES_NAME, PAGE_LOAD_TIMEOUT_STR));
    
    public static void waitUntilPagesIsLoaded() {
        $(byText("Loading")).waitUntil(disappears, wait);
        $(byText("Завантажується")).waitUntil(disappears, wait);
    }

    public static void waitUntilLogOut() {
        $(byText("Wait")).waitUntil(disappears, wait);
        $(byText("Зачекайте")).waitUntil(disappears, wait);
    }
}
