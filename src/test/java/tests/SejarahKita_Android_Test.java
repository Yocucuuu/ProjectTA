package tests;


import PageObjects.SejarahKita.*;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.*;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.clipboard.ClipboardContentType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;
import org.testng.annotations.Optional;
import org.testng.asserts.SoftAssert;
import utils.JsonReader;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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

    private static Object[][] kunci() throws IOException, ParseException {
        return JsonReader.getJSONdata(System.getProperty("user.dir") + "/data/kunciSejarahkita.json"
                , "kunci", new String[]{"pertanyaan_kalimat", "kunci_jawaban"});
    }
    private static String getJawaban(String soal) throws IOException, ParseException {
        String jawaban= "";
        for(int i=0;i<kunci().length;i++){
            if(kunci()[i][0].equals(soal)){
//                System.out.printf("Pertanyaan : %s  \n",passData()[i][0]);
//                System.out.printf("Jawaban    : %s  \n\n",passData()[i][1]);
                jawaban = (String) kunci()[i][1];
                break;
            }

        }
        return jawaban;
    }
    private void resetLogin() throws IOException {
        URL url = new URL("https://sejarahkita.my.id/resetLogins");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
    }
    private void scrollDown(){
        AndroidTouchAction actions = new AndroidTouchAction(driver);

        Dimension dimension = driver.manage().window().getSize();
        int scrollStart = (int) (dimension.getHeight() * 0.8);
        int scrollEnd = (int) (dimension.getHeight()*0.1);

        actions = new AndroidTouchAction(driver)
                .press(PointOption.point(0, scrollStart))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(0, scrollEnd))
                .release()
                .perform();
    }

    LoginPage login;
    GameFragment gameFragment;
    ProfileFragment profileFragment;


    @BeforeTest
    public void beforeTest() throws IOException {
        resetLogin();
        Android_SejarahKita_Emulator_setUp();
        login = new LoginPage(driver);


    }

    @Test()
    public void emptyField() throws IOException {


        login.clear(login.tvEmail);
        login.clear(login.tvPass);
        login.tapLogin();

    }
    @Test()
    public void emptyPassField() {
        login.clear(login.tvEmail);
        login.clear(login.tvPass);
        login.sendEmail("vanthony@student.ciputra.ac.id");
        login.tapLogin();
        Assert.assertEquals("Please fill all field" , login.getToastText(login.toastMessage));
    }
    @Test()
    public void emptyEmailField() {
        login.clear(login.tvEmail);
        login.clear(login.tvPass);
        login.sendPassword("va123456");
        login.tapLogin();
        Assert.assertEquals("Please fill all field" , login.getToastText(login.toastMessage));
    }
    @Test()
    public void wrongEmailLogin() throws IOException {
        login.sendEmail("vanthony@student.ciputra.ac.idsss");
        login.sendPassword("va123456");
        login.tapLogin();
        Assert.assertEquals("Login Failed" , login.getToastText(login.toastMessage));
    }
    @Test()
    public void wrongPassLogin() throws IOException {
        login.sendEmail("vanthony@student.ciputra.ac.id");
        login.sendPassword("va123456sss");
        login.tapLogin();
        Assert.assertEquals("Login Failed" , login.getToastText(login.toastMessage));
    }
    @Test()
    public void loginWithUsedAccount() throws IOException {
        login.clear(login.tvEmail);
        login.clear(login.tvPass);
        login.sendEmail("narianto@student.ciputra.ac.id");
        login.sendPassword("va123456");
        login.tapLogin();
        Assert.assertEquals("Login Failed" , login.getToastText(login.toastMessage));
    }
    @Test()
    public void loginWithSuspendedAccount() throws IOException {
        login.clear(login.tvEmail);
        login.clear(login.tvPass);
        login.sendEmail("pkrishnacahya@student.ciputra.ac.id");
        login.sendPassword("pk025758");
        login.tapLogin();
        Assert.assertEquals("Login Failed" , login.getToastText(login.toastMessage));
    }
    @Test(priority = 1, enabled = true ,groups = "login")
    public void successLogin() throws IOException {
        SoftAssert softAssert = new SoftAssert();
        ((AndroidDriver)driver).setClipboardText("vanthony@student.ciputra.ac.id");
        String emailFromClipboard = ((AndroidDriver)driver).getClipboardText();
        login.sendEmail(emailFromClipboard);
        login.sendPassword("va123456");
        login.tapLogin();
        profileFragment = new ProfileFragment(driver);
        softAssert.assertTrue(profileFragment.fragProfile.isSelected() );
        login.toProfileFragment();
        WebDriverWait WebWait = new WebDriverWait(driver,10);
        WebWait.withTimeout(Duration.ofSeconds(3));
        softAssert.assertEquals("vanthony@student.ciputra.ac.id", profileFragment.getLoggedInEmail());

    }

    @Test(dependsOnGroups = "login")
    public void getPerformanceData() {
        List<String> performanceTypes = ((AndroidDriver)driver).getSupportedPerformanceDataTypes();
        performanceTypes.forEach((dataType)-> {
            System.out.println("Data Type  : " +dataType);
            try{
                List<List<Object>> performanceData = ((AndroidDriver)driver).getPerformanceData(sejarahKitaPackage, dataType, 5);
                performanceData.forEach((m)->{
                    System.out.println("performance Data : "+m.size()+" -- "+m.toString());
                });
            }catch (Exception e){
                System.out.println("Error : "+e);
            }

        });
        /* cpuinfo
                memoryinfo
        batteryinfo
                networkinfo*/

    }

    @Test(priority = 2 ,dependsOnGroups = "login")
    public void cancelPlayEasy() {
            gameFragment = new GameFragment(driver);
            gameFragment.toGameFragment();
            gameFragment.tapEasyGame(); // tap  on square area ,
            gameFragment.tapCancelButton(); // back to game Fragment
            String selected = gameFragment.getAttribute(gameFragment.fragGame , "selected");
            Assert.assertEquals("true",selected);
    }
    @Test(priority = 2,dependsOnGroups = "login")
    public void cancelPlayHard() {
        gameFragment = new GameFragment(driver);
        gameFragment.toGameFragment();
        gameFragment.tapHardGame(); // tap  on square area ,
        gameFragment.tapCancelButton(); // back to game Fragment
        String selected = gameFragment.getAttribute(gameFragment.fragGame , "selected");
        Assert.assertEquals("true",selected);

    }
