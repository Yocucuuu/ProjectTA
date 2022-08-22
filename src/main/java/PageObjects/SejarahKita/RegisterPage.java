package PageObjects.SejarahKita;

import PageObjects.PageBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

import javax.annotation.ParametersAreNonnullByDefault;

public class RegisterPage extends SejarahKita_PageBase {


    /*
    * email ,username , fullname ,  schooll , city , birthyear ,  pass, confpass
    * */


    @AndroidFindBy(id = "buttonLog" )
    MobileElement loginButton;

    @AndroidFindBy(id ="btn_reg")
    MobileElement registerButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"Email\")")
    public MobileElement tvEmail ;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Username\")")
    MobileElement tvUsername;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Name\")")
    MobileElement tvFullname;

    @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"School\")")
    MobileElement tvSchool;

    @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"City\")")
    MobileElement tvCity ;

    @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"Birthyear\")")
    MobileElement tvBirthyear;

    @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"Password\")")
    MobileElement tvPassword;

    @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"Confirm Password\")")
    MobileElement tvConfpass;

    public RegisterPage(AppiumDriver androidDriver) {
        super(androidDriver);
    }
    public void toLogin(){
        click(loginButton);
    }
    public void tapRegister(){
        click(registerButton);
    }


    public void sendEmail(String email){
        sendText(tvEmail ,email);
    }
    public void sendUsername(String username){
        sendText(tvUsername , username);
    }
    public void sendFullname(String fullname){
        sendText(tvFullname , fullname);
    }
    public void sendSchool(String school){
        sendText(tvSchool , school);
    }
    public void sendCity(String city){
        sendText(tvCity,city);
    }
    public void sendBirthyear(String birthyear){
        sendText(tvBirthyear , birthyear);
    }
    public void sendPassword(String password){
        sendText(tvPassword, password);
    }
    public void sendConfirmationPassword(String confirmation_password){
        sendText(tvConfpass, confirmation_password);
    }

}
