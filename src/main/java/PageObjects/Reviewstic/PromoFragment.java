package PageObjects.Reviewstic;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.*;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.appium.java_client.touch.offset.PointOption;

import java.util.List;

public class PromoFragment extends Reviewstic_Pagebase{

    // tanda + nde kanan atas setelah mbuka frag promo
    @iOSXCUITFindBy(accessibility = "add")
    public MobileElement btnAddPromo;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField[1]")
    public MobileElement etAddVoucherName;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField[2]")
    public MobileElement etAddVoucherDesc;


    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`label == \"Create Voucher\"`]")
    public MobileElement btnAddVoucher;

    @iOSXCUITFindBy(accessibility = "Date Picker") // element mbe id e ga podo , fyi cuma nde ipon 7
    public MobileElement datePickerAddVoucher;


    @iOSXCUITFindBy(accessibility = "Previous Month")
    public MobileElement prevMonth;

    @iOSXCUITFindBy(accessibility = "Next Month")
    public MobileElement nextMonth;

    @iOSXCUITFindBy(accessibility = "Show year picker")
    public MobileElement showYear;

    public MobileElement selectedDate;



    /*
    * cara cari element nde dalem e tanggal pake accesibility ID
    * ex : tanggal 10 --> accesibility = 10
    * 
    *
    * */


    //cara close e tap sembarang nde luar kalender


    public PromoFragment(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public void openDatePicker(){
        waitForVisibility(datePickerAddVoucher);
        new TouchAction<>(driver).tap(PointOption.point(317, 239)).perform();
    }

    public void selectDate(String date){
        selectedDate = (MobileElement) driver.findElementsByAccessibilityId(date);
        selectedDate.click();
    }
    public void closeTrayAddVoucher(){
        waitForVisibility(btnAddVoucher);
        new TouchAction<>(driver)
                .press(PointOption.point(180, 70))
                .moveTo(PointOption.point(180, 500))
                .release()
                .perform();
    }

    public void gotoDetailbyVoucherName(String name){
        List<IOSElement> listElement = ((IOSDriver)driver).findElementsByTagName("XCUIElementTypeCell");
        IOSElement element = listElement.get(listElement.size());
        element.findElementByAccessibilityId(name).click();
    }


    public IOSElement getLastInsertedVoucher(){
        List<IOSElement> listElement = ((IOSDriver)driver).findElementsByTagName("XCUIElementTypeCell");
        IOSElement element = listElement.get(listElement.size());
        return element;
        //IOSElement tvDate = (IOSElement) element.findElementByName();  //yyyy-mm-dd
        //IOSElement tvName = (IOSElement) element.findElementByAccessibilityId(name);
        //IOSElement tvDesc = (IOSElement) element.findElementByAccessibilityId(desc);
    }




}