//        tiap soal easy nilai 10 , kalo salah -1
    @Test(priority = 3,dependsOnGroups = "login")
    public void playEasyCheckAndCheckScore() throws IOException, ParseException, InterruptedException {
        gameFragment = new GameFragment(driver);
        gameFragment.toGameFragment();
        gameFragment.tapEasyGame(); // tap  on square area ,


        PlayingActivity playingActivity = new PlayingActivity(driver);
        int count = 0;
        while( playingActivity.tvSoal  != null ){
            try{
                if(playingActivity.tvScore.isDisplayed()){
                    break;
                }
            }catch (Exception ex){}
            scrollDown();
            count++;
            String soal = playingActivity.getSoal();
            String jawaban = getJawaban(soal);
            playingActivity.wait.withTimeout(Duration.ofSeconds(2));
            if(count< 11){
                playingActivity.isiJawaban(jawaban);
            }else{
                playingActivity.isiJawaban("salah");
            }


            playingActivity.tapBtnjawab();
        }
        System.out.println("soal count : "+count);
        System.out.println("Score : "+playingActivity.tvScore.getText());
        int scoreResult = Integer.parseInt(playingActivity.tvScore.getText());
        playingActivity.backToHome();
        Assert.assertEquals(scoreResult,(5*count)-(5*5));


    }
    @Test(priority = 3,dependsOnGroups = "login")
    public void playHardCheckAndCheckScore() throws IOException, ParseException, InterruptedException {
        gameFragment = new GameFragment(driver);
        gameFragment.toGameFragment();
        gameFragment.tapHardGame(); // tap  on square area ,


        PlayingActivity playingActivity = new PlayingActivity(driver);
        int count = 0;
        while( playingActivity.tvSoal  != null ){
            try{
                if(playingActivity.tvScore.isDisplayed()){
                    break;
                }
            }catch (Exception ex){}
            scrollDown();
            count++;
            String soal = playingActivity.getSoal();
            String jawaban = getJawaban(soal);
            playingActivity.wait.withTimeout(Duration.ofSeconds(2));
            if(count< 11){
                playingActivity.isiJawaban(jawaban);
            }else{
                playingActivity.isiJawaban("salah");
            }


            playingActivity.tapBtnjawab();
        }
        System.out.println("soal count : "+count);
        System.out.println("Score : "+playingActivity.tvScore.getText());
        int scoreResult = Integer.parseInt(playingActivity.tvScore.getText());
        Assert.assertEquals(scoreResult,(5*count)-(3*5));
        playingActivity.backToHome();


    }
    @Test(priority = 3,dependsOnGroups = "login")
    public void playEasyCheckOnHistory() throws IOException, ParseException, InterruptedException {
        gameFragment = new GameFragment(driver);
        gameFragment.toGameFragment();
        gameFragment.tapEasyGame(); // tap  on square area ,


        PlayingActivity playingActivity = new PlayingActivity(driver);
        int count = 0;
        while( playingActivity.tvSoal  != null ){

            try{
                if(playingActivity.tvScore.isDisplayed()){
                    break;
                }
            }catch (Exception ex){}
            scrollDown();
            count++;
            String soal = playingActivity.getSoal();
            String jawaban = getJawaban(soal);
            playingActivity.wait.withTimeout(Duration.ofSeconds(2));
            if(count< 11){
                playingActivity.isiJawaban(jawaban);
            }else{
                playingActivity.isiJawaban("salah");
            }

            playingActivity.tapBtnjawab();
        }
        System.out.println("soal count : "+count);
        System.out.println("Score : "+playingActivity.tvScore.getText());
        int scoreResult = Integer.parseInt(playingActivity.tvScore.getText());
        playingActivity.backToHome();

        // cek history
        playingActivity.toProfileFragment();
        ProfileFragment profile = new ProfileFragment(driver);
        scrollDown();
        profile.tapHistory();

        HistoryActivity history = new HistoryActivity(driver);
        scrollDown();
        scrollDown();
        scrollDown();
        List<MobileElement> listHistory = driver.findElements(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup"));
        MobileElement Rvscore = (MobileElement) listHistory.get(listHistory.size()-1).findElement(By.xpath("//android.widget.FrameLayout/android.view.ViewGroup/android.widget.TextView[3]"));
//        System.out.println("Score : "+Rvscore.getAttribute("resource-id"));
//        System.out.println("Score : "+Rvscore.getAttribute("text"));
        int historyScore = Integer.parseInt(Rvscore.getAttribute("text"));
        Assert.assertEquals(historyScore,scoreResult);
        history.back();




    }
    @Test(priority = 3,dependsOnGroups = "login")
    public void playHardCheckOnHistory() throws IOException, ParseException, InterruptedException {
        gameFragment = new GameFragment(driver);
        gameFragment.toGameFragment();
        gameFragment.tapHardGame(); // tap  on square area ,


        PlayingActivity playingActivity = new PlayingActivity(driver);
        int count = 0;
        while( playingActivity.tvSoal  != null ){
            count++;
            try{
                if(playingActivity.tvScore.isDisplayed()){
                    break;
                }
            }catch (Exception ex){}
            scrollDown();
            String soal = playingActivity.getSoal();
            String jawaban = getJawaban(soal);
            playingActivity.wait.withTimeout(Duration.ofSeconds(2));
            if(count< 11){
                playingActivity.isiJawaban(jawaban);
            }else{
                playingActivity.isiJawaban("salah");
            }

            playingActivity.tapBtnjawab();
        }
        System.out.println("soal count : "+count);
        System.out.println("Score : "+playingActivity.tvScore.getText());
        int scoreResult = Integer.parseInt(playingActivity.tvScore.getText());
        playingActivity.backToHome();

        // cek history
        playingActivity.toProfileFragment();
        ProfileFragment profile = new ProfileFragment(driver);
        scrollDown();
        profile.tapHistory();

        HistoryActivity history = new HistoryActivity(driver);
        scrollDown();
        scrollDown();
        scrollDown();
        List<MobileElement> listHistory = driver.findElements(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup"));
        MobileElement Rvscore = (MobileElement) listHistory.get(listHistory.size()-1).findElement(By.xpath("//android.widget.FrameLayout/android.view.ViewGroup/android.widget.TextView[3]"));
//        System.out.println("Score : "+Rvscore.getAttribute("resource-id"));
//        System.out.println("Score : "+Rvscore.getAttribute("text"));
        int historyScore = Integer.parseInt(Rvscore.getAttribute("text"));
        Assert.assertEquals(historyScore,scoreResult);
        history.back();




    }
    @Test(priority = 4,dependsOnGroups = "login")
    public void showLeaderEasyboard() throws InterruptedException {

        LeaderboardFragment leaderboardFragment = new LeaderboardFragment(driver);
        leaderboardFragment.toLeaderBoardFragment();
        leaderboardFragment.tapShowAll_EasyLeaderboard();


        leaderboardFragment.tapBack();
    }
    @Test(priority = 4,dependsOnGroups = "login")
    public void showLeaderHardboard() throws InterruptedException {
        LeaderboardFragment leaderboardFragment = new LeaderboardFragment(driver);
        leaderboardFragment.toLeaderBoardFragment();
        leaderboardFragment.tapShowAll_HardLeaderboard();

        leaderboardFragment.tapBack();
    }
    @Test(priority = 5,dependsOnGroups = "login")
    public void showPlayinghistory() throws InterruptedException {

        ProfileFragment profile  = new ProfileFragment(driver);
        profile.toProfileFragment();
        scrollDown();
        profile.tapHistory();

        HistoryActivity history = new HistoryActivity(driver);
        history.back();

    }

    @AfterClass(groups = "loggout" , dependsOnGroups = "login")
    public void logout() {
        ProfileFragment profileFragment = new ProfileFragment(driver);
        profileFragment.toProfileFragment();
        scrollDown();
        profileFragment.tapLogout();
        login = new LoginPage(driver);
        Assert.assertEquals(true,login.loginButton.isDisplayed());
    }

    @AfterMethod
    @Parameters({"generateReport"})
    public void afterClass(ITestContext iTestContext, @Optional String generateReport) throws IOException {

//        String path_screenshot = System.getProperty("user.dir")+"/data/";
//        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//        String filename= UUID.randomUUID().toString();
//        File targetFile=new File(path_screenshot + filename +".jpg");
//        FileUtils.copyFile(srcFile,targetFile);
//        Assert.assertEquals("Please fill all field" , login.getToastText(login.toastMessage));
    }

    @AfterTest
    public static void tearDown() {


        // close app with double tap
        driver.navigate().back();
        driver.navigate().back();
        driver.quit();
        // String pageSource = driver.getPageSource();
        /*untuk mendapat XML dari android , tetapi sudah terbantu dengan Appium Inspector */
    }

    @Test(enabled = false)
    public void appiumServerFeature() throws IOException {
        Map<String, Object> caps = driver.getSessionDetails();
        for (Map.Entry<String, Object> entry : caps.entrySet()) {
            System.out.println(entry.getKey() + ":  " + entry.getValue());
        }

        File file  = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("D:/TA/ProjectTA/data/Screenshot.jpg"));

        String pageSource = driver.getPageSource();
        System.out.println("-------------------");
        System.out.println(pageSource);
        System.out.println("------------------- end of page sourve");
//        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
//        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//        driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);

        ScreenOrientation orientation = driver.getOrientation();
        System.out.println("Orientatation :"+orientation.toString());
//        driver.rotate(ScreenOrientation.LANDSCAPE);

//        Location location = driver.location();  // untuk driverWeb ,
//        System.out.println("llocation :"+location.toString());
//        driver.setLocation(new Location(49, 123, 10)); // lat , alt , longitude


//        Set<String> logTypes = driver.manage().logs().getAvailableLogTypes();
//        logTypes.forEach((e) -> { System.out.println(e); });
//        System.out.println("----end of set of logtypes");
//        // get log of entries from server  --relaxed-security
//        LogEntries logEntries = driver.manage().logs().get("logcat");
//        LogEntries logEntries1 = driver.manage().logs().get("server");
//        LogEntries logEntries2 = driver.manage().logs().get("bugreport");
//        System.out.println(logEntries);
//        System.out.println(logEntries1);
//        System.out.println(logEntries2);
//
//
//        // store evene on appium server
//        CustomEvent evt = new CustomEvent();
//        evt.setEventName("funEvent");
//        evt.setVendor("appium");
//        driver.logEvent(evt);
//
// get event on appium server
//        ServerEvents events = driver.getEvents();
//        System.out.println(events);


        // set devices setting
//        driver.setSetting(Setting.WAIT_FOR_IDLE_TIMEOUT, 5000);

        // get devices Settingss
        Map<String, Object> settings = driver.getSettings();
        for (Map.Entry<String, Object> entry : settings.entrySet()) {
            System.out.println(entry.getKey() + ":  " + entry.getValue());
        }

        /*
        Run a WebdriverIO script against the current session,
        allowing execution of many commands in one Appium request.
        * */
//        String script = "const el = await driver.$('~foo');\n"
//                + "await el.click();";
//        driver.executeDriverScript(script, new ScriptOptions().withTimeout(200));





    }

    @Test(enabled = false)
    public void appiumDeviceFeature() throws IOException {

        //https://appium.io/docs/en/about-appium/api/

        String currentPackage = ((AndroidDriver) driver).getCurrentPackage();
        String currentActivity= ((AndroidDriver) driver).currentActivity();
        ((AndroidDriver) driver).startActivity(new Activity("com.example", "ActivityName"));

        driver.installApp(System.getProperty("user.dir")+"/apps/sejarahKita.apk");
        driver.isAppInstalled("com.example.AppName");
        driver.launchApp();
        driver.runAppInBackground(Duration.ofSeconds(10));
        driver.closeApp();
        driver.resetApp();
        driver.removeApp("com.example.AppName");
        driver.activateApp("com.apple.Preferences");
        driver.terminateApp("io.appium.android.apis");
        driver.queryAppState("io.appium.android.apis");
        Map<String, String> appStrings = driver.getAppStringMap("en", System.getProperty("user.dir") + "/data/appStringMap");
        ((AndroidDriver<?>) driver).endTestCoverage("Intent", "/path");

        ((AndroidDriver<?>) driver).getClipboard(ClipboardContentType.PLAINTEXT); // get plaintext
        ((AndroidDriver<?>) driver).getClipboardText();

        String originalInput = "test input";
        String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        String decodedString = new String(decodedBytes);

        ((AndroidDriver<?>) driver).setClipboard("label", ClipboardContentType.PLAINTEXT,decodedBytes);
        ((AndroidDriver<?>) driver).setClipboardText("happy testing");

//        ((AndroidDriver<?>) driver).setPowerAC(PowerACState.OFF);
        ((AndroidDriver<?>) driver).setPowerAC(PowerACState.ON);
        ((AndroidDriver<?>) driver).setPowerCapacity(12);

        ((AndroidDriver<?>)driver).pushFile("/data/local/tmp/foo.bar", new File("/Users/johndoe/files/foo.bar"));
        byte[] fileBase64 =  ((AndroidDriver<?>)driver).pullFile("/path/to/device/foo.bar");
        byte[] folder = ((AndroidDriver<?>)driver).pullFolder("/path/to/device/foo.bar");

//        ((IOSDriver<?>)driver).shake();
        ((AndroidDriver<?>)driver).lockDevice();
        ((AndroidDriver<?>)driver).unlockDevice();
        boolean isLocked =  ((AndroidDriver<?>)driver).isDeviceLocked();


        driver.rotate(ScreenOrientation.LANDSCAPE);
        driver.rotate(ScreenOrientation.PORTRAIT);

//        ((AndroidDriver<?>)driver).pressKey(new KeyEvent(AndroidKey.HOME));
//        ((AndroidDriver<?>)driver).longPressKey(new KeyEvent(AndroidKey.HOME));
        driver.hideKeyboard();
        boolean isKeyboardShown =  ((AndroidDriver<?>)driver).isKeyboardShown();

        ((AndroidDriver<?>)driver).toggleAirplaneMode();
        ((AndroidDriver<?>)driver).toggleData();
        ((AndroidDriver<?>)driver).toggleWifi();
        ((AndroidDriver<?>)driver).toggleLocationServices();
        ((AndroidDriver<?>)driver).sendSMS("555-123-4567", "Hey lol");
        ((AndroidDriver<?>)driver).makeGsmCall("5551234567", GsmCallActions.CALL);
        ((AndroidDriver<?>)driver).setGsmSignalStrength(GsmSignalStrength.GOOD);
        ((AndroidDriver<?>)driver).setGsmVoice(GsmVoiceState.HOME);
        ((AndroidDriver<?>)driver).setNetworkSpeed(NetworkSpeed.LTE);

        List<List<Object>> performanceData = ((AndroidDriver<?>)driver).getPerformanceData("my.app.package", "cpuinfo", 5);
        List<String> performanceTypes = ((AndroidDriver<?>)driver).getSupportedPerformanceDataTypes();

        ((AndroidDriver<?>)driver).startRecordingScreen();
        ((AndroidDriver<?>)driver).stopRecordingScreen();

        ((AndroidDriver<?>)driver).fingerPrint(1);
//        driver.performTouchID(false);// IOS


        ((AndroidDriver<?>)driver).openNotifications();
        Map<String, Map<String, Object>> systemBars = ((AndroidDriver<?>)driver).getSystemBars();

        String time = driver.getDeviceTime();
        ((AndroidDriver<?>)driver).getDisplayDensity();


        MobileElement element = (MobileElement) driver.findElementByClassName("SomeClassName");
        String elText = element.getText();
        String tagName = element.getTagName();
        String getAttribut = element.getAttribute("content-desc");
        boolean isSelected = element.isSelected();
        boolean isEnabled = element.isEnabled();
        boolean isDisplayed = element.isDisplayed();
        Dimension elementSize = element.getSize();
        Rectangle rect = element.getRect();
        String cssProperty = element.getCssValue("style");
        //getLocationinView not supported in java

        element.submit();
        MobileElement currentElement = (MobileElement) driver.switchTo().activeElement();

        MobileElement elementOne = (MobileElement) driver.findElementByClassName("SomeClassName");
        MobileElement elementTwo = (MobileElement) driver.findElementByClassName("SomeOtherClassName");
        boolean isEqual = elementOne.equals(elementTwo);


//        String context = driver.getContext();
//        Set<String> contextNames = driver.getContextHandles();
//        driver.context("NATIVE_APP");


        // move to, di dokumentasinya gerakan mouse tidak compatibel dengan mobile
//        Actions action = new Actions(driver);
//        action.moveToElement(element);
//        action.clickAndHold();
//
//        action.click();
//        action.doubleClick();
//
//        //button down
//        action.clickAndHold();
//
//        // button up
//        action.moveToElement(element, 1, 2);
//        action.clickAndHold();
//        action.moveToElement(element, 10, 10);
//        action.release();
//
//
//        action.perform();
        // rasaa e gapernah pake inni deh , ini soal e buat web



//        TouchActions action = new TouchActions(driver);
//        action.singleTap(element);
//        action.doubleTap(element);
//
//        // move
//        action.down(10,10);
//        action.move(50,50);
//
//        //touch up
//        action.down(10, 10);
//        action.up(20, 20);
//
//        //longpress
//        action.longPress(element);
//
//        action.scroll(element, 10, 100);
//
//        action.flick(element, 1, 10, 10);
//
//
//
//        // Multi Touch
//        TouchAction actionOne = new TouchAction(driver);
//        actionOne.press( PointOption.point(10, 10));
//        actionOne.moveTo(PointOption.point(10, 100));
//        actionOne.release();
//        TouchAction actionTwo = new TouchAction(driver);
//        actionTwo.press(PointOption.point(20, 20));
//        actionTwo.moveTo(PointOption.point(20, 200));
//        actionTwo.release();
//        MultiTouchAction mulAction = new MultiTouchAction(driver);
//        mulAction.add(actionOne);
//        mulAction.add(actionTwo);
//        action.perform();
//
//        //Tooce
//        actionOne.press(PointOption.point(10, 10));
//        actionOne.moveTo( PointOption.point(10, 100));
//        actionOne.release();
//
//
//
//        action.perform();

    }






}
