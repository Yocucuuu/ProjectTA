package PageObjects.Reviewstic;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

import java.util.List;

public class DetailTransaction extends Reviewstic_Pagebase{


    @iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"Reviewistic\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther[5]/XCUIElementTypeOther/XCUIElementTypeStaticText")
    MobileElement labelRatingPrice;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"Reviewistic\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[3]/XCUIElementTypeOther[5]/XCUIElementTypeOther/XCUIElementTypeStaticText")
    MobileElement labelRatingService;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"Reviewistic\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[5]/XCUIElementTypeOther[5]/XCUIElementTypeOther/XCUIElementTypeStaticText")
    MobileElement labelRatingProduct;

    @iOSXCUITFindBy(accessibility = "Empty list")
    MobileElement emptyListTable;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"Reviewistic\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText[3]")
    MobileElement labelReview;


    public DetailTransaction(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public String getPriceRating(){
        return getAttribute(labelRatingPrice , "value");
    }

    public String getServiceRating(){
        return getAttribute(labelRatingService, "value");
    }

    public String getProductRating(){
        return getAttribute(labelRatingProduct , "value");
    }

    public List<MobileElement> getDetailTransaction(){
        return driver.findElementsByXPath("//XCUIElementTypeApplication[@name=\"Reviewistic\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell");
    }


    public String getReview(){
        return getAttribute(labelReview, "value");
    }
}
