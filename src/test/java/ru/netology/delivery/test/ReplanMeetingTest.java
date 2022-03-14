package ru.netology.delivery.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.delivery.data.DataGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ReplanMeetingTest {
    private DataGenerator.UserInfo validUser = DataGenerator.Registration.generateUser("ru");
    private String firstMeetingDate = DataGenerator.generateDate(4);
    private String secondMeetingDate = DataGenerator.generateDate(8);

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
        $("[data-test-id=date] .input__control").sendKeys(Keys.CONTROL + "A");
        $("[data-test-id=date] .input__control").sendKeys(Keys.BACK_SPACE);
    }

    @Test
    void shouldSuccessfulPlanAndReplanMeeting() {
        $("[data-test-id=city] .input__control").setValue(validUser.getCity());
        $("[data-test-id=date] .input__control").setValue(firstMeetingDate);
        $("[data-test-id=name] .input__control").setValue(validUser.getName());
        $("[data-test-id=phone] .input__control").setValue(validUser.getPhone());
        $("[data-test-id=agreement]").click();
        $(byText("Запланировать")).click();
        $("[data-test-id=success-notification] .notification__content")
                .shouldHave(text("Встреча успешно запланирована на " + firstMeetingDate));
        $("[data-test-id=date] .input__control").sendKeys(Keys.CONTROL + "A");
        $("[data-test-id=date] .input__control").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] .input__control").setValue(secondMeetingDate);
        $(byText("Запланировать")).click();
        $("[data-test-id=replan-notification] .notification__title")
                .shouldHave(text("Необходимо подтверждение"), Duration.ofSeconds(15));
        $("[data-test-id=replan-notification] .notification__content")
                .shouldHave(text("У вас уже запланирована встреча на другую дату. Перепланировать?"));
        $(byText("Перепланировать")).click();
        $("[data-test-id=success-notification] .notification__content")
                .shouldHave(text("Встреча успешно запланирована на " + secondMeetingDate));
    }

    @Test
    void shouldPlanAndReplanMeetingWithDifferentByName() {
        $("[data-test-id=city] .input__control").setValue(validUser.getCity());
        $("[data-test-id=date] .input__control").setValue(firstMeetingDate);
        $("[data-test-id=name] .input__control").setValue(validUser.getName());
        $("[data-test-id=phone] .input__control").setValue(validUser.getPhone());
        $("[data-test-id=agreement]").click();
        $(byText("Запланировать")).click();
        $("[data-test-id=success-notification] .notification__content")
                .shouldHave(text("Встреча успешно запланирована на " + firstMeetingDate));
        $("[data-test-id=date] .input__control").sendKeys(Keys.CONTROL + "A");
        $("[data-test-id=date] .input__control").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=name] .input__control").sendKeys(Keys.CONTROL + "A");
        $("[data-test-id=name] .input__control").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=name] .input__control").setValue(DataGenerator.generateName("ru"));
        $("[data-test-id=date] .input__control").setValue(secondMeetingDate);
        $(byText("Запланировать")).click();
        $("[data-test-id=success-notification] .notification__content")
                .shouldHave(text("Встреча успешно запланирована на " + secondMeetingDate));
    }

    @Test
    void shouldPlanAndReplanMeetingWithDifferentByCity() {
        $("[data-test-id=city] .input__control").setValue(DataGenerator.generateCity());
        $("[data-test-id=date] .input__control").setValue(firstMeetingDate);
        $("[data-test-id=name] .input__control").setValue(validUser.getName());
        $("[data-test-id=phone] .input__control").setValue(validUser.getPhone());
        $("[data-test-id=agreement]").click();
        $(byText("Запланировать")).click();
        $("[data-test-id=success-notification] .notification__content")
                .shouldHave(text("Встреча успешно запланирована на " + firstMeetingDate));
        $("[data-test-id=date] .input__control").sendKeys(Keys.CONTROL + "A");
        $("[data-test-id=date] .input__control").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=city] .input__control").sendKeys(Keys.CONTROL + "A");
        $("[data-test-id=city] .input__control").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=city] .input__control").setValue(DataGenerator.generateDoubleCity());
        $("[data-test-id=date] .input__control").setValue(secondMeetingDate);
        $(byText("Запланировать")).click();
        $("[data-test-id=success-notification] .notification__content")
                .shouldHave(text("Встреча успешно запланирована на " + secondMeetingDate));
    }

    @Test
    void shouldPlanAndReplanMeetingWithDifferentByPhone() {
        $("[data-test-id=city] .input__control").setValue(validUser.getCity());
        $("[data-test-id=date] .input__control").setValue(firstMeetingDate);
        $("[data-test-id=name] .input__control").setValue(validUser.getName());
        $("[data-test-id=phone] .input__control").setValue(validUser.getPhone());
        $("[data-test-id=agreement]").click();
        $(byText("Запланировать")).click();
        $("[data-test-id=success-notification] .notification__content")
                .shouldHave(text("Встреча успешно запланирована на " + firstMeetingDate));
        $("[data-test-id=date] .input__control").sendKeys(Keys.CONTROL + "A");
        $("[data-test-id=date] .input__control").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=phone] .input__control").sendKeys(Keys.CONTROL + "A");
        $("[data-test-id=phone] .input__control").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=phone] .input__control").setValue(DataGenerator.generatePhone("ru"));
        $("[data-test-id=date] .input__control").setValue(secondMeetingDate);
        $(byText("Запланировать")).click();
        $("[data-test-id=success-notification] .notification__title")
                .shouldHave(text("Успешно!"), Duration.ofSeconds(15));
        $("[data-test-id=success-notification] .notification__content")
                .shouldHave(text("Встреча успешно запланирована на " + secondMeetingDate));
    }

}
