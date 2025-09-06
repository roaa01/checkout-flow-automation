package tests;

import config.ConfigManager;
import config.DriverManager;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SetupVerificationTest {

    @BeforeMethod
    public void setup() {
        DriverManager.setDriver(ConfigManager.getBrowser());
    }

    @Test
    public void verifySetup() {
        DriverManager.getDriver().get(ConfigManager.getBaseUrl());
        String title = DriverManager.getDriver().getTitle();
        System.out.println("Page Title: " + title);
        Assert.assertNotNull(title, "Page title should not be null");
    }

    @AfterMethod
    public void teardown() {
        DriverManager.quitDriver();
    }
}