package ru.iteco.fmhandroid.page;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.assertEquals;
import static ru.iteco.fmhandroid.data.dataHelper.elementWaiting;
import static ru.iteco.fmhandroid.data.dataHelper.withIndex;

import android.os.SystemClock;

import androidx.test.espresso.ViewInteraction;
import  ru.iteco.fmhandroid.data.dataHelper;
import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;


public class Claim{

    public ViewInteraction titleLabel = onView(withId(R.id.title_label_text_view));
    public ViewInteraction title = onView(withId(R.id.title_text_view));
    public ViewInteraction executorLabel = onView(withId(R.id.executor_name_label_text_view));
    public ViewInteraction executorName = onView(withId(R.id.executor_name_text_view));
    public ViewInteraction dateLabel = onView(withId(R.id.plane_date_label_text_view));
    public ViewInteraction claimDate = onView(withId(R.id.plane_date_text_view));
    public ViewInteraction claimTime = onView(withId(R.id.plan_time_text_view));
    public ViewInteraction status = onView(withId(R.id.status_label_text_view));
    public ViewInteraction statusImg = onView(withId(R.id.status_icon_image_view));
    public ViewInteraction description = onView(withId(R.id.description_material_card_view));
    public ViewInteraction descriptionText = onView(withId(R.id.description_text_view));
    public ViewInteraction authorLabel = onView(withId(R.id.author_label_text_view));
    public ViewInteraction authorName = onView(withId(R.id.author_name_text_view));
    public ViewInteraction createdLabel = onView(withId(R.id.create_data_label_text_view));
    public ViewInteraction dateCreated = onView(withId(R.id.create_data_text_view));
    public ViewInteraction timeCreated = onView(withId(R.id.create_time_text_view));
    public ViewInteraction editClaimButton = onView(withId(R.id.edit_processing_image_button));
    public ViewInteraction statusChangeButton = onView(withId(R.id.status_processing_image_button));
    public ViewInteraction exitClaimButton = onView(withId(R.id.close_image_button));
    public ViewInteraction commentsList = onView(withId(R.id.claim_comments_list_recycler_view));
    public ViewInteraction addCommentButton = onView(withId(R.id.add_comment_image_button));
    public ViewInteraction editCommentButton = onView(withId(R.id.edit_comment_image_button));
    public ViewInteraction takeToWork = onView(withText("take to work"));
    public ViewInteraction cancelClaim = onView(withText("Cancel"));
    public ViewInteraction throwOffClaim = onView(withText("Throw off"));
    public ViewInteraction toExecuteClaim = onView(withText("To execute"));
    public ViewInteraction editTextComment = onView(withId(R.id.editText));
    public ViewInteraction editTextCommentOkButton = onView(withText("OK"));
    public ViewInteraction editCommentSaveButton = onView(withId(R.id.save_button));
    public ViewInteraction editCommentField = onView(allOf(withHint("Comment"),
            withParent(withParent(withId(R.id.comment_text_input_layout)))));
    public ViewInteraction textComment = onView(withText(R.id.comment_description_text_view));

    public void checkClaimScreenLoaded() {
        Allure.step("Проверка загрузки экрана отдельной заявки");
        elementWaiting(withText("Title"), 5000);
        titleLabel.check(matches(isDisplayed()));
        title.check(matches(isDisplayed()));
        executorLabel.check(matches(isDisplayed()));
        executorName.check(matches(isDisplayed()));
        dateLabel.check(matches(isDisplayed()));
        claimDate.check(matches(isDisplayed()));
        claimTime.check(matches(isDisplayed()));
        status.check(matches(isDisplayed()));
        descriptionText.check(matches(isDisplayed()));
        authorLabel.check(matches(isDisplayed()));
        authorName.check(matches(isDisplayed()));
        createdLabel.check(matches(isDisplayed()));
        dateCreated.check(matches(isDisplayed()));
        timeCreated.check(matches(isDisplayed()));
    }

    public String getStatus() {
        return dataHelper.Text.getText(status);
    }

    public String getClaimTitle() {
        return dataHelper.Text.getText(title);
    }

    public String getClaimDescription() {
        return dataHelper.Text.getText(descriptionText);
    }

    public String getClaimDate() {
        return dataHelper.Text.getText(claimDate);
    }

    public String getClaimTime() {
        return dataHelper.Text.getText(claimTime);
    }

    public void openComment(int index) {
        onView(withIndex(withId(R.id.edit_comment_image_button), index)).perform(click());
    }

    public void addCommentWhenStatusChange(String comment) {
        Allure.step("Добавление комментария при смене статуса");
        editTextComment.perform(replaceText(comment));
        editTextCommentOkButton.perform(click());
    }

    public void addComment(String comment) {
        Allure.step("Добавление нового комментария");
        editCommentField.perform(click()).perform(replaceText(comment));
        editCommentSaveButton.perform(click());
    }

    public void checkClaimStatusLoaded() {
        Allure.step("Проверка отображения статуса заявки");
        SystemClock.sleep(2000);
        status.check(matches(isDisplayed()));
    }

    public void checkCommentFieldLoaded() {
        Allure.step("Проверка отображения поля комментария");
        elementWaiting(withId(R.id.editText), 5000);
    }

    public void checkCommentLoaded() {
        Allure.step("Проверка отображения поля комментария при смене статуса");
        elementWaiting(withId(R.id.comment_text_input_layout), 5000);
    }

    public void checkCreatedClaimElement(String title, String date, String time, String description) {
        Allure.step("Проверка полей заявки");
        assertEquals(title, getClaimTitle());
        assertEquals(date, getClaimDate());
        assertEquals(time, getClaimTime());
        assertEquals(description, getClaimDescription());
    }

    public void checkComment(String comment) {
        Allure.step("Проверка текста в поле комментария");
        onView(allOf(withId(R.id.comment_description_text_view), withText(comment))).
                check(matches(isDisplayed()));
    }
}
