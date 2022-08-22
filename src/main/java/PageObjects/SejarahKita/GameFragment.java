package PageObjects.SejarahKita;

import PageObjects.PageBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;

public class GameFragment extends SejarahKita_PageBase {

    String id_btnEasy = "fab_easy_game_fragment";
    String id_btnHard = "fab_hard_game_fragment";
    String id_btnCasual = "fab_casual_game_fragment";
    String id_btnCancel = "btn_cancel_game_loading_fragment";

    @AndroidFindBy(id ="cons_cv_easy_game_fragment")
    public MobileElement btnEasy ;

    @AndroidFindBy(id ="cons_cv_hard_game_fragment")
    public MobileElement btnHard ;

    @AndroidFindBy(id ="cons_cv_casual_game_fragment")
    public MobileElement btnCasual;

    @AndroidFindBy(id ="btn_cancel_game_loading_fragment")
    MobileElement btnCancel;




    public GameFragment(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public void tapEasyGame(){
        click(btnEasy);
    }

    public void tapHardGame(){
        click(btnHard);
    }

    public void tapCasualGame(){

        click(btnCasual);
    }

    public void tapCancelButton(){
        click(btnCancel);
    }

}
