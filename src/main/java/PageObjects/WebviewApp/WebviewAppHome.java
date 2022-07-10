package PageObjects.WebviewApp;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

// biar ga kebanyakan class , web envato jadi satu sama home ae
public class WebviewAppHome extends WebviewApp_Pagebase{

    @AndroidFindBy(xpath = "(//android.view.View[@content-desc=\"WEBVIEW APP PAGE\"])[1]/android.widget.TextView")
    MobileElement toWebviewAppPage;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"VISIT GOOGLE\"]/android.widget.TextView")
    MobileElement toGoogleInternal;

    @AndroidFindBy(accessibility = "TRY DOWNLOAD")
    MobileElement downloadFile;

    @AndroidFindBy(accessibility = "TRY UPLOAD")
    MobileElement toUploadFile;

    @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"Choose Images\")")
    MobileElement btnUploadFile;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.ListView/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ImageView")
    MobileElement uploadFromFile;





    public WebviewAppHome(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public void toWebView(){
        click(toWebviewAppPage);
    }

    public void toGoogle(){
        click(toGoogleInternal);
    }

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
