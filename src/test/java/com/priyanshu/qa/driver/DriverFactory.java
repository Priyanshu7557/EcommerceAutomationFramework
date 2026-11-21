
package com.priyanshu.qa.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.PageLoadStrategy;

public final class DriverFactory {

    private DriverFactory() {
        // Prevent object creation
    }

    public static WebDriver createDriver() {

        String browser = System.getProperty("browser", "firefox")
                .trim()
                .toLowerCase();

        boolean headless = Boolean.parseBoolean(
                System.getProperty("headless", "false")
        );

        System.out.println("Selected browser: " + browser);
        System.out.println("Headless mode: " + headless);

        switch (browser) {

            case "firefox": {

                System.out.println("STEP 1: Firefox browser selected");

                FirefoxOptions options = new FirefoxOptions();

                options.setBinary(
                        "/Applications/Firefox.app/Contents/MacOS/firefox"
                );

                options.setPageLoadStrategy(PageLoadStrategy.EAGER);

                if (headless) {
                    options.addArguments("-headless");
                }

                System.out.println("STEP 2: Firefox options configured");
                System.out.println("STEP 3: Creating FirefoxDriver...");

                WebDriver driver = new FirefoxDriver(options);

                System.out.println("STEP 4: Firefox launched successfully");

                return driver;
            }

            case "chrome": {

                System.out.println("STEP 1: Chrome browser selected");

                ChromeOptions options = new ChromeOptions();

                options.setPageLoadStrategy(PageLoadStrategy.EAGER);

                if (headless) {
                    options.addArguments("--headless=new");
                    options.addArguments("--window-size=1440,1000");
                }

                options.addArguments("--disable-notifications");

                System.out.println("STEP 2: Chrome options configured");
                System.out.println("STEP 3: Creating ChromeDriver...");

                WebDriver driver = new ChromeDriver(options);

                System.out.println("STEP 4: Chrome launched successfully");

                return driver;
            }

            default:
                throw new IllegalArgumentException(
                        "Unsupported browser: " + browser
                                + ". Please use firefox or chrome."
                );
        }
    }
}
