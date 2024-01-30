package testlogic.apitesting;

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
        Assert.assertEquals(expectedStatusCode, currentRes.getStatusCode());
    }
}
