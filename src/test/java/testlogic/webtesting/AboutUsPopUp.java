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

public class AboutUsPopUp {
    /* Web Element in About Us Pop Up */

    @FindBy(xpath = "//div[@id='videoModal']//button[@type='button'][normalize-space()='Close']")
    private WebElement closedVideo;

    /* Action in About Us Pop Up */
    public AboutUsPopUp(){
        PageFactory.initElements(Utility.getDriver(), this);
    }

    public void videoClicked() throws InterruptedException {
        // click one time to play video
        // click second time to pause video
        // please add code detail
        // videoElement.click();
        By byLocator = By.className("vjs-poster");
        new WebDriverWait(Utility.getDriver(), Duration.ofSeconds(5)).until(ExpectedConditions
                .presenceOfElementLocated(byLocator));
        WebElement findElement = Utility.getDriver().findElement(byLocator);
        findElement.click();
        Thread.sleep(5000);
    }

    public void buttonClosedVideoClicked(){
        closedVideo.click();
    }

    public void verifyVideoAlreadyPlaySuccessfully(){
        System.out.println("Verify Video Already Played Successfully");
        // video played
        By byLocator = By.tagName("video");
        new WebDriverWait(Utility.getDriver(), Duration.ofSeconds(5)).until(ExpectedConditions
                .presenceOfElementLocated(byLocator));
        WebElement findElement = Utility.getDriver().findElement(byLocator);
        System.out.println(findElement.getAttribute("src"));
        Assert.assertTrue(findElement.getAttribute("src").contains("blob"));
        // audio listening

        // please add code detail
    }
}