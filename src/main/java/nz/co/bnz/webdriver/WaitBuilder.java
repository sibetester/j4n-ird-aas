package nz.co.bnz.webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public final class WaitBuilder {

    private static final int DEFAULT_WAIT_SECONDS = Integer.valueOf(System.getProperty("waitbuilder.default.timeout", "30"));

    private static final int DEFAULT_SLEEP_MILLIS = 500;

    public static FluentWait<WebDriver> createDefaultWebDriverWait(WebDriver driver) {
        return new WebDriverWait(driver, DEFAULT_WAIT_SECONDS)
            .ignoring(StaleElementReferenceException.class)
            .ignoring(NoSuchElementException.class);
    }

    public static FluentWait<WebElement> createDefaultWebElementWait(WebElement element) {
        return new FluentWait<>(element).ignoring(StaleElementReferenceException.class)
            .ignoring(NoSuchElementException.class)
            .withTimeout(DEFAULT_WAIT_SECONDS, TimeUnit.SECONDS)
            .pollingEvery(DEFAULT_SLEEP_MILLIS, TimeUnit.MILLISECONDS);
    }

    public static <E> FluentWait<E> createDefaultWait(E element) {
        return new FluentWait<>(element).ignoring(StaleElementReferenceException.class)
            .ignoring(NoSuchElementException.class)
            .withTimeout(DEFAULT_WAIT_SECONDS, TimeUnit.SECONDS)
            .pollingEvery(DEFAULT_SLEEP_MILLIS, TimeUnit.MILLISECONDS);
    }

}
