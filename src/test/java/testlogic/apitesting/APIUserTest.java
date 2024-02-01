package testlogic.apitesting;

import Model.apitesting.UserProfile;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import helper.SetUpEndPoint;
import io.opentelemetry.semconv.SemanticAttributes;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import testlogic.apitesting.APITestProcessGeneric;
import testlogic.apitesting.request.RequestAPIUserManagement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class APIUserTest {

    private Response res;

    public Response getRes() {
        return res;
    }

    public void setRes(Response res) {
        this.res = res;
    }

    //hit api get list users
    public void hitAPIGetListUsers(){
       res = RequestAPIUserManagement.getListUsers(SetUpEndPoint.getURL()); //call API Get List User
       System.out.println(res.getBody().asString()); // logging response API
    }

    /* check response body lis user consist of data (id, title, firstName, lastName, picture), total, page, and limit */
    public void checkResponseBodyListUsers(){
        System.out.println("validation response body list user process");
        JSONArray data = new JSONArray(res.getBody().jsonPath().getList("data")); // get data json in array
        for(int i=0; i<data.length(); i++){
            JSONObject user = (JSONObject) data.get(i); // get element array

            // verify data
            Assert.assertNotNull(user.get("id"));
            assertThat(user.get("title")).isIn("mr", "ms", "mrs", "miss", "dr", ""); // check title value between mr, ms, mrs, miss, dr, and blank
            Assert.assertNotNull(user.get("firstName")); // check first name not null
            Assert.assertNotNull(user.get("lastName")); // check last name not null
        }
    }

    public void hitAPIGetProfileUser(String idUser) {
        res = RequestAPIUserManagement.getProfileUser(SetUpEndPoint.getURL(), idUser); //call API Get List User
        System.out.println(res.getBody().asString()); // logging response API
    }

    public void checkResponseBodyProfileUser() {
        System.out.println("validation response body profile user process normal");
        JSONObject userProfile = new JSONObject(res.getBody().asString()); // get data json in object

        // verify data
        Assert.assertNotNull(userProfile.get("id"));
        assertThat(userProfile.get("title")).isIn("mr", "ms", "mrs", "miss", "dr", ""); // check title value between mr, ms, mrs, miss, dr, and blank
        Assert.assertNotNull(userProfile.get("firstName")); // check first name not null
        Assert.assertNotNull(userProfile.get("lastName")); // check last name not null
        assertThat(userProfile.get("gender")).isIn("male", "female", ""); // check gender value between male, female, and blank
    }

    public void checkResponseBodyGetProfileUserFailed(String expectedMessage) {
        System.out.println("validation response body profile user process failed");
        JSONObject notification = new JSONObject(res.getBody().asString()); // get data json in object

        // verify data
        String actualMessage = notification.get("error").toString();
        System.out.println("actual message: " + actualMessage);
        Assert.assertEquals(actualMessage, expectedMessage);
    }

    public String hitAPIPostNewUser(UserProfile dataUser) {
        res = RequestAPIUserManagement.postCreateUser(SetUpEndPoint.getURL(), dataUser); //call API Post New User
        System.out.println(res.getBody().asString()); // logging response API

        return res.getBody().jsonPath().get("id");
    }

    public String hitAPIPostNewUser(String endPoint, UserProfile dataUser) {
        res = RequestAPIUserManagement.postCreateUser(endPoint, dataUser); //call API Post New User
        System.out.println(res.getBody().asString()); // logging response API

        return res.getBody().jsonPath().get("id");
    }

    public void hitAPIUpdateProfileUser(UserProfile dataUser, String idUser) {
        res = RequestAPIUserManagement.putUser(SetUpEndPoint.getURL(), dataUser, idUser); //call API Put User by Id
        System.out.println(res.getBody().asString()); // logging response API
    }

    public void checkResponseBodyCreateUser(UserProfile dataTestUser) throws ParseException {
        System.out.println("test logic for check response body create user");
        // please add code detail
        Gson json = new Gson();
        UserProfile actualData = json.fromJson(res.getBody().asString(), UserProfile.class);
        System.out.print("Actual Data : ");
        System.out.println(json.toJson(actualData));
        System.out.print("Test Data : ");
        System.out.println(json.toJson(dataTestUser));
        Assert.assertEquals(actualData.getTitle(), dataTestUser.getTitle());
        Assert.assertEquals(actualData.getFirstName(), dataTestUser.getFirstName());
        Assert.assertEquals(actualData.getLastName(), dataTestUser.getLastName());
        Assert.assertEquals(actualData.getPicture(), dataTestUser.getPicture());
        Assert.assertEquals(actualData.getGender(), dataTestUser.getGender());
        Assert.assertEquals(actualData.getEmail(), dataTestUser.getEmail());
        Assert.assertEquals(actualData.getDateOfBirth(), dataTestUser.getDateOfBirth());
        Assert.assertEquals(actualData.getPhone(), dataTestUser.getPhone());
        Assert.assertEquals(actualData.getLocation().getStreet(), dataTestUser.getLocation().getStreet());
        Assert.assertEquals(actualData.getLocation().getCity(), dataTestUser.getLocation().getCity());
        Assert.assertEquals(actualData.getLocation().getState(), dataTestUser.getLocation().getState());
        Assert.assertEquals(actualData.getLocation().getCountry(), dataTestUser.getLocation().getCountry());
        Assert.assertEquals(actualData.getLocation().getTimezone(), dataTestUser.getLocation().getTimezone());
        SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        Assert.assertEquals(sdfDate.format(sdfTime.parse(actualData.getRegisterDate())), sdfDate.format(new Date()));
        Assert.assertEquals(sdfDate.format(sdfTime.parse(actualData.getUpdatedDate())), sdfDate.format(new Date()));

    }

    public void checkResponseBodyUpdateProfileUser(UserProfile dataTestUser, String idUserUpdate) throws ParseException {
        System.out.println("test logic for check response body update user");
        // please add code detail
        Gson json = new Gson();
        UserProfile actualData = json.fromJson(res.getBody().asString(), UserProfile.class);
        System.out.print("Actual Data : ");
        System.out.println(json.toJson(actualData));
        System.out.print("Test Data : ");
        System.out.println(json.toJson(dataTestUser));
        Assert.assertEquals(actualData.getId(), idUserUpdate);
        Assert.assertEquals(actualData.getTitle(), dataTestUser.getTitle());
        Assert.assertEquals(actualData.getFirstName(), dataTestUser.getFirstName());
        Assert.assertEquals(actualData.getLastName(), dataTestUser.getLastName());
        Assert.assertEquals(actualData.getPicture(), dataTestUser.getPicture());
        Assert.assertEquals(actualData.getGender(), dataTestUser.getGender());
        Assert.assertEquals(actualData.getDateOfBirth(), dataTestUser.getDateOfBirth());
        Assert.assertEquals(actualData.getPhone(), dataTestUser.getPhone());
        Assert.assertEquals(actualData.getLocation().getStreet(), dataTestUser.getLocation().getStreet());
        Assert.assertEquals(actualData.getLocation().getCity(), dataTestUser.getLocation().getCity());
        Assert.assertEquals(actualData.getLocation().getState(), dataTestUser.getLocation().getState());
        Assert.assertEquals(actualData.getLocation().getCountry(), dataTestUser.getLocation().getCountry());
        Assert.assertEquals(actualData.getLocation().getTimezone(), dataTestUser.getLocation().getTimezone());
        SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        Assert.assertEquals(sdfDate.format(sdfTime.parse(actualData.getRegisterDate())), sdfDate.format(new Date ()));
        Assert.assertEquals(sdfDate.format(sdfTime.parse(actualData.getUpdatedDate())), sdfDate.format(new Date()));

    }

    public void hitAPIDeleteUserById(String idUser) {
        res = RequestAPIUserManagement.deleteUserById(SetUpEndPoint.getURL(), idUser);
        System.out.println("response after hit API delete");
        System.out.println(res.getBody().asString());
    }

    public void checkResponseBodyDeleteUser(String idUserDelete) {
        System.out.println("test logic for check response body delete user");
        // please add code detail
        System.out.println("id Delete:" + res.getBody());
        Assert.assertNotNull(res.getBody().jsonPath().get("id"));

    }
}
