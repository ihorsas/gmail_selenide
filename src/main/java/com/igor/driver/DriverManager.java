package com.igor.driver;

import com.codeborne.selenide.WebDriverRunner;
import com.igor.utils.highlighter.Highlighter;
import com.igor.utils.url.Url;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.codeborne.selenide.Configuration;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Configuration.browserCapabilities;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.addListener;
import static com.igor.page.BasePage.waitUntilPagesIsLoaded;
import static com.igor.utils.constant.Constants.*;
import static com.igor.utils.parser.PropertyLoader.getValue;

public class DriverManager {
    private DriverManager() {
    }

    public static void initializeDriver() {
        if(!WebDriverRunner.hasWebDriverStarted()) {
            Configuration.timeout = Long.parseLong(getValue(WAIT_TIMERS_PROPERTIES_NAME, IMPLICITRY_WAIT_STR));
            Url url = new Url();
            url.setDns("stackoverflow.com");
            url.setUrn("users/signup?ssrc=head&returnurl=%2fusers%2fstory%2fcurrent%27");
            baseUrl = url.getUrl();
            startMaximized = false;
            browser = "chrome";
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-web-security");
            options.addArguments("--user-data-dir");
            options.addArguments("--allow-running-insecure-content");
            options.addArguments("--disable-extensions");
            options.addArguments("--no-sandbox");
            browserCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
            addListener(new Highlighter());
            open("/");
            waitUntilPagesIsLoaded();
        }
    }

}
