package com.priyanshu.qa.tests;

import com.priyanshu.qa.base.BaseTest;
import com.priyanshu.qa.config.ConfigReader;
import com.priyanshu.qa.pages.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutTests extends BaseTest {

    @BeforeMethod
    public void loginAndAddProduct() {
        new LoginPage(getDriver())
                .enterUsername(ConfigReader.get("default.username"))
                .enterPassword(ConfigReader.get("default.password"))
                .clickLogin()
                .addProductToCart("Sauce Labs Backpack")
                .openCart();
    }

    @Test(description = "Customer can complete the checkout journey")
    public void completeCheckoutTest() {
        CheckoutInfoPage info = new CartPage(getDriver()).clickCheckout();
        CheckoutOverviewPage overview = info
                .enterCustomerDetails("Priyanshu", "Rai", "273001")
                .continueToOverview();

        Assert.assertTrue(overview.getItemTotal().contains("29.99"));
        Assert.assertTrue(overview.getTax().contains("$"));
        Assert.assertTrue(overview.getTotal().contains("$"));

        CheckoutCompletePage complete = overview.finishOrder();
        Assert.assertEquals(complete.getConfirmationHeader(), "Thank you for your order!");
        Assert.assertTrue(complete.getConfirmationText().contains("dispatched"));
    }

    @Test(description = "Checkout requires a first name")
    public void checkoutMissingFirstNameTest() {
        CheckoutInfoPage info = new CartPage(getDriver()).clickCheckout();
        String error = info.continueWithMissingDetailsAndGetError();

        Assert.assertTrue(error.contains("First Name is required"));
    }
}
