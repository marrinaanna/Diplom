package ru.iteco.fmhandroid.tests;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeDown;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static ru.iteco.fmhandroid.data.dataHelper.nestedScrollTo;


import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;

import ru.iteco.fmhandroid.data.screenLoad;
import ru.iteco.fmhandroid.data.someData;
import ru.iteco.fmhandroid.page.Claim;
import ru.iteco.fmhandroid.page.Claims;
import ru.iteco.fmhandroid.page.CreateClaim;
import ru.iteco.fmhandroid.ui.AppActivity;
@LargeTest
@RunWith(AndroidJUnit4.class)

public class CreateAndEditClaim {
    Claims claimsPage = new Claims();
    Claim claimPage = new Claim();
    CreateClaim createClaimPage = new CreateClaim();
    Claims.FilterClaimsWindow filterClaimsWindow = new Claims.FilterClaimsWindow();
    someData dat = new someData();
    screenLoad readyScreen = new screenLoad();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void readyScreen() {
        readyScreen.readyClaimsScreen();
    }

    String title = dat.title;
    String description = dat.description;
    String date = dat.date;
    String date2 = dat.date2;
    String time = dat.time;
    String comment = dat.comment;
    String comment2 = dat.comment2;

    @Test
    @DisplayName("Создание заявки")
    public void testCreateClaim() {
        claimsPage.createClaimButton.perform(click());
        createClaimPage.checkCreateClaimScreenLoaded();
        createClaimPage.fillInTitle(title);
        createClaimPage.fillInDate(date2);
        createClaimPage.fillInTime(time);
        createClaimPage.fillItDescription(description);
        createClaimPage.saveClaim();
        claimsPage.checkClaimsScreenLoaded();
    }

    @Test
    @DisplayName("Редактирование заявки (title, date, time, description) в статусе open")
    public void testEditClaim() {
        claimsPage.claimsFilterButton.check(matches(isDisplayed()));
        claimsPage.claimsFilterButton.perform(click());
        filterClaimsWindow.checkFilterScreenLoaded();
        filterClaimsWindow.checkOpen();
        filterClaimsWindow.uncheckInProgress();
        filterClaimsWindow.uncheckExecuted();
        filterClaimsWindow.uncheckCanceled();
        filterClaimsWindow.cLickOk();
        claimsPage.openClaim(0);
        claimPage.editClaimButton.perform(nestedScrollTo());
        claimPage.editClaimButton.perform(click());
        createClaimPage.checkEditClaimScreenLoaded();
        createClaimPage.fillInTitle(title);
        createClaimPage.fillInDate(date);
        createClaimPage.fillInTime(time);
        createClaimPage.fillItDescription(description);
        createClaimPage.saveClaim();
        claimPage.checkClaimScreenLoaded();
        claimPage.title.perform(swipeDown());
        claimPage.checkCreatedClaimElement(title, date, time, description);
    }

    @Test
    @DisplayName("Добавление нового комментария к заявке в статусе open")
    public void testAddNewComment() {
        claimsPage.claimsFilterButton.check(matches(isDisplayed()));
        claimsPage.claimsFilterButton.perform(click());
        filterClaimsWindow.checkFilterScreenLoaded();
        filterClaimsWindow.checkOpen();
        filterClaimsWindow.uncheckInProgress();
        filterClaimsWindow.uncheckExecuted();
        filterClaimsWindow.uncheckCanceled();
        filterClaimsWindow.cLickOk();
        claimsPage.openClaim(0);
        claimPage.addCommentButton.perform(nestedScrollTo());
        claimPage.addCommentButton.perform(click());
        claimPage.checkCommentLoaded();
        claimPage.addComment(comment);
        claimPage.checkClaimScreenLoaded();
        claimPage.addCommentButton.perform(nestedScrollTo());
        claimPage.checkComment(comment);
    }

    @Test
    @DisplayName("Редактирование комментария в заявке в статусе open")
    public void testEditComment() {
        claimsPage.claimsFilterButton.check(matches(isDisplayed()));
        claimsPage.claimsFilterButton.perform(click());
        filterClaimsWindow.checkFilterScreenLoaded();
        filterClaimsWindow.checkOpen();
        filterClaimsWindow.uncheckInProgress();
        filterClaimsWindow.uncheckExecuted();
        filterClaimsWindow.uncheckCanceled();
        filterClaimsWindow.cLickOk();
        claimsPage.openClaim(0);
        claimPage.addCommentButton.perform(nestedScrollTo());
        claimPage.addCommentButton.perform(click());
        claimPage.checkCommentLoaded();
        claimPage.addComment(comment);
        claimPage.checkClaimScreenLoaded();
        claimPage.openComment(0);
        claimPage.checkCommentLoaded();
        claimPage.addComment(comment2);
        claimPage.checkClaimScreenLoaded();
        claimPage.checkComment(comment2);
    }
}