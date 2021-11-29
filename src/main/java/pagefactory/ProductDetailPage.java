package pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class ProductDetailPage {
    //define and locate web element
    @FindBy(xpath = "//*[@id='quantity']")
    private WebElement quantity;
    @FindBy(css = "span[title=\"Ruby on Rails Tote\"]")
    private WebElement clickOnProductName;
    @FindBy(id = "add-to-cart-button")
    private WebElement addToCart;

    public ProductDetailPage(WebDriver driver) {
        //will wait till all elements are found
        PageFactory.initElements(new AjaxElementLocatorFactory
                (driver,2), this);
    }
    public void addQuantityNAddToCart(){
        //try using xpath,
        quantity.clear();
        //update quantity
        quantity.sendKeys("3");
        //Add to cart
        addToCart.click();
    }
}
