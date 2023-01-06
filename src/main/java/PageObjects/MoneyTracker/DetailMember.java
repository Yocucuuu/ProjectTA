package PageObjects.MoneyTracker;

import PageObjects.PageBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class DetailMember extends PageBase {
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"Money+\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeButton[2]")
    private IOSElement btnEdit;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"Money+\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeButton[1]")
    private IOSElement btnBack;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"Money+\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeButton[2]")
    private IOSElement btnDelete;

    @iOSXCUITFindBy(accessibility = "Member Name")
    private IOSElement inputName;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"Money+\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeButton[2]")
    private IOSElement btnDone; // tanda centang kanan atass

    @iOSXCUITFindBy(xpath ="//XCUIElementTypeApplication[@name=\"Money+\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeButton[3]")
    private IOSElement btnDoneEdit; // tanda centang kanan atass

    @iOSXCUITFindBy(accessibility = "Please Enter Member Name")
    private IOSElement errorMessage;


    @iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"Money+\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeButton")
    private IOSElement btnAddTransaction;


    public DetailMember(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public Boolean verifyName(String name){
        waitForVisibility((MobileElement) driver.findElementByAccessibilityId(name));
        return driver.findElementByAccessibilityId(name).isDisplayed();
    }

    public void editMemberName(String name){
        clear(inputName);
        sendText(inputName,name);
    }

    public void toEditPage(){
        click(btnEdit);
    }

    public void tapDelete(){
        click(btnDelete);
        waitForVisibility((MobileElement) driver.findElementByAccessibilityId("OK"));
        driver.findElementByAccessibilityId("OK").click();
    }
    public void inputMemberName(String name){
        sendText(inputName,name);
    }

    public String getErrorMessage(){
        waitForVisibility(errorMessage);
        return  errorMessage.getText();
    }

    public void done(){
        click(btnDone);
    }

    public void doneEdit(){
        click(btnDoneEdit);
    }

    public void addTransaction(){
        click(btnAddTransaction);
    }
}
