package ru.iteco.fmhandroid.tests;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.junit.Assert.assertEquals;
import static ru.iteco.fmhandroid.data.dataHelper.nestedScrollTo;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;


import ru.iteco.fmhandroid.data.screenLoad;
import ru.iteco.fmhandroid.page.Claim;
import ru.iteco.fmhandroid.page.Claims;
import ru.iteco.fmhandroid.page.CreateClaim;
import ru.iteco.fmhandroid.ui.AppActivity;
@LargeTest
@RunWith(AndroidJUnit4.class)

public class ClaimsUnitTest {
    Claim claimPage = new Claim();
    Claims claimsPage = new Claims();
    CreateClaim createClaimPage = new CreateClaim();
    Claims.FilterClaimsWindow filterClaimsWindow = new Claims.FilterClaimsWindow();
    screenLoad readyScreen = new screenLoad();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void readyScreen() {
        readyScreen.readyClaimsScreen();
    }

    @Test
    @DisplayName("Очистить фильтр")
    public void testFilterClear() {
        claimsPage.claimsFilterButton.check(matches(isDisplayed()));
        claimsPage.claimsFilterButton.perform(click());
        filterClaimsWindow.checkFilterScreenLoaded();
        filterClaimsWindow.uncheckOpen();
        filterClaimsWindow.uncheckInProgress();
        filterClaimsWindow.uncheckExecuted();
        filterClaimsWindow.uncheckCanceled();
        filterClaimsWindow.cLickOk();
        claimsPage.emptyClaimList();
    }

    @Test
    @DisplayName("Выбрать фильтр по статусу  Open")
    public void testFilterOpen() {
        claimsPage.claimsFilterButton.check(matches(isDisplayed()));
        claimsPage.claimsFilterButton.perform(click());
        filterClaimsWindow.checkFilterScreenLoaded();
        filterClaimsWindow.checkOpen();
        filterClaimsWindow.uncheckInProgress();
        filterClaimsWindow.uncheckExecuted();
        filterClaimsWindow.uncheckCanceled();
        filterClaimsWindow.cLickOk();
        claimsPage.openClaim(0);
        claimPage.checkClaimScreenLoaded();
        assertEquals("Open", claimPage.getStatus());
    }

    @Test
    @DisplayName("Выбрать фильтр по статусу  In progress")
    public void testFilterInProgress() {
        claimsPage.claimsFilterButton.check(matches(isDisplayed()));
        claimsPage.claimsFilterButton.perform(click());
        filterClaimsWindow.checkFilterScreenLoaded();
        filterClaimsWindow.uncheckOpen();
        filterClaimsWindow.checkInProgress();
        filterClaimsWindow.uncheckExecuted();
        filterClaimsWindow.uncheckCanceled();
        filterClaimsWindow.cLickOk();
        claimsPage.openClaim(0);
        claimPage.checkClaimScreenLoaded();
        assertEquals("In progress", claimPage.getStatus());
    }

    @Test
    @DisplayName("Выбрать фильтр по статусу Ececuted")
    public void testFilterExecuted() {
        claimsPage.claimsFilterButton.check(matches(isDisplayed()));
        claimsPage.claimsFilterButton.perform(click());
        filterClaimsWindow.checkFilterScreenLoaded();
        filterClaimsWindow.uncheckOpen();
        filterClaimsWindow.uncheckInProgress();
        filterClaimsWindow.checkExecuted();
        filterClaimsWindow.uncheckCanceled();
        filterClaimsWindow.cLickOk();
        claimsPage.openClaim(0);
        claimPage.checkClaimScreenLoaded();
        assertEquals("Executed", claimPage.getStatus());
    }

    @Test
    @DisplayName("Выбрать фильтр по статусу  Cancelled")
    public void testFilterCancelled() {
        claimsPage.claimsFilterButton.check(matches(isDisplayed()));
        claimsPage.claimsFilterButton.perform(click());
        filterClaimsWindow.checkFilterScreenLoaded();
        filterClaimsWindow.uncheckOpen();
        filterClaimsWindow.uncheckInProgress();
        filterClaimsWindow.uncheckExecuted();
        filterClaimsWindow.checkCanceled();
        filterClaimsWindow.cLickOk();
        claimsPage.openClaim(0);
        claimPage.checkClaimScreenLoaded();
        assertEquals("Canceled", claimPage.getStatus());
    }

