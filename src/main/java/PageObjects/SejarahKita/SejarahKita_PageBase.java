package PageObjects.SejarahKita;

import PageObjects.PageBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;

//    'email' => 'vanthony@student.ciputra.ac.id',
//    'password' => Hash::make('va123456'),
//    String id_frProfile = "profileFragment";
//    String id_frGame = "gameFragment";
//    String id_frLeaderboard = "leaderboardFragment";

public class SejarahKita_PageBase extends PageBase {

    @AndroidFindBy(id = "gameFragment")
    public MobileElement fragGame;

    @AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc=\"Navigate up\"]")
    @AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc=\"Kembali ke atas\"]")
    public MobileElement backButton;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.Toast")
    public MobileElement toastMessage;

    @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"Failed\")")
    public MobileElement failedLoginToast;

    @AndroidFindBy(id = "profileFragment")
    public MobileElement fragProfile;

    @AndroidFindBy(id = "leaderboardFragment")
    public MobileElement fragLeadeboard;

    @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"Bacaa plis\")")
    public MobileElement sms;


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

    public String getToastMessage(){
        waitForVisibility(toastMessage);
        return getAttribute(toastMessage,"text");
    }
    public void back(){ click(backButton);}

    public void tapSmsnotif(){
        click(sms);
    }


}
