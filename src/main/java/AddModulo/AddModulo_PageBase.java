package AddModulo;

import PageObjects.PageBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class AddModulo_PageBase extends PageBase {

    @iOSXCUITFindBy(id = "")
    public MobileElement btn_back;

    @iOSXCUITFindBy(id = "")
    public  MobileElement btn_HowToPlay;

    @iOSXCUITFindBy(id = "")
    public MobileElement btn_about;

    @iOSXCUITFindBy(id = "")
    public MobileElement btn_play;

    @iOSXCUITFindBy(id = "")
    public MobileElement select_easy;

    @iOSXCUITFindBy(id = "")
    public MobileElement select_hard;

    public AddModulo_PageBase(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public void back(){
        click(btn_back);
    }

    public void toAbout(){
        click(btn_about);
    }

    public void toHowtoPlay(){
        click(btn_HowToPlay);
    }

    public void playEasy(){
        click(btn_play);
        click(select_easy);
    }

    public void playHard(){
        click(btn_play);
        click(select_hard);
    }



}
