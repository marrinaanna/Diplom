package ru.iteco.fmhandroid.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;
import static ru.iteco.fmhandroid.data.dataHelper.elementWaiting;
import static ru.iteco.fmhandroid.data.dataHelper.withIndex;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class Controlpanel{

    public ViewInteraction controlPanelScreenName = onView(withText("Control panel"));
    public ViewInteraction menuButton = onView(withId(R.id.main_menu_image_button));
    public ViewInteraction menuNews = onView(withText("News"));
    public ViewInteraction sortButton = onView(withId(R.id.sort_news_material_button));
    public ViewInteraction newsFilterButton = onView(withId(R.id.filter_news_material_button));
    public ViewInteraction addNewsButton = onView(withId(R.id.edit_news_material_button));
    public ViewInteraction createNewsButton = onView( withId(R.id.add_news_image_view));
    public ViewInteraction listOfNews = onView(withId(R.id.news_list_recycler_view));
    public ViewInteraction butterflyImageNews = onView(withId(R.id.empty_news_list_image_view));

    public ViewInteraction deleteNewsButton = onView(withIndex(withId(R.id.delete_news_item_image_view), 0));
    public ViewInteraction editNewsButton = onView(withIndex(withId(R.id.edit_news_item_image_view), 0));
    public ViewInteraction editNewsButtonAfterEdit = onView(withIndex(withId(R.id.edit_news_item_image_view), 0));
    public ViewInteraction openDescriptionNewsButton = onView(withIndex(withId(R.id.view_news_item_image_view), 0));
    public ViewInteraction newsStatus = onView(withIndex(withId(R.id.news_item_published_text_view), 0));
    public ViewInteraction newsStatusAfterChange = onView(withIndex(withId(R.id.news_item_published_text_view), 0));
    public ViewInteraction newsStatusIcon = onView(withIndex(withId(R.id.news_item_published_icon_image_view), 0));
    public ViewInteraction newsItemTitle = onView(withIndex(withId(R.id.news_item_title_text_view), 0));
    public ViewInteraction newsItemTitleAfterDelete = onView(withIndex(withId(R.id.news_item_title_text_view), 0));
    public ViewInteraction newsDescription = onView(withIndex(withId(R.id.news_item_description_text_view), 0));
    public ViewInteraction newsDataPublished = onView(withIndex(withId(R.id.news_item_publication_date_text_view), 0));
    public ViewInteraction newsDataPCreated = onView(withIndex(withId(R.id.news_item_create_date_text_view), 0));
    public ViewInteraction newsCard = onView(withIndex(withId(R.id.news_item_material_card_view), 0));

    public ViewInteraction cancelButton = onView(withText("CANCEL"));
    public ViewInteraction okButton = onView(withText("OK"));

    public void checkControlPanelScreenLoaded() {
        Allure.step("Проверка загрузки страницы Control panel");
        elementWaiting(withText("Control panel"), 10000);
        controlPanelScreenName.check(matches(isDisplayed()));
        sortButton.check(matches(isDisplayed()));
        newsFilterButton.check(matches(isDisplayed()));
        createNewsButton.check(matches(isDisplayed()));
        listOfNews.check(matches(isDisplayed()));
    }

    public void checkListNewsLoaded() {
        Allure.step("Проверка загрузки списка новостей");
        elementWaiting(withId(R.id.news_list_recycler_view), 10000);
    }

    public void checkItemScreenLoaded() {
        Allure.step("Проверка загрузки страницы новости");
        newsItemTitle.check(matches(isDisplayed()));
        deleteNewsButton.check(matches(isDisplayed()));
        editNewsButton.check(matches(isDisplayed()));
        openDescriptionNewsButton.check(matches(isDisplayed()));
        newsStatus.check(matches(isDisplayed()));
        newsStatusIcon.check(matches(isDisplayed()));
        newsDescription.check(matches(isDisplayed()));
        newsDataPublished.check(matches(isDisplayed()));
        newsDataPCreated.check(matches(isDisplayed()));
    }

    public void deleteNews() {
        Allure.step("Удалить новость");
        deleteNewsButton.perform(click());
        okButton.perform(click());
    }

    public void checkDescriptionAndDate(String title, String date) {
        Allure.step("Сравнение описания и даты");
        onView(allOf(withId(R.id.news_item_title_text_view), withText(title))).
                check(matches(isDisplayed()));
    }
    public void checkTitleDescription(String title) {
        Allure.step("Сравнение заголовка");
        onView(allOf(withId(R.id.news_item_title_text_view), withText(title))).
                check(matches(isDisplayed()));
    }

    public void checkNewsNotDisplayed(String title) {
        Allure.step("Проверка что новость не отображается");
        onView(allOf(withText(title))).check(matches(not(isDisplayed())));
    }

    public void chooseFirstNews() {
        Allure.step("Выбрать первую новость");
        newsCard.perform(click());
    }
    public void goToNewsPage() {
        Allure.step("Переход в раздел новостей");
        menuButton.check(matches(isDisplayed()));
        menuButton.perform(click());
        menuNews.check(matches(isDisplayed()));
        menuNews.perform(click());
    }

}