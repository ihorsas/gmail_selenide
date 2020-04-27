package com.igor;

import com.igor.model.User;
import com.igor.page.LogInPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.igor.utils.parser.JsonParser.getValidUser;

public class LoginTest extends GmailTest {

    @Test
    public void loginTest() {
        User validUser = getValidUser();
        LogInPage logInBO = new LogInPage();
        logInBO.logIn(validUser.getUsername(), validUser.getPassword())
                .verifyInboxOpen();
    }

    @Test
    public void invalidUsernameTest() {
        LogInPage logInBO = new LogInPage();
        logInBO.goToGmail()
                .enterUsername("sdfasdjnfsdfjn@dfkfsdjk.com")
                .verifyErrorMessageUsername();
    }

    @Test
    public void invalidPasswordTest() {
        User validUser = getValidUser();
        LogInPage logInBO = new LogInPage();
        logInBO.goToGmail()
                .enterUsername(validUser.getUsername())
                .enterPassword("123231123")
                .verifyErrorMessagePassword();
    }
}
