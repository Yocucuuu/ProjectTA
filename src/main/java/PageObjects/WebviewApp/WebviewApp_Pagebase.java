package PageObjects.WebviewApp;

import PageObjects.PageBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebviewApp_Pagebase extends PageBase {

    // appium --chromedriver-executable C:\Users\asus\node_modules\chromedriver\lib\chromedriver
    public WebviewApp_Pagebase(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    @AndroidFindBy(accessibility = "Interstitial close button")
    public MobileElement btn_closeAds;

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

    public void closeAds(){
        wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(btn_closeAds));
        click(btn_closeAds);
    }

    public void toHome(){
        click(btn_drawer);
        click(btn_home);
    }

    public void toFeature(){
        click(btn_drawer);
        click(btn_feature);
    }

    public void toSupport(){
        click(btn_drawer);
        click(btn_support);
    }

    public void toOurProduct(){
        click(btn_drawer);
        click(btn_ourProduct);
    }

    public void toAboutUs(){
        click(btn_drawer);
        click(btn_aboutUs);
    }
    public void toContact(){
        click(btn_drawer);
        click(btn_contact);
    }

    public void toPrivacyPolicy(){
        click(btn_drawer);
        click(btn_privacyPolicy);
    }



}
