package stepdefinitions.webtesting;

import helper.Utility;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import testlogic.webtesting.LandingPage;

public class LandingOperationStep {
    LandingPage landingPage;

    public LandingOperationStep(){
        landingPage = new LandingPage();
    }

    @Given("user go to Product Store page {string}")
    public void navigateToWebApp(String url) throws InterruptedException {
        Utility.openPage(url); // navigate to url website
        Thread.sleep(2000);
    }

    @And("user click menu Log in")
    public void navigateToLoginForm() throws InterruptedException {
        landingPage.menuLoginClicked(); // open Form Login
        Thread.sleep(2000);
    }

    @When("user input username {string}")
    public void entryUsernameField(String username) throws InterruptedException {
        landingPage.fillUsername(username); // input user name
        Thread.sleep(2000);
    }

    @And("user input password {string}")
    public void entryPasswordField(String password) throws InterruptedException {
        landingPage.fillPassword(password); // input password
        Thread.sleep(2000);
    }

    @And("user click button login")
    public void clickButtonLogin() throws InterruptedException {
        landingPage.buttonLoginClicked(); // click button login
        Thread.sleep(2000);
    }

    @Then("User login successfully with welcome message {string}")
    public void loginSuccessfully(String welcomeMessage) throws InterruptedException {
        landingPage.verifyLoginSuccessfull(welcomeMessage);
        Thread.sleep(2000);
    }
}
