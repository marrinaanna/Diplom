package ru.iteco.fmhandroid.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.swipeDown;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;

import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;

import ru.iteco.fmhandroid.data.dataHelper;
import ru.iteco.fmhandroid.data.screenLoad;
import ru.iteco.fmhandroid.data.someData;
import ru.iteco.fmhandroid.page.Controlpanel;
import ru.iteco.fmhandroid.page.CreatAndEditNews;
import ru.iteco.fmhandroid.page.Filters;
import ru.iteco.fmhandroid.page.News;
import ru.iteco.fmhandroid.ui.AppActivity;
@LargeTest
@RunWith(AndroidJUnit4.class)

public class NewsUnitTest {
    News newsPage = new News();
    static Controlpanel controlPanelPage = new Controlpanel();
    Filters filterNewsPage = new Filters();
    static someData dat = new someData();
    static screenLoad readyScreen = new screenLoad();
    static CreatAndEditNews createEditNewsPage = new CreatAndEditNews();
    static String category = dat.category;
    static String title = dat.title;
    static String description = dat.description;
    static String date = dat.date;
    static String time = dat.time;
    static String category2 = dat.category2;
    static String title2 = dat.title2;
    static String description2 = dat.description2;
    static String date2 = dat.date2;
    static String time2 = dat.time2;

    @ClassRule
    public static ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule2 =
            new ActivityScenarioRule<>(AppActivity.class);
    @BeforeClass
    public static void readyScreen() {
        readyScreen.readyNewsScreen();
        controlPanelPage.addNewsButton.perform(click());
        controlPanelPage.createNewsButton.perform(click());
        createEditNewsPage.checkCreateNewsScreenLoaded();
        createEditNewsPage.fillInFormNews(category, title, date, time, description);
        createEditNewsPage.saveNews();
        controlPanelPage.createNewsButton.perform(click());
        createEditNewsPage.checkCreateNewsScreenLoaded();
        createEditNewsPage.fillInFormNews(category2, title2, date2, time2, description2);
        createEditNewsPage.saveNews();
        controlPanelPage.editNewsButton.perform(click());
        createEditNewsPage.checkEditNewsScreenLoaded();
        createEditNewsPage.switcher.perform(click());
        createEditNewsPage.saveNews();
    }
    @Before
    public void readyScreen2() {
        readyScreen.readyNewsScreen();
    }

    String startDate = dat.startDate;
    String endDate = dat.endDate;

    @Test
    @DisplayName("News: Фильтр по дате")
    public void testSortNews() {

        controlPanelPage.newsFilterButton.perform(click());
        filterNewsPage.checkFilterNewsScreenLoaded();
        filterNewsPage.startDateField.perform(replaceText(startDate));
        filterNewsPage.endDateField.perform(replaceText(endDate));
        filterNewsPage.filterButton.perform(click());
        String firstNewsTitle = dataHelper.Text.getText(newsPage.titleFirstNews);
        controlPanelPage.sortButton.perform(click());
        controlPanelPage.listOfNews.perform(swipeUp());
        controlPanelPage.checkListNewsLoaded();
        controlPanelPage.checkTitleDescription(firstNewsTitle);
    }

    @Test
    @DisplayName("News: Переход  на экран редактирования новостей Control Panel")
    public void testGoToEditNews() {
        newsPage.editButton.perform(click());
        controlPanelPage.checkControlPanelScreenLoaded();
    }

    @Test
    @DisplayName("News: Развернуть описание")
    public void testOpenOneNews() {
        controlPanelPage.chooseFirstNews();
        controlPanelPage.newsDescription.check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Control Panel: сортировка")
    public void testCpSortNews() {
        newsPage.goToControlPanel();
        controlPanelPage.newsFilterButton.perform(click());
        filterNewsPage.checkFilterNewsScreenLoaded();
        filterNewsPage.startDateField.perform(replaceText(startDate));
        filterNewsPage.endDateField.perform(replaceText(endDate));
        filterNewsPage.filterButton.perform(click());
        controlPanelPage.checkListNewsLoaded();
        String firstNewsTitle = dataHelper.Text.getText(controlPanelPage.newsItemTitle);
        String firstNewsDate = dataHelper.Text.getText(controlPanelPage.newsDataPublished);
        controlPanelPage.sortButton.perform(click());
        controlPanelPage.listOfNews.perform(swipeUp());
        controlPanelPage.checkListNewsLoaded();
        controlPanelPage.checkDescriptionAndDate(firstNewsTitle, firstNewsDate);
    }

    @Test
    @DisplayName("Control Panel: фильтр Active")
    public void testCpFilterActive() {
        newsPage.goToControlPanel();
        controlPanelPage.newsFilterButton.perform(click());
        filterNewsPage.checkFilterNewsScreenLoaded();
        filterNewsPage.checkBoxNotActive.perform(click());
        filterNewsPage.checkBoxNotActive.check(matches(isNotChecked()));
        filterNewsPage.filterButton.perform(click());
        controlPanelPage.checkListNewsLoaded();
        String firstNewsStatus = dataHelper.Text.getText(controlPanelPage.newsStatus);
        assertEquals(firstNewsStatus, "Active");
    }

    @Test
    @DisplayName("Control Panel: фильтр Not Active")
    public void testCpFilterNotActive() {
        newsPage.goToControlPanel();
        controlPanelPage.newsFilterButton.perform(click());
        filterNewsPage.checkFilterNewsScreenLoaded();
        filterNewsPage.checkBoxActive.perform(click());
        filterNewsPage.checkBoxActive.check(matches(isNotChecked()));
        filterNewsPage.filterButton.perform(click());
        controlPanelPage.checkListNewsLoaded();
        String firstNewsStatus = dataHelper.Text.getText(controlPanelPage.newsStatus);
        assertEquals(firstNewsStatus, "Not active");
    }

    @Test
    @Ignore
    @DisplayName("Control Panel: Фильтры Not Active и Active сняты")
    //фильтр не работает
    public void testCpFiltersEmpty() {
        newsPage.goToControlPanel();
        controlPanelPage.newsFilterButton.perform(click());
        filterNewsPage.checkFilterNewsScreenLoaded();
        filterNewsPage.checkBoxActive.perform(click());
        filterNewsPage.checkBoxActive.check(matches(isNotChecked()));
        filterNewsPage.checkBoxNotActive.perform(click());
        filterNewsPage.checkBoxNotActive.check(matches(isNotChecked()));
        filterNewsPage.filterButton.perform(click());
        controlPanelPage.checkListNewsLoaded();
        controlPanelPage.butterflyImageNews.check(matches(isDisplayed()));
        onView(withText("There is nothing here yet…"));
        onView(withText("Refresh")).check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Control Panel: Развернуть описание")
    public void testCpOpenOneNews() {
        newsPage.goToControlPanel();
        controlPanelPage.chooseFirstNews();
        controlPanelPage.newsDescription.check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Control Panel: Удаление новости")
    public void testCpDeleteNews() {
        newsPage.goToControlPanel();
        String title = dataHelper.Text.getText(controlPanelPage.newsItemTitle);
        controlPanelPage.deleteNewsButton.perform(click());
        controlPanelPage.cancelButton.check(matches(isDisplayed()));
        controlPanelPage.okButton.check(matches(isDisplayed()));
        controlPanelPage.okButton.perform(click());
        controlPanelPage.checkListNewsLoaded();
        String title2 = dataHelper.Text.getText(controlPanelPage.newsItemTitleAfterDelete);
        assertNotEquals(title, title2);
    }
}