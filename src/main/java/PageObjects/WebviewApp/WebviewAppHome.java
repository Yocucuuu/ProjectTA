package PageObjects.WebviewApp;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

// biar ga kebanyakan class , web envato jadi satu sama home ae
public class WebviewAppHome extends WebviewApp_Pagebase{

    @AndroidFindBy(xpath = "(//android.view.View[@content-desc=\"WEBVIEW APP PAGE\"])[1]/android.widget.TextView")
    MobileElement toWebviewAppPage;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"VISIT GOOGLE\"]/android.widget.TextView")
    MobileElement toGoogleInternal;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"TRY GEOLOCATION\"]/android.widget.TextView")
    MobileElement tryGeolocation;

    @AndroidFindBy(accessibility = "TRY DOWNLOAD")
    MobileElement downloadFile;

    @AndroidFindBy(accessibility = "TRY UPLOAD")
    MobileElement toUploadFile;

    @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"Pilih gambar\")")
    MobileElement btnUploadFile;


    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"File\")")
    MobileElement uploadFromFile;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"geo_update_button\")")
    MobileElement btnUpdateGeo;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"geo_watch_button\")")
    MobileElement cbGeo;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"latitude\")")
    MobileElement tvLatitude;


    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"longitude\")")
    MobileElement tvLongitude;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"reverse\")")
    MobileElement reverseGeo;


    public WebviewAppHome(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public void toWebView(){
        click(toWebviewAppPage);
    }

    public void toGoogle(){
        click(toGoogleInternal);
    }
    public void toTryGeolocation(AndroidDriver driver){
        waitForVisibility(toWebviewAppPage);
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"TRY GEOLOCATION\").instance(0))");
        click(tryGeolocation);
    }
    public void clickBtnUpdateGeo(){click(btnUpdateGeo);}
    public void clickCbCaptureGeo(){click(cbGeo);}
    public String getLatitude(){
        return getAttribute(tvLatitude,"text");
    }
    public String getLongitude(){
        return getAttribute(tvLongitude,"text");
    }
    public String getReverseGeo(){
        return getAttribute(reverseGeo,"text");
    }
    public String getCheckedGeoCapture(){return getAttribute(cbGeo,"checked");}


    public void tapDownloadFile(){
        click(downloadFile);
    }
    public void tapUploadFile(){
        click(btnUploadFile);
    }
    public void toUploadFile(){
        click(toUploadFile);
    }
    public void uploadFromFile(){
        click(uploadFromFile);
    }
}
