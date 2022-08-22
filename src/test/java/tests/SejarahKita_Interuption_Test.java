package tests;

import PageObjects.PageBase;
import PageObjects.SejarahKita.GameFragment;
import PageObjects.SejarahKita.LoginPage;
import PageObjects.SejarahKita.PlayingActivity;
import PageObjects.SejarahKita.ProfileFragment;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.*;
import io.appium.java_client.android.connection.ConnectionState;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.clipboard.ClipboardContentType;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.*;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import utils.JsonReader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

public class SejarahKita_Interuption_Test extends TestBase {


    LoginPage login;
    GameFragment gameFragment;
    ProfileFragment profileFragment;
    WebDriverWait wait;



    @BeforeTest(groups = "login")
    public  void beforeTest() throws IOException, InterruptedException {
        resetLogin();
        Thread.sleep(10000);
        Android_SejarahKita_Emulator_setUp();


    }
    @BeforeMethod
    public void beforeMethod() throws IOException, InterruptedException {

        resetLogin();
        Thread.sleep( 10000);
        driver.removeApp(sejarahKitaPackage);
        driver.installApp(sejarahKitaAppPath);
        driver.activateApp(sejarahKitaPackage);

        login = new LoginPage(driver);
        login.sendEmail("vanthony@student.ciputra.ac.id");
        login.sendPassword("va123456");
        login.tapLogin();
        login.waitForVisibility(login.fragGame);
    }

    @AfterMethod // setiap kali ada error pada test akan membuat screenshot untuk mengetahui dimana letak erornya
    public void afterMerthod(ITestResult result) throws IOException, InterruptedException {

        if (result.getStatus() == ITestResult.FAILURE) {
            String time = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
            String newFold = System.getProperty("user.dir")+"/data/SejarahKita_Android_TestFail/";
            String path_screenshot = newFold;
            File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            String filename= result.getName()+time.toString();
            File targetFile=new File(path_screenshot + filename +".jpg");
            FileUtils.copyFile(srcFile,targetFile);

        }



    }

    @AfterClass(groups = "logout" )
    public void logoutAndQuit() throws IOException {
        SoftAssert softAssert = new SoftAssert();
        ProfileFragment profileFragment = new ProfileFragment(driver);
        profileFragment.toProfileFragment();
        profileFragment.waitForVisibility(profileFragment.lblEmail);
        scrollDown();
        profileFragment.tapLogout();

        login = new LoginPage(driver);
        login.waitForVisibility(login.loginButton);
        softAssert.assertEquals(true,login.loginButton.isDisplayed());


//        driver.navigate().back();
//        driver.navigate().back(); // punya fungsi yang sama
        ((AndroidDriver)driver).pressKey(new KeyEvent(AndroidKey.BACK));
        ((AndroidDriver)driver).pressKey(new KeyEvent(AndroidKey.BACK)); // double press
    }


    @AfterTest
    public  void tearDown() throws Exception {
        writeLog();
        driver.quit();
    }

