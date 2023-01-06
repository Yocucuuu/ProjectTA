package PageObjects.MoneyTracker;

import PageObjects.PageBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class DetailCategories extends PageBase {

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"Money+\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeButton[2]")
    private IOSElement btnEdit;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"Money+\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeButton[1]")
    private IOSElement btnBack;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"Money+\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeButton[2]")
    private IOSElement btnDelete;

    @iOSXCUITFindBy(accessibility = "Category Name")
    private IOSElement inputName;

    public DetailCategories(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public Boolean verifyName(String name){
        waitForVisibility((MobileElement) driver.findElementByAccessibilityId(name));
        return driver.findElementByAccessibilityId(name).isDisplayed();
    }

    public void editCategoriesName(String name){
        clear(inputName);
        sendText(inputName,name);
    }

    public void toEditPage(){
        click(btnEdit);
    }

    public void tapDelete() throws InterruptedException {
        click(btnDelete);
        Thread.sleep(1000);
        waitForVisibility((IOSElement) driver.findElementByAccessibilityId("OK"));
        driver.findElementByAccessibilityId("OK").click();
    }

}
