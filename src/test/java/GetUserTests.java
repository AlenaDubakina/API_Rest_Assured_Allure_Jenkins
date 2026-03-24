import client.UserClient;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import models.Address;
import models.Company;
import models.Geo;
import models.User;
import org.testng.annotations.Test;

import java.util.List;

import static assertions.UserAssertions.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@Epic("API тестирование")
@Feature("Пользователи")
@Story("Получение пользователей")
public class GetUserTests {
    UserClient userClient = new UserClient();

    @Test
    @Description("Проверка количества пользователей")
    public void getUsersTest() {
        List<User> users = userClient.getRequest("/users")
                .then()
                .log().ifValidationFails()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList("", User.class);

        assertUsersSize(users, 10);
    }

    @Test
    @Description("Проверка обязательных полей у пользователей")
    public void getUsersRequiredFields() {
        List<User> users = userClient.getRequest("/users")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList(".", User.class);

        users.forEach(user -> assertAllUsersHaveRequiredFields(user));
    }

    @Test
    @Description("Проверка даннных пользователя по ID")
    public void getUserTest() {
        User expectedUser = new User(1,
                "Leanne Graham",
                "Bret",
                "Sincere@april.biz", new Address("Kulas Light",
                "Apt. 556",
                "Gwenborough",
                "92998-3874",
                new Geo("-37.3159", "81.1496")),
                "1-770-736-8031 x56442",
                "hildegard.org", new Company("Romaguera-Crona",
                "Multi-layered client-server neural-net",
                "harness real-time e-markets"));

        User actualUser = userClient.getRequest("/users/1")
                .then()
                .log().ifValidationFails()
                .extract()
                .as(User.class);

        assertUserEquals(actualUser, expectedUser);
    }

    @Test
    @Description("Проверка валидности JSON схемы пользователей")
    public void checkedSchema() {
        userClient.getRequest("/users/1")
                .then()
                .log().ifValidationFails()
                .body(matchesJsonSchemaInClasspath("schema.json"));
    }

    @Test
    @Description("Получение несуществующего пользователя")
    public void getInvalidUserTest() {
        userClient.getRequest("/users/999")
                .then()
                .log().ifValidationFails()
                .statusCode(404);
    }
}