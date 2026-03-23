package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class User {
    private int id;
    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;

    public static User defaultOf() {
        return new User(11,
                "New User",
                "User",
                "email@gmail.com",
                Address.defaultOf(),
                "898-567-482",
                "newUser.com",
                Company.defaultOf());
    }
}