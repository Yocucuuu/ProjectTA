package PageObjects.Reviewstic;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class TransactionFragment extends Reviewstic_Pagebase{

    @iOSXCUITFindBy(accessibility = "Transaction number...")
    MobileElement etSearchTransaction;

    @iOSXCUITFindBy(accessibility = "Clear text")
    MobileElement btnClearSearch; // cuma akan muncul kalau ada tet nde dalem e ET

    /*
    * cara dapet list per row findelement byaccesibility id dari row
    * #14
    * */

    public TransactionFragment(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public void searchTransaction(String id){
        sendText(etSearchTransaction , id);
    }

    public void clearSearch(){
        click(btnClearSearch);
    }




}
