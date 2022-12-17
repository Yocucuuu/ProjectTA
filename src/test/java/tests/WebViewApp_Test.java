package tests;

import PageObjects.WebviewApp.WebviewAppHome;
import org.testng.ITestResult;
import PageObjects.WebviewApp.WebviewApp_Pagebase;
import PageObjects.WebviewApp.Webview_Envato;
import PageObjects.WebviewApp.Webview_eClass;
import PageObjects.WebviewTest.MediaPage;
import PageObjects.WebviewTest.WebviewTest_Home;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.html5.Location;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.*;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class WebViewApp_Test extends TestBase{


    @BeforeClass
    public void setup() throws MalformedURLException {
        Android_WebViewApp_C9_setUp();
//        Android_Emulator_setUp();
        driver.activateApp("com.robotemplates.webviewapp");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }


    @AfterMethod
    public void afterMethod(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            System.out.println(driver.getPageSource());
            String time = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
            String newFold = System.getProperty("user.dir")+"/data/WebviewAppp_Testfail/";
            String path_screenshot = newFold;
            File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            String filename= result.getName()+time.toString();
            File targetFile=new File(path_screenshot + filename +".jpg");
            FileUtils.copyFile(srcFile,targetFile);
        }
        driver.resetApp();
    }
    
    @AfterClass
    public void afterClass() throws Exception {
//        writeLog();
//        driver.quit();
    }

    WebviewApp_Pagebase pagebase;
    WebviewAppHome homePage;
    Webview_Envato envato;

    // samsung c9 or bluestack
    @Test(priority = 0 )
    public void openEachPage() throws InterruptedException {

        pagebase = new WebviewApp_Pagebase(driver);
        SoftAssert softAssert = new SoftAssert();
        pagebase.toHome();
        String val ;
        val = pagebase.getAttribute(pagebase.tv_nav, "text");
        softAssert.assertEquals("Home",val);

        pagebase.toFeature();
        val =pagebase.getAttribute(pagebase.tv_nav, "text");
        softAssert.assertEquals("Features",val);

        pagebase.toAboutUs();
        softAssert.assertEquals("About Us",pagebase.getAttribute(pagebase.tv_nav, "text"));
        pagebase.toSupport();
        softAssert.assertEquals("Support",pagebase.getAttribute(pagebase.tv_nav, "text"));
        pagebase.toOurProduct();
        softAssert.assertEquals("Our Products",pagebase.getAttribute(pagebase.tv_nav, "text"));
        pagebase.toContact();
        softAssert.assertEquals("Contact",pagebase.getAttribute(pagebase.tv_nav, "text"));
        pagebase.toPrivacyPolicy();
        softAssert.assertEquals("Privacy Policy",pagebase.getAttribute(pagebase.tv_nav, "text"));

        softAssert.assertAll();
    }

    // bluestak bahasa indonesia
    @Test(priority = 1, enabled = true)
    public void uploadFile() throws IOException, InterruptedException {
        /* lokasi remote path harus filenya , lokasi filenya lengkap sama filenya   */
        ((AndroidDriver)driver).pushFile("/sdcard/DCIM/SharedFolder/gambar.png",
                new File(System.getProperty("user.dir")+"/data/downloadedFile/logoAppium.png"));
        WebviewAppHome home = new WebviewAppHome(driver);
        home.toHome();
        driver.findElement(By.xpath("(//android.view.View[@content-desc=\"WEBVIEW APP PAGE\"])[1]/android.widget.TextView"));
        ((AndroidDriver<?>) driver).findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"TRY DOWNLOAD\").instance(0))");

        home.toUploadFile();

        driver.findElement(By.xpath("//android.view.View[@content-desc=\"Postimage\"]"));
        scrollDown();
        home.tapUploadFile();
        home.uploadFromFile();

        MediaPage media = new MediaPage(driver);
        media.openLeftMenuTray();
        media.selectImageMenu();
        media.selectFolderImage();
        media.tapTargetImage();

        driver.findElement(By.xpath("//android.view.View[@content-desc=\"Unggah gambar lain\"]/android.widget.TextView"));


    }

    // bluestak bahasa indonesia
    @Test(priority = 2 ,enabled = true)
    public void downloadHappycat() throws IOException, InterruptedException {


        try{
            File file = new File(System.getProperty("user.dir")+"/data/downloadedFile/happycats.mp3");
            file.delete();
        }catch (Exception ex){}

        WebviewAppHome home = new WebviewAppHome(driver);
        home.toHome();
        driver.findElement(By.xpath("(//android.view.View[@content-desc=\"WEBVIEW APP PAGE\"])[1]/android.widget.TextView"));
        ((AndroidDriver<?>) driver).findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"TRY DOWNLOAD\").instance(0))");

        home.tapDownloadFile();

        Thread.sleep(10_000);
        /* in this moment dah kedownload */
        byte[] fileBase64 = ((AndroidDriver)driver).pullFile("/storage/emulated/0/download/happycats.mp3");
        OutputStream os = new FileOutputStream(System.getProperty("user.dir")+
                "/data/downloadedFile/happycats.mp3");
        // Starting writing the bytes in it
        os.write(fileBase64);
        // Close the file connectionsz
        os.close();
        File file = new File(System.getProperty("user.dir")+"/data/downloadedFile/happycats.mp3");
        Assert.assertTrue(file.exists());


    }

    // bluestak bahasa indonesia
    @Test(priority = 3, enabled = true)
    public void downloadFolder() throws IOException, InterruptedException {

        byte[] fileBase64 = ((AndroidDriver)driver).pullFolder("/sdcard/DCIM/SharedFolder/");
        //dapat berupa zipnya . jadi file yang disimpen zipnya
        OutputStream os = new FileOutputStream(System.getProperty("user.dir")+"/data/downloadedFile/downloadFolder.zip");
        os.write(fileBase64);
        os.close();
        File file = new File(System.getProperty("user.dir")+"/data/downloadedFile/downloadFolder.zip");
        Assert.assertTrue(file.exists());
    }

    // samsung c9 , mari berdoa internet nde U101 lancar teman"
    @Test(priority = 4,  enabled = true)
    public void toWebviewAppEnvato() throws InterruptedException {

        SoftAssert softAssert =new SoftAssert();
        homePage = new WebviewAppHome(driver);
        homePage.toHome();
        homePage.toWebView();

        envato =  new Webview_Envato(driver);
        Thread.sleep(5000);
        envato.openProfileTray();
        envato.clickBtnSignIn();

        envato.fillUsername("yoshuadwi2000@gmail.com");
        envato.fillPassword("Yoshuadwi06");
        envato.submitLogin();
        envato.openProfileTrayAfterLogin();
        softAssert.assertTrue(envato.getLoggedUser().contains("Yoshuadwi2000"));

        envato.openCart();

        Thread.sleep(2000);
        (new TouchAction(driver)).tap(PointOption.point(1018,255)).perform();
        envato.logout();

        envato.openProfileTray();
        envato.clickBtnSignIn();
        envato.fillUsername("yoshuadwi2000@gmail.com");
        envato.fillPassword("Yoshuadwi06");
        envato.submitLogin();

        softAssert.assertAll();




    }

    // samsung c9
    @Test(priority = 5 ,enabled = true)
    public void WebviewApp_openEclass() throws InterruptedException {

        SoftAssert softAssert =new SoftAssert();
        homePage = new WebviewAppHome(driver);
        homePage.toHome();
        driver.findElement(MobileBy.AccessibilityId("WEBVIEW APP PAGE"));
        ((AndroidDriver<?>) driver).findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)." +
                "instance(0)).scrollIntoView(new UiSelector().textContains(\"VISIT GOOGLE\").instance(0))");

        homePage.toGoogle();
       driver.findElement(By.className("android.widget.EditText")).sendKeys("");

       int size=  driver.findElements(By.className("android.widget.EditText")).size();
        List<WebElement> listElement =  driver.findElements(By.className("android.widget.EditText"));
        listElement.get(0).click();
        listElement.get(0).sendKeys("eclass istts");
        ((AndroidDriver)driver).pressKey(new KeyEvent(AndroidKey.SEARCH));
        ((AndroidDriver)driver).pressKey(new KeyEvent(AndroidKey.NUMPAD_ENTER));
