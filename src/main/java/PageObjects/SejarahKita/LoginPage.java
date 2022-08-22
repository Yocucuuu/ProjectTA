package PageObjects.SejarahKita;

import PageObjects.PageBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;

public class LoginPage extends SejarahKita_PageBase {

    @AndroidFindBy(id = "btn_login")
    public MobileElement loginButton ;

    @AndroidFindBy(id = "buttonReg")
    public MobileElement  registerButton;

    @AndroidFindBy(uiAutomator =
            "new UiSelector().className(\"android.widget.EditText\").instance(0)")
    public MobileElement  tvEmail;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").instance(1)")
    public MobileElement  tvPass;


    public LoginPage(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public void tapLogin(){
        click(loginButton);
    }

    public void toRegisterPage(){


        click(registerButton);
    }

    public void sendEmail(String email){
        sendText(tvEmail,email);
    }

    public void sendPassword(String password){
        sendText(tvPass,password);
    }


}
