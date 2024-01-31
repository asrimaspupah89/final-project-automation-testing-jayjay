package testlogic.webtesting;

import helper.Utility;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FormLogin {
    /* Web Element in Form Login */

    @FindBy(id = "loginusername")
    private WebElement userName;

    @FindBy(id = "loginpassword")
    private WebElement password;

    @FindBy(css = "button[onclick='logIn()']")
    private WebElement loginButton;

    @FindBy(xpath = "//div[@id='logInModal']//button[@type='button'][normalize-space()='Close']")
    private WebElement cancelLoginButton;

    /* Action in Form Login */
    public FormLogin(){
        PageFactory.initElements(Utility.getDriver(), this);
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

    public void buttonCancelLoginClicked(){
        cancelLoginButton.click();
    }
}
