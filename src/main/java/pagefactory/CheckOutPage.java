package pagefactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;


public class CheckOutPage extends Util{
    //define and locate web element
    @FindBy(css = "span[id=\"summary-order-total\"]")
    private WebElement readOrderTotal;
    @FindBy(id = "add-to-cart-button")
    private WebElement addToCart;
    @FindBy(id = "id='order_email'")
    private WebElement emailAddress;
    @FindBy(id = "order_bill_address_attributes_firstname")
    private WebElement firstName;
    @FindBy(id = "order_bill_address_attributes_lastname")
    private WebElement lastName;
    @FindBy(id = "order_bill_address_attributes_address1")
    private WebElement addressOne;
    @FindBy(id = "order_bill_address_attributes_address2")
    private WebElement addressTwo;
    @FindBy(id = "order_bill_address_attributes_city")
    private WebElement city;
    @FindBy(id = "order_bill_address_attributes_state_id")
    private WebElement dropDownState;
    @FindBy(id = "order_bill_address_attributes_zipcode")
    private WebElement zipcode;
    @FindBy(id = "order_bill_address_attributes_country_id")
    private WebElement dropDownCountry;
    @FindBy(id = "order_bill_address_attributes_phone")
    private WebElement phone;
    @FindBy(css = "input[value='Save and Continue']")
    private WebElement saveNContinueButton;
    @FindBy(id = "save_user_address")
    private WebElement saveMyAddressChecked;
    @FindBy(id = "order_use_billing")
    private WebElement shippingAddressChecked;
    @FindBy(css = "tr[data-hook='item_total']")
    private WebElement itemTotal;
    @FindBy(id = "summary-order-total")
    private WebElement summaryOrderTotal;

    //shipping details-UPS Two Day
    @FindBy(css = "input[data-cost='$10.00']")
    private WebElement shippingDetailsChecked;
    @FindBy(css = "tr[class='total']")
    private WebElement northAmerica;
    @FindBy(css = "td[data-hook='shipping-total'")
    private WebElement shippingTotal;
    @FindBy(id = "summary-order-total")
    private WebElement orderSummary;
    @FindBy(id="save_user_address")
    private WebElement saveBillingAddress;


    //select payment method
    @FindBy(id = "order_payments_attributes__payment_method_id_3")
    private WebElement paymentMethodCheck;



    public CheckOutPage(WebDriver driver) {

        //will wait till all elements are found
        PageFactory.initElements(new AjaxElementLocatorFactory
                (driver, 5), this);
    }

    public String returnOrderTotalValue() {
        String orderTotal = readOrderTotal.getText();
        //assert on cart total should be equal to expected result
        String expectedTotal = "$47.97";
        //  Assert.assertEquals(orderTotal, expectedTotal, "Failed in comparison");
        // System.out.println("Expected Order Total is :"+expectedTotal);
        return orderTotal;
    }

    public void addBillingAddress()  {
        //add billing address detail
        firstName.sendKeys("Sonal");
        lastName.sendKeys("Singh");
        addressOne.sendKeys("Address 1, 607 Street");
        addressTwo.sendKeys("Address 2 790");
        city.sendKeys("San Franciso");

        //to select items from drop down of state
        WebElement dropdownState = dropDownState;
        Select selectState = new Select(dropdownState);
        selectState.selectByValue("2822");
        zipcode.sendKeys("23344");

        //to select items from drop down of state
        WebElement dropdownCountry = dropDownCountry;
        Select selectCountry = new Select(dropdownCountry);
        selectCountry.selectByValue("233");
        phone.sendKeys("2334444");
        saveBillingAddress.click();
        saveNContinueButton.click();
    }

    public void addShippingMethod() {
        //check shipping method
        shippingDetailsChecked.click();
        //verify selected shipping added to order total
        String expectedShippingValue = "$10.00";
        String actualShippingValue = shippingTotal.getText();
        Assert.assertEquals(actualShippingValue, expectedShippingValue, "Selected shipping not matched");

        //verify order summary after adding shipping
        String expectedOrderSummary = "$60.37";
        String actualOrderSummary = orderSummary.getText();
        Assert.assertEquals(actualOrderSummary, expectedOrderSummary, "Selected shipping not matched");

        //Save and Continue
        saveNContinueButton.click();
    }

    public void selectPaymentMethod() {
        //select payment method as check and place order
        paymentMethodCheck.click();
        saveNContinueButton.click();
    }

}
