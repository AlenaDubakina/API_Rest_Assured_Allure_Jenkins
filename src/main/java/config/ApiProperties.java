package config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config/api.properties")
public interface ApiProperties extends Config {

    @Key("api.base.uri")
    String baseUrl();
}