    @Test( priority= 0, groups ={"playGames","withInteruption"} )
    public void playCasual_phoneSleepInteruption() throws IOException, ParseException, InterruptedException {
        SoftAssert softAssert =  new SoftAssert();
        gameFragment = new GameFragment(driver);
        gameFragment.toGameFragment();
        gameFragment.tapCasualGame();// tap  on square area ,
        PlayingActivity playingActivity = new PlayingActivity(driver);

        int count = 0;
        int wrongCount =0;
        while( true ){
            try{
                if(playingActivity.tvScore.isDisplayed()){
                    break;
                }
            }catch (Exception ex){}
            String soal = playingActivity.getSoal();
            String jawaban = getJawaban(soal);


            if(new Random(1).nextInt(10) < 7){

                ((AndroidDriver) driver).lockDevice();
                softAssert.assertTrue( ((AndroidDriver) TestBase.driver).isDeviceLocked());
                Thread.sleep(2000);  // lock for 2 second
                ((AndroidDriver) TestBase.driver).unlockDevice();
            }
            wait = new WebDriverWait(driver,0);
            try{
                wait.until(ExpectedConditions.elementToBeClickable(By.id(playingActivity.inputJawaban.getId())));
                wait.until(ExpectedConditions.elementToBeClickable(By.id(playingActivity.btnSubmit.getId())));
            }catch (Exception ex){
                scrollDown();
            }
            playingActivity.click(playingActivity.inputJawaban);
            playingActivity.isiJawaban(jawaban);
            ((AndroidDriver)driver).pressKey(new KeyEvent(AndroidKey.ENTER));
            softAssert.assertTrue(((AndroidDriver)driver).isKeyboardShown() );
            ((AndroidDriver)driver).hideKeyboard();
            count++;
            playingActivity.tapBtnjawab();
        }

        System.out.println("soal count : "+count);
        System.out.println("Score : "+playingActivity.tvScore.getText());
        int score = (10*count) ;
        int scoreResult = Integer.parseInt(playingActivity.tvScore.getText());
        softAssert.assertEquals(score,scoreResult);
        playingActivity.backToHome();

        softAssert.assertAll();


    }

    @Test(priority= 1,groups ={"playGames","withInteruption"}  )
    public void playCasual_lowBattInterupt() throws IOException, ParseException {
        SoftAssert softAssert = new SoftAssert();
        int desiredBatteryCap = 15;
        ((AndroidDriver)driver).setPowerCapacity(desiredBatteryCap);
        ((AndroidDriver)driver).setPowerAC(PowerACState.OFF);
        int battery=Integer.parseInt(getBatteryCapasity());
        softAssert.assertEquals(desiredBatteryCap , battery);

        gameFragment = new GameFragment(driver);
        gameFragment.toGameFragment();
        gameFragment.tapCasualGame();// tap  on square area ,
        PlayingActivity playingActivity = new PlayingActivity(driver);

        int count = 0;
        int wrongCount =0;
        while( true ){
            try{
                if(playingActivity.tvScore.isDisplayed()){
                    break;
                }
            }catch (Exception ex){}
            String soal = playingActivity.getSoal();
            String jawaban = getJawaban(soal);
            wait = new WebDriverWait(driver,0);
            try{
                wait.until(ExpectedConditions.elementToBeClickable(By.id(playingActivity.inputJawaban.getId())));
                wait.until(ExpectedConditions.elementToBeClickable(By.id(playingActivity.btnSubmit.getId())));
            }catch (Exception ex){
                scrollDown();
            }


            if(count == 3){
                desiredBatteryCap = 5;
                ((AndroidDriver)driver).setPowerCapacity(desiredBatteryCap);
                ((AndroidDriver)driver).setPowerAC(PowerACState.OFF);
                battery=Integer.parseInt(getBatteryCapasity());
                softAssert.assertEquals(desiredBatteryCap , battery);
            }

            playingActivity.click(playingActivity.inputJawaban);
            playingActivity.isiJawaban(jawaban);
            ((AndroidDriver)driver).pressKey(new KeyEvent(AndroidKey.ENTER));
            ((AndroidDriver)driver).hideKeyboard();
            count++;
            playingActivity.tapBtnjawab();
        }

        System.out.println("soal count : "+count);
        System.out.println("Score : "+playingActivity.tvScore.getText());
        int score = (10*count) - (5 * wrongCount);
        int scoreResult = Integer.parseInt(playingActivity.tvScore.getText());
        softAssert.assertEquals(score,scoreResult);
        playingActivity.backToHome();

        ((AndroidDriver)driver).setPowerCapacity(100);
        ((AndroidDriver)driver).setPowerAC(PowerACState.ON);


        softAssert.assertAll();
    }

