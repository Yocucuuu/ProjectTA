package tests;

import PageObjects.WebviewTest.WebviewTest_Home;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class WebviewTest_Hybridweb_Test extends TestBase{


    @BeforeClass
    public void beforeClass() throws MalformedURLException {
//        Android_WebviewTest_C9_setUp();
        Android_WebviewTest_Emulator_setUp();
    }

    @AfterClass
    public void afterClass() {

        driver.quit();
    }

    @Test
    public void brokenLinkTest() throws MalformedURLException, InterruptedException {

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
        By byPass =  By.id("id_password");
        By byEmail =  By.id("id_email");
        By bySubmit =  By.className("login-btn");
        By namaUser =  By.className("tutor_name");

        driver.findElement(byEmail).sendKeys("218116775");
        driver.findElement(byPass).sendKeys("123456789*joshua");
        driver.findElement(bySubmit).click();


        String loggedUser = driver.findElement(namaUser).getText();
        softAssert.assertEquals("Yoshua Dwi Santoso" , loggedUser);

        List<WebElement> links =  driver.findElements(By.tagName("a"));
        for(WebElement link : links) {
            HttpURLConnection con;
            String url = link.getAttribute("href");
            if (url == null || url.isEmpty()) {
                continue;
            }
            try {
                con = (HttpURLConnection)
                        (new URL(url).openConnection());
                con.setRequestMethod("HEAD");
                con.connect();
                int respCode = con.getResponseCode();
                softAssert.assertTrue(respCode>=200 && respCode<300);
                if (respCode >= 200 && respCode <300) {
                    System.out.println(url + " is a valid link");
                } else {
                    System.out.println(url + " is a broken link");
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        //logout
        driver.executeScript(" window.location.href = \"https://eclass.stts.edu/logout\"; ");
        softAssert.assertAll();
    }

}
