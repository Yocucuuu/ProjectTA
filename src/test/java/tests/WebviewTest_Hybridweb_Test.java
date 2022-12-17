package tests;

import PageObjects.WebviewTest.WebviewTest_Home;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class WebviewTest_Hybridweb_Test extends TestBase{

    By byPass =  By.id("id_password");
    By byEmail =  By.id("id_email");
    By bySubmit =  By.className("login-btn");
    By namaUser =  By.className("tutor_name");

    By nav =  By.id("toggleMenu");
    By menuKelas =  By.className("menu--item"); //[2];
    By selectPeriode = By.cssSelector("body > div.wrapper > div.sa4d25 > div > div > div.col-md-12.mt-10 > div._14d25 > div > div"); //->option ke2
//    By selectPeriode = By.id("periode"); //->option ke2
    By listPeriode = By.cssSelector("body > div.wrapper > div.sa4d25 > div > div > div.col-md-12.mt-10 > div._14d25 > div > div > div.menu.transition.visible");//[1] ->div[0]
    By listKelas = By.className("list_kelas");
    By namaKelas = By.className("tutor150");// nama kelas
    By namaDetail = By.className("prfledt1");// nama kelas
    @BeforeClass
    public void beforeClass() throws MalformedURLException {
        Android_WebviewTest_C9_setUp();
//        Android_WebviewTest_Emulator_setUp();
    }



    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    @Test
    public void loginEclass() throws MalformedURLException, InterruptedException {
        driver.resetApp();
        SoftAssert  softAssert = new SoftAssert();
        WebviewTest_Home home = new WebviewTest_Home(driver);
        home.click(home.gotoWeb);
        home.click(home.inputUrl);
        home.sendText(home.inputUrl, "https://eclass.stts.edu/login");
        home.click(home.buttonGo);
        Thread.sleep(5000);

        Set<String> contextNames = driver.getContextHandles();
        contextNames.forEach((e) ->     { System.out.println(e); });
        driver.context("WEBVIEW_com.snc.test.webview2");
        softAssert.assertEquals("WEBVIEW_com.snc.test.webview2", driver.getContext());


        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
        By byPass =  By.id("id_password");
        By byEmail =  By.id("id_email");
        By bySubmit =  By.className("login-btn");
        By namaUser =  By.className("tutor_name");

        driver.findElement(byEmail).sendKeys("218116775");
        driver.findElement(byPass).sendKeys("123456789*joshua");
        driver.findElement(bySubmit).click();


        String loggedUser = driver.findElement(namaUser).getText();
        softAssert.assertEquals("Yoshua Dwi Santoso" , loggedUser);

//        List<WebElement> links =  driver.findElements(By.tagName("a"));
//        for(WebElement link : links) {
//            HttpURLConnection con;
//            String url = link.getAttribute("href");
//            if (url == null || url.isEmpty()) {
//                continue;
//            }
//            try {
//                con = (HttpURLConnection)
//                        (new URL(url).openConnection());
//                con.setRequestMethod("HEAD");
//                con.connect();
//                int respCode = con.getResponseCode();
//                softAssert.assertTrue(respCode>=200 && respCode<300);
//                if (respCode >= 200 && respCode <300) {
//                    System.out.println(url + " is a valid link");
//                } else {
//                    System.out.println(url + " is a broken link");
//                }
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }

        //logout
        driver.executeScript(" window.location.href = \"https://eclass.stts.edu/logout\"; ");
        softAssert.assertAll();
        ((AndroidDriver) driver).longPressKey(new KeyEvent(AndroidKey.BACK)); // close
    }

    @Test
    public void showMataKuliahDetail() throws MalformedURLException, InterruptedException {
        driver.resetApp();
        WebDriverWait wait = new WebDriverWait(driver,5);
        SoftAssert  softAssert = new SoftAssert();
        WebviewTest_Home home = new WebviewTest_Home(driver);
        home.click(home.gotoWeb);
        home.click(home.inputUrl);
        home.sendText(home.inputUrl, "https://eclass.stts.edu/login");
        home.click(home.buttonGo);
        Thread.sleep(5000);

        Set<String> contextNames = driver.getContextHandles();
        contextNames.forEach((e) -> { System.out.println(e); });
        driver.context("WEBVIEW_com.snc.test.webview2");
        softAssert.assertEquals("WEBVIEW_com.snc.test.webview2", driver.getContext());


        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);


        driver.findElement(byEmail).sendKeys("218116775");
        driver.findElement(byPass).sendKeys("123456789*joshua");
        driver.findElement(bySubmit).click();

        // click Nav
        driver.findElement(nav).click();

//        // select kelas ->diarahkan ke halaman list kelas
        ((WebElement)driver.findElements(menuKelas).get(1)).click();

//        // ke dropdown priode, pilih periode ke 1
//        // select salah satu kelas

        WebElement select =  ((WebElement)driver.findElements(selectPeriode).get(0));
        System.out.println(select.isDisplayed());
        select.click();

        wait.until(ExpectedConditions.elementToBeClickable(listPeriode));
        WebElement ddPeriode = driver.findElement(listPeriode);
        ArrayList<WebElement> periodes = (ArrayList<WebElement>) ddPeriode.findElements(By.className("item"));
//        periodes.get(0).click(); // sudah dibenerin tim IT yay keren fastrep
        periodes.get(1).click();

        ArrayList<WebElement> kelases = (ArrayList<WebElement>) driver.findElements(listKelas);
        System.out.println(kelases.size());
        WebElement kelas  = kelases.get(0);

        // get judul matkul dari list
        wait.until(ExpectedConditions.elementToBeClickable(By.className("tutor150")));
        String judul = kelas.findElement(By.className("tutor150")).getText();
        System.out.println(judul);
        kelas.click();

        // get judul di halaman detail
        String judulDetail = driver.findElement(By.className("prfledt1")).findElement(By.tagName("h2")).getText();

        softAssert.assertEquals(judul, judulDetail);


        //logout
        driver.executeScript(" window.location.href = \"https://eclass.stts.edu/logout\"; ");
        softAssert.assertAll();
        ((AndroidDriver) driver).longPressKey(new KeyEvent(AndroidKey.BACK)); // close
    }




}
