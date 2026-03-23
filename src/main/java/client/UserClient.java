package client;

import config.ApiConfig;
import io.restassured.response.Response;
import models.User;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class UserClient {
    public Response getRequest(String endpoint) {
        return given()
                .spec(ApiConfig.requestSpecBuilder)
                .when()
                .get(endpoint);
    }

    public Response postRequest(String endpoint, User user) {
        return given()
                .spec(ApiConfig.requestSpecBuilder)
                .body(user)
                .when()
                .post(endpoint);
    }

    public Response putRequest(String endpoint, User updateUser) {
        return given()
                .spec(ApiConfig.requestSpecBuilder)
                .body(updateUser)
                .when()
                .put(endpoint);
    }

    public Response patchRequest(String endpoint, Map<String, Object> updateField) {
        return given()
                .spec(ApiConfig.requestSpecBuilder)
                .body(updateField)
                .patch(endpoint);
    }

    public Response deleteRequest(String endpoint) {
        return given()
                .spec(ApiConfig.requestSpecBuilder)
                .when()
                .delete(endpoint);
    }
}