package nz.co.bnz.webdriver;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.concordion.api.extension.Extension;
import org.concordion.ext.ScreenshotExtension;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebDriverFixture {

    @Extension
    public ScreenshotExtension extension = new ScreenshotExtension();

    private static final Logger LOG = LoggerFactory.getLogger(WebDriverFixture.class);

    @ClassRule
    public static WebDriverRule driverRule = new WebDriverRule();

    private WebDriver driver;

    @Rule
    public TestWatcher testWatcher = new TestWatcher() {
        @Override
        protected void starting(Description description) {
            LOG.info(">> Running test  - " + description.getClassName() + " : " + description.getMethodName());
        }

        @Override
        protected void succeeded(Description description) {
            LOG.info(">> Finished test - " + description.getClassName() + " : " + description.getMethodName());
        }

        @Override
        protected void failed(Throwable e, Description description) {
            LOG.info(">> Failed test - " + description.getClassName() + " : " + description.getMethodName());
        }
    };

    public WebDriverFixture() {
        LOG.info("Running tests (" + this.getClass().getSimpleName() + ")");

        try {
            LOG.info("Running test on client computer: '" + InetAddress.getLocalHost().getHostName() + "'");
        } catch (UnknownHostException ignored) {
        }
    }

    @Before
    public void before() {
        WebDriver driver = getWebDriver();
        extension.setScreenshotTaker(new SeleniumScreenshotTaker(driver));
    }

    @After
    public void after() {
        if (driver != null) {
            try {
                driver.close();
                driver.manage().deleteAllCookies();
                driver.quit();
                driver = null;
            } catch (Exception ex) {
                LOG.info("unable to quit session", ex);
            }
        }
    }

    protected WebDriver getWebDriver() {
        if (driver == null) {
            driver = driverRule.createDriver();
        }
        return driver;
    }

    public void logExample(String exampleText) {
        LOG.info("Running example: " + exampleText.replaceAll("(?:\\s\\s+)", " "));
    }

}
