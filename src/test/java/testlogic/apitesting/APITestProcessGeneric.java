package testlogic.apitesting;

import Model.apitesting.Location;
import Model.apitesting.UserProfile;
import helper.Utility;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.Assert;

import java.io.File;

public class APITestProcessGeneric {

    /*check response data in application JSONShcema*/
    public static void validationResponseData(Response currentRes, String data){
        System.out.println("check response data : " + data);
        File fileUsersJson = getJSONSchemaFile(data);
        currentRes.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(fileUsersJson));
    }

    private static File getJSONSchemaFile(String JSONFile) {
        return new File("src/test/resources/JSONSchemaData/" + JSONFile);
    }

    /*check status code response same with  status code expected*/
    public static void validationStatusCode(Response currentRes, int expectedStatusCode){
        System.out.println("check status code : " + expectedStatusCode);
        Assert.assertEquals(currentRes.getStatusCode(), expectedStatusCode);
    }

    public static UserProfile prepareDataUserTestPost() {
        UserProfile dataUser = new UserProfile();
        Location dataLocation   = new Location();

        // prepare data test user
        // information user
        String title        = "miss";
        String firstName    = "Mahardika test";
        String lastName     = "Create User";
        String picture      = "https://randomuser.me/api/portraits/med/women/89.jpg";
        String gender       = "female";
        String email        = Utility.generateRandomEmail(); // generate email
        String dateOfBirth  = "1945-11-17T06:31:57.367Z";
        String phone        = "087-368-211";

        dataUser.setTitle(title);
        dataUser.setFirstName(firstName);
        dataUser.setLastName(lastName);
        dataUser.setPicture(picture);
        dataUser.setGender(gender);
        dataUser.setEmail(email);
        dataUser.setDateOfBirth(dateOfBirth);
        dataUser.setPhone(phone);

        // location
        String street       = "Jl. Situ Raja";
        String city         = "Malang";
        String state        = "Jawa Timur";
        String country      = "Indonesia";
        String timezone     = "+6:00";

        dataLocation.setStreet(street);
        dataLocation.setCity(city);
        dataLocation.setState(state);
        dataLocation.setCountry(country);
        dataLocation.setTimezone(timezone);
        dataUser.setLocation(dataLocation);

        return dataUser;
    }

    public static UserProfile prepareDataUserTestUpdate() {
        UserProfile dataUser = new UserProfile();
        Location dataLocation   = new Location();

        // prepare data test user
        // information user
        String title        = "mr";
        String firstName    = "Andika test";
        String lastName     = "Update User";
        String picture      = "https://randomuser.me/api/portraits/med/women/89.jpg";
        String gender       = "male";
        String dateOfBirth  = "1945-11-17T06:31:57.367Z";
        String phone        = "087-368-200";

        dataUser.setTitle(title);
        dataUser.setFirstName(firstName);
        dataUser.setLastName(lastName);
        dataUser.setPicture(picture);
        dataUser.setGender(gender);
        dataUser.setDateOfBirth(dateOfBirth);
        dataUser.setPhone(phone);

        // location
        String street       = "Jl. Mataram";
        String city         = "Aceh";
        String state        = "Sumatera";
        String country      = "Indonesia";
        String timezone     = "+10:00";

        dataLocation.setStreet(street);
        dataLocation.setCity(city);
        dataLocation.setState(state);
        dataLocation.setCountry(country);
        dataLocation.setTimezone(timezone);
        dataUser.setLocation(dataLocation);

        return dataUser;
    }
}
