import client.UserClient;
import org.testng.annotations.Test;

public class DeleteUserTests {
    UserClient userClient = new UserClient();

    @Test
    public void deleteUserTest() {
        userClient.deleteRequest("/users/1")
                .then()
                .log().ifValidationFails()
                .statusCode(200);
    }

    @Test
    public void negativeDeleteUserTest() {
        userClient.deleteRequest("/user/1000")
                .then()
                .log().ifValidationFails()
                .statusCode(404);
    }
}