package tests;

import io.appium.java_client.android.*;
import io.appium.java_client.android.connection.ConnectionStateBuilder;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.Map;

public class justScrap extends TestBase{

    @Test
    public void testName() throws MalformedURLException, InterruptedException {
        Android_Emulator_setUp();
        boolean instaled = driver.isAppInstalled(sejarahKitaPackage);

        Map<String, Object> status = driver.getStatus();
        for (Map.Entry<String, Object> list : status.entrySet()) {
            System.out.println(list.getKey() + ":  " + list.getValue());
        }

        System.out.println("--------");
        Map<String, Object> caps = driver.getSessionDetails();
        for (Map.Entry<String, Object> entry : caps.entrySet()) {
            System.out.println(entry.getKey() + ":  " + entry.getValue());
        }
        System.out.println("--------");

        // get devices Settingss
        Map<String, Object> settings = ((AndroidDriver)driver).getSettings();
        for (Map.Entry<String, Object> set : settings.entrySet()) {
            System.out.println(set.getKey() + ":  " + set.getValue());
        }
        System.out.println("--------");

        System.out.println(((AndroidDriver)driver).getDeviceTime());
        System.out.println(((AndroidDriver)driver).getDisplayDensity());
        ((AndroidDriver)driver).fingerPrint(1);








        System.out.println("--------");

//        ((AndroidDriver)driver).lockDevice();
//        System.out.println( ((AndroidDriver)driver).isDeviceLocked());
//        Thread.sleep(3000);
//        ((AndroidDriver)driver).unlockDevice();
//        System.out.println( ((AndroidDriver)driver).isDeviceLocked());




//        ((AndroidDriver)driver).toggleAirplaneMode();
//        ((AndroidDriver)driver).toggleWifi();
//        ((AndroidDriver)driver).toggleData();
//        ((AndroidDriver)driver).toggleLocationServices();
//        ((AndroidDriver)driver).setPowerAC(PowerACState.OFF);;
//        ((AndroidDriver)driver).setPowerCapacity(10);;


        /*sumpa kasih testcase opo iki wkwkkw */
//        ((AndroidDriver)driver).sendSMS("0895631523006","hey"); // simulasi aku menerima SMS
//        ((AndroidDriver)driver).makeGsmCall("0895631523006", GsmCallActions.CALL);
//        ((AndroidDriver)driver).makeGsmCall("0895631523006", GsmCallActions.ACCEPT);
//        ((AndroidDriver)driver).makeGsmCall("0895631523006", GsmCallActions.CANCEL);
//        ((AndroidDriver)driver).setNetworkSpeed(NetworkSpeed.LTE);
//        ((AndroidDriver)driver).setGsmSignalStrength(GsmSignalStrength.GREAT);
//        ((AndroidDriver)driver).makeGsmCall("0895631523006", GsmCallActions.ACCEPT);

        ((AndroidDriver)driver).openNotifications();










        driver.quit();

    }

    public void setConnectionToOFF() {
        try {
            ((AndroidDriver)driver).setConnection(new ConnectionStateBuilder().withWiFiDisabled().build());
            System.out.println("Switching OFF the connection : " + ((AndroidDriver)driver).getConnection());
        } catch (Exception e) {
            System.out.println("Connection could not be switch OFF");
        }
    }

    public void setConnectionToON() {
        try {
            ((AndroidDriver)driver).setConnection(new ConnectionStateBuilder().withWiFiEnabled().build());
            System.out.println("Switching On the connection: " + ((AndroidDriver)driver).getConnection());
        } catch (Exception e) {
            System.out.println("Connection could not be switch ON");
        }
    }
}
