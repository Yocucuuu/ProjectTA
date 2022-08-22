package PageObjects.WebviewApp;

import PageObjects.PageBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class Webview_eClass extends PageBase {

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"id_email\")")
    MobileElement inpEmail;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"id_password\")")
    MobileElement inpPassword;

    @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"Sign In\")")
    MobileElement btnLogin;

    public Webview_eClass(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public void sendEmail(String email){
       sendText(inpEmail , email);
    }

    public void sendPassword(String pass){
        sendText(inpPassword , pass);
    }

    public void clickSubmit(){
        tap(btnLogin);
    }

}
