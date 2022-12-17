package PageObjects.GymRecord;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class DetailProgram extends GymRecord_PageBase {

    @iOSXCUITFindBy(accessibility = "")
    public MobileElement btnManage;

    @iOSXCUITFindBy(accessibility = "")
    public MobileElement btnBack;

    @iOSXCUITFindBy(accessibility = "")
    public MobileElement btn_Manage;

    @iOSXCUITFindBy(accessibility = "")
    public MobileElement btnEdit;

    @iOSXCUITFindBy(accessibility = "")
    public MobileElement btnDeleteProgram;

    public DetailProgram(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }
}
