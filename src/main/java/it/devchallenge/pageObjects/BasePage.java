package it.devchallenge.pageObjects;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public abstract class BasePage {


    @Step("Open url {url}")
    public void goToUrl(String url) {
        open(url);
    }

    @Step("Click button {button}")
    public void clickButtonByText(String button) {
        $x(".//span[@class='ui-button-text ui-c' and text() = '" + button + "']").click();
    }

    public class Asserts {

        @Step("Verify that {button} button is displayed")
        public void verifyButtonIsPresentByText(String button) {
            $x(".//span[@class='ui-button-text ui-c' and text() = '" + button + "']").shouldBe(visible);
        }
    }
}
