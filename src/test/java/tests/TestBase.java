package tests;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.serverevents.ServerEvents;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.*;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.JsonReader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class TestBase  {


    public static AppiumDriver driver;
    public static String sejarahKitaPackage = "com.uc.sejarahkita_mobile";
    public static String sejarahKitaActivity = "com.uc.sejarahkita_mobile.view.MainActivity";
    public static String sejarahKitaAppPath = System.getProperty("user.dir")+"/apps/app-debug.apk";
    public static String sejarahKitaErrAppPath = System.getProperty("user.dir")+"/apps/app-debugERR.apk";
    public static String chromeDriverPath = System.getProperty("user.dir")+"/apps/chromedriver86.exe";
    public static String sejarahKitaCoveragePath = System.getProperty("user.dir")+"/apps/";
    public static String webViewTestPackage= "com.snc.test.webview2";
    public static String webViewTestActivity= "com.snc.test.webview.activity.MainActivity";


    public static void Android_SejarahKita_Emulator_setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "7.1");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("deviceName", "emulator-5554");
        capabilities.setCapability("appPackage","com.uc.sejarahkita_mobile");
        capabilities.setCapability("appActivity","com.uc.sejarahkita_mobile.view.MainActivity");
        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
//        capabilities.setCapability("app",sejarahKitaAppPath);
//        autoGrantPermissions

        //driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS); // dipakai di pageOBJECT design pattern
//        driver.manage().timeouts().pageLoadTimeout(1, TimeUnit.SECONDS);


    }

    // Just open Bluestack and set Driver
    public static void Android_Emulator_setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "7.1");
//        capabilities.setCapability("app", sejarahKitaAppPath);
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("deviceName", "emulator-5554");
        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
    }
    public static void Android_SamsungC9_setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "7.1");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("udid", "804a468e"); // udid Samsung C9
        capabilities.setCapability("autoGrantPermissions",true);
//        capabilities.setCapability("fullReset",true);
        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        // untuk find element history


    }
    public static void iOS_setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("automationName", "XCuiTest");
        capabilities.setCapability("platformVersion", "15.1");
        capabilities.setCapability("deviceName", "asus's Iphone");
        driver = new IOSDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
    }

    public static void Android_SejarahKita_SamsungC9_setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "7.1");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("udid", "804a468e");
        capabilities.setCapability("app",sejarahKitaAppPath);

//        capabilities.setCapability("appPackage",sejarahKitaPackage);
//        capabilities.setCapability("appActivity","com.uc.sejarahkita_mobile.view.MainActivity");;

        capabilities.setCapability("autoGrantPermissions",true);

        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
        //driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS); // dipakai di pageOBJECT design pattern
//        driver.manage().timeouts().pageLoadTimeout(1, TimeUnit.SECONDS);

    }
    public static void Android_WebviewTest_Emulator_setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "7.1");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("udid", "emulator-5554");
        capabilities.setCapability("appPackage","com.snc.test.webview2");
        capabilities.setCapability("appActivity","com.snc.test.webview.activity.MainActivity");
        capabilities.setCapability("acceptInsecureCerts",true);
        capabilities.setCapability("chromeOptions", ImmutableMap.of("w3c", false));
        capabilities.setCapability("appium:chromedriverExecutable",chromeDriverPath);
        driver = new AppiumDriver(new URL("http://localhost:4723/wd/hub"), capabilities);

    }
    public static void Android_WebviewTest_C9_setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "7.1");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("udid", "804a468e");
        capabilities.setCapability("appPackage","com.snc.test.webview2");
        capabilities.setCapability("appActivity","com.snc.test.webview.activity.MainActivity");
        capabilities.setCapability("acceptInsecureCerts",true);
        capabilities.setCapability("chromeOptions", ImmutableMap.of("w3c", false));
        capabilities.setCapability("appium:chromedriverExecutable",chromeDriverPath);
        driver = new AppiumDriver(new URL("http://localhost:4723/wd/hub"), capabilities);

    }
    public static void Android_WebViewApp_C9_setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "7.1");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("autoGrantPermissions",true);
        capabilities.setCapability("udid", "804a468e");
        capabilities.setCapability("appPackage","com.robotemplates.webviewapp");
        capabilities.setCapability("appActivity","com.robotemplates.webviewapp.activity.MainActivity");;
        capabilities.setCapability("acceptInsecureCerts",true);
        capabilities.setCapability("chromeOptions", ImmutableMap.of("w3c", false));
        capabilities.setCapability("appium:chromedriverExecutable",chromeDriverPath);
        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);


