package ru.iteco.fmhandroid.tests;


import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.Matchers.not;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.data.screenLoad;
import ru.iteco.fmhandroid.page.About;
import ru.iteco.fmhandroid.page.Claim;
import ru.iteco.fmhandroid.page.Claims;
import ru.iteco.fmhandroid.page.CreateClaim;
import ru.iteco.fmhandroid.page.Main;
import ru.iteco.fmhandroid.page.News;
import ru.iteco.fmhandroid.page.OurMission;
import ru.iteco.fmhandroid.ui.AppActivity;
@LargeTest
@RunWith(AndroidJUnit4.class)

public class MainUnitTest {
    OurMission ourMission = new OurMission();
    Main mainScreenPage = new Main();
    News newsPage = new News();
    Claim claimPage = new Claim();
    Claims claimsPage = new Claims();
    CreateClaim createClaimPage = new CreateClaim();
    About aboutPage = new About();
    screenLoad readyScreen = new screenLoad();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void readyScreen() {
        readyScreen.readyMainScreen();
    }

    @Test
    @DisplayName("Отображение и открытие раздела \"Our Mission'")
    public void testOpenOurMission() {
        mainScreenPage.ourMissionButton.check(matches(isDisplayed()));
        mainScreenPage.ourMissionButton.perform(click());
        ourMission.checkOurMissionScreenLoaded();
        ourMission.isOurMissionScreen();
    }

    @Test
    @DisplayName("Отображение и открытие \"Profile'")
    public void testOpenProfile() {
        mainScreenPage.logOutButton.check(matches(isDisplayed()));
        mainScreenPage.logOutButton.perform(click());
        mainScreenPage.logOut.check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Отображение и открытие Menu")
    public void testOpenMenu() {
        mainScreenPage.menuButton.check(matches(isDisplayed()));
        mainScreenPage.menuButton.perform(click());
        mainScreenPage.menuMain.check(matches(isDisplayed()));
        mainScreenPage.menuNews.check(matches(isDisplayed()));
        mainScreenPage.menuClaims.check(matches(isDisplayed()));
        mainScreenPage.menuAbout.check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Вкладка News: развернуть, свернуть ")
    public void testOpenClseUnitNews() {
        mainScreenPage.unitNewsButton.check(matches(isDisplayed()));
        mainScreenPage.unitNewsButton.perform(click());
        mainScreenPage.allNewsButton.check(matches(not(isDisplayed())));
        mainScreenPage.unitNewsButton.perform(click());
        mainScreenPage.allNewsButton.check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Вкладка News: переход в раздел All News")
    public void testToAllNews() {
        mainScreenPage.allNewsButton.check(matches(isDisplayed()));
        mainScreenPage.allNewsButton.perform(click());
        newsPage.checkNewsScreenLoaded();
    }

    @Test
    @DisplayName("Вкладка Claims: свернуть/развернуть список")
    public void testOpenCloseUnitClaim() {
        mainScreenPage.unitClaimsButton.check(matches(isDisplayed()));
        mainScreenPage.unitClaimsButton.perform(click());
        mainScreenPage.allClaimsButton.check(matches(not(isDisplayed())));
        mainScreenPage.unitClaimsButton.perform(click());
        mainScreenPage.allClaimsButton.check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Вкладка Claims: развернуть отзыв")
    public void testOpenClaim() {
        mainScreenPage.singleClaim.check(matches(isDisplayed()));
        mainScreenPage.singleClaim.perform(actionOnItemAtPosition(0, click()));
        claimPage.checkClaimScreenLoaded();
    }

    @Test
    @DisplayName("Вкладка Claims: переход в All claims")
    public void testToAllClaims() {
        mainScreenPage.allClaimsButton.check(matches(isDisplayed()));
        mainScreenPage.allClaimsButton.perform(click());
        claimsPage.checkClaimsScreenLoaded();
    }

    @Test
    @DisplayName("Вкладка Claims: добавить новый отзыв")
    public void testAddNewClaim() {
        mainScreenPage.createClaimButton.check(matches(isDisplayed()));
        mainScreenPage.createClaimButton.perform(click());
        createClaimPage.checkCreateClaimScreenLoaded();
    }

    @Test
    @DisplayName("Menu: перейти в раздел Main")
    public void testToMain() {
        mainScreenPage.menuButton.check(matches(isDisplayed()));
        mainScreenPage.menuButton.perform(click());
        mainScreenPage.menuClaims.check(matches(isDisplayed()));
        mainScreenPage.menuClaims.perform(click());
        claimsPage.checkClaimsScreenLoaded();
        mainScreenPage.menuButton.check(matches(isDisplayed()));
        mainScreenPage.menuButton.perform(click());
        mainScreenPage.menuMain.check(matches(isDisplayed()));
        mainScreenPage.menuMain.perform(click());
        mainScreenPage.checkMainScreenLoaded();
    }

    @Test
    @DisplayName("Menu: перейти в раздел Claims")
    public void testToClaims() {
        mainScreenPage.goToClaims();
        claimsPage.checkClaimsScreenLoaded();
    }

    @Test
    @DisplayName("Menu: перейти в раздел News")
    public void testToNews() {
        mainScreenPage.goToNews();
        newsPage.checkNewsScreenLoaded();
    }

    @Test
    @DisplayName("Menu: перейти в раздел About")
    public void testToAbout() {
        mainScreenPage.menuButton.check(matches(isDisplayed()));
        mainScreenPage.menuButton.perform(click());
        mainScreenPage.menuAbout.check(matches(isDisplayed()));
        mainScreenPage.menuAbout.perform(click());
        aboutPage.checkAboutScreenLoaded();
    }
}