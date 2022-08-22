package tests;

import AddModulo.AddModulo_Home;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class AddModulo_IOS_Test extends TestBase{

    @BeforeClass
    public void beforeClass() throws MalformedURLException {

        iOS_Iphone7_setUp();
    }

    @AfterClass
    public void afterClass() throws Exception {
        writeLog();
        driver.quit();
    }

    @Test
    public void toHowToplay() {
        AddModulo_Home  home = new AddModulo_Home(driver);
        home.toHowtoPlay();
        home.back();
    }

    @Test
    public void toAbout() {
        AddModulo_Home  home = new AddModulo_Home(driver);
        home.toAbout();
        home.back();
    }

    @Test
    public void playEasy() {
        AddModulo_Home  home = new AddModulo_Home(driver);
        home.playEasy();
    }

    @Test
    public void playHard() {
        AddModulo_Home  home = new AddModulo_Home(driver);
        home.playHard();
    }

    @Test
    public void playEasyOpenNotid() {
        AddModulo_Home  home = new AddModulo_Home(driver);
        home.playEasy();

    }


}
