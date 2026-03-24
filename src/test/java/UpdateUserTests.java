import client.UserClient;
import data.UserData;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import models.User;
import org.testng.annotations.Test;

import java.util.Map;

import static assertions.UserAssertions.*;

@Epic("API тестирование")
@Feature("Пользователи")
@Story("Обновление пользователя")
public class UpdateUserTests {
    UserClient userClient = new UserClient();

    @Test(dataProviderClass = UserData.class,
            dataProvider = "positiveCreate")
    @Description("Обновление данных пользователя")
    public void putUserTest(User user) {
        User updateUser = userClient.putRequest("/users/1", user)
                .then()
                .log().ifValidationFails()
                .statusCode(200)
                .extract()
                .as(User.class);

        assertUserEqualsWithoutField(updateUser, user, "id");
        assertUserIdIsEquals(updateUser, 1);
    }

    @Test(dataProviderClass = UserData.class,
            dataProvider = "positiveUpdate")
    @Description("Частичное обновление данных пользователя")
    public void updateUser(Map<String, Object> updateUser) {
        User originalUser = userClient.getRequest("/users/1")
                .then()
                .log().ifValidationFails()
                .statusCode(200)
                .extract()
                .as(User.class);

        String key = updateUser.keySet().iterator().next();
        Object expectedValue = updateUser.get(key);

        User updateUserField = userClient.patchRequest("/users/1", updateUser)
                .then()
                .log().ifValidationFails()
                .statusCode(200)
                .extract()
                .as(User.class);

        assertUserFieldEquals(updateUserField, key, expectedValue);
        assertUserEqualsWithoutField(updateUserField, originalUser, key);
    }
}