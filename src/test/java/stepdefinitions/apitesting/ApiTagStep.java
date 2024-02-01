package stepdefinitions.apitesting;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import testlogic.apitesting.APIListTagTest;
import testlogic.apitesting.APITestProcessGeneric;

public class ApiTagStep {
    APIListTagTest apiTag;

    public ApiTagStep(){
        apiTag = new APIListTagTest();
    }

    @When("hit api get list tags")
    public void hitApiGetListTags() {
        apiTag.hitAPIGetLisTags();
    }

    @Then("validation response body get list tags")
    public void validationResponseBodyGetListTags() {
        System.out.println("validation response body list tag process");
        // please add code detail
        apiTag.checkResponseBodyListTags();
    }

    @Then("validation status code api tag is equals {int}")
    public void validation_status_code_is_equals(Integer statusCode) {
        // check status response same with param statusCode
        APITestProcessGeneric.validationStatusCode(apiTag.getRes(), statusCode);
    }
}
