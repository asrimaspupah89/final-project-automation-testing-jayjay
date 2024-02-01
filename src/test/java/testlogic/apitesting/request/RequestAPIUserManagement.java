package testlogic.apitesting.request;

import Model.apitesting.UserProfile;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

public class RequestAPIUserManagement {
    private static RequestSpecification request;

    private static void setUpHeader(){
        request = RestAssured.given()
                .header("Content-Type", "application/json") // set the header tp accept json
                .header("Accept", "application/json")
                .header("app-id", "65b903ce6f8cd0533e947f68");
    }

    public static Response getListUsers(String endpoint){
        setUpHeader(); // set up header
        return request.when().get(endpoint); // call API to get list user
    }

    public static Response getProfileUser(String url, String idUser) {
        setUpHeader(); // set up header
        String endpoint = url + idUser; // assign end point for get current user id
        System.out.println("Final Endpoint : " + endpoint);
        return request.when().get(endpoint); // call API to get profile user
    }

    public static Response postCreateUser(String endpoint, UserProfile dataUser) {
        // create data with json format
        Gson payload = new Gson();
        System.out.println("Gson :" + payload.toJson(dataUser));
        setUpHeader(); // set up header
        return  request.body(payload.toJson(dataUser)).when().post(endpoint); // call API create user
    }

    public static Response putUser(String url, UserProfile dataUserUpdate, String idUser) {
        String endpoint = url + idUser;
        Gson payload = new Gson();
        System.out.println("Gson :" + payload.toJson(dataUserUpdate));
        System.out.println(endpoint);
        setUpHeader(); // set up header
        return  request.body(payload.toJson(dataUserUpdate)).when().put(endpoint); // call API edit user
    }

    public static Response deleteUserById(String url, String idUser) {
        String endpoint = url + idUser;
        setUpHeader(); // set up header
        return request.when().delete(endpoint); // call API to get delete user by id
    }
}
