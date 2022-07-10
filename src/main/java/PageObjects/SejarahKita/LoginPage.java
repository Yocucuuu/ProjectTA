package PageObjects.SejarahKita;

import PageObjects.PageBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;

public class LoginPage extends SejarahKita_PageBase {

//    String xpath_tvEmail ;
//    String xpath_tvPassword ;
//    String id_btnLogin = "btn_login";
//    String id_btnRegister = "buttonReg";

    @AndroidFindBy(id = "btn_login")
    public MobileElement loginButton ;

    @AndroidFindBy(id = "buttonReg")
    public MobileElement  registerButton;

    @AndroidFindBy(id = "img_logo_login_fragment")
    public MobileElement  logoLogin;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout[1]/android.widget.FrameLayout/android.widget.EditText")
    public MobileElement  tvEmail;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout[2]/android.widget.FrameLayout/android.widget.EditText")
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
