package testlogic.apitesting.request;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RequestAPITagManagement {
    private static RequestSpecification request;

    private static void setUpHeader(){
        request = RestAssured.given()
                .header("Content-Type", "application/json") // set the header tp accept json
                .header("Accept", "application/json")
                .header("app-id", "65b903ce6f8cd0533e947f68");
    }
    public static Response getListTags(String endpoint) {
        setUpHeader(); // set up header
        return request.when().get(endpoint); // call API to get list user
    }
}
