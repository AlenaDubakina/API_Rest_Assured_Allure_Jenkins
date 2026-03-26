package config;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;

public class ApiConfig {
    private static final ApiProperties properties = ConfigFactory.create(ApiProperties.class);

    public static RequestSpecification requestSpecBuilder = new RequestSpecBuilder()
            .setBaseUri(properties.baseUrl())
            .setContentType(ContentType.JSON)
            .setAccept(ContentType.JSON)
            .build();
}