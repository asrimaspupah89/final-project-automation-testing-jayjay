package testlogic.webtesting;

import Model.webtesting.ItemProduct;
import Model.webtesting.Order;
import helper.Utility;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CartPage {
    /* Web Elemen in Product Page */

    @FindBy(id = "totalp")
    private WebElement totalPrice;

    /* Web Element in Form Cart */
    @FindBy(xpath = "//button[normalize-space()='Place Order']")
    private WebElement placeOrderButton;

    @FindBy(id = "name")
    private WebElement name;

    @FindBy(id = "country")
    private WebElement country;

    @FindBy(id = "city")
    private WebElement city;

    @FindBy(id = "card")
    private WebElement creditCard;

    @FindBy(id = "month")
    private WebElement month;

    @FindBy(id = "year")
    private WebElement year;

    @FindBy(css = "button[onclick='purchaseOrder()']")
    private WebElement purchasedButton;

    @FindBy(xpath = "//div[@id='orderModal']//button[@type='button'][normalize-space()='Close']")
    private WebElement cancelOrderButton;

    /* Web Element Information Message When Success Order */

    @FindBy(css = "body > div:nth-child(19) > h2:nth-child(6)")
    private WebElement informationHeader;

    @FindBy(xpath = "//p[contains(@class,'lead text-muted')]")
    private WebElement InformationOrder;

    @FindBy(xpath = "//div[@id='orderModal']//button[@type='button'][normalize-space()='Close']")
    private WebElement okButton;

    @FindBy(id = "tbodyid")
    private WebElement tableOrder;

    public CartPage(){
        PageFactory.initElements(Utility.getDriver(), this);
    }

    public void buttonPlacerOrderClicked() {
        placeOrderButton.click();
    }

    public void fillName(String value) {
        name.sendKeys(value);
    }

    public void fillCountry(String value) {
        country.sendKeys(value);
    }

    public void fillCity(String value) {
        city.sendKeys(value);
    }

    public void fillCreditCard(String value) {
        creditCard.sendKeys(value);
    }

    public void fillMonth(String value) {
        month.sendKeys(value);
    }

    public void fillYear(String value) {
        year.sendKeys(value);
    }

    public void buttonPurchasedClicked() {
        purchasedButton.click();
    }

    public void verifyOrderSuccessfully(int exoectedTotalPrice, String nameCustomer, String crdCard){
        String expectedHeaderInformation = "Thank you for your purchase!";
        Assert.assertEquals(informationHeader.getText(), expectedHeaderInformation);

        // get current date
        DateFormat dform = new SimpleDateFormat("dd/MM/yyyy");
        Date obj = new Date();
        String currentDate = dform.format(obj);

        // verify information message with:
        //Id: 811467
        //Amount: 0 USD
        //Card Number: 55555
        //Name: Asri Maspupah
        //Date: 31/0/2024

        String expectedAmount = "Amount: " + exoectedTotalPrice + " USD";
        String expectedCardNumber = "Card Number: " + crdCard;
        String expectedName = "Name: " + nameCustomer;
        String expectedDate = "Date: " + currentDate;

        // get actual from web element InformationOrder
        String[] dataOrder = InformationOrder.getText().split("\\r?\\n|\\r");
//        for(int i=0; i<dataOrder.length; i++){
//            System.out.println("Line "+ i);
//            System.out.println(dataOrder[i]);
//        }
        String[] actualID = dataOrder[0].split(":"); // please add code detail
        String actualAmount = dataOrder[1]; // please add code detail
        String actualNumber = dataOrder[2]; // please add code detail
        String actualName = dataOrder[3]; // please add code detail
        String actualDate = dataOrder[4];  // please add code detail

        //verify information displayed
        Assert.assertEquals(actualID[0].trim(),"Id");
        Assert.assertTrue(actualID[1].trim().matches("[0-9]+"));
        Assert.assertEquals(actualAmount, expectedAmount);
        Assert.assertEquals(actualNumber, expectedCardNumber);
        Assert.assertEquals(actualName, expectedName);
        Assert.assertEquals(actualDate, expectedDate);
    }

    public void verifyAddItemToCartPageSuccessfully(Order dataTestOrder) {
        // calculate total price
        dataTestOrder.calculateTotalPrice();
        System.out.println("Total Harga " + dataTestOrder.getTotalPrice());
        /* check kesamaan data grid produc dengan variable data Test Order
         *   1. sama jumlah datanya
         *   2. sama data ordernya : tittle, price
         *   3. sama harga totalnya
         */

        //verify data order displayed
        System.out.println(tableOrder.getText());
        String[] actualProduct = tableOrder.getText().split("\\r?\\n|\\r");

        // please add code detail for check data order

        int actualTotalPrice = 0;
        if (!totalPrice.getText().isBlank()){
            actualTotalPrice = Integer.parseInt(totalPrice.getText());
        }
        Assert.assertEquals(actualTotalPrice, dataTestOrder.getTotalPrice());
        Assert.assertEquals(actualProduct.length,dataTestOrder.getDataOrdered().size());
        for(int i =0; i< dataTestOrder.getDataOrdered().size(); i++){
            ItemProduct itemProduct = dataTestOrder.getDataOrdered().get(i);
            String actualDataProduct = actualProduct[i].replace("Delete","");
            String expectedDataProduct = itemProduct.getName()+" "+itemProduct.getPrice();
            Assert.assertEquals(actualDataProduct.trim(), expectedDataProduct.trim());
        }
    }
}
