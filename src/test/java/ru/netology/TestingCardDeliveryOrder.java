package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.Keys.HOME;
import static org.openqa.selenium.Keys.SHIFT;


class TestingCardDeliveryOrder {
    @BeforeEach
    void setup() {
        Selenide.open("http://localhost:9999");
    }

    public String generateDate(int days, String pattern) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    void positiveTestingOfCardOrderForm() {
        $("[data-test-id='city'] input").setValue("Владивосток");
        String planningDate = generateDate(11, "dd.MM.yyyy");
        $("[data-test-id='date'] input").press(Keys.chord(SHIFT, HOME), Keys.BACK_SPACE).setValue(planningDate);
        $$("[name='name']").findBy(Condition.visible).setValue("Федоров Иван");
        $$("[name='phone']").findBy(Condition.visible).setValue("+79204088265");
        $$("[data-test-id='agreement']").findBy(Condition.visible).click();
        $$("button").findBy(Condition.text("Забронировать")).click();
        $("[data-test-id='notification']").should(Condition.visible, Duration.ofSeconds(16)).should(Condition.text("Успешно!" + " Встреча успешно забронирована на " + planningDate));

    }

//    @Test
//    void shouldChooseCityAndDateFromDropdown() {
//        String deliveryDate = generateDate(8, "d");
//        String deliveryMonth = generateDate(8, "MM");
//        String deliveryFullDate = generateDate(8, "dd.MM.yyyy");
//
//        $("[data-test-id='city'] input").setValue("Мо");
//        $$(".menu-item").findBy(Condition.text("Москва")).click();
//
//        $("[data-test-id='date'] input").sendKeys(Keys.chord(SHIFT, HOME), Keys.BACK_SPACE);
//        $("span.input__box span button").click();
//        //если дата в этом месяце, то
//        if (deliveryMonth.equals(LocalDate.now().format(DateTimeFormatter.ofPattern("MM")))) {
//            $$(".calendar__day").findBy(Condition.text(deliveryDate)).click();
//        } else {
//            //если дата в следующем месяце, то
//            $(".calendar__arrow_direction_right[data-step='1'").click();
//            $$(".calendar__day").findBy(Condition.text(deliveryDate)).click();
//        }
//        $("[data-test-id='name'] input").setValue("Санта Клаус");
//        $("[data-test-id='phone'] input").setValue("+79208447566");
//        $("[data-test-id='agreement']").click();
//        $$("button").findBy(Condition.text("Забронировать")).click();
//        $("[data-test-id='notification']").should(Condition.visible,
//                Duration.ofSeconds(16)).should(Condition.text("Успешно!" +
//                "Встреча успешно забронирована на" + deliveryFullDate));
//    }

}
