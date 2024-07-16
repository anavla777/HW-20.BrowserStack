package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.FIRST)
@Config.Sources({
        "classpath:${device}.properties",
        "classpath:pixel7.properties"
})
public interface BrowserStackConfig extends Config {

    String browserstackApp();

    String browserstackDevice();

    String browserstackPlatform();

    String browserstackProject();

    String browserstackBuild();

    String browserstackName();

    String browserstackUrl();
}
