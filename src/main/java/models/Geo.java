package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Geo {
    private String lat;
    private String lng;

    public static Geo defaultOf() {
        return new Geo("50.5555",
                "-50.4444");
    }
}