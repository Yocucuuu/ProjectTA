package PageObjects.SejarahKita;

import PageObjects.PageBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;


public class SejarahKita_PageBase extends PageBase {

//    'email' => 'vanthony@student.ciputra.ac.id',
//    'password' => Hash::make('va123456'),
//    String id_frProfile = "profileFragment";
//    String id_frGame = "gameFragment";
//    String id_frLeaderboard = "leaderboardFragment";


    @AndroidFindBy(id = "gameFragment")
    public MobileElement fragGame;

    @AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc=\"Navigate up\"]") //kalo ada.....
    public MobileElement backButton;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.Toast")
    public MobileElement toastMessage;

    @AndroidFindBy(id = "profileFragment")
    public MobileElement fragProfile;

    @AndroidFindBy(id = "leaderboardFragment")
    public MobileElement fragLeadeboard;

    public SejarahKita_PageBase(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }


    public void toGameFragment(){
        click(fragGame);
    }

    public void toLeaderBoardFragment(){
        click(fragLeadeboard);
    }

    public void toProfileFragment(){
        click(fragProfile);
    }

    public void back(){ click(backButton);}


}
