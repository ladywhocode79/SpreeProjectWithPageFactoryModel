package org.example;



import org.testng.annotations.Test;
import pagefactory.*;

import java.util.concurrent.TimeUnit;

/**
 * Unit test for spree App.
 */
public class AppTest extends BaseTest
{


    public void useSideBarToSelectProduct(){
        System.out.println("Flow to select category from side menu");
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        CategoryPage categoryPage = new CategoryPage(driver);
        ProductDetailPage productDetailPage = new ProductDetailPage(driver);
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        CheckOutPage checkOutPage = new CheckOutPage(driver);

        //variables to store email and password
        String email = "simple12@dd.com";
        String password = "2345678";
        //click on login link on home page
        homePage.clickOnLogin();
        loginPage.loginToSpree(email,password);
        //verify the login successfull message
        homePage.verifyLoginSuccessful();

        //select category from side bar
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        categoryPage.searchNSelectInCategory();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //search product,select and click on product name link
        categoryPage.selectFromProducList();

        //product detail page update the quantity and click add to cart
        productDetailPage.addQuantityNAddToCart();

        //select total on checkout page and return total
        shoppingCartPage.checkOut();
        String orderTotal =checkOutPage.returnOrderTotalValue();
        System.out.println("Actual Order total : " +orderTotal);
    }


    //flow to select category from drop down list and search
    public void loginNAddProductToCart()
    {
        System.out.println("Flow to select category from drop down list and search");
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        CategoryPage categoryPage = new CategoryPage(driver);
        ProductDetailPage productDetailPage = new ProductDetailPage(driver);
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        CheckOutPage checkOutPage = new CheckOutPage(driver);

        //variables to store email and password
        String email = "simple12@dd.com";
        String password = "2345678";
        //click on login link on home page
        homePage.clickOnLogin();
        loginPage.loginToSpree(email,password);
        //verify the login successfull message
        homePage.verifyLoginSuccessful();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //select category from dropdown
        categoryPage.selectCategoryFromDropDown();

        //search product,select and click on product name link
        categoryPage.selectFromProducList();

        //product detail page update the quantity and click add to cart
        productDetailPage.addQuantityNAddToCart();

        //select total on checkout page and return total
        shoppingCartPage.checkOut();
        String orderTotal= checkOutPage.returnOrderTotalValue();
        //printing actual total
        System.out.println("Actual Order total : " +orderTotal);

    }

}

