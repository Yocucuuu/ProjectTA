package rusak;

import PageObjects.PageBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;

public class HomePageOmniNotes extends PageBase {
    public HomePageOmniNotes(AndroidDriver appiumDriver) {
        super(appiumDriver);
    }

    @AndroidFindBy(id = "it.feio.android.omninotes:id/fab_expand_menu_button")
    MobileElement buttonAddExpand;

    @AndroidFindBy(id = "it.feio.android.omninotes:id/fab_note")
    MobileElement buttonAddNote;

    public void clickAddExpand(){
        buttonAddExpand = (MobileElement)driver.findElement(By.id("fab_expand_menu_button"));
        click(buttonAddExpand);
    }

    public void clickAddNote(){
        click(buttonAddNote);
    }

    public void skipWelcome(){

        // ada 5 slide
        for(int i=0;i<5;i++){
            MobileElement buttonNext = (MobileElement) driver.findElement(By.id("next"));
            click(buttonNext);
        }
        MobileElement buttonDone = (MobileElement) driver.findElement(By.id("done"));
        click(buttonDone);

    }


}
