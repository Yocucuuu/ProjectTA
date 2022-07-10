package tests;

import PageObjects.WebviewApp.WebviewAppHome;
import PageObjects.WebviewApp.WebviewApp_Pagebase;
import PageObjects.WebviewApp.Webview_Envato;
import PageObjects.WebviewTest.MediaPage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.clipboard.ClipboardContentType;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import rusak.HomePageEvernote;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class WebViewApp_Test extends TestBase{

    //        ((AndroidDriver)driver).pressKey(new KeyEvent(AndroidKey.BACK));


    @BeforeTest
    public void setup() throws MalformedURLException {

        Android_WebViewApp_Emulator_setUp();
        System.out.println(((AndroidDriver)driver).getCurrentPackage());
        System.out.println(((AndroidDriver)driver).currentActivity());
    }

    WebviewApp_Pagebase pagebase;
    WebviewAppHome homePage;
    Webview_Envato envato;
    @BeforeMethod()
    public void makeInstance(){

    }


    @Test(enabled = false)
    public void openEachPage(){
        pagebase = new WebviewApp_Pagebase(driver);
        SoftAssert softAssert = new SoftAssert();

        pagebase.toHome();
        softAssert.assertEquals("WebviewTest_Home",pagebase.getAttribute(pagebase.tv_nav, "text"));
        pagebase.toFeature();
        softAssert.assertEquals("Features",pagebase.getAttribute(pagebase.tv_nav, "text"));
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
        pagebase.toHome();
        softAssert.assertEquals("WebviewTest_Home",pagebase.getAttribute(pagebase.tv_nav, "text"));
        pagebase.toFeature();
        softAssert.assertEquals("Features",pagebase.getAttribute(pagebase.tv_nav, "text"));
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

    @Test(enabled = false)
    public void uploadFile() throws IOException {
        /* lokasi remote path harus filenya , lokasi filenya lengkap sama filenya   */
        ((AndroidDriver)driver).pushFile("/sdcard/DCIM/SharedFolder/gambar.png",new File(System.getProperty("user.dir")+"/data/downloadedFile/logoAppium.png"));
        WebviewAppHome home = new WebviewAppHome(driver);
        home.toUploadFile();
        home.tapUploadFile();
        home.uploadFromFile();

        MediaPage media = new MediaPage(driver);
        media.openLeftMenuTray();
        media.selectImageMenu();
        media.selectFolderImage();
        media.tapTargetImage();




    }

    @Test
    public void downloadFolder() throws IOException {
        byte[] fileBase64 = ((AndroidDriver)driver).pullFolder("/sdcard/DCIM/SharedFolder/");
        //dapat berupa zipnya . jadi file yang disimpen zipnya
        OutputStream os = new FileOutputStream(System.getProperty("user.dir")+"/data/downloadedFile/downloadFolder.zip");
        // Starting writing the bytes in it
        os.write(fileBase64);
        // Close the file connectionsz
        os.close();

    }
    /* download happy cats yang didownload dari webview App */
    @Test(enabled = false)
    public void downloadHappycat() throws IOException {

        try{
            File file = new File(System.getProperty("user.dir")+"/data/downloadedFile/happycats.mp3");
            file.delete();
        }catch (Exception ex){}
        WebviewAppHome home = new WebviewAppHome(driver);
        home.toHome();
        home.tapDownloadFile();
        /* in this moment dah kedownload */

        byte[] fileBase64 = ((AndroidDriver)driver).pullFile("/storage/emulated/0/download/happycats.mp3");
        OutputStream os = new FileOutputStream(System.getProperty("user.dir")+"/data/downloadedFile/happycats.mp3");
        // Starting writing the bytes in it
        os.write(fileBase64);
        // Close the file connectionsz
        os.close();
        File file = new File(System.getProperty("user.dir")+"/data/downloadedFile/happycats.mp3");
        Assert.assertTrue(file.exists());

    }

    // Aplikasi e so flaky , aplikasi yang automated pas landscape sama portrait beda dom e ,zzz
    @Test(enabled = false)
    public void toWebviewAppEnvato() {

        SoftAssert softAssert =new SoftAssert();
        driver.rotate(ScreenOrientation.LANDSCAPE);
        softAssert.assertEquals(driver.getOrientation(),ScreenOrientation.LANDSCAPE);


        homePage = new WebviewAppHome(driver);
        homePage.toHome();
        homePage.toWebView();

        envato =  new Webview_Envato(driver);
        softAssert.assertEquals(envato.getWebTittle(),"Universal Android WebView App");

        envato.openProfileTray();
        envato.clickBtnSignIn();

        envato.fillUsername("yoshuadwi2000@gmail.com");
        envato.fillPassword("Yoshuadwi06");
        envato.submitLogin();
        envato.openProfileTrayAfterLogin();
        softAssert.assertTrue(envato.getLoggedUser().contains("Yoshuadwi2000"));
        envato.logout();

        softAssert.assertAll();




    }

    //    @Test
//    public void gotoHome() {
//        pagebase.toHome();
//        Assert.assertEquals("WebviewTest_Home",pagebase.getAttribute(pagebase.tv_nav, "text"));
//    }
//
//    @Test
//    public void gotoFeature() {
//        pagebase.toFeature();
//        Assert.assertEquals("Features",pagebase.getAttribute(pagebase.tv_nav, "text"));
//    }
//
//    @Test
//    public void gotoAboutUs() {
//        pagebase.toAboutUs();
//        Assert.assertEquals("About Us",pagebase.getAttribute(pagebase.tv_nav, "text"));
//    }
//
//    @Test
//    public void gotoSupport() {
//        pagebase.toSupport();
//        Assert.assertEquals("Support",pagebase.getAttribute(pagebase.tv_nav, "text"));
//    }
//
//    @Test
//    public void gotoOurProduct() {
//        pagebase.toOurProduct();
//        Assert.assertEquals("Our Products",pagebase.getAttribute(pagebase.tv_nav, "text"));
//    }
//
//    @Test
//    public void gotoContact() {
//        pagebase.toContact();
//        Assert.assertEquals("Contact",pagebase.getAttribute(pagebase.tv_nav, "text"));
//    }
//
//    @Test
//    public void gotoPrivacyPolicy() {
//        pagebase.toPrivacyPolicy();
//        Assert.assertEquals("Privacy Policy",pagebase.getAttribute(pagebase.tv_nav, "text"));
//    }
    // kalau ada ads yang muncul tutup
    public void closeAds(){
        try{
            MobileElement element = (MobileElement) new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//android.widget.ImageButton[@content-desc=\"Interstitial close button\"]")
            ));
            System.out.println(element.isDisplayed());
//            closeAds();


        }catch (Exception ex){
            System.out.println("theres error  Closed Ads");
        }


    }

    @AfterClass
    public void afterClass()
    {
        driver.quit();
    }
}
