package PageObjects.SejarahKita;

import PageObjects.PageBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class RegisterPage extends SejarahKita_PageBase {


    /*
    * email ,username , fullname ,  schooll , city , birthyear ,  pass, confpass
    * */
    String xpath_tvEmail = "";
    String xpath_tvUsername = "";
    String xpath_tvFullname = "";
    String xpath_tvSchool = "";
    String xpath_tvCity = "";
    String xpath_tvBirthyear = "";
    String xpath_tvPassword = "";
    String xpath_tvConfPass = "";
    String id_btnLogin = "buttonLog";
    String id_btnRegister = "btn_reg";

    MobileElement loginButton = (MobileElement) driver.findElementById(id_btnLogin);
    MobileElement registerButton = (MobileElement) driver.findElementById(id_btnRegister);

    MobileElement tvEmail = (MobileElement) driver.findElementByXPath(xpath_tvEmail);
    MobileElement tvUsername = (MobileElement) driver.findElementByXPath(xpath_tvPassword);
    MobileElement tvFullname = (MobileElement) driver.findElementByXPath(xpath_tvFullname);
    MobileElement tvSchool = (MobileElement) driver.findElementByXPath(xpath_tvSchool);
    MobileElement tvCity = (MobileElement) driver.findElementByXPath(xpath_tvCity);
    MobileElement tvBirthyear = (MobileElement) driver.findElementByXPath(xpath_tvBirthyear);
    MobileElement tvConfpass = (MobileElement) driver.findElementByXPath(xpath_tvConfPass);
    MobileElement tvPassword = (MobileElement) driver.findElementByXPath(xpath_tvPassword);

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
        sendText(tvFullname , username);
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
