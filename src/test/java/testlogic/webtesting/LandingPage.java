package testlogic.webtesting;

import helper.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class LandingPage {

    /*Web Element in Form Login*/
    @FindBy(id = "login2")
    private WebElement menuLogin;

    @FindBy(id = "loginusername")
    private WebElement userName;

    @FindBy(id = "loginpassword")
    private WebElement password;

    @FindBy(css = "button[onclick='logIn()']")
    private WebElement loginButton;

    @FindBy(xpath = "//div[@id='logInModal']//button[@type='button'][normalize-space()='Close']")
    private WebElement cancelLoginButton;

    @FindBy(id = "nameofuser")
    private WebElement welcomeUser;

    public LandingPage(){
        PageFactory.initElements(Utility.getDriver(), this);
    }
    public void menuLoginClicked(){
        menuLogin.click();
    }

    public void fillUsername(String value) {
        userName.sendKeys(value);
    }

    public void fillPassword(String value) {
        password.sendKeys(value);
    }

    public void buttonLoginClicked() {
        loginButton.click();
    }

    public void buttonCancelinFormLoginCllicked(){
        cancelLoginButton.click();
    }

    public void verifyLoginSuccessfull(String expectedWelcomingMessage){
        // wait element welcomeUser is displayed
        WebDriverWait wait = new WebDriverWait(Utility.getDriver(), Duration.ofSeconds(5));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser")));

        System.out.println("Actual welcoming message : " + welcomeUser.getText());

        //verify welcomming message displayed
        Assert.assertEquals(expectedWelcomingMessage, welcomeUser.getText()); // welcoming message is match
    }
}
