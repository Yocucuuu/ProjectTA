package PageObjects.WebviewTest;

import PageObjects.PageBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class WebviewTest_Home extends PageBase {


    @AndroidFindBy(id = "action_go_website")
    public MobileElement gotoWeb;

    @AndroidFindBy(id = "input_url")
    public MobileElement inputUrl;

    @AndroidFindBy(id = "android:id/button1")
    public MobileElement buttonGo;

    public WebviewTest_Home(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }
}
