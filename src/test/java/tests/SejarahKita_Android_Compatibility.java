package tests;

import PageObjects.SejarahKita.*;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;

public class SejarahKita_Android_Compatibility extends TestBase{
    @BeforeTest
    public void setUp() throws IOException {
        resetLogin();
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
    }



    @Test(priority = 0 )
    public void XHDPI_320_Density() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "7.1");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("udid", "emulator-5556");
        capabilities.setCapability("app",sejarahKitaErrAppPath);
        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
        Thread.sleep(5_000);
        SoftAssert softAssert =new SoftAssert();
        softAssert.assertEquals("RUNNING_IN_FOREGROUND",driver.queryAppState(sejarahKitaPackage).toString());
        softAssert.assertEquals(new Long(320), ((AndroidDriver)driver).getDisplayDensity());


        openPage();

        softAssert.assertAll();
        writeLog();
        driver.quit();
    }

    @Test(priority = 1 )
    public void XXHDPI_420_Density() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "7.1");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("udid", "emulator-5554"); // pastiin start  yang  gaada playstore e sek
        capabilities.setCapability("app",sejarahKitaErrAppPath);
        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
        Thread.sleep(10_000);
        SoftAssert softAssert =new SoftAssert();
        softAssert.assertEquals("RUNNING_IN_FOREGROUND",driver.queryAppState(sejarahKitaPackage).toString());
        softAssert.assertEquals(new Long(420),((AndroidDriver)driver).getDisplayDensity());


        openPage();

        softAssert.assertAll();
        writeLog();
        driver.quit();
    }

    private void openPage() throws InterruptedException {
        SoftAssert softAssert =new SoftAssert();
        softAssert.assertEquals("RUNNING_IN_FOREGROUND",driver.queryAppState(sejarahKitaPackage).toString());


        LoginPage login = new LoginPage(driver);
        login.sendEmail("vanthony@student.ciputra.ac.id");
        login.sendPassword("va123456");
        login.tapLogin();

        ProfileFragment profileFragment = new ProfileFragment(driver);
        profileFragment.toProfileFragment();
        Thread.sleep(2000);
        softAssert.assertEquals("vanthony@student.ciputra.ac.id", profileFragment.getLoggedInEmail());

        LeaderboardFragment leaderboardFragment = new LeaderboardFragment(driver);
        leaderboardFragment.toLeaderBoardFragment();
        leaderboardFragment.tapShowAll_EasyLeaderboard();
        leaderboardFragment.tapBack();
        leaderboardFragment.tapShowAll_HardLeaderboard();
        leaderboardFragment.tapBack();

        GameFragment gameFragment =  new GameFragment(driver);
        gameFragment.toGameFragment();
        gameFragment.tapEasyGame();
        gameFragment.tapCancelButton();
        gameFragment.tapHardGame();
        gameFragment.tapCancelButton();
        gameFragment.waitForVisibility(gameFragment.btnEasy);
        scrollDown();
        gameFragment.tapCasualGame();
        gameFragment.tapCancelButton();

        profileFragment.toProfileFragment();
        profileFragment.waitForVisibility(profileFragment.lblEmail);
        scrollDown();
        profileFragment.tapLogout();

    }
}
