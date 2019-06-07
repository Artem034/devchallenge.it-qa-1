package it.devchallenge.utils;

import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.ex.ErrorMessages;
import com.codeborne.selenide.ex.UIAssertionError;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import com.google.common.io.Files;
import io.qameta.allure.Attachment;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

public class ScreenshotsOnFailExtension extends ScreenShooterExtension {
    private static final Logger log = Logger.getLogger(ScreenshotsOnFailExtension.class.getName());
    private final boolean captureSuccessfulTests;

    public ScreenshotsOnFailExtension() {
        this(false);
    }

    public ScreenshotsOnFailExtension(boolean captureSuccessfulTests) {
        this.captureSuccessfulTests = captureSuccessfulTests;
    }

    @Override
    public void afterEach(ExtensionContext context) {
        if (this.captureSuccessfulTests) {
            log.info(ErrorMessages.screenshot(WebDriverRunner.driver()));
        } else {
            Optional<Throwable> executionException = context.getExecutionException();
            if (executionException.isPresent() && !(executionException.get() instanceof UIAssertionError)) {
                log.info(ErrorMessages.screenshot(WebDriverRunner.driver()));
            }
            else {
                pageScreenshotOnAssert();
            }
        }

    }

    @Attachment(type = "image/png")
    private byte[] pageScreenshotOnAssert() {
        File screenshot = Screenshots.takeScreenShotAsFile();
        try {
            return Files.toByteArray(screenshot);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
