package com.igor.business;

import com.igor.logger.AllureLogger;
import com.igor.page.DraftMessagePage;
import com.igor.page.MainPage;
import com.igor.page.SentPage;
import com.igor.page.widget.NewMessageWidget;
import com.igor.page.widget.SendingMessageDialogWidget;
import io.qameta.allure.Step;

import static org.testng.Assert.assertEquals;

public class MessageBO {
    private MainPage mainPage;
    private SentPage sentPage;
    private DraftMessagePage draftMessagePage;
    private NewMessageWidget newMessageWidget;
    private SendingMessageDialogWidget sendingMessageDialogWidget;

    public MessageBO() {
        mainPage = new MainPage();
        sentPage = new SentPage();
        draftMessagePage = new DraftMessagePage();
        newMessageWidget = new NewMessageWidget();
        sendingMessageDialogWidget = new SendingMessageDialogWidget();
    }

    @Step("Fill fields for new message with receiver: {0}, topic: {1} and message: {2}. running method: {method} step")
    public MessageBO fillFieldsForMessage(String receiver, String topic, String message) {
        AllureLogger.info("Opening new message widget");
        mainPage.clickToComposeButton();
        AllureLogger.info("set receiver");
        newMessageWidget.setReceiverField(receiver);
        AllureLogger.info("set title");
        newMessageWidget.setTitleField(topic);
        AllureLogger.info("set message");
        newMessageWidget.setMessageField(message);
        return this;
    }

    @Step("Send message running method: {method} step")
    public MessageBO sendMessage() {
        AllureLogger.info("sending message");
        newMessageWidget.clickToSendButton();
        return this;
    }

    @Step("Create draft message message running method: {method} step")
    public MessageBO createDraftMessage() {
        AllureLogger.info("saving message");
        newMessageWidget.clickOnButtonSaveAndCloseFormMessage();
        return this;
    }

    @Step("Checking that letter with topic: {0} is in sent page. running method: {method} step")
    public MessageBO verifyLetterSent(String topic) {
        AllureLogger.info("waiting while sending message dialog widget is active");
        sendingMessageDialogWidget.waitWhileMessageSending();
        AllureLogger.info("opening sent page");
        mainPage.proceedToSentPage();
        AllureLogger.info("checking sent page");
        assertEquals(sentPage.getLetter(), topic, "Letter topic is not the same");
        return this;
    }

    @Step("Checking that draft message with topic: {0} is in draft message page. running method: {method} step")
    public MessageBO verifyDraftMessageCreated(String topic) {
        AllureLogger.info("opening draft messages page");
        mainPage.proceedToDraftMessages();
        AllureLogger.info("checking draft messages page");
        assertEquals(draftMessagePage.getLetter(), topic, "Letter topic is not the same");
        return this;
    }
}
