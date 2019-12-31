package org.entando.selenium.smoketests;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUntil {

    public static WebElement isVisible(WebDriver driver, By by) {
        new WebDriverWait(driver, 20, 100).until(webDriver -> {
            try {
                return driver.findElement(by).isDisplayed();
            } catch (NoSuchElementException e) {
                return false;
            } catch (StaleElementReferenceException e) {
                System.out.println(e);
                return false;
            }
        });
        return driver.findElement(by);
    }

    public static WebElement isVisible(WebDriver driver, WebElement element) {
        new WebDriverWait(driver, 20, 100).until(webDriver -> {
            try {
                return element.isDisplayed();
            } catch (StaleElementReferenceException e) {
                System.out.println(e);
                return false;
            }
        });
        return element;
    }

    public static void urlContaining(WebDriver driver, String path) {
        new FluentWait(driver) {
            @Override
            protected RuntimeException timeoutException(String message, Throwable lastException) {
                ScreenPrintSaver.save(driver);
                return super.timeoutException(message, lastException);
            }
        }.pollingEvery(Duration.ofMillis(100))
                .withTimeout(Duration.ofSeconds(30))
                .until(webDriver -> driver.getCurrentUrl().contains(path));
    }
}

