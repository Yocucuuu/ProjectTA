package tests;


import PageObjects.SejarahKita.LoginPage;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class SejarahKita_Installation_Test extends TestBase {


    /*
    *
    *
    * */

    @BeforeTest
    public void beforeTest() throws MalformedURLException {
        Android_Emulator_setUp();
    }


    @Test(priority = 1)
    public void uninstallProgram() {
        boolean instaled = driver.isAppInstalled(sejarahKitaPackage);
        if(instaled){
             driver.removeApp(sejarahKitaPackage); // install ulang sejarah kita
        }
        Assert.assertFalse(  driver.isAppInstalled(sejarahKitaPackage) );
    }

    @Test(priority = 2)
    public void installProgram(){
        boolean instaled = driver.isAppInstalled(sejarahKitaPackage);
        if(!instaled){
            driver.installApp(sejarahKitaAppPath); // install ulang sejarah kita
        }
        Assert.assertTrue(  driver.isAppInstalled(sejarahKitaPackage) );
    }

    @Test(priority = 3)
    public void launchApp() throws InterruptedException {
        /*
         If the app-under-test (AUT) is closed, or backgrounded,
         it will launch it. If the AUT is already open,
         it will background it and re-launch it.
         iOS tests with XCUITest can also use the mobile: launchApp method.
        * */

        SoftAssert softAssert = new SoftAssert();
        driver.activateApp(sejarahKitaPackage);
        LoginPage login = new LoginPage(driver);
        softAssert.assertTrue(login.logoLogin.isDisplayed());
        softAssert.assertEquals("RUNNING_IN_FOREGROUND",driver.queryAppState(sejarahKitaPackage).toString());
        softAssert.assertAll();

        /*driver.activateApp(sejarahKitaPackage);
        driver.resetApp();
        driver.runAppInBackground(Duration.ofSeconds(10));
        driver.queryAppState(sejarahKitaPackage);
        Map<String, String> appStrings = driver.getAppStringMap("en", sejarahKitaAppPath);
        driver.launchApp();
        driver.removeApp(sejarahKitaPackage);*/
    }

    @Test(priority = 4)
    public void TerminateApp() {
        LoginPage login = new LoginPage(driver);
        login.waitForVisibility(login.tvEmail);
        driver.terminateApp(sejarahKitaPackage); // nutup aplikasi
        Assert.assertEquals("NOT_RUNNING",driver.queryAppState(sejarahKitaPackage).toString());
    }

    @Test(priority = 6)
    public void close() {
        driver.activateApp(sejarahKitaPackage);
        LoginPage login = new LoginPage(driver);
        login.waitForVisibility(login.tvEmail);
        driver.closeApp();
        System.out.println(driver.queryAppState(sejarahKitaPackage).toString());
    }

    @Test(priority = 5)
    public void resetApp() {
        driver.activateApp(sejarahKitaPackage);
        driver.resetApp();

    }

    @Test
    public void terminateApp() {
        driver.activateApp(sejarahKitaPackage);
        driver.terminateApp(sejarahKitaPackage);
        System.out.println("Terminate : "+driver.queryAppState(sejarahKitaPackage));
    }

    @Test
    public void runTobackgroun() {
        driver.activateApp(sejarahKitaPackage);
        driver.runAppInBackground(Duration.ofSeconds(10));
        System.out.println("Background : "+driver.queryAppState(sejarahKitaPackage));
    }


    @Test
    public void endTestCoverage() {
        ((AndroidDriver)driver).endTestCoverage("Intent",sejarahKitaCoveragePath);
    }



}
