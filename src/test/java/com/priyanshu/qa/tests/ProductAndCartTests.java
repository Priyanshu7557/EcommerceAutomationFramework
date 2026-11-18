package com.priyanshu.qa.tests;

import com.priyanshu.qa.base.BaseTest;
import com.priyanshu.qa.config.ConfigReader;
import com.priyanshu.qa.pages.CartPage;
import com.priyanshu.qa.pages.InventoryPage;
import com.priyanshu.qa.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ProductAndCartTests extends BaseTest {

    @BeforeMethod
    public void loginBeforeTest() {
        new LoginPage(getDriver())
                .enterUsername(ConfigReader.get("default.username"))
                .enterPassword(ConfigReader.get("default.password"))
                .clickLogin();
    }

    @Test(description = "Products can be sorted by name from A to Z")
    public void sortProductsNameAscendingTest() {
        InventoryPage inventory = new InventoryPage(getDriver()).sortProductsBy("Name (A to Z)");
        List<String> names = inventory.getProductNames();

        Assert.assertEquals(names.get(0), "Sauce Labs Backpack");
        Assert.assertEquals(names.get(names.size() - 1), "Test.allTheThings() T-Shirt (Red)");
    }

    @Test(description = "A selected product can be added to and removed from the cart")
    public void addAndRemoveProductFromCartTest() {
        String product = "Sauce Labs Backpack";
        InventoryPage inventory = new InventoryPage(getDriver()).addProductToCart(product);

        Assert.assertEquals(inventory.getCartBadgeCount(), "1");

        CartPage cart = inventory.openCart();
        Assert.assertEquals(cart.getCartItemCount(), 1);
        Assert.assertTrue(cart.containsProduct(product));

        cart.removeProduct(product);
        Assert.assertEquals(cart.getCartItemCount(), 0);
    }

    @Test(description = "Multiple selected products are reflected in the cart")
    public void addMultipleProductsToCartTest() {
        InventoryPage inventory = new InventoryPage(getDriver())
                .addProductToCart("Sauce Labs Backpack")
                .addProductToCart("Sauce Labs Bike Light");

        Assert.assertEquals(inventory.getCartBadgeCount(), "2");
        CartPage cart = inventory.openCart();
        Assert.assertEquals(cart.getCartItemCount(), 2);
    }
}
