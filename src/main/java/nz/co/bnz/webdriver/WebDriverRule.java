package nz.co.bnz.webdriver;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.rules.ExternalResource;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * JUnit rule for managing WebDriver as an external resource.
 *
 * Tests/Fixtures using junit can use this class as a {@link Rule} or {@link ClassRule}
 *
 */
public class WebDriverRule extends ExternalResource {

    @Override
    protected void before() throws Throwable {
    }

    public WebDriver createDriver() {
        WebDriver driver = new FirefoxDriver(getDesiredCapabilities());
        driver.manage().window().setSize(new Dimension(1050, 768));
        return driver;
    }

    private static DesiredCapabilities getDesiredCapabilities() {

        DesiredCapabilities dc = DesiredCapabilities.firefox();

        FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.setPreference("browser.startup.homepage", "about:blank");
        firefoxProfile.setPreference("startup.homepage_welcome_url", "about:blank");
        firefoxProfile.setPreference("startup.homepage_welcome_url.additional", "about:blank");
        firefoxProfile.setPreference("app.update.auto", false);
        firefoxProfile.setPreference("app.update.enabled", false);
        firefoxProfile.setPreference("app.update.silent", true);

        firefoxProfile.setPreference("network.automatic-ntlm-auth.trusted-uris", ".thenational.com, .bnz.co.nz");
        firefoxProfile.setPreference("network.automatic-ntlm-auth.allow-non-fqdn", true);
        firefoxProfile.setPreference("network.negotiate-auth.delegation-uris", ".thenational.com, .bnz.co.nz");
        firefoxProfile.setPreference("network.negotiate-auth.trusted-uris", ".thenational.com, .bnz.co.nz");
        firefoxProfile.setPreference("network.negotiate-auth.using-native-gsslib", false);

        dc.setCapability(FirefoxDriver.PROFILE, firefoxProfile);

        Proxy proxy = new Proxy();
        proxy.setProxyType(ProxyType.SYSTEM);

        dc.setCapability(CapabilityType.PROXY, proxy);
        dc.setCapability(CapabilityType.HAS_NATIVE_EVENTS, false);

        return dc;
    }
}
