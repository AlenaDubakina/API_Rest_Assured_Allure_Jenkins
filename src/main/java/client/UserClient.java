package client;

import config.ApiConfig;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.User;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class UserClient {
    @Step("Отправить get запрос '{endpoint}'")
    public Response getRequest(String endpoint) {
        return given()
                .spec(ApiConfig.requestSpecBuilder)
                .when()
                .get(endpoint);
    }

    @Step("Отправить post запрос для создания пользователя c именем: '{user.name}'")
    public Response postRequest(String endpoint, User user) {
        return given()
                .spec(ApiConfig.requestSpecBuilder)
                .body(user)
                .when()
                .post(endpoint);
    }

    @Step("Обновить пользователя с ID = {updateUser.id}")
    public Response putRequest(String endpoint, User updateUser) {
        return given()
                .spec(ApiConfig.requestSpecBuilder)
                .body(updateUser)
                .when()
                .put(endpoint);
    }

    @Step("Частично обновить данные пользователя: {updateField}")
    public Response patchRequest(String endpoint, Map<String, Object> updateField) {
        return given()
                .spec(ApiConfig.requestSpecBuilder)
                .body(updateField)
                .patch(endpoint);
    }

    @Step("Удалить пользователя с ID = {userId}")
    public Response deleteRequest(String userId) {
        return given()
                .spec(ApiConfig.requestSpecBuilder)
                .when()
                .delete("/users/" + userId);
    }
}