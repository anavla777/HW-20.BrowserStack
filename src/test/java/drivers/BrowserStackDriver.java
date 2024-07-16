package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.AuthConfig;
import config.BrowserStackConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

public class BrowserStackDriver implements WebDriverProvider {


    private static final BrowserStackConfig config = ConfigFactory.create(BrowserStackConfig.class);
    static final AuthConfig authConfig = ConfigFactory.create(AuthConfig.class, System.getProperties());


    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {

        return getWebDriver();
    }

    public WebDriver getWebDriver() {
        MutableCapabilities caps = new MutableCapabilities();

        // Set your access credentials
        caps.setCapability("browserstack.user", authConfig.bsLogin());
        caps.setCapability("browserstack.key", authConfig.bsPassword());

        // Set URL of the application under test
        caps.setCapability("app", config.browserstackApp());

        // Specify device and os_version for testing
        caps.setCapability("device", config.browserstackDevice());
        caps.setCapability("os_version", config.browserstackPlatform());

        // Set other BrowserStack capabilities
        caps.setCapability("project", config.browserstackProject());
        caps.setCapability("build", config.browserstackBuild());
        caps.setCapability("name", config.browserstackName());

        // Initialise the remote Webdriver using BrowserStack remote URL
        // and desired capabilities defined above
        try {
            return new RemoteWebDriver(
                    new URL(config.browserstackUrl()), caps);
        } catch (
                MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
