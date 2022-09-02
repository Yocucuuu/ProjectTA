package PageObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.serverevents.CustomEvent;
import io.appium.java_client.serverevents.ServerEvents;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
//        driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS); // webcontext

public class PageBase {

    public AppiumDriver driver;
    public static final long WAIT = 35;
    public WebDriverWait wait;
    CustomEvent evt;

    public void printContext(String position){
        System.out.println("Position  : " + position);
        Set<String> contextNames = driver.getContextHandles();
        for (String contextName : contextNames) {
            System.out.println(contextName); //prints out something like NATIVE_APP \n WEBVIEW_1
        }
    }

    public PageBase(AppiumDriver appiumDriver) {
        PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
        driver = appiumDriver;
        wait = new WebDriverWait(driver, WAIT);
        evt = new CustomEvent();
    }

    public void waitForVisibility(MobileElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void clear(MobileElement element) {
        waitForVisibility(element);
        element.clear();
    }

    public void tap(MobileElement element) {
        waitForVisibility(element);
        element.click();
    }
    public void click(MobileElement element) {
        waitForVisibility(element);
        evt.setVendor(driver.getAutomationName());
        evt.setEventName("Click element" + element.getTagName());
        driver.logEvent(evt);
        element.click();
//        AndroidTouchAction actions = new AndroidTouchAction(driver);
//        actions.tap(PointOption.point(element.getLocation())).perform();

    }

    public void sendText(MobileElement element, String text) {
        waitForVisibility(element);
        evt.setVendor(driver.getAutomationName());
        evt.setEventName("Send Element element" + element.getTagName());
        driver.logEvent(evt);
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
