package data;

import models.Address;
import models.Company;
import models.Geo;
import models.User;
import org.testng.annotations.DataProvider;

import java.util.Map;

public class UserData {
    @DataProvider
    public Object[][] positiveCreate() {
        return new Object[][]{
                {User.defaultOf()},
                {User.defaultOf().setName("NewName")},
                {User.defaultOf().setUsername("NewUsername")},
                {User.defaultOf().setEmail("NewEmail@gmail.com")},
                {User.defaultOf().setAddress(Address.defaultOf().setCity("NewCity"))},
                {User.defaultOf().setAddress(Address.defaultOf().setStreet("NewStreet"))},
                {User.defaultOf().setAddress(Address.defaultOf().setSuite("NewSuite"))},
                {User.defaultOf().setAddress(Address.defaultOf().setZipcode("7685 - 456"))},
                {User.defaultOf().setAddress(Address.defaultOf().setGeo(Geo.defaultOf().setLat("-59.8967")))},
                {User.defaultOf().setAddress(Address.defaultOf().setGeo(Geo.defaultOf().setLng("55.8967")))},
                {User.defaultOf().setPhone("89640840843")},
                {User.defaultOf().setWebsite("newWebsite.com")},
                {User.defaultOf().setCompany(Company.defaultOf().setName("NewNameCompany"))},
                {User.defaultOf().setCompany(Company.defaultOf().setBs("NewBs"))},
                {User.defaultOf().setCompany(Company.defaultOf().setCatchPhrase("NewCatchPhrase"))},
        };
    }

    @DataProvider
    public Object[][] positiveUpdate() {
        return new Object[][]{
                {Map.of("name", User.defaultOf().setName("NewName").getName())},
                {Map.of("username", User.defaultOf().setUsername("NewUsername").getUsername())},
                {Map.of("email", User.defaultOf().setEmail("NewEmail@gmail.com").getEmail())},
                {Map.of("phone", User.defaultOf().setPhone("89640840843").getPhone())},
                {Map.of("website", User.defaultOf().setWebsite("newWebsite.com").getWebsite())},
        };
    }
}