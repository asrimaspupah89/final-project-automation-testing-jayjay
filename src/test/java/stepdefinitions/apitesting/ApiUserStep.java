package stepdefinitions.apitesting;

import helper.SetUpEndPoint;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import testlogic.apitesting.APIUserTest;
import testlogic.apitesting.APITestProcessGeneric;

public class ApiUserStep {
    APIUserTest api;

    public ApiUserStep(){
        api = new APIUserTest();
    }

    @Given("prepare url for {string}")
    public void prepare_url_for(String endpoint) {
        // prepare end point API
        SetUpEndPoint.prepareURL(endpoint); // prepare end point
    }
    @When("hit api get list users")
    public void hit_api_get_list_users() {
        // call API for get all data user
        api.hitAPIGetListUsers();
    }
    @Then("validation status code is equals {int}")
    public void validation_status_code_is_equals(Integer statusCode) {
        // check status response same with param statusCode
        APITestProcessGeneric.validationStatusCode(api.getRes(), statusCode);
    }
    @Then("validation response body get list users")
    public void validation_response_body_get_list_users() {
        // check response body not null and field gender filled with female or male, also field status filled with active or inactive
        api.checkResponseBodyListUser();
    }
    @Then("validation response json with JSONSchema {string}")
    public void validation_response_json_with_json_schema(String dataType) {
        // check response data using Json Schema format
        APITestProcessGeneric.validationResponseData(api.getRes(), dataType);
    }
}
