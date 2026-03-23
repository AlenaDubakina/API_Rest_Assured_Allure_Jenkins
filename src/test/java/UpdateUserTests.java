import client.UserClient;
import data.UserData;
import org.testng.annotations.Test;

import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

public class UpdateUserTests {
    UserClient userClient = new UserClient();

    @Test(dataProviderClass = UserData.class,
            dataProvider = "positiveUpdate")
    public void updateUser(Map<String, Object> updateUser) {

        String key = updateUser.keySet().iterator().next();

        userClient.patchRequest("/users/1", updateUser)
                .then()
                .log().ifValidationFails()
                .statusCode(200)
                .body(key, equalTo(updateUser.get(key)));
    }
}