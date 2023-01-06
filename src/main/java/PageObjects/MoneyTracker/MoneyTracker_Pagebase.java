package PageObjects.MoneyTracker;

import PageObjects.PageBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.ios.IOSTouchAction;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

import java.time.Duration;

public class MoneyTracker_Pagebase extends PageBase {

    @iOSXCUITFindBy(accessibility = "Settings\nTab 4 of 4")
    private IOSElement fragSettings;

    public MoneyTracker_Pagebase(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public void tapSettingFrag(){
         new IOSTouchAction(driver)
                .press(PointOption.point(325,630 ))
                .release()
                .perform();
        //driver.findElementByXPath("//XCUIElementTypeImage[@name=\"Settings Tab 4 of 4\"]").click();
//        click(fragSettings);
    }


}
