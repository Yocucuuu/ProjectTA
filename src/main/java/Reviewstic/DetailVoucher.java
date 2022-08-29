package Reviewstic;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class DetailVoucher extends Reviewstic_Pagebase{


    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[1]")
    MobileElement labelVoucherUsed;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[1]")
    MobileElement labelVoucherName;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTable[1]")
    MobileElement tableTransaction;

    @iOSXCUITFindBy(accessibility =  "Empty list")
    public MobileElement emptyTable;

    MobileElement detailTransaction;


    public DetailVoucher(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }


    public MobileElement getVoucherById(String id){
        waitForVisibility(tableTransaction);
        return (MobileElement) driver.findElementByAccessibilityId("#"+id);


    }
}
