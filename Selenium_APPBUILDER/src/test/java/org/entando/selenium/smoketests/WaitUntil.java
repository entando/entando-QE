package org.entando.selenium.smoketests;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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

    public static void urlEndingWith(WebDriver driver, String path) {
        new FluentWait(driver)
                .pollingEvery(Duration.ofMillis(100))
                .withTimeout(Duration.ofSeconds(90))
                .until(
                        webDriver -> {
                            return driver.getCurrentUrl().endsWith(path);
                        }
                );
    }
}

