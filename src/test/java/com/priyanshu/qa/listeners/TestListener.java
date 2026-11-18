package com.priyanshu.qa.listeners;

import com.priyanshu.qa.base.BaseTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = BaseTest.getDriver();
        if (driver == null) return;

        try {
            Path screenshotDir = Path.of("screenshots");
            Files.createDirectories(screenshotDir);
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String safeName = result.getName().replaceAll("[^a-zA-Z0-9_-]", "_");
            Path destination = screenshotDir.resolve(safeName + "_" + timestamp + ".png");
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Files.write(destination, screenshot);
            System.out.println("Failure screenshot saved: " + destination.toAbsolutePath());
        } catch (IOException | RuntimeException e) {
            System.err.println("Could not save failure screenshot: " + e.getMessage());
        }
    }
}
