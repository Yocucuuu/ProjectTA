package tests;

import PageObjects.PageBase;
import io.appium.java_client.AppiumDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Reviestic_iOS_Test extends TestBase {


    @BeforeClass
    public void beforeClass() {

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    @Test
    public void demo() {
    }
}
