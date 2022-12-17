package PageObjects.GymRecord;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;


/*
* bisa diswipe  kiri untuk ganti exercise
* yang sudah dipilih di workout plan yang dibuat
* */
public class NewSets extends GymRecord_PageBase{

    @iOSXCUITFindBy(accessibility = "")
    private MobileElement btnDone;

    @iOSXCUITFindBy(accessibility = "")
    private MobileElement btnAddSet;

    @iOSXCUITFindBy(accessibility = "")
    private MobileElement btnDone;

    @iOSXCUITFindBy(accessibility = "")
    private MobileElement btnSave;

    @iOSXCUITFindBy(accessibility = "")
    private MobileElement btnBack;

    public NewSets(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public void tapAddSet(){
        click(btnAddSet);
    }

    public void tapDone(){
        click(btnDone);
    }

    public void tapSave(){
        click(btnSave);
    }

    public void back(){
        click(btnBack);
    }

    public void inputNewSet(int weight , int reps){
        // findelement ....

    }


}
