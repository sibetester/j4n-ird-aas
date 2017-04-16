package nz.govt.ird.payekiwisaverdeductionscalculator;

import nz.co.bnz.webdriver.WaitBuilder;
import nz.co.bnz.webdriver.WebDriverUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Function;

public class SummaryPage {

    private static final Logger LOG = LoggerFactory.getLogger(SummaryPage.class);

    private final WebDriver driver;

    private SummaryPage(WebDriver driver) {
        this.driver = driver;
    }

    public static SummaryPage fetch(WebDriver driver) {
        return WaitBuilder.createDefaultWebDriverWait(driver).until(new Function<WebDriver, SummaryPage>() {
            @Override
            public SummaryPage apply(WebDriver input) {
                WebElement element = ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("#summary85 > div > table > tbody > tr:nth-child(1) > td:nth-child(2)")).apply(input);
                if (element != null) {
                    return new SummaryPage(input);
                }
                return null;
            }
        });
    }

    public String getName() {
        String yourName = "#summary85 > div > table > tbody > tr:nth-child(1) > td:nth-child(2)";
        return WebDriverUtils.getMandatoryElement(driver, By.cssSelector(yourName)).getText();
    }

    public String getReferenceNumber() {
        String referenceNumber = "#summary85 > div > table > tbody > tr:nth-child(2) > td:nth-child(2)";
        return WebDriverUtils.getMandatoryElement(driver, By.cssSelector(referenceNumber)).getText();
    }

    public String getTaxCode() {
        String taxCode = "#summary85 > div > table > tbody > tr:nth-child(3) > td:nth-child(2)";
        return WebDriverUtils.getMandatoryElement(driver, By.cssSelector(taxCode)).getText();
    }

    public String getPayFrequency() {
        String payFrequency = "#summary96 > div > table > tbody > tr:nth-child(1) > td:nth-child(2)";
        return WebDriverUtils.getMandatoryElement(driver, By.cssSelector(payFrequency)).getText();
    }

    public String getPayAmount() {
        String payAmount = "#summary96 > div > table > tbody > tr:nth-child(3) > td:nth-child(2)";
        return WebDriverUtils.getMandatoryElement(driver, By.cssSelector(payAmount)).getText();
    }

    public String getCalculatePAYE() {
        String calculationPAYE = "#summary104 > div > table > tbody > tr:nth-child(2) > td:nth-child(3)";
        return WebDriverUtils.getMandatoryElement(driver, By.cssSelector(calculationPAYE)).getText();
    }
}
