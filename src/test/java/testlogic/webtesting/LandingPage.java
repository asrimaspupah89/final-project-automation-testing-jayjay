package testlogic.webtesting;

import helper.Utility;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {

    public LandingPage(){
        PageFactory.initElements(Utility.getDriver(), this);
    }
}
