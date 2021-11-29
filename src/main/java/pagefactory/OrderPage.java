package pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;

public class OrderPage {
    //locate order placed successful message
    @FindBy(css = "div[class='alert alert-notice']")
    private WebElement orderConfirmationMessage;

    public OrderPage(WebDriver driver){
        //will wait till all elements are found
        PageFactory.initElements(new AjaxElementLocatorFactory
                (driver,2), this);
    }
    public void orderConfirmation(){
        //check for order successful message
        String expectedOrderConfirmationMessage ="Your order has been processed successfully";
        String actualOrderConfirmationMessage =orderConfirmationMessage.getText();
        Assert.assertEquals(actualOrderConfirmationMessage,expectedOrderConfirmationMessage,
                "Order confirmation message didn't matched");
        System.out.println("Login to checkout testcase passed");
    }
}
