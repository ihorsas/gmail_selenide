package com.igor;

import com.igor.business.MessageBO;
import com.igor.page.LogInPage;
import com.igor.page.MainPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.UUID;
import java.util.stream.Stream;

import static com.igor.page.BasePage.waitUntilPagesIsLoaded;
import static com.igor.utils.parser.JsonParser.*;


public class InboxTest extends GmailTest {
    private ThreadLocal<String> MESSAGE_TITLE = new ThreadLocal<>();

    @DataProvider(parallel = true)
    public Iterator<Object[]> users() {
        return Stream.of(getUsers()).iterator();
    }

    @Test(dataProvider = "users")
    public void sendEmailTest(String username, String password) {
        MESSAGE_TITLE.set(UUID.randomUUID().toString());
        LogInPage logInPage = new LogInPage();
        logInPage.logIn(username, password)
                .verifyInboxOpen();
        waitUntilPagesIsLoaded();
        new MessageBO().fillFieldsForMessage(getReceiver(), MESSAGE_TITLE.get(), getMessage())
                .sendMessage()
                .verifyLetterSent(MESSAGE_TITLE.get());

    }

    @Test(dataProvider = "users")
    void saveDraftMessageTest(String username, String password) {
        MESSAGE_TITLE.set(UUID.randomUUID().toString());
        LogInPage logInPage = new LogInPage();
        logInPage.logIn(username, password)
                .verifyInboxOpen();
        waitUntilPagesIsLoaded();
        new MessageBO().fillFieldsForMessage(getReceiver(), MESSAGE_TITLE.get(), getMessage())
                .createDraftMessage()
                .verifyDraftMessageCreated(MESSAGE_TITLE.get());
    }

    @AfterMethod
    void teardown() {
        new MainPage().logOut();
        super.tearDown();
    }
}
