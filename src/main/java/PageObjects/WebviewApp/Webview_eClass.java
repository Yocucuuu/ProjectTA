package PageObjects.WebviewApp;

import PageObjects.PageBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class Webview_eClass extends PageBase {

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").clickable(true)")
    public MobileElement btn_closeAds;

    @AndroidFindBy(accessibility = "Interstitial close button")
    public MobileElement btn_closeAdsPic;


    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"id_email\")")
    MobileElement inpEmail;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"id_password\")")
    MobileElement inpPassword;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").instance(0)")
    MobileElement btnLogin;


    public Webview_eClass(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }
    public void closeAds() {

        try {
            ((AndroidDriver<?>) driver).findElementByAndroidUIAutomator(
                    "new UiSelector().resourceId(\"adContainer\")");
            click(btn_closeAds);
            System.out.println("btn ads video");
        } catch (Exception ex) {
        }
        try {
            click(btn_closeAdsPic);
            System.out.println("btn ads pic");
        } catch (Exception ex) {
        }

    }
    public void sendEmail(String email){
        closeAds();
       sendText(inpEmail , email);


    }

    public void sendPassword(String pass){
        closeAds();
        sendText(inpPassword , pass);


    }

    public void clickSubmit(){
        closeAds();
        tap(btnLogin);

    }

}
