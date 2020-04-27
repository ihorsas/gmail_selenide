package com.igor.business;

import com.igor.logger.AllureLogger;
import com.igor.page.DraftMessagePage;
import com.igor.page.MainPage;
import com.igor.page.SentPage;
import com.igor.page.widget.AlertDialogWidget;
import com.igor.page.widget.NewMessageWidget;
import com.igor.page.widget.SendingMessageDialogWidget;
import io.qameta.allure.Step;
import org.testng.Assert;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class MessageBO {
    private MainPage mainPage;
    private SentPage sentPage;
    private DraftMessagePage draftMessagePage;
    private NewMessageWidget newMessageWidget;
    private AlertDialogWidget alertDialogWidget;
    private SendingMessageDialogWidget sendingMessageDialogWidget;

    public MessageBO() {
        mainPage = new MainPage();
        sentPage = new SentPage();
        draftMessagePage = new DraftMessagePage();
        newMessageWidget = new NewMessageWidget();
        alertDialogWidget = new AlertDialogWidget();
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

    @Step("Correct receiver with receiver: {0}. running method: {method} step")
    public MessageBO correctReceiver(String receiver) {
        AllureLogger.info("closing alert dialog");
        alertDialogWidget.clickToButtonOk();
        AllureLogger.info("deleting incorrect receiver");
        newMessageWidget.clickToDeleteContact();
        AllureLogger.info("writing correct receiver");
        newMessageWidget.setReceiverField(receiver);
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
        AllureLogger.info("sending message");
        newMessageWidget.clickOnButtonSaveAndCloseFormMessage();
        return this;
    }


    @Step("Checking that alert dialog opened. running method: {method} step")
    public MessageBO verifyAlertWidgetVisible() {
        AllureLogger.info("checking opened alert dialog");
        assertTrue(alertDialogWidget.alertDialogIsEnable(), "Alert dialog is not opened");
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

    public MessageBO verifyDraftMessageCreated(String topic) {
        AllureLogger.info("opening draft messages page");
        mainPage.proceedToDraftMessages();
        AllureLogger.info("checking draft messages page");
        assertEquals(draftMessagePage.getLetter(), topic, "Letter topic is not the same");
        return this;
    }
}
