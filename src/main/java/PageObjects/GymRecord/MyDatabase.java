package PageObjects.GymRecord;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class MyDatabase extends GymRecord_PageBase{

    @iOSXCUITFindBy(accessibility = "")
    public MobileElement btnTabExercise;

    @iOSXCUITFindBy(accessibility = "")
    public MobileElement addTabPrograms;

    @iOSXCUITFindBy(accessibility = "")
    public MobileElement workoutCounts;

    @iOSXCUITFindBy(accessibility = "")
    public MobileElement programsCounts;

    @iOSXCUITFindBy(accessibility = "")
    public MobileElement exercisesCounts;

    @iOSXCUITFindBy(accessibility = "")
    public MobileElement newProgram;

    @iOSXCUITFindBy(accessibility = "")
    public MobileElement newExercise;

    @iOSXCUITFindBy(accessibility = "")
    public MobileElement exerciseCollection;

    @iOSXCUITFindBy(accessibility = "")
    public MobileElement detailExerciseCollection;

    @iOSXCUITFindBy(accessibility = "")
    public MobileElement detailExercise;




    public MyDatabase(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    private void selectExerciseTab(){
        click(btnTabExercise);
    }
    private void selectProgramTab(){
        click(btnTabExercise);
    }
    public void tapNewExercise(){
        selectExerciseTab();
        click(newExercise);
    }
    public void tapNewProgram(){
        selectProgramTab();
        click(newProgram);
    }

}
