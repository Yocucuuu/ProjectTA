package tests;


import PageObjects.SejarahKita.GameFragment;
import PageObjects.SejarahKita.LoginPage;
import PageObjects.SejarahKita.ProfileFragment;
import PageObjects.SejarahKita.RegisterPage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.clipboard.ClipboardContentType;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class SejarahKita_RegisterLogin_Test extends TestBase {

    LoginPage login;
    GameFragment gameFragment;
    ProfileFragment profileFragment;
    WebDriverWait wait;

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException {
        resetLogin();
        Android_SejarahKita_SamsungC9_setUp();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        Android_SejarahKita_Emulator_setUp();
        login = new LoginPage(driver);
    }
    @AfterMethod // setiap kali ada error pada test akan membuat screenshot untuk mengetahui dimana letak erornya
    public void afterMerthod(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            String time = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
            String newFold = System.getProperty("user.dir")+"/data/SejarahKita_Android_TestFail/";
            String path_screenshot = newFold;
            File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            String filename= result.getName()+time.toString();
            File targetFile=new File(path_screenshot + filename +".jpg");
            FileUtils.copyFile(srcFile,targetFile);
        }
//        driver.resetApp();
        driver.closeApp();
        driver.activateApp(sejarahKitaPackage);
    }

    @AfterClass(groups = "logout" , dependsOnGroups = {"loginSuccess","registerFail"})
    public void logoutAndQuit() throws IOException {

        try{
            login = new LoginPage(driver);
            if(login.loginButton.isDisplayed()){
                ((AndroidDriver)driver).pressKey(new KeyEvent(AndroidKey.BACK));
                ((AndroidDriver)driver).pressKey(new KeyEvent(AndroidKey.BACK)); // double press
            }
        }catch (Exception ex){
            SoftAssert softAssert = new SoftAssert();
            ProfileFragment profileFragment = new ProfileFragment(driver);
            profileFragment.toProfileFragment();
            profileFragment.waitForVisibility(profileFragment.lblEmail);
            scrollDown();
            profileFragment.tapLogout();

            login = new LoginPage(driver);
            login.waitForVisibility(login.tvEmail);
            softAssert.assertEquals(true,login.loginButton.isDisplayed());
//        driver.navigate().back();
//        driver.navigate().back(); // punya fungsi yang sama
            ((AndroidDriver)driver).pressKey(new KeyEvent(AndroidKey.BACK));
            ((AndroidDriver)driver).pressKey(new KeyEvent(AndroidKey.BACK)); // double press
        }

    }

    @AfterTest
    public  void tearDown() throws Exception {
        writeLog();
        driver.quit();
    }

    @Test(groups = "loginFail")
    public void emptyField() throws IOException {
        login = new LoginPage(driver);
        login.clear(login.tvEmail);
        login.clear(login.tvPass);
        login.tapLogin();
        Assert.assertEquals("Please fill all field" ,
                login.getToastText(login.toastMessage));
    }
    @Test(groups = "loginFail")
    public void emptyPassField() {
        login.clear(login.tvEmail);
        login.clear(login.tvPass);
        login.sendEmail("vanthony@student.ciputra.ac.id");
        login.tapLogin();
        Assert.assertEquals("Please fill all field" , login.getToastText(login.toastMessage));
    }
    @Test(groups = "loginFail")
    public void emptyEmailField() {

        login.clear(login.tvEmail);
        login.clear(login.tvPass);
        login.sendPassword("va123456");
        login.tapLogin();
        Assert.assertEquals("Please fill all field" , login.getToastText(login.toastMessage));
    }
    @Test(groups = "loginFail")
    public void wrongEmailLogin() throws IOException {
        login.clear(login.tvEmail);
        login.clear(login.tvPass);
        login.sendEmail("vanthony@student.ciputra.ac.idsss");
        login.sendPassword("va123456");
        login.tapLogin();
        Assert.assertEquals("Login Failed" , login.getToastText(login.toastMessage));
    }
    @Test(groups = "loginFail")
    public void wrongPassLogin() throws IOException {
        login.clear(login.tvEmail);
        login.clear(login.tvPass);
        login.sendEmail("vanthony@student.ciputra.ac.id");
        login.sendPassword("va123456sss");
        login.tapLogin();
        Assert.assertEquals("Login Failed" , login.getToastText(login.toastMessage));
    }
    @Test(groups = "loginFail")
    public void loginWithUsedAccount() throws IOException {
        login.clear(login.tvEmail);
        login.clear(login.tvPass);
        login.sendEmail("narianto@student.ciputra.ac.id");
        login.sendPassword("nathabel2762");
        login.tapLogin();
        Assert.assertEquals("Login Failed" , login.getToastText(login.toastMessage));
    }
    @Test(groups = "loginFail")
    public void loginWithSuspendedAccount() throws IOException {
        login.clear(login.tvEmail);
        login.clear(login.tvPass);
        login.sendEmail("pkrishnacahya@student.ciputra.ac.id");
        login.sendPassword("pk025758");
        login.tapLogin();
        Assert.assertEquals("Login Failed" , login.getToastText(login.toastMessage));
    }

