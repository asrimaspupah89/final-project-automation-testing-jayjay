package stepdefinitions.apitesting;

import Model.apitesting.UserProfile;
import helper.SetUpEndPoint;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import testlogic.apitesting.APIUserTest;
import testlogic.apitesting.APITestProcessGeneric;

public class ApiUserStep {
    APIUserTest apiUser;
    UserProfile dataTestCreateUser, dataTestUpdateUser;

    public ApiUserStep(){
        apiUser = new APIUserTest();
    }

    @Given("prepare url for {string}")
    public void prepare_url_for(String endpoint) {
        // prepare end point API
        SetUpEndPoint.prepareURL(endpoint); // prepare end point
    }
    @Then("validation response json with JSONSchema {string}")
    public void validation_response_json_with_json_schema(String dataType) {
        // check response data using Json Schema format
        APITestProcessGeneric.validationResponseData(apiUser.getRes(), dataType);
    }
    @Then("validation status code is equals {int}")
    public void validation_status_code_is_equals(Integer statusCode) {
        // check status response same with param statusCode
        APITestProcessGeneric.validationStatusCode(apiUser.getRes(), statusCode);
    }

    /***************************** Step Operation For Validation API Get List Users ***************************** */
    @When("hit api get list users")
    public void hit_api_get_list_users() {
        // call API for get all data user
        apiUser.hitAPIGetListUsers();
    }
    @Then("validation response body get list users")
    public void validation_response_body_get_list_users() {
        // check response body not null and field gender filled with female or male, also field status filled with active or inactive
        apiUser.checkResponseBodyListUser();
    }

    /***************************** Step Operation For Validation API Get Profile User ***************************** */
    @When("hit api get profile user by id {string}")
    public void hitApiGetProfileUserById(String idUser) {
        apiUser.hitAPIGetProfileUser(idUser);
    }
    @Then("validation response body get profile user")
    public void validationResponseBodyGetProfileUser() {
        apiUser.checkResponseBodyProfileUser();
    }
    @Then("validation response body get profile user failed with message {string}")
    public void validationResponseBodyFailedWithMessage(String message) {
        apiUser.checkResponseBodyGetProfileUserFailed(message);
    }

    /***************************** Step Operation For Validation API Post Create User ***************************** */

    @When("hit api post create new user")
    public void hitApiPostCreateNewUser() {
        dataTestCreateUser = APITestProcessGeneric.prepareDataUserTestPost();
        apiUser.hitAPIPostNewUser(dataTestCreateUser);
    }
    @Then("validation response body post create new user")
    public void validationResponseBodyPostCreateNewUser() {
        apiUser.checkResponseBodyCreateUser(dataTestCreateUser);
    }

    @When("hit api update profile user by id {string}")
    public void hitApiUpdateProfileUserById(String idUser) {
        dataTestUpdateUser = APITestProcessGeneric.prepareDataUserTestUpdate();
        apiUser.hitAPIUpdateProfileUser(dataTestUpdateUser, idUser);
    }

    @Then("validation response body update user")
    public void validationResponseBodyUpdateUser() {
        System.out.println("step check response body update user");
        apiUser.checkResponseBodyUpdateProfileUser(dataTestCreateUser);
    }
}
