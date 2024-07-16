package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.FIRST)
@Config.Sources({
        "classpath:${device}.properties",
        "classpath:pixel7.properties"
})
public interface BrowserStackConfig extends Config {

    @DefaultValue("bs://c700ce60cf13ae8ed97705a55b8e022f13c5827c")
    String browserstackApp();

    @DefaultValue("Google Pixel 7")
    String browserstackDevice();

    @DefaultValue("13.0")
    String browserstackPlatform();

    @DefaultValue("Appium Java Project")
    String browserstackProject();

    @DefaultValue("browserstack-build-1")
    String browserstackBuild();

    @DefaultValue("Wikipedia app tests")
    String browserstackName();

    @DefaultValue("https://hub.browserstack.com/wd/hub")
    String browserstackUrl();
}
