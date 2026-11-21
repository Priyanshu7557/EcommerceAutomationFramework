
package com.priyanshu.qa.base;

import com.priyanshu.qa.config.ConfigReader;
import com.priyanshu.qa.driver.DriverFactory;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.time.Duration;

public abstract class BaseTest {

    private static final ThreadLocal<WebDriver> DRIVER =
            new ThreadLocal<>();

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void setUp(@Optional("firefox") String browser) {

        System.out.println("STEP 1: setUp started");
        System.out.println("Browser received from TestNG: " + browser);

        System.setProperty("browser", browser);

        WebDriver driver = DriverFactory.createDriver();

        DRIVER.set(driver);

        System.out.println("STEP 2: Browser driver created");

        driver.manage().timeouts()
                .implicitlyWait(Duration.ZERO);

        driver.manage().timeouts()
                .pageLoadTimeout(Duration.ofSeconds(30));

        System.out.println("STEP 3: Opening SauceDemo");

        driver.get(ConfigReader.get("base.url"));

        System.out.println("STEP 4: Website opened successfully");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        WebDriver driver = DRIVER.get();

        if (driver != null) {
            try {
                driver.quit();
                System.out.println("Browser closed successfully");
            } finally {
                DRIVER.remove();
            }
        }
    }

    public static WebDriver getDriver() {
        return DRIVER.get();
    }
}