    @Test
    @DisplayName("Переход к созданию новой заявки")
    public void testToAllNews() {
        claimsPage.createClaimButton.check(matches(isDisplayed()));
        claimsPage.createClaimButton.perform(click());
        createClaimPage.checkCreateClaimScreenLoaded();
    }

    @Test
    @DisplayName("Открыть заявку")
    public void testOpenCloseUnitClaim() {
        claimsPage.openClaim(0);
        claimPage.checkClaimScreenLoaded();
    }

    @Test
    @DisplayName("Взять в работу заявку Open - Take to work")
    public void testToWork() {
        claimsPage.claimsFilterButton.check(matches(isDisplayed()));
        claimsPage.claimsFilterButton.perform(click());
        filterClaimsWindow.checkFilterScreenLoaded();
        filterClaimsWindow.checkOpen();
        filterClaimsWindow.uncheckInProgress();
        filterClaimsWindow.uncheckExecuted();
        filterClaimsWindow.uncheckCanceled();
        filterClaimsWindow.cLickOk();
        claimsPage.openClaim(0);
        claimPage.statusChangeButton.perform(nestedScrollTo());
        claimPage.statusChangeButton.perform(click());
        claimPage.takeToWork.perform(click());
        claimPage.checkClaimStatusLoaded();
        assertEquals("In progress", claimPage.getStatus());
    }

    @Test
    @DisplayName("Отклонить заявку Open - Cancel")
    public void testToCancel() {
        claimsPage.claimsFilterButton.check(matches(isDisplayed()));
        claimsPage.claimsFilterButton.perform(click());
        filterClaimsWindow.checkFilterScreenLoaded();
        filterClaimsWindow.checkOpen();
        filterClaimsWindow.uncheckInProgress();
        filterClaimsWindow.uncheckExecuted();
        filterClaimsWindow.uncheckCanceled();
        filterClaimsWindow.cLickOk();
        claimsPage.openClaim(0);
        claimPage.statusChangeButton.perform(nestedScrollTo());
        claimPage.statusChangeButton.perform(click());
        claimPage.cancelClaim.perform(click());
        claimPage.checkClaimStatusLoaded();
        assertEquals("Canceled", claimPage.getStatus());
    }

    @Test
    @DisplayName("Выполнить заявку In Progress - To execute")
    public void testToExecute() {
        String comment = "Done";
        claimsPage.claimsFilterButton.check(matches(isDisplayed()));
        claimsPage.claimsFilterButton.perform(click());
        filterClaimsWindow.checkFilterScreenLoaded();
        filterClaimsWindow.uncheckOpen();
        filterClaimsWindow.checkInProgress();
        filterClaimsWindow.uncheckExecuted();
        filterClaimsWindow.uncheckCanceled();
        filterClaimsWindow.cLickOk();
        claimsPage.openClaim(0);
        claimPage.statusChangeButton.perform(nestedScrollTo());
        claimPage.statusChangeButton.perform(click());
        claimPage.toExecuteClaim.perform(click());
        claimPage.checkCommentFieldLoaded();
        claimPage.addCommentWhenStatusChange(comment);
        claimPage.checkClaimStatusLoaded();
        assertEquals("Executed", claimPage.getStatus());
    }

    @Test
    @DisplayName("Отказаться от заявки  (In Progress) - Throw off")
    public void testToThrowOff() {
        String comment = "Done";
        claimsPage.claimsFilterButton.check(matches(isDisplayed()));
        claimsPage.claimsFilterButton.perform(click());
        filterClaimsWindow.checkFilterScreenLoaded();
        filterClaimsWindow.uncheckOpen();
        filterClaimsWindow.checkInProgress();
        filterClaimsWindow.uncheckExecuted();
        filterClaimsWindow.uncheckCanceled();
        filterClaimsWindow.cLickOk();
        claimsPage.openClaim(0);
        claimPage.statusChangeButton.perform(nestedScrollTo());
        claimPage.statusChangeButton.perform(click());
        claimPage.throwOffClaim.perform(click());
        claimPage.checkCommentFieldLoaded();
        claimPage.addCommentWhenStatusChange(comment);
        claimPage.checkClaimStatusLoaded();
        assertEquals("Open", claimPage.getStatus());
    }
}