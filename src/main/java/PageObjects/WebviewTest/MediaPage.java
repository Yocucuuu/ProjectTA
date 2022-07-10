package PageObjects.WebviewTest;

import PageObjects.PageBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class MediaPage extends PageBase {

    @AndroidFindBy(accessibility = "Show roots")
    MobileElement leftMenuTray;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.support.v4.widget.DrawerLayout/android.widget.LinearLayout[2]/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[1]/android.widget.LinearLayout")
    MobileElement imageMenu;

    @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"SharedFolder\")")
    MobileElement folderImage;

    @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"gambar.png\")")
    MobileElement targetImage;

    @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"Upload Completed!\")")
    MobileElement successMessage;

    public MediaPage(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }
     public void openLeftMenuTray(){
        click(leftMenuTray);
     }
     public void  selectFolderImage(){
        click(folderImage);
     }
     public void selectImageMenu(){
        click(imageMenu);
     }
     public void tapTargetImage(){
        click(targetImage);
     }
     public String getSuccessMesageText(){
        return getAttribute(successMessage,"text");
     }
}
