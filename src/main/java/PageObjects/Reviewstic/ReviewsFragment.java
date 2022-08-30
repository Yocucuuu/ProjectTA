package PageObjects.Reviewstic;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class ReviewsFragment extends Reviewstic_Pagebase{

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"See All\"]")
    MobileElement btnSeeAll;


    public ReviewsFragment(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public void toAllReview(){
        click(btnSeeAll);
    }
}
