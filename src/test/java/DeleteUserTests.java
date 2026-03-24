import client.UserClient;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

@Epic("API тестирование")
@Feature("Пользователи")
@Story("Удаление пользователя")
public class DeleteUserTests {
    UserClient userClient = new UserClient();

    @Test
    @Description("Удаление пользователя позитивный тест")
    public void deleteUserTest() {
        userClient.deleteRequest("1")
                .then()
                .log().ifValidationFails()
                .statusCode(200);
    }
}