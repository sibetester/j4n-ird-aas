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

public class UserAndTaxYearPage {

    private static final Logger LOG = LoggerFactory.getLogger(UserAndTaxYearPage.class);

    private final WebDriver driver;

    private UserAndTaxYearPage(WebDriver driver) {
        this.driver = driver;
    }

    public static UserAndTaxYearPage fetch(WebDriver driver) {
        String linkToCalculator = "http://brc.ird.govt.nz/web-determinations/startsession/PAYE_Calculator/en-GB/Attribute~b1%40Rules_ProceduralRules_VisibilityRules_doc~global~global";
        driver.get(linkToCalculator);

        return WaitBuilder.createDefaultWebDriverWait(driver).until(new Function<WebDriver, UserAndTaxYearPage>() {
            @Override
            public UserAndTaxYearPage apply(WebDriver input) {
                WebElement element = ExpectedConditions.presenceOfElementLocated(By.id("n11")).apply(input);
                if (element != null) {
                    return new UserAndTaxYearPage(input);
                }
                return null;
            }
        });
    }

    public void enterUserAndTaxDetails(String user, String period) {
        selectUser(user);
        selectTaxPeriod(period);
        WebDriverUtils.getMandatoryElement(driver, By.id("submit")).click();
    }

    private void selectUser(String user) {
        if (user.contentEquals("Employee")) {
            WebDriverUtils.getMandatoryElement(driver, By.id("n11")).click();
        }
        else if (user.contentEquals("Employer")) {
            WebDriverUtils.getMandatoryElement(driver, By.id("n12")).click();
        }
        else {
            LOG.warn("Invalid option for user: " + user);
        }
    }

    private void selectTaxPeriod(String period) {
        (new Select(WebDriverUtils.getMandatoryElement(driver, By.id("qss2Interviews_PAYECalculatorUpdated_xintglobalglobal7"))))
            .selectByValue(period);
    }

}
