package PageObjects.WebviewApp;

import PageObjects.PageBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebviewApp_Pagebase extends PageBase {

    // appium --chromedriver-executable C:\Users\asus\node_modules\chromedriver\lib\chromedriver
    public WebviewApp_Pagebase(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

//    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View[1]/android.view.View[2]/android.widget.TextView")
//    @AndroidFindBy(accessibility = "Interstitial close button")
//    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/" +
//            "android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View[1]/android.view.View[2]/android.widget.Button")
    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").clickable(true)")
    public MobileElement btn_closeAds;

    @AndroidFindBy(accessibility = "Interstitial close button")
    public MobileElement btn_closeAdsPic;


    @AndroidFindBy(id = "abgcp")
    public MobileElement addContainer;

    @AndroidFindBy(accessibility = "WEBVIEW APP PAGE")
    MobileElement btn_toWebviewAppPage;

    @AndroidFindBy(accessibility = "Open navigation drawer")
    MobileElement btn_drawer;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/androidx.appcompat.widget.LinearLayoutCompat[1]/android.widget.CheckedTextView")
    MobileElement btn_home;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/androidx.appcompat.widget.LinearLayoutCompat[2]/android.widget.CheckedTextView")
    MobileElement btn_feature;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/androidx.appcompat.widget.LinearLayoutCompat[3]/android.widget.CheckedTextView")
    MobileElement btn_support;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/androidx.appcompat.widget.LinearLayoutCompat[4]/android.widget.CheckedTextView")
    MobileElement btn_ourProduct;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/androidx.appcompat.widget.LinearLayoutCompat[5]/android.widget.CheckedTextView")
    MobileElement btn_aboutUs;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/androidx.appcompat.widget.LinearLayoutCompat[6]/android.widget.CheckedTextView")
    MobileElement btn_contact;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/androidx.appcompat.widget.LinearLayoutCompat[7]/android.widget.CheckedTextView")
    MobileElement btn_privacyPolicy;


    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.view.ViewGroup/android.widget.LinearLayout/android.view.ViewGroup/android.widget.TextView")
    public MobileElement tv_nav;


    public void toWebviewAppPage(){
        click(btn_toWebviewAppPage);
    };

//    public void closeAds(){
//        wait = new WebDriverWait(driver, 5);
//        wait.until(ExpectedConditions.elementToBeClickable(btn_closeAds));
//        click(btn_closeAds);
//    }
    public void closeAds(){

        try{
            ((AndroidDriver<?>) driver).findElementByAndroidUIAutomator(
                    "new UiSelector().resourceId(\"adContainer\")");
            click(btn_closeAds);
            System.out.println("btn ads video");
        }catch (Exception ex){}
        try{
            click(btn_closeAdsPic);
            System.out.println("btn ads pic");
        }catch (Exception ex){}

//        try{
//            click(btn_closeAdsPic);
//            System.out.println("btn ads Pic");
//        }catch (Exception ex){}

//        try{
////            MobileElement el2 = (MobileElement) driver.findElementByAccessibilityId("Advertisement");
////            MobileElement el23 = (MobileElement) driver.findElementByXPath(
////   "//android.view.View[@content-desc=\"Advertisement\"]/android.widget.Image");
////            (new TouchAction(driver)).tap(PointOption.point(1005,57)).perform();  // C9
////            Advertisement
//            ((AndroidDriver<?>) driver).findElementByAndroidUIAutomator("new UiSelector().textContains(\"Advertisement\").instance(0)");
////            driver.findElement(By.xpath("//android.view.View[@content-desc=\"Advertisement\"]"));
//            (new TouchAction(driver)).tap(PointOption.point(850,35)).perform();
//            System.out.println("Closed Ads");
//        }catch (Exception ex){
//            System.out.println(driver.getPageSource());
//            System.out.println("There is no ads");
//        }
    }

    public void toHome(){
        click(btn_drawer);
        click(btn_home);
        closeAds();
    }

    public void toFeature(){

        click(btn_drawer);
        click(btn_feature);
        closeAds();
    }

    public void toSupport(){
        click(btn_drawer);
        click(btn_support);
        closeAds();
    }

    public void toOurProduct(){
        click(btn_drawer);
        click(btn_ourProduct);
        closeAds();
    }

    public void toAboutUs(){
        click(btn_drawer);
        click(btn_aboutUs);
        closeAds();
    }
    public void toContact(){
        click(btn_drawer);
        click(btn_contact);
        closeAds();
    }

    public void toPrivacyPolicy(){
        click(btn_drawer);
        click(btn_privacyPolicy);
        closeAds();
    }



}
