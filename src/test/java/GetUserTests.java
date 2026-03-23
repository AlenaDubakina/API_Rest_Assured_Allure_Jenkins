import client.UserClient;
import models.User;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class GetUserTests {
    UserClient userClient = new UserClient();

    @Test
    public void getUsersTest() {
        List<User> users = userClient.getRequest("/users")
                .then()
                .log().ifValidationFails()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList("", User.class);

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(users)
                .as("Пользователи не должны быть пустыми")
                .isNotEmpty();

        softAssertions.assertThat(users)
                .as("У пользователей невалидный email")
                .allMatch(user -> user.getEmail().contains("@"));

        softAssertions.assertThat(users)
                .as("Список пользователей должен содержать 10 элементов")
                .hasSize(10);

        softAssertions.assertThat(users)
                .as("У всех пользователей должен быть заполнен id")
                .allMatch(user-> user.getId() > 0);

        softAssertions.assertAll();
    }

    @Test
    public void checkedSchema() {
        userClient.getRequest("/users/1")
                .then()
                .log().ifValidationFails()
                .body(matchesJsonSchemaInClasspath("schema.json"));
    }

    @Test
    public void getUserTest() {
        User user1 = userClient.getRequest("/users/1")
                .then()
                .log().ifValidationFails()
                .extract()
                .as(User.class);

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(user1)
                .as("Проверка основных полей пользователя")
                .extracting(User::getId,
                        User::getName,
                        User::getUsername,
                        User::getEmail,
                        User::getPhone,
                        User::getWebsite)
                .containsExactly(1,
                        "Leanne Graham",
                        "Bret",
                        "Sincere@april.biz",
                        "1-770-736-8031 x56442",
                        "hildegard.org");

        softAssertions.assertThat(user1.getAddress().getCity())
                .as("Некорректное поле address")
                .isEqualTo("Gwenborough");

        softAssertions.assertThat(user1.getCompany().getName())
                .as("Некорректное поле company")
                .isEqualTo("Romaguera-Crona");
    }

    @Test
    public void getInvalidUserTest() {
        userClient.getRequest("/users/999")
                .then()
                .log().ifValidationFails()
                .statusCode(404);
    }
}