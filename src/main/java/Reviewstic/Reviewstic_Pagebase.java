package Reviewstic;

import PageObjects.PageBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.*;

public class Reviewstic_Pagebase extends PageBase {

    @iOSXCUITFindBy(accessibility = "Review")
    public MobileElement fragReviews;

    @iOSXCUITFindBy(accessibility = "Transaction")
    public MobileElement fragTransaction;

    @iOSXCUITFindBy(id = "Promos")
    public MobileElement fragPromo;

    @iOSXCUITFindBy(id = "")
    public MobileElement buttonBack;

    @iOSXCUITFindBy(accessibility = "Ok")
    public MobileElement btnOK;  // kalo ada pesan error , diminta acc

    public Reviewstic_Pagebase(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }
}
