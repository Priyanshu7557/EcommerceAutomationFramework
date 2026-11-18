package com.priyanshu.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CartPage extends BasePage {
    private final By cartItems = By.className("cart_item");
    private final By checkoutButton = By.id("checkout");
    private final By continueShoppingButton = By.id("continue-shopping");

    public CartPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cart_contents_container")));
    }

    public int getCartItemCount() {
        return driver.findElements(cartItems).size();
    }

    public boolean containsProduct(String productName) {
        List<WebElement> items = driver.findElements(cartItems);
        return items.stream().anyMatch(item ->
                item.findElement(By.className("inventory_item_name")).getText().equals(productName));
    }

    public CartPage removeProduct(String productName) {
        for (WebElement item : driver.findElements(cartItems)) {
            if (item.findElement(By.className("inventory_item_name")).getText().equals(productName)) {
                item.findElement(By.tagName("button")).click();
                return this;
            }
        }
        throw new IllegalArgumentException("Cart item was not found: " + productName);
    }

    public CheckoutInfoPage clickCheckout() {
        driver.findElement(checkoutButton).click();
        return new CheckoutInfoPage(driver);
    }

    public InventoryPage continueShopping() {
        driver.findElement(continueShoppingButton).click();
        return new InventoryPage(driver);
    }
}
