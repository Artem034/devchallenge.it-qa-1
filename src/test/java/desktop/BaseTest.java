package desktop;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit5.TextReportExtension;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import it.devchallenge.utils.ScreenshotsOnFailExtension;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static it.devchallenge.utils.BrowserLogs.checkLogs;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Execution(ExecutionMode.CONCURRENT)
@ExtendWith({TextReportExtension.class})
@ExtendWith({ScreenshotsOnFailExtension.class})
public class BaseTest {

    @BeforeAll
    public void setUP() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        Configuration.browserSize = "1920x1080";
        Configuration.screenshots = true;
    }

    @AfterEach
    void afterEachSetup() {
        checkLogs();
    }

    @AfterAll
    void tearDown() {
        SelenideLogger.removeListener("allure");
    }
}
