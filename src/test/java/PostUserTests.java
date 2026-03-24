import client.UserClient;
import data.UserData;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import models.User;
import org.testng.annotations.Test;

import static assertions.UserAssertions.assertUserEqualsWithoutField;
import static assertions.UserAssertions.assertUserIdIsPositive;

@Epic("API тестирование")
@Feature("Пользователи")
@Story("Создание пользователя")
public class PostUserTests {
    UserClient userClient = new UserClient();

    @Test(dataProviderClass = UserData.class,
            dataProvider = "positiveCreate")
    @Description("Создание нового пользователя позитивный тест")
    public void postUserTest(User user) {
        User newUser = userClient.postRequest("/users", user)
                .then()
                .log().ifValidationFails()
                .statusCode(201)
                .extract()
                .as(User.class);

        assertUserEqualsWithoutField(newUser, user, "id");
        assertUserIdIsPositive(newUser);
    }
}