    @Test(priority= 2,groups ={"playGames","withInteruption"}  )
    public void playCasual_SMSInteruption() throws IOException, ParseException {
        SoftAssert softAssert = new SoftAssert();

        gameFragment = new GameFragment(driver);
        gameFragment.toGameFragment();
        gameFragment.waitForVisibility(gameFragment.btnEasy);
        scrollDown();
        gameFragment.tapCasualGame();// tap  on square area ,
        PlayingActivity playingActivity = new PlayingActivity(driver);

        int count = 0;
        int wrongCount =0;
        while( true ){
            try{
                if(playingActivity.tvScore.isDisplayed()){
                    break;
                }
            }catch (Exception ex){}
            String soal = playingActivity.getSoal();
            String jawaban = getJawaban(soal);
            wait = new WebDriverWait(driver,0);
            try{
                wait.until(ExpectedConditions.elementToBeClickable(By.id(playingActivity.inputJawaban.getId())));
                wait.until(ExpectedConditions.elementToBeClickable(By.id(playingActivity.btnSubmit.getId())));
            }catch (Exception ex){
                scrollDown();
            }

            if(count == 2){ // just read the message
                ((AndroidDriver)driver).setGsmSignalStrength(GsmSignalStrength.MODERATE);
                ((AndroidDriver)driver).sendSMS("16505556789","Hiii Bro");
                String state = ((AndroidDriver)driver).queryAppState(sejarahKitaPackage).toString();
                softAssert.assertEquals(state , "RUNNING_IN_FOREGROUND");
            }else if(count == 4){ // open sms app
                ((AndroidDriver)driver).sendSMS("16505556789","Bacaa plis ");
                ((AndroidDriver<?>) driver).openNotifications();
                playingActivity.tapSmsnotif();
                try{
                    playingActivity.waitForVisibility(playingActivity.sms);

                }catch (Exception ex){}

                String state = ((AndroidDriver)driver).queryAppState(sejarahKitaPackage).toString();
                softAssert.assertEquals(state , "RUNNING_IN_BACKGROUND");
                softAssert.assertNotEquals(sejarahKitaPackage , ((AndroidDriver)driver).getCurrentPackage());
                driver.activateApp(sejarahKitaPackage);
                playingActivity.waitForVisibility(playingActivity.inputJawaban);
                softAssert.assertEquals(sejarahKitaPackage , ((AndroidDriver)driver).getCurrentPackage());
            }

            playingActivity.click(playingActivity.inputJawaban);
            playingActivity.isiJawaban(jawaban);
            ((AndroidDriver)driver).pressKey(new KeyEvent(AndroidKey.ENTER));
            ((AndroidDriver)driver).hideKeyboard();
            count++;
            playingActivity.tapBtnjawab();
        }

        System.out.println("soal count : "+count);
        System.out.println("Score : "+playingActivity.tvScore.getText());
        int score = (10*count) - (5 * wrongCount);
        int scoreResult = Integer.parseInt(playingActivity.tvScore.getText());
        softAssert.assertEquals(score,scoreResult);
        playingActivity.backToHome();


        softAssert.assertAll();
    }

    @Test(priority= 3,groups ={"playGames","withInteruption"}  )
    public void playCasual_PhoneInteruption() throws IOException, ParseException, InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        gameFragment = new GameFragment(driver);
        gameFragment.toGameFragment();
        gameFragment.waitForVisibility(gameFragment.fragGame);
        scrollDown();
        gameFragment.tapCasualGame();// tap  on square area ,
        PlayingActivity playingActivity = new PlayingActivity(driver);

