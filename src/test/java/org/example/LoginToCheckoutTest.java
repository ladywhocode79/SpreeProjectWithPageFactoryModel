package org.example;

import org.testng.annotations.Test;
import pagefactory.*;

import java.util.concurrent.TimeUnit;

public class LoginToCheckoutTest extends BaseTest{
    @Test
    //flow to select category from drop down list and search
    public void loginNAddProductToCart() throws Exception {
        System.out.println("Flow to select category from drop down list and search");
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        CategoryPage categoryPage = new CategoryPage(driver);
        ProductDetailPage productDetailPage = new ProductDetailPage(driver);
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        OrderPage orderPage = new OrderPage(driver);

        //variables to store email and password
        String email = "abcss@ss.com";
        String password = "2345678";
        //click on login link on home page
        homePage.clickOnLogin();
        loginPage.loginToSpree(email,password);
        //verify the login successful message
        homePage.verifyLoginSuccessful();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //select category from dropdown
        categoryPage.selectCategoryFromDropDown();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //search product,select and click on product name link
        categoryPage.selectFromProducList();

        //product detail page update the quantity and click add to cart
        productDetailPage.addQuantityNAddToCart();

        //select total on checkout page and return total
        shoppingCartPage.checkOut();
        String orderTotal= checkOutPage.returnOrderTotalValue();
        //printing actual total
        System.out.println("Actual Order total : " +orderTotal);
        //add billing address
        checkOutPage.addBillingAddress();
        //select shipping value
        checkOutPage.addShippingMethod();
        //select payment method and place order
        checkOutPage.selectPaymentMethod();
        //verify order confirmation message
        orderPage.orderConfirmation();
    }
}
