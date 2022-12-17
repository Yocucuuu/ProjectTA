package PageObjects.GymRecord;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class NewExercise extends GymRecord_PageBase{


    @iOSXCUITFindBy(accessibility = "")
    private MobileElement inp_Name;

    @iOSXCUITFindBy(accessibility = "")
    private MobileElement switchDoubleWeigth;

    @iOSXCUITFindBy(accessibility = "")
    private MobileElement switchBodyweight

    @iOSXCUITFindBy(accessibility = "")
    private MobileElement btnCreate;

    @iOSXCUITFindBy(accessibility = "")
    private MobileElement btnGotIt;

    @iOSXCUITFindBy(accessibility = "")
    private MobileElement btnBack;


    public NewExercise(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    // Legs, Shoulder , Back , Abs, Chest , Cardio
    public void chooseCategory(String category){

    }
    public void fillExerciseTittle(String tittle){
        inp_Name.sendKeys(tittle);
    }

    public void selectDoubleWeightRadio(){
        click(switchDoubleWeigth);
    }
    public void selectBodyWeightRadio(){
        click(switchBodyweight);
    }
    public void tapCreateExercise(){
        click(btnCreate);
    }

    public boolean createIsDisplayed(){
        return btnCreate.isDisplayed();
    }

    public void tapGotIt(){
        click(btnGotIt); // redirect to myDatabase
    }

    public void back(){
        click(btnBack);
    }

}
