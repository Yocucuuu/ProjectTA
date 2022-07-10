package PageObjects.SejarahKita;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;

public class ProfileFragment extends SejarahKita_PageBase {

    String id_btnLogout ="btn_logout_profile_fragment";
    public ProfileFragment(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    @AndroidFindBy(id = "btn_logout_profile_fragment")
    MobileElement btnLogout ;

    @AndroidFindBy(id = "lbl_email_profile_fragment")
    MobileElement lblEmail;

    @AndroidFindBy(id = "btn_playing_history_profile_fragment")
    MobileElement btnHistory;

    @AndroidFindBy(id = "lbl_hard_ranked_point_profile_fragment")
    MobileElement labelHard;

    @AndroidFindBy(id = "lbl_easy_ranked_point_profile_fragment") // 11 RP , jan lupa di trim
    MobileElement labelEasy;

    public void tapLogout(){
        click(btnLogout);
    }
    public void tapHistory(){ click(btnHistory);}
    public String getLoggedInEmail(){
        return getText(lblEmail);
    }
}
