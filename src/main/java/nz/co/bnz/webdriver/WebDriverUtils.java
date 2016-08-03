package nz.co.bnz.webdriver;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * A collection of static methods that wrap WebDriver behaviour to create a friendlier API
 */
public class WebDriverUtils {

    public static WebElement getMandatoryElement(WebDriver driver, By by) {
        List<WebElement> elems = WaitBuilder.createDefaultWebDriverWait(driver).until(
            ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        if (elems.isEmpty()) {
            throw new NoSuchElementException("Couldn't find element by " + by);
        }
        return elems.get(0);
    }

}
