package PageObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class PageBase {

    public AppiumDriver driver;
    public static final long WAIT = 15;
    public WebDriverWait wait;

    public void printContext(String position){
        System.out.println("Position  : " + position);
        Set<String> contextNames = driver.getContextHandles();
        for (String contextName : contextNames) {
            System.out.println(contextName); //prints out something like NATIVE_APP \n WEBVIEW_1
        }
    }

    public PageBase(AppiumDriver appiumDriver) {
//        PageFactory.initElements(new AppiumFieldDecorator(androidDriver), this);
        PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
        driver = appiumDriver;
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
//        driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS); // webcontext


    }

    public void waitForVisibility(MobileElement element) {
        wait = new WebDriverWait(driver, WAIT);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void clear(MobileElement element) {
        waitForVisibility(element);
        element.clear();
    }

    public void click(MobileElement element) {
        waitForVisibility(element);
        element.click();
    }

    public void sendText(MobileElement element, String text) {
        waitForVisibility(element);
        element.sendKeys(text);
    }

    public String getText(MobileElement element) {
        waitForVisibility(element);
        return element.getText();
    }
    public String getToastText(MobileElement element) {
        return element.getText();
    }

    public String getAttribute(MobileElement element, String attribute) {
        waitForVisibility(element);
        return element.getAttribute(attribute);
    }

}
