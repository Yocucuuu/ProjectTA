package rusak;

import PageObjects.PageBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class HomePageEvernote extends PageBase {


    public HomePageEvernote(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    @AndroidFindBy(id =  "com.evernote:id/main_fab_vector_drawable")
    MobileElement addButton;

    @AndroidFindBy(id="com.evernote:id/skittle_0")
    MobileElement addNotes;



    public void clickAddButton(){
        waitForVisibility(addButton);
        click(addButton);
    }

    public void clickAddNotes(){
        waitForVisibility(addNotes);
        click(addNotes);
    }



}
