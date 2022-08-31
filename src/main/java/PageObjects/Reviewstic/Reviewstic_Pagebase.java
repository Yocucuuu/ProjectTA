package PageObjects.Reviewstic;

import PageObjects.PageBase;
import PageObjects.SejarahKita.GameFragment;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.*;

public class Reviewstic_Pagebase extends PageBase {

    @iOSXCUITFindBy(accessibility = "Review")
    public MobileElement fragReviews;

    @iOSXCUITFindBy(accessibility = "Transaction")
    public MobileElement fragTransaction;

    @iOSXCUITFindBy(id = "Promos")
    public MobileElement fragPromo;

    @iOSXCUITFindBy(id = "back")
    public MobileElement buttonBack;

    @iOSXCUITFindBy(accessibility = "Ok")
    public MobileElement btnOK;  // kalo ada pesan error , diminta acc

    public Reviewstic_Pagebase(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public void toPromoFrag(){
        click(fragPromo);
    }

    public void toTransactionFrag(){
        click(fragTransaction);

    }

    public void toReviewFrag(){
        click(fragReviews);
    }

    public void confirmErrorMessage(){
        click(btnOK);
    }


}
