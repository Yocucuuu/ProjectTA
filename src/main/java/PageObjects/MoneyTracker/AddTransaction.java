package PageObjects.MoneyTracker;

import PageObjects.PageBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class AddTransaction extends PageBase {

    @iOSXCUITFindBy(accessibility = "Income" )
    private IOSElement tabIncome;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"Money+\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther[5]/XCUIElementTypeOther[2]/XCUIElementTypeOther[5]/XCUIElementTypeButton")
    private IOSElement btnAddandDone; // nambah trans terus nutup app e

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"Money+\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther[5]/XCUIElementTypeOther[2]/XCUIElementTypeOther[4]/XCUIElementTypeButton")
    private IOSElement btnAdd; // ini nambah tok

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"Money+\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeButton")
    private IOSElement btnAccount;

    private IOSElement buttons[] = new  IOSElement[10];
    public AddTransaction(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    private void prepButton(){
        for(int i=0;i<10;i++){
            buttons[i] = (IOSElement) driver.findElementByAccessibilityId(i+"");
        }
    }

    public void setValue(String value){
        prepButton();
        char[] arrValue = value.toCharArray();
        for (char i: arrValue) {
            int num = Integer.parseInt(i+"");
            click(buttons[num]);
        }

    }

    public void selectAccount(String account){
        click(btnAccount);
        String s = String.format(" //XCUIElementTypeImage[contains(@name,'%s')]",account);
        waitForVisibility((MobileElement) driver.findElementByXPath(s));
        driver.findElementByXPath(s).click();

    }
    public void tapAddAndDone(){
        click(btnAddandDone);
    }

    public void tapAdd(){
        click(btnAdd);
    }
    public void selectCategories(String name){
        String s = String.format(" //XCUIElementTypeImage[@name=\"%s\"]",name);
        waitForVisibility((MobileElement) driver.findElementByXPath(s));
        driver.findElementByXPath(s).click();
    }

    public void clikTabIncome(){
        click(tabIncome);
    }
}
