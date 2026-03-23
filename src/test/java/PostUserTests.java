import client.UserClient;
import data.UserData;
import models.User;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PostUserTests {
    UserClient userClient = new UserClient();

    @Test(dataProviderClass = UserData.class,
            dataProvider = "positiveCreate")
    public void postUserTest(User user) {
        User newUser = userClient.postRequest("/users", user)
                .then()
                .log().ifValidationFails()
                .statusCode(201)
                .extract()
                .as(User.class);

        assertThat(newUser)
                .as("Созданный user не валидный")
                .extracting(User::getName,
                        User::getUsername,
                        User::getEmail,
                        User::getAddress,
                        User::getPhone,
                        User::getWebsite,
                        User::getCompany)
                .containsExactly(user.getName(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getAddress(),
                        user.getPhone(),
                        user.getWebsite(),
                        user.getCompany());
    }

    @Test(dataProviderClass = UserData.class,
            dataProvider = "positiveCreate")
    public void putUserTest(User user) {
        User updateUser = userClient.putRequest("/users/1", user)
                .then()
                .log().ifValidationFails()
                .statusCode(200)
                .extract()
                .as(User.class);

        assertThat(updateUser)
                .as("Пользователь не обновлен")
                .extracting(User::getName,
                        User::getUsername,
                        User::getEmail,
                        User::getAddress,
                        User::getPhone,
                        User::getWebsite,
                        User::getCompany)
                .containsExactly(user.getName(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getAddress(),
                        user.getPhone(),
                        user.getWebsite(),
                        user.getCompany());
    }
}