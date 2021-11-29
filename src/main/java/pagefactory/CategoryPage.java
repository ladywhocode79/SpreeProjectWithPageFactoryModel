package pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class CategoryPage {

    //define and locate web element
    @FindBy(css = "a[href=\"/t/bags\"]")
    private WebElement selectCategoryBags;
    @FindBy(css = "span[title=\"Ruby on Rails Tote\"]")
    private WebElement selectProductRuby;
    @FindBy(id = "taxon")
    private WebElement selectCategoryFromDropDown;
    @FindBy(css = "input[value='Search']")
    private WebElement clickOnSearch;

    public CategoryPage(WebDriver driver){


        //will wait till all elements are found
        PageFactory.initElements(new AjaxElementLocatorFactory
                (driver,2), this);
    }

    public void searchNSelectInCategory(){
        //search in category and select first option from side menu
        selectCategoryBags.click();
    }
    public void selectFromProducList(){
        //select from category
        selectProductRuby.click();
    }
    public void selectCategoryFromDropDown(){
        //to select items from drop down and click on search
        WebElement dropdown = selectCategoryFromDropDown;
        Select select = new Select(dropdown);
        select.selectByValue("1");

        //find the search button anc click to search
        clickOnSearch.click();;

    }
}
