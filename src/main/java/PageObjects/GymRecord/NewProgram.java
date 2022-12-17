package PageObjects.GymRecord;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

import java.util.List;


public class NewProgram extends GymRecord_PageBase{

    @iOSXCUITFindBy(accessibility = "")
    private MobileElement inpTittle;

    @iOSXCUITFindBy(accessibility = "")
    private MobileElement inpSubTittle;

    @iOSXCUITFindBy(accessibility = "")
    private MobileElement btnAddExercise; // dibawah slider warna

    @iOSXCUITFindBy(accessibility = "")
    private MobileElement btnAdd; // button dibawah setelah tap +AddExercise

    @iOSXCUITFindBy(accessibility = "")
    private MobileElement btnDelete; // btn setelah milih exercise

    @iOSXCUITFindBy(accessibility = "")
    private MobileElement btnSuperset; // btn setelah milih exercise

    @iOSXCUITFindBy(accessibility = "")
    private MobileElement btnBack;


    public NewProgram(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public void tapBack(){
        click(btnBack);
    }
    public void selectCategory(String category){
        // finddelement.....
    }
    public void addNewExercise(List<String> names){
        //find... for click ,
    }
    public void tapAdd(){
        click(btnAdd);  // btn ini saat milih exercise e
    }

    public void tapDelete(){
        click(btnDelete);
    }
    public void tapSuperset(){
        click(btnSuperset);
    }
    public void selectAddedExercise(String[] exercises){
        for(int i=0;i<exercises.length ;i++){
            // find...... click
        }
    }

    public void inputProgramTittle(String text){

    }
    public void inputProgramSubTittle(String text){

    }


}
