package PageObjects.MoneyTracker;

import PageObjects.PageBase;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class CategoriesSetting extends PageBase {



    @iOSXCUITFindBy(accessibility = "Income" )
    private IOSElement tabIncome;


    @iOSXCUITFindBy(accessibility = "Expenses\nExpenses" )
    @iOSXCUITFindBy(accessibility = "Expenses" )
    private IOSElement tabExpenses;

    @iOSXCUITFindBy(accessibility = "+Add")
    private IOSElement btnAdd;




    public CategoriesSetting(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }


    public void toTabExpenses(){
        click(tabExpenses);
    }

    public void toTabIncome(){
        click(tabIncome);
    }

    public void tapAddButton(){
        click(btnAdd);
    }

    public Boolean findExpenseCategories(String name) throws InterruptedException {
        Thread.sleep(1000);
        try{
            String s = String.format(" //XCUIElementTypeImage[@name=\"%s\"]",name);
            waitForVisibility((MobileElement) driver.findElementByXPath(s));
            return driver.findElementByXPath(s).isDisplayed();
        }catch (Exception ex){
            return false;
        }
    }
    public Boolean findIncomeCategories(String name){
        try{
            String s = String.format(" //XCUIElementTypeImage[@name=\"%s\"]",name);
            waitForVisibility((MobileElement) driver.findElementByXPath(s));
            return driver.findElementByXPath(s).isDisplayed();
        }catch (Exception ex){
            return false;
        }

    }
    public void selectExpenseCategories(String name){
        String s = String.format(" //XCUIElementTypeImage[@name=\"%s\"]",name);
        waitForVisibility((MobileElement) driver.findElementByXPath(s));
        driver.findElementByXPath(s).click();
    }


}