        int count = 0;
        int wrongCount =0;
        while( true ){
            try{
                if(playingActivity.tvScore.isDisplayed()){
                    break;
                }
            }catch (Exception ex){}
            String soal = playingActivity.getSoal();
            String jawaban = getJawaban(soal);
            wait = new WebDriverWait(driver,0);
            try{
                wait.until(ExpectedConditions.elementToBeClickable(By.id(playingActivity.inputJawaban.getId())));
                wait.until(ExpectedConditions.elementToBeClickable(By.id(playingActivity.btnSubmit.getId())));
            }catch (Exception ex){
                scrollDown();
            }

            if(count == 2){ // just read the message
//                ((AndroidDriver)driver).setGsmVoice(GsmVoiceState.ON);
                ((AndroidDriver)driver).makeGsmCall("5551234567", GsmCallActions.CALL);
                String state = ((AndroidDriver)driver).queryAppState(sejarahKitaPackage).toString();
                softAssert.assertEquals(state , "RUNNING_IN_FOREGROUND");
                Thread.sleep(5000);

                ((AndroidDriver)driver).makeGsmCall("5551234567", GsmCallActions.ACCEPT);

                state = ((AndroidDriver)driver).queryAppState(sejarahKitaPackage).toString();
                softAssert.assertEquals(state , "RUNNING_IN_FOREGROUND");
                Thread.sleep(5000);

                ((AndroidDriver)driver).makeGsmCall("5551234567", GsmCallActions.CANCEL);
                state = ((AndroidDriver)driver).queryAppState(sejarahKitaPackage).toString();
                softAssert.assertEquals(state , "RUNNING_IN_FOREGROUND");
            }

            playingActivity.click(playingActivity.inputJawaban);
            playingActivity.isiJawaban(jawaban);
            ((AndroidDriver)driver).pressKey(new KeyEvent(AndroidKey.ENTER));
            ((AndroidDriver)driver).hideKeyboard();
            count++;
            playingActivity.tapBtnjawab();
        }

        int score = (10*count) - (5 * wrongCount);
        int scoreResult = Integer.parseInt(playingActivity.tvScore.getText());
        softAssert.assertEquals(score,scoreResult);
        playingActivity.backToHome();


        softAssert.assertAll();


    }

    @Test(priority= 4,groups ={"playGames","withInteruption"}  )
    public void playCasualwithBackgroundInteruption() throws IOException, ParseException {
        SoftAssert softAssert = new SoftAssert();
        gameFragment = new GameFragment(driver);
        gameFragment.toGameFragment();
        gameFragment.waitForVisibility(gameFragment.fragGame);
        scrollDown();
        gameFragment.tapCasualGame();// tap  on square area ,
        PlayingActivity playingActivity = new PlayingActivity(driver);
        int count = 0;
        int wrongCount =0;
        while( true ){
            try{
                if(playingActivity.tvScore.isDisplayed()){
                    break;
                }
            }catch (Exception ex){}
            String soal = playingActivity.getSoal();
            String jawaban = getJawaban(soal);
            wait = new WebDriverWait(driver,0);
            try{
                wait.until(ExpectedConditions.elementToBeClickable(By.id(playingActivity.inputJawaban.getId())));
                wait.until(ExpectedConditions.elementToBeClickable(By.id(playingActivity.btnSubmit.getId())));
            }catch (Exception ex){
                scrollDown();
            }
            if(count ==5){
              driver.runAppInBackground(Duration.ofSeconds(60));
            }

            playingActivity.click(playingActivity.inputJawaban);
            playingActivity.isiJawaban(jawaban);
            ((AndroidDriver)driver).pressKey(new KeyEvent(AndroidKey.ENTER));
            ((AndroidDriver)driver).hideKeyboard();
            count++;
            playingActivity.tapBtnjawab();
        }

        int score = (10*count) - (5 * wrongCount);
        int scoreResult = Integer.parseInt(playingActivity.tvScore.getText());
        softAssert.assertEquals(score,scoreResult);
        playingActivity.backToHome();


        softAssert.assertAll();
    }

    public void testSMS() throws IOException, ParseException {
        SoftAssert softAssert = new SoftAssert();

        gameFragment = new GameFragment(driver);
        gameFragment.toGameFragment();
        gameFragment.tapCasualGame();// tap  on square area ,
        PlayingActivity playingActivity = new PlayingActivity(driver);

        int count = 0;
        int wrongCount =0;

        try{
            playingActivity.waitForVisibility(playingActivity.sms);

        }catch (Exception ex){}

        String state = ((AndroidDriver)driver).queryAppState(sejarahKitaPackage).toString();
        softAssert.assertEquals(state , "RUNNING_IN_BACKGROUND");
        driver.activateApp(sejarahKitaPackage);

        softAssert.assertAll();
    }

}
