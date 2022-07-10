package tests;

import PageObjects.WebviewTest.WebviewTest_Home;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.Set;

public class WebviewTest_Hybridweb_Test extends TestBase{

    @Test
    public void beforeClass() throws MalformedURLException {
        Android_WebviewTest_Emulator_setUp();
        WebviewTest_Home home = new WebviewTest_Home(driver);
        home.click(home.gotoWeb);
        home.click(home.inputUrl);
        home.sendText(home.inputUrl, "https://sejarahkita.my.id/login");
        home.click(home.buttonGo);
        Set<String> contextNames = driver.getContextHandles();
        contextNames.forEach((e) -> { System.out.println(e); });
        driver.context("WEBVIEW_com.snc.test.webview2");
//        System.out.println(driver.getPageSource());
        By byPass =  By.id("password");
        By byEmail =  By.id("email");
        By bySubmit =  By.xpath("/html/body/main/div/div[2]/div[1]/div[2]/button");
        WebDriverWait wait= new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(byEmail));
        WebElement email = driver.findElement(byEmail);
        email.sendKeys("johndoe@gmail.com");

        wait.until(ExpectedConditions.presenceOfElementLocated(byPass));
        WebElement password = driver.findElement(byPass);
        password.sendKeys("12345678");

        wait.until(ExpectedConditions.presenceOfElementLocated(bySubmit));
        WebElement submit = driver.findElement(bySubmit);
        submit.click();


    }
}
