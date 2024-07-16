package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.AuthConfig;
import config.BrowserStackConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;


public class BrowserStackMobileDriver implements WebDriverProvider {


    static final AuthConfig authConfig = ConfigFactory.create(AuthConfig.class, System.getProperties());
    private static final BrowserStackConfig config = ConfigFactory.create(BrowserStackConfig.class);


    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        if (System.getProperty("device").equals("ios")) {
            return getIosDriver();
        } else {
            return getAndroidDriver();
        }
    }

    public AndroidDriver getAndroidDriver() {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setAutomationName(config.browserstackName());
        options.setPlatformVersion(config.browserstackPlatform());
        options.setDeviceName(config.browserstackDevice());
        options.setFullReset(false);
        options.setApp(config.browserstackApp());

        try {
            return new AndroidDriver(new URI("https://" + authConfig.bsLogin() + ":" + authConfig.bsPassword()
                    + "@hub-cloud.browserstack.com/wd/hub").toURL(), options);
        } catch (MalformedURLException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public IOSDriver getIosDriver() {
        XCUITestOptions options = new XCUITestOptions();
        options.setPlatformName("iOS");
        options.setAutomationName(config.browserstackName());
        options.setPlatformVersion(config.browserstackPlatform());
        options.setDeviceName(config.browserstackDevice());
        options.setFullReset(false);
        options.setApp(config.browserstackApp());
        try {
            return new IOSDriver(new URI("https://" + authConfig.bsLogin() + ":" + authConfig.bsPassword()
                    + "@hub-cloud.browserstack.com/wd/hub").toURL(), options);
        } catch (MalformedURLException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

}
