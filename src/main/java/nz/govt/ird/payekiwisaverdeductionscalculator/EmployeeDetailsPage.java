package nz.govt.ird.payekiwisaverdeductionscalculator;

import nz.co.bnz.webdriver.WaitBuilder;
import nz.co.bnz.webdriver.WebDriverUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Function;

public class EmployeeDetailsPage {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeDetailsPage.class);

    private final WebDriver driver;

    private EmployeeDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    public static EmployeeDetailsPage fetch(WebDriver driver) {
        return WaitBuilder.createDefaultWebDriverWait(driver).until(new Function<WebDriver, EmployeeDetailsPage>() {
            @Override
            public EmployeeDetailsPage apply(WebDriver input) {
                WebElement element = ExpectedConditions.presenceOfElementLocated(
                        By.id("qss3Interviews_PAYECalculatorUpdated_xintglobalglobal7")).apply(input);
                if (element != null) {
                    return new EmployeeDetailsPage(input);
                }
                return null;
            }
        });
    }

    public void enterPayDetails(String name, String reference, String tax, String frequency, String perPay, String amount, String employeeDeductions, String employerContributions) {

        WebDriverUtils.getMandatoryElement(driver, By.id("qss3Interviews_PAYECalculatorUpdated_xintglobalglobal7"))
                .sendKeys(name);
        WebDriverUtils.getMandatoryElement(driver, By.id("qss3Interviews_PAYECalculatorUpdated_xintglobalglobal9"))
                .sendKeys(reference);
        (new Select(WebDriverUtils.getMandatoryElement(driver, By.id("qss3Interviews_PAYECalculatorUpdated_xintglobalglobal11"))))
                .selectByValue(tax);

        (new Select(WebDriverUtils.getMandatoryElement(driver, By.id("qss3Interviews_PAYECalculatorUpdated_xintglobalglobal19"))))
                .selectByValue(frequency);
        WebDriverUtils.getMandatoryElement(driver, By.id("qss3Interviews_PAYECalculatorUpdated_xintglobalglobal20"))
                .sendKeys(perPay);
        selectAmountNetOrGross(amount);

        (new Select(WebDriverUtils.getMandatoryElement(driver, By.id("qss3Interviews_PAYECalculatorUpdated_xintglobalglobal28"))))
                .selectByValue(employeeDeductions);

        WebDriverUtils.getMandatoryElement(driver, By.id("qss3Interviews_PAYECalculatorUpdated_xintglobalglobal30"))
                .sendKeys(employerContributions);

        WebDriverUtils.getMandatoryElement(driver, By.id("submit")).click();
    }

    private void selectAmountNetOrGross(String amount) {
        if (amount.contentEquals("Gross")) {
            WebDriverUtils.getMandatoryElement(driver, By.id("n81")).click();
        } else if (amount.contentEquals("Net")) {
            WebDriverUtils.getMandatoryElement(driver, By.id("n92")).click();
        } else {
            LOG.warn("Invalid option for amount: " + amount);
        }
    }
}
