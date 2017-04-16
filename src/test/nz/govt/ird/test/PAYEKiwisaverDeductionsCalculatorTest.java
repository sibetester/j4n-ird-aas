package nz.govt.ird.test;

import nz.co.bnz.webdriver.WebDriverFixture;
import nz.govt.ird.payekiwisaverdeductionscalculator.EmployeeDetailsPage;
import nz.govt.ird.payekiwisaverdeductionscalculator.SummaryPage;
import nz.govt.ird.payekiwisaverdeductionscalculator.UserAndTaxYearPage;

import org.concordion.api.FullOGNL;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(ConcordionRunner.class)
@FullOGNL
public class PAYEKiwisaverDeductionsCalculatorTest extends WebDriverFixture {

    private static final Logger LOG = LoggerFactory.getLogger(PAYEKiwisaverDeductionsCalculatorTest.class);

    public void enterUserAndTaxDetails() {
        UserAndTaxYearPage userTaxYearPage = UserAndTaxYearPage.fetch(getWebDriver());
        userTaxYearPage.enterUserAndTaxDetails("Employee", "1 April 2017 to 31 March 2018");
    }

    public void enterPayDetails() {
        EmployeeDetailsPage employeeDetailsPage = EmployeeDetailsPage.fetch(getWebDriver());
        employeeDetailsPage.enterPayDetails("Test User", "TEST1234", "M", "Weekly", "1,024.00", "Net");
    }

    public boolean hasName() {
        return SummaryPage.fetch(getWebDriver()).getName().contentEquals("Test User");
    }

    public boolean hasReferenceNumber() {
        return SummaryPage.fetch(getWebDriver()).getReferenceNumber().contentEquals("TEST1234");
    }

    public boolean hasTaxCode() {
        return SummaryPage.fetch(getWebDriver()).getTaxCode().contentEquals("M");
    }

    public boolean hasPayFrequency() {
        return SummaryPage.fetch(getWebDriver()).getPayFrequency().contentEquals("Weekly");
    }

    public boolean hasPayAmount() {
        return SummaryPage.fetch(getWebDriver()).getPayAmount().contentEquals("$1,024.00 net");
    }
}
