# E-commerce End-to-End Automation Framework

A portfolio-ready UI automation project using **Java, Selenium WebDriver, TestNG, Maven and Page Object Model (POM)**. The framework tests the public SauceDemo demo shop: https://www.saucedemo.com/

> This is a learning/portfolio project against a public demo application. Do not use it for real purchases or against a site without permission.

## What is covered

- Valid login, invalid password and locked-out user scenarios
- Product inventory and product count validation
- Product sorting (A to Z)
- Add one or multiple products to cart
- Remove products from cart
- End-to-end checkout and order confirmation
- Checkout validation for missing first name
- Explicit waits and reusable page objects
- Failure screenshots through a TestNG listener
- Chrome and Firefox browser selection
- Headless execution for CI
- Jenkins pipeline example

## Technology

- Java 26 (the project is configured to compile for Java 26)
- Selenium 4.35.0
- TestNG 7.11.0
- Maven
- Chrome or Firefox
- Jenkins (optional CI example)

Selenium Manager, included with Selenium 4, automatically manages the browser driver in standard local setups. You normally do not need to download ChromeDriver manually.

## Project structure

```text
EcommerceAutomationFramework/
├── pom.xml
├── testng.xml
├── Jenkinsfile
├── README.md
└── src
    └── test
        ├── java/com/priyanshu/qa
        │   ├── base/BaseTest.java
        │   ├── config/ConfigReader.java
        │   ├── driver/DriverFactory.java
        │   ├── listeners/TestListener.java
        │   ├── pages/
        │   │   ├── BasePage.java
        │   │   ├── LoginPage.java
        │   │   ├── InventoryPage.java
        │   │   ├── CartPage.java
        │   │   ├── CheckoutInfoPage.java
        │   │   ├── CheckoutOverviewPage.java
        │   │   └── CheckoutCompletePage.java
        │   └── tests/
        │       ├── LoginTests.java
        │       ├── ProductAndCartTests.java
        │       └── CheckoutTests.java
        └── resources/config.properties
```

## Requirements

1. Install JDK 26 and verify:

   ```bash
   java -version
   javac -version
   ```

2. Install Maven and verify:

   ```bash
   mvn -version
   ```

3. Install Google Chrome (or Firefox).
4. Ensure the computer has internet access on the first Maven dependency download and when Selenium Manager needs browser-driver metadata.


## Setup and Installation

### 1. Clone the repository

```bash
git clone https://github.com/Priyanshu7557/EcommerceAutomationFramework.git
```

### 2. Open in IntelliJ IDEA

- Open IntelliJ IDEA.
- Select File → Open.
- Choose the cloned project folder.
- Load the Maven project.
- Configure JDK 26.

### 3. Install dependencies

Open the IntelliJ terminal and run:

```bash
mvn clean install
```

### Run from IntelliJ terminal

Run the full suite:

```bash
mvn clean test
```

Run with Chrome in headless mode:

```bash
mvn clean test -Dbrowser=chrome -Dheadless=true
```

Run with Firefox:

```bash
mvn clean test -Dbrowser=firefox
```

Run one test class:

```bash
mvn -Dtest=LoginTests test
```

Run one test method:

```bash
mvn -Dtest=LoginTests#validLoginTest test
```

If IntelliJ's Maven runner does not pass a property as expected, use the terminal command above.

## Demo application test credentials

SauceDemo provides public demo accounts. This framework uses:

- Standard user: `standard_user`
- Password: `secret_sauce`
- Locked account test: `locked_out_user`

These are public demo credentials, not personal credentials. The test data is in `src/test/resources/config.properties`.

## Reports and screenshots

- Maven Surefire XML and text reports: `target/surefire-reports/`
- TestNG output: `test-output/` may be generated when running the suite directly from TestNG in the IDE.
- Failure screenshots: `screenshots/`

The screenshot listener saves a PNG when a test fails and the browser is still available.

## How the framework works

1. `BaseTest` starts the browser before each test and closes it afterward.
2. `DriverFactory` creates Chrome or Firefox. Selenium Manager resolves the driver.
3. Page classes keep locators and browser actions separate from test assertions.
4. Test classes describe user journeys and use TestNG assertions.
5. `TestListener` captures screenshots on failures.
6. Maven/Surefire launches the TestNG suite.

## Important design notes

- Page Object Model keeps selectors and page behavior in page classes, which makes maintenance easier.
- Explicit waits are used for elements that need to become visible/clickable. Implicit wait is set to zero to avoid mixing wait strategies.
- Each test starts with a fresh browser session, helping test isolation.
- This starter project validates UI behavior. It does **not** claim API or SQL integration; add those only after connecting to a suitable test API/database and writing real checks.
- The included Jenkinsfile assumes Jenkins has tools configured with names `JDK26` and `Maven3`, and a Chrome browser available on the agent. Adapt the agent/tool names to your Jenkins setup.

## Suggested next improvements

1. Add a TestNG `DataProvider` for multiple login datasets.
2. Add API checks with REST Assured against a permitted demo API.
3. Add database checks only when a test database is available.
4. Add Allure or Extent Reports after the basic suite is stable.
5. Add retry logic only for genuinely transient failures; do not hide real defects.
6. Add GitHub Actions workflow and badges.
7. Add test case IDs and defect links to the README.

## GitHub upload

From the project root:

```bash
git init
git add .
git commit -m "Initial e-commerce automation framework"
git branch -M main
git remote add origin https://github.com/YOUR_USERNAME/ecommerce-automation-framework.git
git push -u origin main
```

Replace `YOUR_USERNAME` with your GitHub username. Do not commit passwords, access tokens, or private test data.
