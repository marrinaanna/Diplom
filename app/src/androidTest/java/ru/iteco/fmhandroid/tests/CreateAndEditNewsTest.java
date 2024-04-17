package ru.iteco.fmhandroid.tests;

import static androidx.test.espresso.action.ViewActions.click;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;

import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.data.dataHelper;
import ru.iteco.fmhandroid.data.screenLoad;
import ru.iteco.fmhandroid.data.someData;
import ru.iteco.fmhandroid.page.Controlpanel;
import ru.iteco.fmhandroid.page.CreatAndEditNews;
import ru.iteco.fmhandroid.page.News;
import ru.iteco.fmhandroid.ui.AppActivity;
@LargeTest
@RunWith(AndroidJUnit4.class)

public class CreateAndEditNewsTest {
    News newsPage = new News();
    Controlpanel controlPanelPage = new Controlpanel();
    CreatAndEditNews createEditNewsPage = new CreatAndEditNews();
    someData dat = new someData();
    screenLoad readyScreen = new screenLoad();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void readyScreen() {
        readyScreen.readyNewsScreen();
    }

    String category = dat.category;
    String title = dat.title;
    String description = dat.description;
    String date = dat.date;
    String time = dat.time;
    String category2 = dat.category2;
    String title2 = dat.title2;
    String description2 = dat.description2;
    String date2 = dat.date2;
    String time2 = dat.time2;
    String emptyField = dat.emptyField;

    @Test
    @DisplayName("Control panel. Создание новости")
    public void testCreateNews() {
        controlPanelPage.addNewsButton.perform(click());
        controlPanelPage.createNewsButton.perform(click());
        createEditNewsPage.checkCreateNewsScreenLoaded();
        createEditNewsPage.fillInFormNews(category, title, date, time, description);
        createEditNewsPage.saveNews();
        controlPanelPage.checkControlPanelScreenLoaded();
        createEditNewsPage.getEditNewsButtonByTitle(title).perform(click());
        createEditNewsPage.checkEditNewsScreenLoaded();
        createEditNewsPage.checkNewsExists(category, title, date, time, description);
        createEditNewsPage.saveNews();
        controlPanelPage.checkControlPanelScreenLoaded();
        controlPanelPage.deleteNews();
    }

    @Test
    @DisplayName("Control panel: Создание новой объявления. Поля не заполнены")
    public void testCreateNewsEmptyField() {
        controlPanelPage.addNewsButton.perform(click());
        controlPanelPage.createNewsButton.perform(click());
        createEditNewsPage.checkCreateNewsScreenLoaded();
        createEditNewsPage.fillInFormNews(emptyField, emptyField, emptyField, emptyField, emptyField);
        createEditNewsPage.saveNews();
        dataHelper.checkMessage(R.string.empty_fields, true);
    }

    @Test
    @DisplayName("Control panel: Отредактировать новость")
    public void testEditTitleNews() {
        controlPanelPage.addNewsButton.perform(click());
        controlPanelPage.createNewsButton.perform(click());
        createEditNewsPage.checkCreateNewsScreenLoaded();
        createEditNewsPage.fillInFormNews(category, title, date, time, description);
        createEditNewsPage.saveNews();
        controlPanelPage.editNewsButton.perform(click());
        createEditNewsPage.checkEditNewsScreenLoaded();
        createEditNewsPage.fillInFormNews(category2, title2, date2, time2, description2);
        createEditNewsPage.saveNews();
        controlPanelPage.checkControlPanelScreenLoaded();
        controlPanelPage.editNewsButtonAfterEdit.perform(click());
        createEditNewsPage.checkEditNewsScreenLoaded();
        createEditNewsPage.checkNewsExists(category2, title2, date2, time2, description2);
        createEditNewsPage.saveNews();
        controlPanelPage.checkControlPanelScreenLoaded();
        controlPanelPage.deleteNews();
    }

    @Test
    @DisplayName("Control panel: Отредактировать новость и отменить редактирование")
    public void testCancelEditNews() {
        controlPanelPage.addNewsButton.perform(click());
        controlPanelPage.createNewsButton.perform(click());
        createEditNewsPage.checkCreateNewsScreenLoaded();
        createEditNewsPage.fillInFormNews(category, title, date, time, description);
        createEditNewsPage.saveNews();
        controlPanelPage.editNewsButton.perform(click());
        createEditNewsPage.checkEditNewsScreenLoaded();
        createEditNewsPage.fillInFormNews(category2, title2, date2, time2, description2);
        createEditNewsPage.cancelEdit();
        controlPanelPage.checkControlPanelScreenLoaded();
        controlPanelPage.editNewsButtonAfterEdit.perform(click());
        createEditNewsPage.checkEditNewsScreenLoaded();
        createEditNewsPage.checkNewsExists(category, title, date, time, description);
        createEditNewsPage.saveNews();
        controlPanelPage.checkControlPanelScreenLoaded();
        controlPanelPage.deleteNews();
    }

    @Test
    @DisplayName("Control panel: Отредактировать объявление. Изменить статус.")
    public void testChangeStatusNews() {
        controlPanelPage.addNewsButton.perform(click());
        controlPanelPage.createNewsButton.perform(click());
        createEditNewsPage.checkCreateNewsScreenLoaded();
        createEditNewsPage.fillInFormNews(category, title, date, time, description);
        createEditNewsPage.saveNews();
        String statusBefore = dataHelper.Text.getText(controlPanelPage.newsStatus);
        controlPanelPage.editNewsButton.perform(click());
        createEditNewsPage.checkEditNewsScreenLoaded();
        createEditNewsPage.switcher.perform(click());
        createEditNewsPage.saveNews();
        controlPanelPage.checkControlPanelScreenLoaded();
        String statusAfter = dataHelper.Text.getText(controlPanelPage.newsStatusAfterChange);
        assertNotEquals(statusBefore, statusAfter);
        controlPanelPage.deleteNews();
    }
}