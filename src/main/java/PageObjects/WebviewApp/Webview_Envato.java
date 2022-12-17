package PageObjects.WebviewApp;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

public class Webview_Envato extends WebviewApp_Pagebase{

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View/android.view.View/android.view.View[2]/android.view.View[2]")
    public MobileElement tvTittle;

//    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[2]/android.view.View/android.view.View[2]/android.view.View[2]/android.view.View[2]")
    @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"Hi,\")")
    public MobileElement lblUsername;

    @AndroidFindBy(accessibility = "Open account menu")
    public MobileElement btnProfileAfterLogin;

//    @AndroidFindBy(accessibility = "Sign In \uF130")
    @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"Sign In\")")
    public MobileElement btnToSignIn;

//    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View/android.view.View[2]/android.view.View[2]/android.view.View[2]/android.view.View[1]/android.view.View[1]/android.view.View[2]/android.widget.EditText")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"username\")")
    public MobileElement etUsername;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"password\")")
    public MobileElement etPassword;

//    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View/android.view.View[2]/android.view.View[2]/android.view.View[2]/android.widget.Button")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"sso-forms__submit\")")
    public MobileElement btnLogin;



//  @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View/android.view.View/android.view.View[1]/android.view.View[2]/android.view.View/android.view.View/android.view.View[2]/android.view.View")
//    @AndroidFindBy(accessibility = "8431507?ref=robotemplates&clickthrough_id&redirect_back=true#account")
    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"\uF13B\"]")
//    @AndroidFindBy(accessibility = "cart#account")
//    @AndroidFindBy(accessibility = "Open account menu")
    public MobileElement btnProfile;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Cart\"])[2]/android.widget.Image/android.view.View")
    @AndroidFindBy(uiAutomator= "new UiSelector().textContains(\"Cart\")")
    public MobileElement btnCart;

//    @AndroidFindBy(accessibility = "Sign Out")
    @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"Sign out\")")
    public MobileElement btnLogout;

    @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"Shopping Cart\")")
    public MobileElement lblCart;







    public Webview_Envato(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public void openCart(){
        closeAds();
        click((MobileElement) driver.findElement(By.xpath("(//android.view.View[@content-desc=\"Cart\"])[2]/android.widget.Image")));
    }

    public String getWebTittle() throws InterruptedException {
        Thread.sleep(5000);
        return getAttribute(tvTittle,"text");
    }
    public String getLoggedUser(){
        return  getAttribute(lblUsername,"text");
    }

    public void clickBtnSignIn(){

        click(btnToSignIn);
        closeAds();
        System.out.println("click sign inn ");
    }

    public void fillUsername(String username){
        sendText(etUsername,username);
    }
    public void fillPassword(String password){
        sendText(etPassword,password);
    }
    public void submitLogin(){

        click(btnLogin);
        closeAds();
        System.out.println("click Submit");
    }
    public void openProfileTray(){

//        waitForVisibility(btnCart);
//        click(btnProfile);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//         samsung c9
        (new TouchAction(driver)).tap(PointOption.point(1015,252)).perform();

        // bluestack
//        (new TouchAction(driver)).tap(PointOption.point(1041,154)).perform();
        System.out.println("open tray");
        closeAds();
    }
    public void openProfileTrayAfterLogin(){

        click(btnProfileAfterLogin);
        closeAds();
    }

    public Boolean isPageCart(){
        waitForVisibility(lblCart);
        return lblCart.isDisplayed();
    }

    public void logout(){

        click(btnLogout);
        closeAds();
    }
}
