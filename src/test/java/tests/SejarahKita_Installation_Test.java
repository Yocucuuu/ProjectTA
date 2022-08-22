package tests;


import PageObjects.SejarahKita.LoginPage;
import PageObjects.SejarahKita.ProfileFragment;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.appmanagement.BaseRemoveApplicationOptions;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.beans.Visibility;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class SejarahKita_Installation_Test extends TestBase {


    /*
    *
    *
    * */

    @BeforeTest
    public void beforeTest() throws IOException {
        Android_SamsungC9_setUp();
        resetLogin();
        boolean instaled = driver.isAppInstalled(sejarahKitaPackage);
        if(instaled){

            driver.removeApp(sejarahKitaPackage); // install ulang sejarah kita
        }
        System.out.println(driver.queryAppState(sejarahKitaPackage));
    }
    @AfterTest()
    public void tearDown() throws IOException {
        driver.quit();
    }

    @AfterMethod // setiap kali ada error pada test akan membuat screenshot untuk mengetahui dimana letak erornya
    public void afterMerthod(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            String time = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
            String newFold = System.getProperty("user.dir")+"/data/SejarahKita_Android_TestFail/";
            String path_screenshot = newFold;
            File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            String filename= result.getName()+time.toString();
            File targetFile=new File(path_screenshot + filename +".jpg");
            FileUtils.copyFile(srcFile,targetFile);
        }
    }
    @Test(priority = 1)
    public void installProgram(){

        SoftAssert softAssert = new SoftAssert();
        driver.installApp(sejarahKitaAppPath);
        softAssert.assertTrue(  driver.isAppInstalled(sejarahKitaPackage) );
        softAssert.assertEquals("NOT_RUNNING",  driver.queryAppState(sejarahKitaPackage).toString());


        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void launchApp() throws InterruptedException {

        SoftAssert softAssert = new SoftAssert();
        driver.activateApp(sejarahKitaPackage);
        LoginPage login = new LoginPage(driver);
        login.waitForVisibility(login.tvEmail);
        softAssert.assertTrue(login.tvEmail.isDisplayed());
        softAssert.assertEquals("RUNNING_IN_FOREGROUND",driver.queryAppState(sejarahKitaPackage).toString());
        softAssert.assertAll();
        softAssert.assertAll();

           /*
         If the app-under-test (AUT) is closed, or backgrounded,
         it will launch it. If the AUT is already open,
         it will background it and re-launch it.
         iOS tests with XCUITest can also use the mobile: launchApp method.
        * */


        /*Current the target app status. (Clients wrap the response properly)
        0 is not installed.
        1 is not running.
        2 is running in background or suspended.
        3 is running in background.
        4 is running in foreground. (number)*/



    }

    @Test(priority = 3)
    public void uninstallProgram() {
//        boolean instaled = driver.isAppInstalled(sejarahKitaPackage);
//        if(instaled){
//            driver.removeApp(sejarahKitaPackage);
//        }
//        Assert.assertFalse(  driver.isAppInstalled(sejarahKitaPackage) );
    }


    public void TerminateApp() {
        LoginPage login = new LoginPage(driver);
        login.waitForVisibility(login.tvEmail);
        driver.terminateApp(sejarahKitaPackage); // nutup aplikasi
        Assert.assertEquals("NOT_RUNNING",driver.queryAppState(sejarahKitaPackage).toString());
    }


    public void close() {
        driver.activateApp(sejarahKitaPackage);
        LoginPage login = new LoginPage(driver);
        login.waitForVisibility(login.tvEmail);
        driver.closeApp();
        System.out.println(driver.queryAppState(sejarahKitaPackage).toString());
    }


    public void resetApp() {
        driver.activateApp(sejarahKitaPackage);
        ((AndroidDriver)driver).resetApp();

    }


    public void terminateApp() {
        driver.activateApp(sejarahKitaPackage);
        driver.terminateApp(sejarahKitaPackage);
        System.out.println("Terminate : "+driver.queryAppState(sejarahKitaPackage));
    }


    public void runTobackgroun() {
        driver.activateApp(sejarahKitaPackage);
        driver.runAppInBackground(Duration.ofSeconds(10));
        System.out.println("Background : "+driver.queryAppState(sejarahKitaPackage));
    }



    public void endTestCoverage() {
        ((AndroidDriver)driver).endTestCoverage("Intent",sejarahKitaCoveragePath);
        ((AndroidDriver)driver).getBatteryInfo();
    }



}
