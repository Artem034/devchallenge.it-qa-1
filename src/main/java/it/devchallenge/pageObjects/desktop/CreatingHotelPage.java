package it.devchallenge.pageObjects.desktop;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import it.devchallenge.pageObjects.BasePage;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


public class CreatingHotelPage extends BasePage {

    public static final String CREATING_HOTEL_PAGE_URL = "http://localhost:8080/article/faces/hotel.xhtml";

    @Step("Field {fieldName} allows to input alphanumeric characters")
    public void fillInputByNameRandomAlphanumericCharacters(String fieldName) {
        int randomInt = ThreadLocalRandom.current().nextInt(1, 99);
        String generatedString = RandomStringUtils.random(randomInt, true, true);
        $x(".//label[@class='ui-outputlabel ui-widget' and text() = '" + fieldName + "']/../..//input").setValue(generatedString);
    }

    @Step("Click by random Global Rating")
    public void clickByRandomGlobalRating() {
        Random rand = new Random();
        List ratings = $$x(".//div[@id='add_hotel:rate']/div");
        SelenideElement mark = (SelenideElement) ratings.get(rand.nextInt(ratings.size()));
        mark.click();
    }

    @Step("Fill construction date: Today")
    public void fillConstructionDate() {
        $(By.id("add_hotel:dateOfConstruction_input")).setValue((LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"))));
        Selenide.screenshot("azazazaz");
    }

    @Step("Fill construction date: {customDate}")
    public void fillConstructionDate(String customDate) {
        $(By.id("add_hotel:dateOfConstruction_input")).setValue(customDate);
    }

    @Step("Verify incorrect date message is shown")
    public void verifyIncorrectCustomMessageIsShownForField(String fieldName, String customMessage) {
        $x(".//label[@class='ui-outputlabel ui-widget ui-state-error' and text() = '" + fieldName + "']/../../td/div/span[2]").should(Condition.matchesText(customMessage));
    }

    @Step("Select random country")
    public void selectRandomCountry() {
        $(By.id("add_hotel:country_label")).click();
        int randomRange = $$x(".//div[@id='add_hotel:country_panel']/div/ul/li").excludeWith(selected).size();
        int randomInt = ThreadLocalRandom.current().nextInt(1, randomRange);
        $$x(".//div[@id='add_hotel:country_panel']/div/ul/li").get(randomInt).click();
    }

    @Step("Select random city")
    public void selectRandomCity() {
        $(By.id("add_hotel:city_label")).click();
        int randomRange = $$x(".//div[@id='add_hotel:city_panel']/div/ul/li").excludeWith(selected).size();
        int randomInt = ThreadLocalRandom.current().nextInt(1, randomRange);
        System.out.println(randomInt);
        $$x(".//div[@id='add_hotel:city_panel']/div/ul/li").get(randomInt).click();
    }

    @Step("Field {fieldName} allows to input alphanumeric characters and save data")
    public void fillTextAreaByNameRandomAlphanumericCharacters(String fieldName) {
        int randomInt = ThreadLocalRandom.current().nextInt(1, 99);
        String generatedString = RandomStringUtils.random(randomInt, true, true);
        $x(".//label[@class='ui-outputlabel ui-widget' and text() = '" + fieldName + "']/../..//textarea").setValue(generatedString);
    }

    public class Asserts extends BasePage.Asserts {

        @Step("Verify that Data section is displayed")
        public void verifyBlockDataIsPresent() {
            $(By.id("add_hotel")).shouldBe(visible);
        }

        @Step("Field {fieldName} marked with asterisks")
        public void fieldMarkedWithAsterisk(String fieldName) {
            $x(".//label[@class='ui-outputlabel ui-widget' and text() = '" + fieldName + "']/span").shouldHave(text("*"));
        }

        @Step("Verify that it is not possible to save the empty \"{fieldName}\" field and a meaningful error message is displayed")
        public void verifyEmptyFieldNotAllowed(String fieldName) {
            $x(".//label[@class='ui-outputlabel ui-widget ui-state-error' and text() = '" + fieldName + "']/../../td/div/span[2]").shouldHave(text(fieldName + " Validation Error: Value is required."));
        }


    }
}
