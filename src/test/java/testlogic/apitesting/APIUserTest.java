package testlogic.apitesting;

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
    public void checkResponseBodyListUser(){
        System.out.println("validation response body process");
        JSONArray data = new JSONArray(res.getBody().jsonPath().getList("data"));
        for(int i=0; i<data.length(); i++){
            JSONObject user = (JSONObject) data.get(i);
            Assert.assertNotNull(user.get("id"));
            assertThat(user.get("title")).isIn("mr", "ms", "mrs", "miss", "dr", "");
            Assert.assertNotNull(user.get("firstName"));
            Assert.assertNotNull(user.get("lastName"));
        }
    }
}
