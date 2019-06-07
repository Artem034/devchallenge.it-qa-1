package desktop.frontEnd;

import com.codeborne.selenide.Selenide;
import desktop.BaseTest;
import io.qameta.allure.Link;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import it.devchallenge.pageObjects.desktop.CreatingHotelPage;
import it.devchallenge.pageObjects.desktop.HomePage;
import it.devchallenge.utils.junitTags.Desktop;
import it.devchallenge.utils.junitTags.Regresion;
import it.devchallenge.utils.junitTags.Sanity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static it.devchallenge.pageObjects.desktop.CreatingHotelPage.CREATING_HOTEL_PAGE_URL;
import static it.devchallenge.pageObjects.desktop.HomePage.HOME_PAGE_URL;
import static org.junit.jupiter.api.Assertions.assertAll;

@Desktop
@Link(name = "Link for requirement ", url = "https://drive.google.com/file/d/1mUkrgtGWUqkxosZRX8WIPLtjeYvlmoUA/view")
@DisplayName("User Stories")
class CreatingHotelFormTest extends BaseTest {

    private HomePage homePage = new HomePage();
    private CreatingHotelPage creatingHotelPage = new CreatingHotelPage();
    private CreatingHotelPage.Asserts creatingHotelPageAssert = creatingHotelPage.new Asserts();

    @Test
    @Sanity
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Verify that user can open New Hotel page")
    void verifyNewHotelPage() {
        homePage.goToUrl(HOME_PAGE_URL);
        homePage.headerFragment.openMenu("Article", "New", "Hotel");
        creatingHotelPageAssert.verifyBlockDataIsPresent();
        creatingHotelPageAssert.verifyButtonIsPresentByText("Save");
    }

    @ParameterizedTest
    @Regresion
    @ValueSource(strings = {"Name:", "Date of Construction:", "Country:", "City:", "Short Description:", "Description:"})
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Verify field with asterisks and NOT allow empty field")
    void verifyMandatoryInputFields(String field) {
        creatingHotelPage.goToUrl(CREATING_HOTEL_PAGE_URL);
        assertAll(
                () -> creatingHotelPageAssert.fieldMarkedWithAsterisk(field),
                () -> creatingHotelPage.clickButtonByText("Save"),
                () -> creatingHotelPageAssert.verifyEmptyFieldNotAllowed(field)
        );
    }

    @Test
    @Regresion
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Verify field allows to inputs alphanumeric characters and save Data")
    void saveRandomHotel() {
        creatingHotelPage.goToUrl(CREATING_HOTEL_PAGE_URL);
        creatingHotelPage.fillInputByNameRandomAlphanumericCharacters("Name:");
        creatingHotelPage.clickByRandomGlobalRating();
        creatingHotelPage.fillConstructionDate();
        creatingHotelPage.selectRandomCountry();
        creatingHotelPage.selectRandomCity();
        creatingHotelPage.fillInputByNameRandomAlphanumericCharacters("Short Description:");
        creatingHotelPage.fillTextAreaByNameRandomAlphanumericCharacters("Description:");
        creatingHotelPage.fillTextAreaByNameRandomAlphanumericCharacters("Notes:");
        creatingHotelPage.clickButtonByText("Save");
        Selenide.screenshot("aaa");
    }

    @Test
    @Regresion
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Verify Construction Date field NOT allows incorrect date")
    void fillIncorrectConstructionDate() {
        creatingHotelPage.goToUrl(CREATING_HOTEL_PAGE_URL);
        creatingHotelPage.fillConstructionDate("13/13/13");
        creatingHotelPage.clickButtonByText("Save");
        creatingHotelPage.verifyIncorrectCustomMessageIsShownForField("Date of Construction:", "could not be understood as a date. Example: 6/7/19");
    }
}
