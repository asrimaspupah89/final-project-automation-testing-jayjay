package testlogic.apitesting;

import helper.SetUpEndPoint;
import io.restassured.response.Response;
import org.testng.Assert;
import testlogic.apitesting.request.RequestAPITagManagement;

public class APIListTagTest {
    private Response res;

    public Response getRes() {
        return res;
    }

    public void hitAPIGetLisTags() {
        res = RequestAPITagManagement.getListTags(SetUpEndPoint.getURL()); //call API Get List User
        System.out.println(res.getBody().asString()); // logging response API
    }

    public void checkResponseBodyListTags() {
        System.out.println("test logic for check response body get List user");
        // please add code detail
        Assert.assertNotNull(res.getBody().jsonPath().get("data"));
    }
}