//        capabilities.setCapability("deviceName", "emulator-5554");
//        ((AndroidDriver)driver).startActivity(new Activity("com.robotemplates.webviewapp",
//                "com.robotemplates.webviewapp.activity.MainActivity"));

//        capabilities.setCapability("appPackage","com.robotemplates.webviewapp")c;
//        capabilities.setCapability("appActivity","com.robotemplates.webviewapp.activity.MainActivity");
//        System.out.println(((AndroidDriver)driver).getCurrentPackage());
//        System.out.println(((AndroidDriver)driver).currentActivity());

    }
    public void iOS_Iphone7_setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone X");
        capabilities.setCapability("automationName","XCUITest");
        capabilities.setCapability("showXcodeLog",true);
        capabilities.setCapability("app", System.getProperty("user.dir") + "/apps/AddModulo.zip");
        driver = new IOSDriver<>(new URL("http://localhost:4723/wd/hub"), capabilities);

       /* iPhone Simulator, iPad Simulator, iPhone Retina 4-inch, Android Emulator, Galaxy S4, etc....
        On iOS, this should be one of the valid devices returned
            by instruments with instruments -s devices or xctrace with xcrun xctrace list devices (since Xcode 12).
        On Android this capability is currently ignored, though it remains required.*/
    }

    public void iOS_Iphone7_Reviewistic() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone");
        capabilities.setCapability("automationName","XCUITest");
        capabilities.setCapability("showXcodeLog",true);
        capabilities.setCapability("udid","285d71b11b4217e1ff18108230450dc53680038e");
        capabilities.setCapability("bundleId","com.macrosoft.reviewistic");
//        capabilities.setCapability("xcodeOrgId","com.macrosoft.reviewistic");
//        capabilities.setCapability("xcodeSigningId","com.macrosoft.reviewistic");
        driver = new IOSDriver<>(new URL("http://localhost:4723/wd/hub"), capabilities);

       /* iPhone Simulator, iPad Simulator, iPhone Retina 4-inch, Android Emulator, Galaxy S4, etc....
        On iOS, this should be one of the valid devices returned
            by instruments with instruments -s devices or xctrace with xcrun xctrace list devices (since Xcode 12).
        On Android this capability is currently ignored, though it remains required.*/
    }






