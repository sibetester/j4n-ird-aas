package nz.co.bnz.webdriver;

import java.io.IOException;
import java.io.OutputStream;

import org.concordion.ext.ScreenshotTaker;
import org.concordion.ext.ScreenshotUnavailableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

/**
 * User the current WebDriver to take the screenshot (if supported) instead of
 * the graphics driver for the machine the tests are running on
 */
public class SeleniumScreenshotTaker implements ScreenshotTaker {
    private final WebDriver driver;

    public SeleniumScreenshotTaker(WebDriver driver) {
        WebDriver baseDriver = driver;
        while (baseDriver instanceof EventFiringWebDriver) {
            baseDriver = ((EventFiringWebDriver)baseDriver).getWrappedDriver();
        }
        this.driver = baseDriver;
    }

    @Override
    public int writeScreenshotTo(OutputStream outputStream) throws IOException {
        byte[] screenshot;
        try {
            screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
        } catch (ClassCastException e) {
            throw new ScreenshotUnavailableException("driver does not implement TakesScreenshot");
        }

        int width = 0;
        if (outputStream != null){
            outputStream.write(screenshot);

            // borrowed from Concordion, work out width of images + a fudge factor
            width = ((Long)((JavascriptExecutor)driver).executeScript("return document.body.clientWidth")).intValue() + 2;
        }

        return width;
    }

    @Override
    public String getFileExtension() {
        return "png";
    }
}
