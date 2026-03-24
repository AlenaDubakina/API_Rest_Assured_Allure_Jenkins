package assertions;

import io.qameta.allure.Step;
import models.User;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UserAssertions {

    @Step("Проверить, что у пользователя присвоен id")
    public static void assertUserIdIsPositive(User user) {
        assertThat(user.getId())
                .as("ID пользователя должен быть положительным, но был %d", user.getId())
                .isPositive();
    }

    @Step("Проверить, что у пользователя id = {expectedId}")
    public static void assertUserIdIsEquals(User user, Integer expectedId) {
        assertThat(user.getId())
                .as("ID пользователя должен быть %d, но был %d", expectedId, user.getId())
                .isEqualTo(expectedId);
    }

    @Step("Проверить, что у пользователя заполнено поле name")
    public static void assertUserNameIsNotEmpty(User user) {
        assertThat(user.getName())
                .as("Поле name не должно быть пустым")
                .isNotEmpty();
    }

    @Step("Проверить, что у пользователя {fieldName} = {expectedName}")
    public static void assertUserFieldEquals(User user, String fieldName, Object expectedName) {
        Object actualField = extractFieldValue(user, fieldName);
        assertThat(actualField)
                .as("Поле %s должно быть %s, а было %s", fieldName, expectedName, user.getName())
                .isEqualTo(expectedName);
    }

    @Step("Проверить, что у пользователя заполнено поле userName")
    public static void assertUserNameFieldIsNotEmpty(User user) {
        assertThat(user.getUsername())
                .as("Поле userName не должно быть пустым")
                .isNotEmpty();
    }

    @Step("Проверить, что email пользователя содержит '@'")
    public static void assertUserHasValidEmail(User user) {
        assertThat(user.getEmail())
                .as("У пользователей невалидный email")
                .contains("@");
    }

    @Step("Проверить, что список содержит {expectedSize} элементов")
    public static void assertUsersSize(List<User> users, int expectedSize) {
        assertThat(users)
                .as("Список пользователей содержит некорректное количество элементов")
                .hasSize(expectedSize);
    }

    @Step("Проверить обязательные поля всех пользователей")
    public static void assertAllUsersHaveRequiredFields(User user) {
        assertUserIdIsPositive(user);
        assertUserNameIsNotEmpty(user);
        assertUserNameFieldIsNotEmpty(user);
        assertUserHasValidEmail(user);
    }

    @Step("Проверить, что пользователь соответствует ожидаемому")
    public static void assertUserEquals(User actual, User expected) {
        assertThat(actual)
                .usingRecursiveComparison()
                .as("Пользователь не соответствует ожидаемому")
                .isEqualTo(expected);
    }

    @Step("Проверить, что пользователь соответствует ожидаемому (без поля {fieldName})")
    public static void assertUserEqualsWithoutField(User actual, User expected, String fieldName) {
        assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFields(fieldName)
                .as("Пользователь не соответствует ожидаемому")
                .isEqualTo(expected);
    }

    private static Object extractFieldValue(User user, String fieldName) {
        switch (fieldName) {
            case "name":
                return user.getName();
            case "username":
                return user.getUsername();
            case "email":
                return user.getEmail();
            case "phone":
                return user.getPhone();
            case "website":
                return user.getWebsite();
            default:
                throw new IllegalArgumentException("Некорректное поле: " + fieldName);
        }
    }
}