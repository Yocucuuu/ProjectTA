package PageObjects.MoneyTracker;

import PageObjects.PageBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class MembersSetting extends PageBase {

    @iOSXCUITFindBy(accessibility = "+Add")
    private IOSElement btnAdd;

    public MembersSetting(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public void tapAdd(){
        click(btnAdd);
    }

    public Boolean findMember(String name){
        try{
            String s = String.format(" //XCUIElementTypeImage[@name=\"%s\"]",name);
            waitForVisibility((MobileElement) driver.findElementByXPath(s));
            return driver.findElementByXPath(s).isDisplayed();
        }catch (Exception ex){
            return false;
        }

    }
    public void selectMembers(String name){
        String s = String.format(" //XCUIElementTypeImage[@name=\"%s\"]",name);
        waitForVisibility((MobileElement) driver.findElementByXPath(s));
        driver.findElementByXPath(s).click();
    }
}
