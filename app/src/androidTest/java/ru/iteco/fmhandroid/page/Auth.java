package ru.iteco.fmhandroid.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

import ru.iteco.fmhandroid.data.dataHelper;
import static ru.iteco.fmhandroid.data.dataHelper.*;

public class Auth {
    ViewInteraction screenName =
            onView(allOf(withText("Authorization"), withParent(withParent(withId(R.id.nav_host_fragment)))));
    ViewInteraction loginField =
            onView(allOf(withHint("Login"), withParent(withParent(withId(R.id.login_text_input_layout)))));
    ViewInteraction passField =
            onView(allOf(withHint("Password"), withParent(withParent(withId(R.id.password_text_input_layout)))));
    ViewInteraction signButton =
            onView(allOf(withText("Sign in"), withId(R.id.enter_button)));

    int massageIdWrongAuth = R.string.wrong_login_or_password;
    int massageIdEmptyAuth = R.string.empty_login_or_password;
    Main mainScreenPage = new Main();

    public void checkLoadScreen() {
        Allure.step("Проверка загрузки экрана авторизации");
        elementWaiting(withId(R.id.enter_button), 10000);
    }

    public void isAuthScreen() {
        Allure.step("Проверка элементтов экрана авторизации");
        screenName.check(matches(isDisplayed()));
        loginField.check(matches(isDisplayed()));
        passField.check(matches(isDisplayed()));
        signButton.check(matches(isDisplayed()));
    }

    public void login() {
        Allure.step("Login");
        isAuthScreen();
        enterLogin(dataHelper.AuthInfo.validAuth().getLogin());
        enterPassword(dataHelper.AuthInfo.validAuth().getPass());
        signIn();
        mainScreenPage.checkMainScreenLoaded();
        mainScreenPage.isMainScreen();
    }

    public void enterLogin(String info) {
        loginField.perform(replaceText(info), closeSoftKeyboard());
    }

    public void enterPassword(String info) {
        passField.perform(replaceText(info), closeSoftKeyboard());
    }

    public void signIn() {
        signButton.perform(click());
    }

}