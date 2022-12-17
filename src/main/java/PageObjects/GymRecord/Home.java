package PageObjects.GymRecord;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class Home extends GymRecord_PageBase{

    @iOSXCUITFindBy(accessibility = "")
    private MobileElement calendar;

    @iOSXCUITFindBy(accessibility = "")
    private MobileElement btnAdd;

    @iOSXCUITFindBy(accessibility = "")
    private MobileElement btnAddExercise;

    @iOSXCUITFindBy(accessibility = "")
    private MobileElement btnAddProgram;

    @iOSXCUITFindBy(accessibility = "")
    private MobileElement toDatabase;


    @iOSXCUITFindBy(accessibility = "")
    private MobileElement btnPlanManager;
    /*Button di dalam Kebab menu (btnPlanManager)*/

    @iOSXCUITFindBy(accessibility = "")
    private MobileElement btnClearAll;

    @iOSXCUITFindBy(accessibility = "")
    private MobileElement btnEdit;

    @iOSXCUITFindBy(accessibility = "")
    private MobileElement btnSaveAsProgram;
    /*----------------------------------*/






    @iOSXCUITFindBy(accessibility = "")
    private MobileElement lblDate;

    public Home(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }


    public void toMyDatabase(){
        click(toDatabase);
    }

    public void deleteExercise(String name){

    }
    public void seeHistoryExercise(String name){

    }

    public void toAddSetExercise(String name){

    }






}
