package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Address {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;

    public static Address defaultOf() {
        return new Address("Street",
                "Apt. 444",
                "Ufa",
                "55555-4444",
                Geo.defaultOf());
    }
}