package PageObjects.SejarahKita;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LeaderboardFragment extends SejarahKita_PageBase{
    public LeaderboardFragment(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    @AndroidFindBy(id="btn_detail_easy_leaderboard_fragment")
    MobileElement btn_showAllEasy;

    @AndroidFindBy(id="btn_detail_hard_leaderboard_fragment")
    MobileElement btn_showAllHard;

    @AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc=\"Navigate up\"]")
    MobileElement backbutton;
    public void tapShowAll_EasyLeaderboard(){
        click(btn_showAllEasy);

    }
    public void tapShowAll_HardLeaderboard(){
        click(btn_showAllHard);
    }

    public void tapBack(){
        click(backbutton);
    }

}