//        driver.findElement(MobileBy.AccessibilityId("https://eclass.istts.ac.id")).click();
        ((AndroidDriver<?>) driver).findElementByAndroidUIAutomator("new UiSelector().textContains(\"eclass.istts.ac.id\")").click();

        Webview_eClass eclas = new Webview_eClass(driver);
        eclas.sendEmail("218116775");
        eclas.sendPassword("123456789*joshua");
        eclas.clickSubmit();
        Thread.sleep(10000);

        Boolean isYoshua =driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"Yoshua Dwi Santoso\")")).isDisplayed();
        softAssert.assertTrue(isYoshua);

        // jok lali assert e
        driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"toggleMenu\")")).click();
        driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"Sign Out\")")).click();

//        Thread.sleep(5000);
    }

    // samsung
    // kok ke reverse geo e ga kescroll
    @Test(priority = 6,enabled = true)
    public void testGeolocation() throws InterruptedException {
        ((AndroidDriver)driver).toggleLocationServices();
        driver.setLocation(new Location(-7.291276687367432 , 112.75882812373266,0));

//        driver.activateApp(webViewAppPackage);
        homePage = new WebviewAppHome(driver);
        homePage.toHome();
        homePage.toTryGeolocation((AndroidDriver)driver);

        driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
        driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"HTML\")"));

        ((AndroidDriver<?>) driver).findElementByAndroidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"watchPosition\").instance(0))");
        System.out.println(homePage.getCheckedGeoCapture().getClass().getSimpleName());
        if(homePage.getCheckedGeoCapture().equals("true")){
            homePage.clickCbCaptureGeo(); // to uncheck
            homePage.clickBtnUpdateGeo();
            System.out.println("Cb geo is turned off");
        }