//    @Test(priority = 1 ,groups = "registerFail")
//    public void registerWithblank(){
//        LoginPage login =  new LoginPage(driver);
//        login.toRegisterPage();
//
//        RegisterPage register = new RegisterPage(driver);
//        register.waitForVisibility(register.tvEmail);
//        scrollDown();
//        register.tapRegister();
//        Assert.assertEquals(register.getToastText(register.toastMessage) , "Please fill all field");
//    }


//    @Test(priority = 1 ,groups = "registerSuccess",dependsOnGroups = "registerFail")
    @Test(priority = 1 ,groups = "registerSuccess")
    public void registerThenLogin(){

        SoftAssert softAssert = new SoftAssert();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.toRegisterPage();

        RegisterPage register = new RegisterPage(driver);
        register.waitForVisibility(register.tvEmail);
        scrollUp();
        register.waitForVisibility(register.tvEmail);

        register.sendEmail("yoshua@gmail.com");
        register.sendFullname("Yoshua Dwi");
        register.sendUsername("yocucuuuu");
        register.sendSchool("SMA TNH");
        register.sendCity("Purwodadi");
        register.sendBirthyear("060200");
        scrollDown();
        register.sendPassword("123456789");
        register.sendConfirmationPassword("123456789");
        register.tapRegister();
        String text="";
        try{
            text= register.getToastText(register.toastMessage);
        }catch (Exception ex){}
        softAssert.assertEquals(text , "Register Success");
        // kalau berhasil register akan diarahkan ke login

        LoginPage login = new LoginPage(driver);
        login.clear(login.tvEmail);
        login.clear(login.tvPass);
        login.sendEmail("yoshua@gmail.com");
        login.sendPassword("123456789");
        login.tapLogin();
        softAssert.assertEquals(login.getToastText(register.toastMessage) , "Login Success");
        softAssert.assertAll();

    }

    @Test(priority = 2 ,groups = "login")
    public void successLogin() throws IOException, InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        String str = "vanthony@student.ciputra.ac.id";
        byte[] base64 = Base64.encodeBase64(str.getBytes(StandardCharsets.UTF_8));
        ((AndroidDriver)driver).setClipboard("lable",ClipboardContentType.PLAINTEXT , base64 );
        String emailFromClipboard = ((AndroidDriver)driver).getClipboard(ClipboardContentType.PLAINTEXT );
        login.sendEmail(emailFromClipboard);
        login.sendPassword("va123456");
        login.tapLogin();

        profileFragment = new ProfileFragment(driver);
        login.toProfileFragment();
        softAssert.assertTrue(profileFragment.fragProfile.isSelected() );
        softAssert.assertAll();
    }
    @Test(priority = 2 ,enabled = false,groups = {"cekSession"} ,dependsOnMethods = "login")
    public void stillLoggedIn() throws IOException {
        SoftAssert softAssert = new SoftAssert();
        driver.closeApp();
        // driver.launchApp();, punya fungsi yang sama namun harus mensetel App pada desCap
        driver.activateApp(sejarahKitaPackage);
        login.toProfileFragment();
        WebDriverWait WebWait = new WebDriverWait(driver,10);
        WebWait.withTimeout(Duration.ofSeconds(3));
        softAssert.assertEquals("vanthony@student.ciputra.ac.id", profileFragment.getLoggedInEmail());
        softAssert.assertAll();
    }

}
