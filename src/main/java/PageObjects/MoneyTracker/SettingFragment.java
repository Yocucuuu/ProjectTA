package PageObjects.MoneyTracker;

import PageObjects.PageBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class SettingFragment extends MoneyTracker_Pagebase {

    @iOSXCUITFindBy(accessibility = "Categories")
    private MobileElement menuCategories;

    @iOSXCUITFindBy(accessibility = "Members")
    private MobileElement menuMembers;

    @iOSXCUITFindBy(accessibility = "Accounts")
    private MobileElement menuAccounts;




    public SettingFragment(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public void toMenuCategories(){
        click(menuCategories);
    }

    public void toMenuMembers(){
        click(menuMembers);
    }

    public void toMenuAccounts(){
        click(menuAccounts);
    }



}
