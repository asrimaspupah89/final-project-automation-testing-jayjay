package testlogic.webtesting;

import helper.Utility;
import org.openqa.selenium.support.PageFactory;

public class CartPage {


    public CartPage(){
        PageFactory.initElements(Utility.getDriver(), this);
    }
}