// ganti scroll manual  ae
//        ((AndroidDriver<?>) driver).
//          findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().
//              scrollable(true).instance(0)).
//                  scrollIntoView(new UiSelector().
//                  textContains(\"Reverse Geocoding\").instance(0))");
        Location loc = driver.location();
        SoftAssert softAssert = new SoftAssert();

        Dimension dimension = driver.manage().window().getSize();
        int scrollStart = (int) (dimension.getHeight() * 0.7);
        int scrollEnd = (int) (dimension.getHeight()*0.3);
        int center = (int) (dimension.getWidth()*0.5);
        new AndroidTouchAction(driver)
                .press(PointOption.point(center, scrollStart))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                .moveTo(PointOption.point(center, scrollEnd))
                .release()
                .perform();
        System.out.println(homePage.getLatitude());
        System.out.println(homePage.getLongitude());
        System.out.println(homePage.getReverseGeo());

        String lat = homePage.getLatitude();
        String lon = homePage.getLongitude();
        String reverseGeo = homePage.getReverseGeo();
        lat = lat.substring(0,lat.length()-1);
        lon = lon.substring(0,lon.length()-1);

        softAssert.assertEquals(Double.parseDouble(lon) , loc.getLongitude(), 0.5d);
        softAssert.assertEquals(Double.parseDouble(lat) , loc.getLatitude(), 0.5d);
        softAssert.assertTrue(reverseGeo.contains("iSTTS"));
        softAssert.assertAll();


    }


    // kalau ada ads yang muncul tutup



}
