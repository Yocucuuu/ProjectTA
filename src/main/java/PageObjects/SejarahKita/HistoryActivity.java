package PageObjects.SejarahKita;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;

import java.util.List;

public class HistoryActivity extends SejarahKita_PageBase{

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView")
    public MobileElement rvPlayingHistory;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.FrameLayout/android.view.ViewGroup/android.widget.TextView[3]")
    public MobileElement firstHistory;


    public HistoryActivity(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public List<MobileElement> getHistory(){
        waitForVisibility(rvPlayingHistory);
        return rvPlayingHistory.findElementsByXPath("/android.view.ViewGroup");
    }

    public MobileElement getFirstHistory(){
        waitForVisibility(firstHistory);
        return firstHistory;
    }





}
