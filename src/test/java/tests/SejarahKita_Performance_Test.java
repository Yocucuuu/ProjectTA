package tests;

import PageObjects.SejarahKita.GameFragment;
import PageObjects.SejarahKita.LoginPage;
import PageObjects.SejarahKita.PlayingActivity;
import PageObjects.SejarahKita.ProfileFragment;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.NetworkSpeed;
import io.appium.java_client.android.PowerACState;
import io.appium.java_client.android.connection.ConnectionState;
import org.apache.commons.io.FileUtils;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class SejarahKita_Performance_Test extends TestBase {

    // testCase and mainkan pada beberapa sinyal

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException {

        Android_SejarahKita_Emulator_setUp();
        driver.manage().timeouts().implicitlyWait(1 , TimeUnit.SECONDS);
        // set connection cuma pake data
        ((AndroidDriver)driver).setConnection(new ConnectionState(ConnectionState.DATA_MASK));
        ConnectionState con = ((AndroidDriver)driver).getConnection();
        System.out.println(con.isDataEnabled());
        System.out.println(con.isWiFiEnabled());
        System.out.println(con.isAirplaneModeEnabled());
        resetLogin();
        Thread.sleep( 10000);
    }


    @AfterTest
    public  void tearDown() throws IOException {
        ((AndroidDriver)driver).setConnection(new ConnectionState(ConnectionState.WIFI_MASK));
        driver.quit();
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
        resetLogin();
        Thread.sleep( 10000);
        driver.resetApp();
//        driver.removeApp(sejarahKitaPackage);
//        driver.installApp(sejarahKitaAppPath);
//        driver.activateApp(sejarahKitaPackage);
    }

    @AfterClass()
    public void Quit() throws Exception {
        ((AndroidDriver)driver).setConnection(new ConnectionState(ConnectionState.WIFI_MASK));
        writeLog();
    }

    private void testCase(NetworkSpeed networkSpeed) throws IOException, ParseException {
        ((AndroidDriver)driver).setNetworkSpeed( networkSpeed);
        SoftAssert softAssert = new SoftAssert();

        LoginPage login = new LoginPage(driver);
        login.sendEmail("vanthony@student.ciputra.ac.id");
        login.sendPassword("va123456");
        login.tapLogin();

        GameFragment game = new GameFragment(driver);
        game.waitForVisibility(game.fragGame);
        scrollDown();
        game.tapCasualGame();

        PlayingActivity playingActivity = new PlayingActivity(driver);
        int count = play();
        int score = (10*count);
        int scoreResult = Integer.parseInt(playingActivity.tvScore.getText());
        softAssert.assertEquals(score,scoreResult);
        playingActivity.backToHome();



        ProfileFragment profileFragment = new ProfileFragment(driver);
        profileFragment.toProfileFragment();
        profileFragment.waitForVisibility(profileFragment.lblEmail);
        scrollDown();
        profileFragment.tapLogout();

        login = new LoginPage(driver);
        login.waitForVisibility(login.tvEmail);
        softAssert.assertEquals(true,login.tvEmail.isDisplayed());
        softAssert.assertAll();
        //play
        // logout
    }

    @Test(priority = 0)
    public void FullPerformance() throws IOException, ParseException {
        testCase(NetworkSpeed.FULL);
    }
    @Test(priority = 1)
    public void LTEPerformance() throws IOException, ParseException {
        testCase( NetworkSpeed.LTE);
    }
    @Test(priority = 2)
    public void EVDOPerformance() throws IOException, ParseException {
        testCase(NetworkSpeed.EVDO);
    }
    @Test(priority = 3)
    public void HSDPAPerformance() throws IOException, ParseException {
        testCase(NetworkSpeed.HSDPA);
    }
    @Test(priority = 4)
    public void UMTSPerformance() throws IOException, ParseException {
        testCase(NetworkSpeed.UMTS);
    }
    @Test(priority = 5)
    public void EdgePerformance() throws IOException, ParseException {
        testCase(NetworkSpeed.EDGE);
    }
    @Test(priority = 6)
    public void GPRSPerformance() throws IOException, ParseException {
        testCase(NetworkSpeed.EDGE);
    }
    @Test(priority = 7)
    public void SCSDPerformance() throws IOException, ParseException {
        testCase(NetworkSpeed.SCSD);
    }
    @Test(priority = 8)
    public void GSMPerformance() throws IOException, ParseException {
        testCase(NetworkSpeed.GSM);
    }

    @Test(priority = 9, groups ={"playGames","lowPerformance"})
    public void playCasual_onLowBaterry() throws IOException, ParseException {
        ((AndroidDriver)driver).setNetworkSpeed(NetworkSpeed.FULL );
        SoftAssert softAssert = new SoftAssert();
        int desiredBatteryCap = 5;
        ((AndroidDriver)driver).setPowerCapacity(desiredBatteryCap);
        ((AndroidDriver)driver).setPowerAC(PowerACState.OFF);
        int battery=Integer.parseInt(getBatteryCapasity());
        softAssert.assertEquals(desiredBatteryCap , battery);

        LoginPage login = new LoginPage(driver);
        login.sendEmail("vanthony@student.ciputra.ac.id");
        login.sendPassword("va123456");
        login.tapLogin();

        GameFragment gameFragment = new GameFragment(driver);
        gameFragment.waitForVisibility(gameFragment.fragGame);
        gameFragment.toGameFragment();
        gameFragment.tapCasualGame();// tap  on square area ,
        PlayingActivity playingActivity = new PlayingActivity(driver);

        int count = play();
        int score = (10*count);
        int scoreResult = Integer.parseInt(playingActivity.tvScore.getText());
        softAssert.assertEquals(score,scoreResult);
        playingActivity.backToHome();

        battery=Integer.parseInt(getBatteryCapasity());
        softAssert.assertEquals(desiredBatteryCap , battery);


        softAssert.assertAll();
    }

    public int play() throws IOException, ParseException {
        int count =0 ;
        PlayingActivity playingActivity = new PlayingActivity(driver);
        while( true ){
            try{
                if(playingActivity.tvScore.isDisplayed()){
                    break;
                }
            }catch (Exception ex){}
            String soal = playingActivity.getSoal();
            String jawaban = getJawaban(soal);

            WebDriverWait wait = new WebDriverWait(driver,0);
            try{
                wait.until(ExpectedConditions.elementToBeClickable(By.id(playingActivity.inputJawaban.getId())));
                wait.until(ExpectedConditions.elementToBeClickable(By.id(playingActivity.btnSubmit.getId())));
            }catch (Exception ex){
                scrollDown();
            }


            count++;
            playingActivity.isiJawaban(jawaban);
            ((AndroidDriver)driver).hideKeyboard();
            playingActivity.tapBtnjawab();
        }

        return count;
    }

}
