package tests;

import PageObjects.SejarahKita.GameFragment;
import PageObjects.SejarahKita.LoginPage;
import PageObjects.WebviewTest.WebviewTest_Home;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.*;
import io.appium.java_client.android.connection.ConnectionState;
import io.appium.java_client.android.connection.ConnectionStateBuilder;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.KeyEventFlag;
import io.appium.java_client.android.nativekey.KeyEventMetaModifier;
import io.appium.java_client.clipboard.ClipboardContentType;
import io.appium.java_client.serverevents.CustomEvent;
import io.appium.java_client.serverevents.ServerEvents;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.html5.Location;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class justScrap extends TestBase{

    private static int MEMORY_USAGE_WAIT = 30000;
    private static int MEMORY_CAPTURE_WAIT = 10;
    private static String PKG = sejarahKitaPackage;
    private static String PERF_TYPE = "memoryinfo";
    private static String PSS_TYPE = "totalPss";
    @Test
    public void test() throws Exception {


        Android_Emulator_setUp();
        Map<String, Object> status = driver.getStatus();
        System.out.println("Status \n");
        for (Map.Entry<String, Object> list : status.entrySet()) {
            System.out.println(list.getKey() + ":  " + list.getValue() +"\n");
        }
        driver.quit();

        System.out.println("Status \n");
        for (Map.Entry<String, Object> list : status.entrySet()) {
            System.out.println(list.getKey() + ":  " + list.getValue() +"\n");
        }


//        Android_SamsungC9_setUp();
//        ((AndroidDriver)driver).toggleLocationServices();
//        driver.setLocation(new Location(-7.291255112033351, 112.75881824874966,0));
//
//        Location loc= driver.location();
//        System.out.println(loc.getLatitude());
//        System.out.println(loc.getLongitude());
//        System.out.println(loc.getAltitude());
//        WebviewTest_Home home = new WebviewTest_Home(driver);
//        home.click(home.gotoWeb);
//        home.click(home.inputUrl);
//        home.sendText(home.inputUrl, "https://sejarahkita.my.id/login");
//        home.click(home.buttonGo);
//        Set<String> contextNames = driver.getContextHandles();
//        contextNames.forEach((e) -> { System.out.println(e); });
//        driver.context("WEBVIEW_com.snc.test.webview2");
//        driver.manage().timeouts().implicitlyWait(11,TimeUnit.SECONDS);
//        driver.manage().timeouts().pageLoadTimeout(17,TimeUnit.SECONDS); //yay
//        driver.manage().timeouts().setScriptTimeout(12,TimeUnit.SECONDS);
////        ((JavascriptExecutor) driver).executeAsyncScript(
//                "fetch('https://sejarahkita.my.id/resetLogins')" +
//                ".then(response  => {});");



//        System.out.println(driver.getPageSource());
//        By byPass =  By.id("password");
//        By byEmail =  By.id("email");
//        By bySubmit =  By.xpath("/html/body/main/div/div[2]/div[1]/div[2]/button");
//
////        By bySubmit =  By.("submit");
//        WebDriverWait wait= new WebDriverWait(driver, 20);
//        ((JavascriptExecutor) driver).executeScript("var xhr = new XMLHttpRequest();\n" +
//                "xhr.open(\"GET\", 'https://sejarahkita.my.id/resetLogins', true);\n" +
//                "xhr.send();" );
////        wait.until(ExpectedConditions.elementToBeClickable(byEmail));
//        WebElement email = driver.findElement(byEmail);
//        email.sendKeys("johndoe@gmail.com");
//
////        wait.until(ExpectedConditions.presenceOfElementLocated(byPass));
//        WebElement password = driver.findElement(byPass);
//        password.sendKeys("12345678");
//
//        scrollDown();
//        WebElement submit = driver.findElement(bySubmit);
//        submit.click();
//

//        driver.quit();
//        new KeyEvent(AndroidKey.BACK).
//        ((AndroidDriver)driver).pressKey(new KeyEvent("aasasd"));
//        ((AndroidDriver)driver).pressKey(new KeyEvent("ayam").withMetaModifier(KeyEventMetaModifier.SHIFT_LEFT_ON));
//        ((AndroidDriver)driver).longPressKey(new KeyEvent(AndroidKey.BACK));


//
//        String str = "https://sejarahkita.my.id/";
//        URL url= new URL("http://localhost:4723/wd/hub");
//        byte[] base64 = Base64.encodeBase64(str.getBytes(StandardCharsets.UTF_8));
//
//
//        ((AndroidDriver)driver).setClipboard("lable",ClipboardContentType.PLAINTEXT , base64 );
//        String getClip = ((AndroidDriver)driver).getClipboard(ClipboardContentType.PLAINTEXT );
//        System.out.println(getClip);
//        byte[] byteArray2 = Base64.decodeBase64(getClip.getBytes());
//        System.out.println(new String(byteArray2));




//        ((AndroidDriver)driver).makeGsmCall("5551234567", GsmCallActions.ACCEPT);
//        LoginPage login = new LoginPage(driver);
//        ((AndroidDriver)driver).setClipboardText("vanthony@student.ciputra.ac.id");
//        String emailFromClipboard = ((AndroidDriver)driver).getClipboardText();
//        login.sendEmail(emailFromClipboard);
//        login.sendPassword("va123456");
//        login.tapLogin();
//        GameFragment game = new GameFragment(driver);
//         login.waitForVisibility(game.btnEasy);
//        scrollDown();
//        driver.setLocation(new Location(100.0, 122.0,0.0));
//        Location loc = driver.location();
//        System.out.println(loc.toString());
//        LoginPage loginPage = new LoginPage(driver);
//        loginPage.tapLogin();
//        ((AndroidDriver)driver).endTestCoverage( "android.intent.action.BOOT_COMPLETED" ,System.getProperty("user.dir")+"/data/endTest.ec" );
        //writeLog();


//        HashMap<String, String> data =  getPerformanceInfo((AndroidDriver) driver);
//        for (Map.Entry<String,String > set : data.entrySet()) {
//
//            // Printing all elements of a Map
//            System.out.println(set.getKey() + " = "
//                    + set.getValue());
//        }
//        try {
//            // get the usage at one point in time
//            int totalPss1 = getMemoryInfo((AndroidDriver) driver).get(PSS_TYPE);
//
//            // then get it again after waiting a while
//            try { Thread.sleep(MEMORY_USAGE_WAIT); }
//            catch (InterruptedException ign) {}
//            int totalPss2 = getMemoryInfo((AndroidDriver) driver).get(PSS_TYPE);
//
//            // finally, verify that we haven't increased usage more than 5%
//            System.out.println(totalPss1);
//            System.out.println(totalPss2);
//        } finally {
//            driver.quit();
//        }

    }






    /*JUST RUN IT ON IOS*/
    public void testIOS()  throws MalformedURLException {
        iOS_setUp();
        System.out.println("Ayam ");
    }

    @Test(enabled = false)
    public void testName() throws MalformedURLException, InterruptedException {
        Android_Emulator_setUp();
        boolean instaled = driver.isAppInstalled(sejarahKitaPackage);

        Map<String, Object> status = driver.getStatus();
        for (Map.Entry<String, Object> list : status.entrySet()) {
            System.out.println(list.getKey() + ":  " + list.getValue());
        }

        System.out.println("--------");
        Map<String, Object> caps = driver.getSessionDetails();
        for (Map.Entry<String, Object> entry : caps.entrySet()) {
            System.out.println(entry.getKey() + ":  " + entry.getValue());
        }
        System.out.println("--------");

        // get devices Settingss
        Map<String, Object> settings = ((AndroidDriver)driver).getSettings();
        for (Map.Entry<String, Object> set : settings.entrySet()) {
            System.out.println(set.getKey() + ":  " + set.getValue());
        }
        System.out.println("--------");

        System.out.println(((AndroidDriver)driver).getDeviceTime());
        System.out.println(((AndroidDriver)driver).getDisplayDensity());
        ((AndroidDriver)driver).fingerPrint(1);








        System.out.println("--------");

//        ((AndroidDriver)driver).lockDevice();
//        System.out.println( ((AndroidDriver)driver).isDeviceLocked());
//        Thread.sleep(3000);
//        ((AndroidDriver)driver).unlockDevice();
//        System.out.println( ((AndroidDriver)driver).isDeviceLocked());




//        ((AndroidDriver)driver).toggleAirplaneMode();
//        ((AndroidDriver)driver).toggleWifi();
//        ((AndroidDriver)driver).toggleData();
//        ((AndroidDriver)driver).toggleLocationServices();
//        ((AndroidDriver)driver).setPowerAC(PowerACState.OFF);;
//        ((AndroidDriver)driver).setPowerCapacity(10);;


        /*sumpa kasih testcase opo iki wkwkkw */
//        ((AndroidDriver)driver).sendSMS("0895631523006","hey"); // simulasi aku menerima SMS
//        ((AndroidDriver)driver).makeGsmCall("0895631523006", GsmCallActions.CALL);
//        ((AndroidDriver)driver).makeGsmCall("0895631523006", GsmCallActions.ACCEPT);
//        ((AndroidDriver)driver).makeGsmCall("0895631523006", GsmCallActions.CANCEL);
//        ((AndroidDriver)driver).setNetworkSpeed(NetworkSpeed.LTE);
//        ((AndroidDriver)driver).setGsmSignalStrength(GsmSignalStrength.GREAT);
//        ((AndroidDriver)driver).makeGsmCall("0895631523006", GsmCallActions.ACCEPT);

        ((AndroidDriver)driver).openNotifications();










        driver.quit();

    }

}
