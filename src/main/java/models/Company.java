package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Company {
    private String name;
    private String catchPhrase;
    private String bs;

    public static Company defaultOf() {
        return new Company("ApiCompany",
                "Testing Api",
                "IT");
    }
}