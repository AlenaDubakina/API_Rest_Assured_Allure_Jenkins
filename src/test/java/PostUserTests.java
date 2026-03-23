import client.UserClient;
import data.UserData;
import models.User;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

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

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(newUser)
                .as("Созданный user не валидный")
                .extracting(User::getName,
                        User::getUsername,
                        User::getEmail,
                        User::getPhone,
                        User::getWebsite)
                .containsExactly(user.getName(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getPhone(),
                        user.getWebsite());

        softAssertions.assertThat(newUser.getAddress())
                .usingRecursiveComparison()
                .as("Некорректный адрес пользователя")
                .isEqualTo(user.getAddress());

        softAssertions.assertThat(newUser.getCompany())
                .usingRecursiveComparison()
                .as("Некорректные данные компании пользователя")
                .isEqualTo(user.getCompany());


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

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(updateUser)
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

        softAssertions.assertThat(updateUser.getAddress())
                .usingRecursiveComparison()
                .as("Некорректный адрес пользователя")
                .isEqualTo(user.getAddress());

        softAssertions.assertThat(updateUser.getCompany())
                .usingRecursiveComparison()
                .as("Некорректные данные компании пользователя")
                .isEqualTo(user.getCompany());

    }
}