//        capabilities.setCapability("app", System.getProperty("user.dir") + "/apps/DailyCheck.zip");
    public static void getServerLog() throws IOException {
        Map<String, Object> status = driver.getStatus();
        for (Map.Entry<String, Object> list : status.entrySet()) {
            System.out.println(list.getKey() + ":  " + list.getValue());
        }

        // get session capabilites
        Map<String, Object> caps = driver.getSessionDetails();
        for (Map.Entry<String, Object> entry : caps.entrySet()) {
            System.out.println(entry.getKey() + ":  " + entry.getValue());
        }

        // get devices Settingss
        Map<String, Object> settings = driver.getSettings();
        for (Map.Entry<String, Object> set : settings.entrySet()) {
            System.out.println(set.getKey() + ":  " + set.getValue());
        }
        String path_screenshot = System.getProperty("user.dir")+"/data/";
        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String filename= UUID.randomUUID().toString();
        File targetFile=new File(path_screenshot + filename +".jpg");
        FileUtils.copyFile(srcFile,targetFile);


    }
    public void tearDown() throws Exception {
        if (null != driver) {
            driver.quit();
        }
    }
    public Object[][] kunci() throws IOException, ParseException {
        return JsonReader.getJSONdata(System.getProperty("user.dir") + "/data/kunciSejarahkita.json"
                , "kunci", new String[]{"pertanyaan_kalimat", "kunci_jawaban"});
    }
    public String getJawaban(String soal) throws IOException, ParseException {
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
    public void resetLogin() throws IOException {
        try{
            URL url = new URL("https://sejarahkita.my.id/resetLogins");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            System.out.println(con.getResponseCode());
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

    }
    public void scrollDown(){

        Dimension dimension = driver.manage().window().getSize();
        int scrollStart = (int) (dimension.getHeight() * 0.8);
        int scrollEnd = (int) (dimension.getHeight()*0.2);
        int center = (int) (dimension.getWidth()*0.5);
        AndroidTouchAction actions = new AndroidTouchAction(driver)
                .press(PointOption.point(center, scrollStart))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
                .moveTo(PointOption.point(center, scrollEnd))
                .release()
                .perform();


    }
    public void scrollUp(){
        AndroidTouchAction actions = new AndroidTouchAction(driver);

        Dimension dimension = driver.manage().window().getSize();
        int scrollStart = (int) (dimension.getHeight() * 0.1);
        int scrollEnd = (int) (dimension.getHeight()*0.9);

        actions = new AndroidTouchAction(driver)
                .press(PointOption.point(0, scrollStart))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
                .moveTo(PointOption.point(0, scrollEnd))
                .release()
                .perform();




    }
    public void scrollToEnd(){

        try{
            try {
                driver.findElement(MobileBy.AndroidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true)).flingToEnd(30)"));
            } catch (InvalidSelectorException e) {
                // ignore
                System.out.println( e.getStackTrace());
            }
        }catch (Exception ex){}


    }
    public  HashMap<String, String> getPerformanceInfo(AndroidDriver driver , String PKG) throws Exception {
        List<String> performanceTypes = ((AndroidDriver) driver).getSupportedPerformanceDataTypes();
        HashMap<String, String> readableData = new HashMap<>();
        for (String types : performanceTypes) {
//            ======cpuinfo=====
//            ======memoryinfo=====
//            ======batteryinfo=====
//            ======networkinfo=====
            readableData.put(types,types+"===================");
            try{
                List<List<Object>> data = ((AndroidDriver) driver).getPerformanceData(PKG, types, 10);
                for (int i = 0; i < data.get(0).size(); i++) {
                    String val;
                    if (data.get(1).get(i) == null) {
                        val = "";
                    } else {
                        val = (String) data.get(1).get(i);
                    }
                    readableData.put((String) data.get(0).get(i), val);
                }
            }catch (Exception ex){
                readableData.put(types+"error",ex.getMessage());
            }

        }
        return readableData;
    }
    public String getBatteryCapasity(){
        String battery = "";
        List<List<Object>> performanceData = ((AndroidDriver)driver).getPerformanceData(sejarahKitaPackage,
                "batteryinfo", 5);
        for(int i=0;i<performanceData.size();i++){
            for(int j=0; j <performanceData.get(i).size() ;j++){
                battery = performanceData.get(i).get(j).toString();
            }
        }
        return battery;
    }
    public void writeLog() throws Exception {
        String time = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
        String filename= System.getProperty("user.dir")+"/data/SejarahKita_Android_Log/log_"+time+".txt";
        File targetFile =new File(filename);
        FileWriter myWriter = new FileWriter(filename);
        Map<String, Object> status = driver.getStatus();
        myWriter.write("Status \n");
        for (Map.Entry<String, Object> list : status.entrySet()) {
            myWriter.write(list.getKey() + ":  " + list.getValue() +"\n");
        }

        myWriter.write("Device Density "+((AndroidDriver)driver).getDisplayDensity() +"\n");
        myWriter.write("====== Session Detail =====");
        Map<String, Object> caps = driver.getSessionDetails();
        for (Map.Entry<String, Object> entry : caps.entrySet()) {
            myWriter.write(entry.getKey() + ":  " + entry.getValue());
            myWriter.write("\r\n");
        }
        myWriter.write("====== Session end =====");
        myWriter.write("====== Device Setting =====");
        Map<String, Object> settings = driver.getSettings();
        for (Map.Entry<String, Object> entry : settings.entrySet()) {
            myWriter.write(entry.getKey() + ":  " + entry.getValue());
            myWriter.write("\r\n");
        }
        myWriter.write("====== End of Device Setting =====");

        myWriter.write("====== Performance  ====="+"\n");
        HashMap<String, String> data =  getPerformanceInfo((AndroidDriver) driver,((AndroidDriver<?>) driver).getCurrentPackage());
        for (Map.Entry<String,String > set : data.entrySet()) {
            // Printing all elements of a Map
            myWriter.write(set.getKey() + " = "
                    + set.getValue() +"\n");
        }

        myWriter.write("======Server Log ===== \n");
        driver.manage().logs().getAvailableLogTypes();
        LogEntries serverLog = driver.manage().logs().get("server");
//        LogEntries bugreport = driver.manage().logs().get("bugreport");


        myWriter.write(serverLog.toString() + "\n");
        for (LogEntry entry : serverLog) {
            myWriter.write(new Date(entry.getTimestamp()) + " " + entry.getMessage() +"\n");
        }
//        myWriter.write(bugreport.toString()+ "\n");
//        for (LogEntry entry : bugreport) {
//            myWriter.write(new Date(entry.getTimestamp()) + " " + entry.getMessage() +"\n");
//        }
        LogEntries logcatLog = driver.manage().logs().get("logcat");
        myWriter.write(logcatLog.toString()+ "\n");
        for (LogEntry entry : logcatLog) {
            myWriter.write(new Date(entry.getTimestamp()) + " " + entry.getMessage() +"\n");
        }
        ServerEvents detail = (ServerEvents) driver.getEvents();
        myWriter.write(detail.toString());
        myWriter.close();

    }


}
