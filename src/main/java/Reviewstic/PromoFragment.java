package Reviewstic;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.*;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class PromoFragment extends Reviewstic_Pagebase{

    @iOSXCUITFindBy(accessibility = "add")
    public MobileElement btnAddPromo;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField[1]")
    public MobileElement etAddVoucherName;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField[2]")
    public MobileElement etAddVoucherDesc;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`label == \"Create Voucher\"`]")
    public MobileElement btnAddVoucher;

    @iOSXCUITFindBy(accessibility = "Date Picker")
    public MobileElement datePickerAddVoucher;

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
}
