package com.priyanshu.qa.tests;

import com.priyanshu.qa.base.BaseTest;
import com.priyanshu.qa.config.ConfigReader;
import com.priyanshu.qa.pages.InventoryPage;
import com.priyanshu.qa.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @Test(description = "Valid user can log in and see the product inventory")
    public void validLoginTest() {
        InventoryPage inventory = new LoginPage(getDriver())
                .enterUsername(ConfigReader.get("default.username"))
                .enterPassword(ConfigReader.get("default.password"))
                .clickLogin();

        Assert.assertEquals(inventory.getPageTitle(), "Products");
        Assert.assertEquals(inventory.getProductCount(), 6);
    }

    @Test(description = "Invalid password displays a useful login error")
    public void invalidPasswordTest() {
        LoginPage login = new LoginPage(getDriver())
                .loginExpectingError(ConfigReader.get("default.username"), "wrong_password");

        Assert.assertTrue(login.getErrorMessage().contains("Username and password do not match"),
                "Expected invalid-credentials error to be displayed");
        Assert.assertTrue(login.isLoginPageDisplayed());
    }

    @Test(description = "Locked out account cannot log in")
    public void lockedOutUserTest() {
        LoginPage login = new LoginPage(getDriver())
                .loginExpectingError("locked_out_user", ConfigReader.get("default.password"));

        Assert.assertTrue(login.getErrorMessage().contains("locked out"),
                "Expected locked-out account error");
    }
}
