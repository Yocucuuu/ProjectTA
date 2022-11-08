package tests;


import PageObjects.SejarahKita.*;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.*;
import io.appium.java_client.android.connection.ConnectionState;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.KeyEventFlag;
import io.appium.java_client.android.nativekey.KeyEventMetaModifier;
import io.appium.java_client.clipboard.ClipboardContentType;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.annotations.Optional;
import org.testng.asserts.SoftAssert;
import utils.JsonReader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class SejarahKita_Android_Test extends TestBase {


    /*
    * Set clipboard , getClipboard send from clipboard to PC
    *
    * //        System.out.println(listHistory.size());
//        System.out.println(listHistory.get(listHistory.size()-1).getAttribute("resource-id")); // cara paling enak cek element
    * */

    // done
//
//    public void appiumServerFeature() throws IOException {
//        Map<String, Object> caps = driver.getSessionDetails();
//        for (Map.Entry<String, Object> entry : caps.entrySet()) {
//            System.out.println(entry.getKey() + ":  " + entry.getValue());
//        }
//
//        File file  = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//        FileUtils.copyFile(file, new File("D:/TA/ProjectTA/data/Screenshot.jpg"));
//
//        String pageSource = driver.getPageSource();
//        System.out.println("-------------------");
//        System.out.println(pageSource);
//        System.out.println("------------------- end of page sourve");
////        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
////        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
////        driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
//
//        ScreenOrientation orientation = driver.getOrientation();
//        System.out.println("Orientatation :"+orientation.toString());
////        driver.rotate(ScreenOrientation.LANDSCAPE);
//
////        Location location = driver.location();  // untuk driverWeb ,
////        System.out.println("llocation :"+location.toString());
////        driver.setLocation(new Location(49, 123, 10)); // lat , alt , longitude
//
//
////        Set<String> logTypes = driver.manage().logs().getAvailableLogTypes();
////        logTypes.forEach((e) -> { System.out.println(e); });
////        System.out.println("----end of set of logtypes");
////        // get log of entries from server  --relaxed-security
////        LogEntries logEntries = driver.manage().logs().get("logcat");
////        LogEntries logEntries1 = driver.manage().logs().get("server");
////        LogEntries logEntries2 = driver.manage().logs().get("bugreport");
////        System.out.println(logEntries);
////        System.out.println(logEntries1);
////        System.out.println(logEntries2);
////
////
////        // store evene on appium server
////        CustomEvent evt = new CustomEvent();
////        evt.setEventName("funEvent");
////        evt.setVendor("appium");
////        driver.logEvent(evt);
////
//// get event on appium server
////        ServerEvents events = driver.getEvents();
////        System.out.println(events);
//
//
//        // set devices setting
////        driver.setSetting(Setting.WAIT_FOR_IDLE_TIMEOUT, 5000);
//
//        // get devices Settingss
//        Map<String, Object> settings = driver.getSettings();
//        for (Map.Entry<String, Object> entry : settings.entrySet()) {
//            System.out.println(entry.getKey() + ":  " + entry.getValue());
//        }
//
//        /*
//        Run a WebdriverIO script against the current session,
//        allowing execution of many commands in one Appium request.
//        * */
////        String script = "const el = await driver.$('~foo');\n"
////                + "await el.click();";
////        driver.executeDriverScript(script, new ScriptOptions().withTimeout(200));
//
//
//
//
//
//    }
//    public void appiumDeviceFeature() throws IOException {
//
//        //https://appium.io/docs/en/about-appium/api/
//
//        String currentPackage = ((AndroidDriver) driver).getCurrentPackage();
//        String currentActivity= ((AndroidDriver) driver).currentActivity();
//        ((AndroidDriver) driver).startActivity(new Activity("com.example", "ActivityName"));
//
//        driver.installApp(System.getProperty("user.dir")+"/apps/sejarahKita.apk");
//        driver.isAppInstalled("com.example.AppName");
//        driver.launchApp();
//        driver.runAppInBackground(Duration.ofSeconds(10));
//        driver.closeApp();
//        driver.resetApp();
//        driver.removeApp("com.example.AppName");
//        driver.activateApp("com.apple.Preferences");
//        driver.terminateApp("io.appium.android.apis");
//        driver.queryAppState("io.appium.android.apis");
//        Map<String, String> appStrings = driver.getAppStringMap("en", System.getProperty("user.dir") + "/data/appStringMap");
//        ((AndroidDriver<?>) driver).endTestCoverage("Intent", "/path");
//
//        ((AndroidDriver<?>) driver).getClipboard(ClipboardContentType.PLAINTEXT); // get plaintext
//        ((AndroidDriver<?>) driver).getClipboardText();
//
//        String originalInput = "test input";
//        String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
//        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
//        String decodedString = new String(decodedBytes);
//
//        ((AndroidDriver<?>) driver).setClipboard("label", ClipboardContentType.PLAINTEXT,decodedBytes);
//        ((AndroidDriver<?>) driver).setClipboardText("happy testing");
//
////        ((AndroidDriver<?>) driver).setPowerAC(PowerACState.OFF);
//        ((AndroidDriver<?>) driver).setPowerAC(PowerACState.ON);
//        ((AndroidDriver<?>) driver).setPowerCapacity(12);
//
//        ((AndroidDriver<?>)driver).pushFile("/data/local/tmp/foo.bar", new File("/Users/johndoe/files/foo.bar"));
//        byte[] fileBase64 =  ((AndroidDriver<?>)driver).pullFile("/path/to/device/foo.bar");
//        byte[] folder = ((AndroidDriver<?>)driver).pullFolder("/path/to/device/foo.bar");
//
////        ((IOSDriver<?>)driver).shake();
//        ((AndroidDriver<?>)driver).lockDevice();
//        ((AndroidDriver<?>)driver).unlockDevice();
//        boolean isLocked =  ((AndroidDriver<?>)driver).isDeviceLocked();
//
//
//        driver.rotate(ScreenOrientation.LANDSCAPE);
//        driver.rotate(ScreenOrientation.PORTRAIT);
//
////        ((AndroidDriver<?>)driver).pressKey(new KeyEvent(AndroidKey.HOME));
////        ((AndroidDriver<?>)driver).longPressKey(new KeyEvent(AndroidKey.HOME));
//        driver.hideKeyboard();
//        boolean isKeyboardShown =  ((AndroidDriver<?>)driver).isKeyboardShown();
//
//        ((AndroidDriver<?>)driver).toggleAirplaneMode();
//        ((AndroidDriver<?>)driver).toggleData();
//        ((AndroidDriver<?>)driver).toggleWifi();
//        ((AndroidDriver<?>)driver).toggleLocationServices();
//        ((AndroidDriver<?>)driver).sendSMS("555-123-4567", "Hey lol");
//        ((AndroidDriver<?>)driver).makeGsmCall("5551234567", GsmCallActions.CALL);
//        ((AndroidDriver<?>)driver).setGsmSignalStrength(GsmSignalStrength.GOOD);
//        ((AndroidDriver<?>)driver).setGsmVoice(GsmVoiceState.HOME);
//        ((AndroidDriver<?>)driver).setNetworkSpeed(NetworkSpeed.LTE);
//
//        List<List<Object>> performanceData = ((AndroidDriver<?>)driver).getPerformanceData("my.app.package", "cpuinfo", 5);
//        List<String> performanceTypes = ((AndroidDriver<?>)driver).getSupportedPerformanceDataTypes();
//
//        ((AndroidDriver<?>)driver).startRecordingScreen();
//        ((AndroidDriver<?>)driver).stopRecordingScreen();
//
//        ((AndroidDriver<?>)driver).fingerPrint(1);
////        driver.performTouchID(false);// IOS
//
//
//        ((AndroidDriver<?>)driver).openNotifications();
//        Map<String, Map<String, Object>> systemBars = ((AndroidDriver<?>)driver).getSystemBars();
//
//        String time = driver.getDeviceTime();
//        ((AndroidDriver<?>)driver).getDisplayDensity();
//
//
//        MobileElement element = (MobileElement) driver.findElementByClassName("SomeClassName");
//        String elText = element.getText();
//        String tagName = element.getTagName();
//        String getAttribut = element.getAttribute("content-desc");
//        boolean isSelected = element.isSelected();
//        boolean isEnabled = element.isEnabled();
//        boolean isDisplayed = element.isDisplayed();
//        Dimension elementSize = element.getSize();
//        Rectangle rect = element.getRect();
//        String cssProperty = element.getCssValue("style");
//        //getLocationinView not supported in java
//
//        element.submit();
//        MobileElement currentElement = (MobileElement) driver.switchTo().activeElement();
//
//        MobileElement elementOne = (MobileElement) driver.findElementByClassName("SomeClassName");
//        MobileElement elementTwo = (MobileElement) driver.findElementByClassName("SomeOtherClassName");
//        boolean isEqual = elementOne.equals(elementTwo);
//
//
////        String context = driver.getContext();
////        Set<String> contextNames = driver.getContextHandles();
////        driver.context("NATIVE_APP");
//
//
//        // move to, di dokumentasinya gerakan mouse tidak compatibel dengan mobile
////        Actions action = new Actions(driver);
////        action.moveToElement(element);
////        action.clickAndHold();
////
////        action.click();
////        action.doubleClick();
////
////        //button down
////        action.clickAndHold();
////
////        // button up
////        action.moveToElement(element, 1, 2);
////        action.clickAndHold();
////        action.moveToElement(element, 10, 10);
////        action.release();
////
////
////        action.perform();
//        // rasaa e gapernah pake inni deh , ini soal e buat web
//
//
//
////        TouchActions action = new TouchActions(driver);
////        action.singleTap(element);
////        action.doubleTap(element);
////
////        // move
////        action.down(10,10);
////        action.move(50,50);
////
////        //touch up
////        action.down(10, 10);
////        action.up(20, 20);
////
////        //longpress
////        action.longPress(element);
////
////        action.scroll(element, 10, 100);
////
////        action.flick(element, 1, 10, 10);
////
////
////
////        // Multi Touch
////        TouchAction actionOne = new TouchAction(driver);
////        actionOne.press( PointOption.point(10, 10));
////        actionOne.moveTo(PointOption.point(10, 100));
////        actionOne.release();
////        TouchAction actionTwo = new TouchAction(driver);
////        actionTwo.press(PointOption.point(20, 20));
////        actionTwo.moveTo(PointOption.point(20, 200));
////        actionTwo.release();
////        MultiTouchAction mulAction = new MultiTouchAction(driver);
////        mulAction.add(actionOne);
////        mulAction.add(actionTwo);
////        action.perform();
////
////        //Tooce
////        actionOne.press(PointOption.point(10, 10));
////        actionOne.moveTo( PointOption.point(10, 100));
////        actionOne.release();
////
////
////
////        action.perform();
//
//    }
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

            wait = new WebDriverWait(driver,0);
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

    LoginPage login;
    GameFragment gameFragment;
    ProfileFragment profileFragment;
    WebDriverWait wait;

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException {
//        Android_SejarahKita_Emulator_setUp();
        resetLogin();
        Android_SejarahKita_SamsungC9_setUp();
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        login = new LoginPage(driver);
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
//        driver.resetApp();

        driver.closeApp();
        driver.activateApp(sejarahKitaPackage);
//        driver.launchApp();

        Thread.sleep(10_000);
    }

    @AfterClass(groups = "logout" , dependsOnGroups = "loginSuccess")
    public void logoutAndQuit() throws IOException {
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

    @AfterTest
    public  void tearDown() throws Exception {
        writeLog();
        driver.quit();
    }

    @Test(priority = 0 ,groups = "login")
    public void successLogin() throws IOException, InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        ((AndroidDriver)driver).setClipboardText("vanthony@student.ciputra.ac.id");
        String emailFromClipboard = ((AndroidDriver)driver).getClipboardText();
        login.sendEmail(emailFromClipboard);
        login.sendPassword("va123456");
        login.tapLogin();

        profileFragment = new ProfileFragment(driver);
        login.toProfileFragment();
        softAssert.assertTrue(profileFragment.fragProfile.isSelected() );
        softAssert.assertAll();
    }

    @Test(priority = 3 ,groups = "cancelPlay" , dependsOnGroups = "login")
    public void cancelPlayEasy() {
            gameFragment = new GameFragment(driver);
            gameFragment.toGameFragment();
            driver.findElement(By.id("cons_cv_easy_game_fragment"));
            gameFragment.tapEasyGame(); // tap  on square area ,
            gameFragment.tapCancelButton(); // back to game Fragment
            String selected = gameFragment.getAttribute(gameFragment.fragGame , "selected");
            Assert.assertEquals("true",selected);
    }
    @Test(priority = 3,groups = "cancelPlay",dependsOnGroups = "login")
    public void cancelPlayHard() {
        gameFragment = new GameFragment(driver);
        gameFragment.toGameFragment();
        driver.findElement(By.id("cons_cv_hard_game_fragment"));
        gameFragment.tapHardGame(); // tap  on square area ,
        gameFragment.tapCancelButton(); // back to game Fragment
        String selected = gameFragment.getAttribute(gameFragment.fragGame , "selected");
        Assert.assertEquals("true",selected);

    }
    @Test(priority = 3,groups = "cancelPlay",dependsOnGroups = "login")
    public void cancelPlayCasual() {
        gameFragment = new GameFragment(driver);
        gameFragment.toGameFragment();
        driver.findElement(By.id("cons_cv_easy_game_fragment"));
        scrollDown();
        gameFragment.tapCasualGame(); // tap  on square area ,
        gameFragment.tapCancelButton(); // back to game Fragment
        String selected = gameFragment.getAttribute(gameFragment.fragGame , "selected");
        Assert.assertEquals("true",selected);

    }
//        max 39 soal no slah score 195 -> 5*39 - 0*5
    /**/


    @Test(priority = 4, dependsOnGroups = "login" , groups="playGames")
    public void playEasyCheckOnHistory() throws IOException, ParseException, InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        gameFragment = new GameFragment(driver);
        gameFragment.toGameFragment();
        gameFragment.tapEasyGame(); // tap  on square area ,
        PlayingActivity playingActivity = new PlayingActivity(driver);
        int count = play();
        int wrongCount =0;

        int score = (5*count) - (5 * wrongCount);
        int scoreResult = Integer.parseInt(playingActivity.tvScore.getText());
        softAssert.assertEquals(score,scoreResult);
        playingActivity.backToHome();

        // cek history
        playingActivity.toProfileFragment();
        ProfileFragment profile = new ProfileFragment(driver);
        profile.toProfileFragment();
        profile.waitForVisibility(profile.lblEmail);
        scrollDown();
        profile.tapHistory();

        HistoryActivity history = new HistoryActivity(driver);
        scrollToEnd();
        List<MobileElement> listHistory = driver.findElements(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup"));
        MobileElement Rvscore =  listHistory.get(listHistory.size()-1).findElement(By.xpath("//android.widget.FrameLayout/android.view.ViewGroup/android.widget.TextView[3]"));

        int historyScore = Integer.parseInt(Rvscore.getAttribute("text"));
        softAssert.assertEquals(score,historyScore);
        history.back();
        softAssert.assertAll();
    }
    @Test(priority = 4 ,groups="playGames",  dependsOnGroups = "login")
    public void playHardCheckOnHistory() throws IOException, ParseException, InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        gameFragment = new GameFragment(driver);
        gameFragment.toGameFragment();
        gameFragment.tapHardGame(); // tap  on square area ,
        PlayingActivity playingActivity = new PlayingActivity(driver);
        int count = play();
        int wrongCount =0;

        System.out.println("soal count : "+count);
        System.out.println("Score : "+playingActivity.tvScore.getText());
        int score = (5*count) - (5 * wrongCount);
        int scoreResult = Integer.parseInt(playingActivity.tvScore.getText());
        softAssert.assertEquals(score,scoreResult);
        playingActivity.backToHome();

        // cek history
        playingActivity.toProfileFragment();
        ProfileFragment profile = new ProfileFragment(driver);
        profile.waitForVisibility(profile.lblEmail);
        scrollDown();
        profile.tapHistory();

        HistoryActivity history = new HistoryActivity(driver);
        scrollToEnd();
        List<MobileElement> listHistory = driver.findElements(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup"));
        MobileElement Rvscore = (MobileElement) listHistory.get(listHistory.size()-1).findElement(By.xpath("//android.widget.FrameLayout/android.view.ViewGroup/android.widget.TextView[3]"));
        int historyScore = Integer.parseInt(Rvscore.getAttribute("text"));
        softAssert.assertEquals(score,historyScore);
        history.back();
        softAssert.assertAll();


    }
    @Test(priority = 4, dependsOnGroups = "login" , groups="playGames")
    public void playEasyCheckOnHistoryAnswersome() throws IOException, ParseException, InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        gameFragment = new GameFragment(driver);
        gameFragment.toGameFragment();
        gameFragment.tapEasyGame(); // tap  on square area ,
        PlayingActivity playingActivity = new PlayingActivity(driver);
        int count = 0;
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


            if(count < 11 ){
                playingActivity.click(playingActivity.inputJawaban);
                softAssert.assertTrue(((AndroidDriver)driver).isKeyboardShown());
                ((AndroidDriver)driver).getKeyboard().pressKey(jawaban);
                ((AndroidDriver)driver).hideKeyboard();
                count++;
            }else{
                playingActivity.click(playingActivity.inputJawaban);
                softAssert.assertTrue(((AndroidDriver)driver).isKeyboardShown());
                playingActivity.isiJawaban("wrong");
                ((AndroidDriver)driver).hideKeyboard();
            }
            playingActivity.tapBtnjawab();
        }
        System.out.println("soal count : "+count);
        System.out.println("Score : "+playingActivity.tvScore.getText());
        int score = (5*count);
        int scoreResult = Integer.parseInt(playingActivity.tvScore.getText());
        softAssert.assertEquals(score,scoreResult);
        playingActivity.backToHome();

        // cek history
        playingActivity.toProfileFragment();
        ProfileFragment profile = new ProfileFragment(driver);
        profile.waitForVisibility(profile.lblEmail);
        scrollDown();
        profile.tapHistory();

        HistoryActivity history = new HistoryActivity(driver);
        scrollToEnd();
        List<MobileElement> listHistory = driver.findElements(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup"));
        MobileElement Rvscore = (MobileElement) listHistory.get(listHistory.size()-1).findElement(By.xpath("//android.widget.FrameLayout/android.view.ViewGroup/android.widget.TextView[3]"));

        int historyScore = Integer.parseInt(Rvscore.getAttribute("text"));
        softAssert.assertEquals(score,historyScore);
        history.back();
        softAssert.assertAll();


    }
    @Test(priority = 4, dependsOnGroups = "login" , groups="playGames")
    public void playHardCheckOnHistoryAnswersome() throws IOException, ParseException, InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        gameFragment = new GameFragment(driver);
        gameFragment.toGameFragment();
        gameFragment.tapHardGame(); // tap  on square area ,
        PlayingActivity playingActivity = new PlayingActivity(driver);
        int count = 0;
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

            if(count < 11 ){
                playingActivity.isiJawaban(jawaban);
                ((AndroidDriver)driver).hideKeyboard();
                count++;
            }else{
                playingActivity.isiJawaban("wrong");
                ((AndroidDriver)driver).hideKeyboard();
            }
            playingActivity.tapBtnjawab();
        }
        System.out.println("soal count : "+count);
        System.out.println("Score : "+playingActivity.tvScore.getText());
        int score = (5*count) ;
        int scoreResult = Integer.parseInt(playingActivity.tvScore.getText());
        softAssert.assertEquals(score,scoreResult);
        playingActivity.backToHome();

        // cek history
        playingActivity.toProfileFragment();
        ProfileFragment profile = new ProfileFragment(driver);
        profile.waitForVisibility(profile.lblEmail);
        scrollDown();
        profile.tapHistory();

        HistoryActivity history = new HistoryActivity(driver);
        scrollToEnd();
        List<MobileElement> listHistory = driver.findElements(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup"));
        MobileElement Rvscore = (MobileElement) listHistory.get(listHistory.size()-1).findElement(By.xpath("//android.widget.FrameLayout/android.view.ViewGroup/android.widget.TextView[3]"));

        int historyScore = Integer.parseInt(Rvscore.getAttribute("text"));
        softAssert.assertEquals(score,historyScore);
        history.back();
        softAssert.assertAll();


    }

    @Test(priority = 4, dependsOnGroups = "login",groups="playGames")
    public void playCasualwithHintAndCheckAndCheckScore() throws IOException, ParseException, InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        gameFragment = new GameFragment(driver);
        gameFragment.toGameFragment();
        driver.findElement(By.id("cons_cv_easy_game_fragment"));
        scrollDown();
        gameFragment.tapCasualGame();// tap  on square area ,
        PlayingActivity playingActivity = new PlayingActivity(driver);
        int count = 0;

        while( true ){
            try{
                if(playingActivity.tvScore.isDisplayed()){
                    break;
                }
            }catch (Exception ex){}
            String soal = playingActivity.getSoal();

            playingActivity.openHintTray();
            String jawaban = playingActivity.getHint();

            playingActivity.closeHintTray();

            wait = new WebDriverWait(driver,0);
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
        System.out.println("soal count : "+count);
        System.out.println("Score : "+playingActivity.tvScore.getText());
        int score = 0; // karna pakai hint terus
        int scoreResult = Integer.parseInt(playingActivity.tvScore.getText());
        softAssert.assertEquals(score,scoreResult);
        playingActivity.backToHome();

        // cek history
        playingActivity.toProfileFragment();
        ProfileFragment profile = new ProfileFragment(driver);
        profile.waitForVisibility(profile.lblEmail);
        scrollDown();
        profile.tapHistory();

        HistoryActivity history = new HistoryActivity(driver);
        scrollToEnd();
        List<MobileElement> listHistory = driver.findElements(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup"));
        MobileElement Rvscore = (MobileElement) listHistory.get(listHistory.size()-1).findElement(By.xpath("//android.widget.FrameLayout/android.view.ViewGroup/android.widget.TextView[3]"));
        int historyScore = Integer.parseInt(Rvscore.getAttribute("text"));
        softAssert.assertEquals(score,historyScore);
        history.back();
        softAssert.assertAll();

    }
    @Test(priority = 4, dependsOnGroups = "login",groups="playGames")
    public void playCasualCheckAndCheckScore() throws IOException, ParseException, InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        gameFragment = new GameFragment(driver);
        gameFragment.toGameFragment();
        driver.findElement(By.id("cons_cv_easy_game_fragment"));
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

            count++;
            playingActivity.isiJawaban(jawaban);
            ((AndroidDriver)driver).hideKeyboard();
            playingActivity.tapBtnjawab();
        }
        System.out.println("soal count : "+count);
        System.out.println("Score : "+playingActivity.tvScore.getText());
        int score = (10*count) - (5 * wrongCount);
        int scoreResult = Integer.parseInt(playingActivity.tvScore.getText());
        softAssert.assertEquals(score,scoreResult);
        playingActivity.backToHome();

        // cek history
        playingActivity.toProfileFragment();
        ProfileFragment profile = new ProfileFragment(driver);
        profile.waitForVisibility(profile.lblEmail);
        scrollDown();
        profile.tapHistory();

        HistoryActivity history = new HistoryActivity(driver);
        scrollToEnd();
        List<MobileElement> listHistory = driver.findElements(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup"));
        MobileElement Rvscore = (MobileElement) listHistory.get(listHistory.size()-1).findElement(By.xpath("//android.widget.FrameLayout/android.view.ViewGroup/android.widget.TextView[3]"));
        int historyScore = Integer.parseInt(Rvscore.getAttribute("text"));
        softAssert.assertEquals(score,historyScore);
        history.back();
        softAssert.assertAll();

    }
    @Test(priority = 4 ,  dependsOnGroups = "login" , groups ="playGames")
    public void playCasualOnLandscape() throws IOException, ParseException {
        SoftAssert softAssert =  new SoftAssert();
        gameFragment = new GameFragment(driver);
        gameFragment.toGameFragment();
        driver.findElement(By.id("cons_cv_easy_game_fragment"));
        scrollDown();
        gameFragment.tapCasualGame();// tap  on square area ,

        PlayingActivity playingActivity = new PlayingActivity(driver);
        driver.rotate(ScreenOrientation.LANDSCAPE);
        softAssert.assertEquals(driver.getOrientation() , ScreenOrientation.LANDSCAPE);

        int count = 0;

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
            playingActivity.isiJawaban(jawaban);
            ((AndroidDriver)driver).pressKey(new KeyEvent(AndroidKey.ENTER));
            count++;
            playingActivity.tapBtnjawab();
        }

        System.out.println("soal count : "+count);
        System.out.println("Score : "+playingActivity.tvScore.getText());
        int score = (10*count) ;
        int scoreResult = Integer.parseInt(playingActivity.tvScore.getText());

        driver.rotate(ScreenOrientation.PORTRAIT);
        softAssert.assertEquals(driver.getOrientation() , ScreenOrientation.PORTRAIT);
        softAssert.assertEquals(score,scoreResult);
        playingActivity.backToHome();

        softAssert.assertAll();


    }


    @Test(priority = 5,dependsOnGroups = "login",groups="showLeaderboard")
    public void showLeaderEasyboard() throws InterruptedException {

        LeaderboardFragment leaderboardFragment = new LeaderboardFragment(driver);
        leaderboardFragment.toLeaderBoardFragment();
        leaderboardFragment.tapShowAll_EasyLeaderboard();


        leaderboardFragment.tapBack();
    }
    @Test(priority = 5,dependsOnGroups = "login",groups="showLeaderboard")
    public void showLeaderHardboard() throws InterruptedException {
        LeaderboardFragment leaderboardFragment = new LeaderboardFragment(driver);
        leaderboardFragment.toLeaderBoardFragment();
        leaderboardFragment.tapShowAll_HardLeaderboard();

        leaderboardFragment.tapBack();
    }

    @Test(priority = 6,dependsOnGroups = "login",groups="shoPlayingHistory")
    public void showPlayinghistory() throws InterruptedException {

        ProfileFragment profile  = new ProfileFragment(driver);
        profile.toProfileFragment();
        profile.waitForVisibility(profile.lblEmail);
        scrollDown();
        profile.tapHistory();

        HistoryActivity history = new HistoryActivity(driver);
        history.back();

    }








}
