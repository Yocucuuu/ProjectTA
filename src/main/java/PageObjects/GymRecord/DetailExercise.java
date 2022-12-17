package PageObjects.GymRecord;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;


public class DetailExercise extends GymRecord_PageBase{



    @iOSXCUITFindBy(accessibility = "")
    public MobileElement btnDeleteExercise;

    @iOSXCUITFindBy(accessibility = "")
    public MobileElement btnSave;

    @iOSXCUITFindBy(accessibility = "")
    public MobileElement btnExit;

    public DetailExercise(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }
}
