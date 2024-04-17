package ru.iteco.fmhandroid.tests;

import static ru.iteco.fmhandroid.data.dataHelper.checkMessage;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.data.dataHelper;
import ru.iteco.fmhandroid.data.screenLoad;
import ru.iteco.fmhandroid.page.Auth;
import ru.iteco.fmhandroid.page.Main;
import ru.iteco.fmhandroid.ui.AppActivity;
@LargeTest
@RunWith(AllureAndroidJUnit4.class)

public class AuthUnitTest {
    Auth authPage = new Auth();
    Main mainScreenPage = new Main();
    screenLoad readyScreen = new screenLoad();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void readyScreen() {
        readyScreen.readyAuthScreen();
    }

    @Test
    @DisplayName("Авторизация с корректными данными")
    public void testAuthValidInfo() {
        authPage.enterLogin(dataHelper.AuthInfo.validAuth().getLogin());
        authPage.enterPassword(dataHelper.AuthInfo.validAuth().getPass());
        authPage.signIn();
        mainScreenPage.checkMainScreenLoaded();
        mainScreenPage.isMainScreen();
    }
    @Test
    @DisplayName("Авторизация с некорректным логином")
    public void testAuthInvalidLogin() {
        authPage.enterLogin(dataHelper.AuthInfo.wrongLogin().getLogin());
        authPage.enterPassword(dataHelper.AuthInfo.validAuth().getPass());
        authPage.signIn();
        checkMessage(R.string.wrong_login_or_password, true);
    }
    @Test
    @DisplayName("Авторизация с некорректным паролем")
    public void testAuthInvalidPassword() {
        authPage.enterLogin(dataHelper.AuthInfo.validAuth().getLogin());
        authPage.enterPassword(dataHelper.AuthInfo.wrongPassword().getPass());
        authPage.signIn();
        checkMessage(R.string.wrong_login_or_password, true);
    }
    @Test
    @DisplayName("Авторизация с пустым логином")
    public void testAuthEmptyLogin() {
        authPage.enterLogin(dataHelper.AuthInfo.emptyLogin().getLogin());
        authPage.enterPassword(dataHelper.AuthInfo.validAuth().getPass());
        authPage.signIn();
        checkMessage(R.string.empty_login_or_password, true);
    }

    @Test
    @DisplayName("Авторизация с пустым паролем")
    public void testAuthEmptyPassword() {
        authPage.enterLogin(dataHelper.AuthInfo.validAuth().getLogin());
        authPage.enterPassword(dataHelper.AuthInfo.emptyPassword().getPass());
        authPage.signIn();
        checkMessage(R.string.empty_login_or_password, true);
    }

    @Test
    @DisplayName("Выход из личного кабинета")
    public void testLogout() {
        authPage.enterLogin(dataHelper.AuthInfo.validAuth().getLogin());
        authPage.enterPassword(dataHelper.AuthInfo.validAuth().getPass());
        authPage.signIn();
        mainScreenPage.checkMainScreenLoaded();
        mainScreenPage.isMainScreen();
        mainScreenPage.logOut();
        authPage.isAuthScreen();
    }
}