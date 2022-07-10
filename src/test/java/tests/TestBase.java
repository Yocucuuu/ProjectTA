package tests;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.UUID;

public class TestBase  {

    //
    public static AppiumDriver driver;
    public static String sejarahKitaPackage = "com.uc.sejarahkita_mobile";
    public static String sejarahKitaAppPath = System.getProperty("user.dir")+"/apps/sejarahKita.apk";
    public static String chromeDriverPath = System.getProperty("user.dir")+"/apps/chromedriver.exe";
    public static String sejarahKitaCoveragePath = System.getProperty("user.dir")+"/apps/";

    public static void Android_Apidemos_Emulator_setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "7.1");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("deviceName", "emulator-5554");
        capabilities.setCapability("appPackage","io.appium.android.apis");
        capabilities.setCapability("appActivity",".ApiDemos");
        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
    }

    // Just open Bluestack and set Driver
    public static void Android_Emulator_setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "7.1");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("deviceName", "emulator-5554");
        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
    }


    public static void Android_SejarahKita_Emulator_setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "7.1");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("deviceName", "emulator-5554");
        capabilities.setCapability("app",sejarahKitaAppPath);
//        autoGrantPermissions
//        capabilities.setCapability("appPackage","com.uc.sejarahkita_mobile");
//        capabilities.setCapability("appActivity","com.uc.sejarahkita_mobile.view.MainActivity");
        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);


    }
    public static void Android_WebviewTest_Emulator_setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "7.1");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("deviceName", "emulator-5554");
        capabilities.setCapability("appPackage","com.snc.test.webview2");
        capabilities.setCapability("appActivity","com.snc.test.webview.activity.MainActivity");
        capabilities.setCapability("acceptInsecureCerts",true);
        capabilities.setCapability("chromeOptions", ImmutableMap.of("w3c", false));
        capabilities.setCapability("appium:chromedriverExecutable",chromeDriverPath);
        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
    }
    public static void Android_WebViewApp_Emulator_setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "7.1");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("deviceName", "emulator-5554");
//        capabilities.setCapability("appPackage","com.robotemplates.webviewapp");
//        capabilities.setCapability("appActivity","com.robotemplates.webviewapp.activity.MainActivity");
        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
        System.out.println(((AndroidDriver)driver).getCurrentPackage());
        System.out.println(((AndroidDriver)driver).currentActivity());
        ((AndroidDriver)driver).startActivity(new Activity("com.robotemplates.webviewapp", "com.robotemplates.webviewapp.activity.MainActivity"));

    }


//    public static void iOS_setUp() throws MalformedURLException {
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability("platformName", "iOS");
//        capabilities.setCapability("platformVersion", "14.4");
//        capabilities.setCapability("deviceName", "iPhone 11 Pro");
//        capabilities.setCapability("app",
//                System.getProperty("user.dir") + "/apps/DailyCheck.zip");
//        driver = new IOSDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
//    }

    public static void getServerLog() throws IOException {
        Map<String, Object> status = driver.getStatus();
        for (Map.Entry<String, Object> list : status.entrySet()) {
            System.out.println(list.getKey() + ":  " + list.getValue());
        }

        // get session capabilites
        Map<String, Object> caps = driver.getSessionDetails();
        for (Map.Entry<String, Object> entry : caps.entrySet()) {
            System.out.println(entry.getKey() + ":  " + entry.getValue());
        }

        // get devices Settingss
        Map<String, Object> settings = driver.getSettings();
        for (Map.Entry<String, Object> set : settings.entrySet()) {
            System.out.println(set.getKey() + ":  " + set.getValue());
        }
        String path_screenshot = System.getProperty("user.dir")+"/data/";
        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String filename= UUID.randomUUID().toString();
        File targetFile=new File(path_screenshot + filename +".jpg");
        FileUtils.copyFile(srcFile,targetFile);


    }
    public static void tearDown() {
        if (null != driver) {

            driver.quit();
        }
    }

}
