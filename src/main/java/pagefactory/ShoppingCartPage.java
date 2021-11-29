package pagefactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class ShoppingCartPage {
    //define and locate web element
    @FindBy(id = "checkout-link")
    private WebElement checkout;

    public ShoppingCartPage(WebDriver driver) {
        //will wait till all elements are found
        PageFactory.initElements(new AjaxElementLocatorFactory
                (driver,2), this);
    }
    public void checkOut(){
        //click on checkout
        checkout.click();
    }
}
