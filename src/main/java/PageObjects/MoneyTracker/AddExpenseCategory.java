package PageObjects.MoneyTracker;

import PageObjects.PageBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class AddExpenseCategory extends PageBase {

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"Money+\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeButton[2]")
    private IOSElement btnDone; // tanda centang kanan atass

    @iOSXCUITFindBy(accessibility = "Back")
    private IOSElement btnBack;

    @iOSXCUITFindBy(accessibility = "Category Name")
    private IOSElement inputName;

    @iOSXCUITFindBy(accessibility = "Please Enter Category Name")
    private IOSElement errorMessage;




    public AddExpenseCategory(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public void inputCategoriesName(String name){
        sendText(inputName,name);
    }

    public String getErrorMessage(){
        waitForVisibility(errorMessage);
        return  errorMessage.getText();
    }

    public void done(){
        click(btnDone);
    }

    public void back(){
        click(btnBack);
    }


}
