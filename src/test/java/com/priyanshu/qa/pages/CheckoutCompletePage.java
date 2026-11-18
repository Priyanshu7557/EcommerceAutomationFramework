package com.priyanshu.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutCompletePage extends BasePage {
    private final By confirmationHeader = By.className("complete-header");
    private final By confirmationText = By.className("complete-text");
    private final By backHomeButton = By.id("back-to-products");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationHeader));
    }

    public String getConfirmationHeader() {
        return driver.findElement(confirmationHeader).getText();
    }

    public String getConfirmationText() {
        return driver.findElement(confirmationText).getText();
    }

    public InventoryPage backToProducts() {
        driver.findElement(backHomeButton).click();
        return new InventoryPage(driver);
    }
}
