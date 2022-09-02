package PageObjects.Reviewstic;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

import java.sql.SQLOutput;

public class AllReviewPage extends Reviewstic_Pagebase{

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"All Reviews\"]")
    public MobileElement tabAllReviews;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Price\"]")
    public MobileElement tabPrice;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Produk\"]")
    public MobileElement tabProduk;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Service\"]")
    public MobileElement tabService;

    @iOSXCUITFindBy(accessibility = "1 ★")
    public MobileElement tab1;

    @iOSXCUITFindBy(accessibility = "2 ★")
    public MobileElement tab2;

    @iOSXCUITFindBy(accessibility = "3 ★")
    public MobileElement tab3;

    @iOSXCUITFindBy(accessibility = "4 ★")
    public MobileElement tab4;

    @iOSXCUITFindBy(accessibility = "5 ★")
    public MobileElement tab5;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"Reviewistic\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable")
    public MobileElement table;

    @Override
    public void sendText(MobileElement element, String text) {
        super.sendText(element, text);
        ((IOSDriver)driver).isKeyboardShown();
        ((IOSDriver)driver).hideKeyboard();
    }

    public AllReviewPage(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }
    public void toTabAll(){
        click(tabAllReviews);
    }

    public void toTabPrice(){
        click(tabPrice);
    }

    public void toTabService(){
        click(tabService);
    }

    public void toTabProduk(){
        click(tabProduk);
    }
    public void toRating(String rating){
        if(rating.equals("1")){
            click(tab1);

        }else if(rating.equalsIgnoreCase("2")){

            click(tab2);
        }else if(rating.equals("3")){

            click(tab3);
        }else if(rating.equalsIgnoreCase("4")){
            click(tab4);
        }else if(rating.equalsIgnoreCase("5")){
            click(tab5);
        }
        System.out.println(".....");
    }

    public void toRating1(){
        click(tab1);
    }
    public void toRating2(){
        click(tab2);
    }

    public void toRating3(){
        click(tab3);
    }

    public void voidtoRating4(){
        click(tab4);
    }

    public void toRating5(){
        click(tab5);
    }

    public int getTableRow(){
        waitForVisibility(table);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return table.findElementsByClassName("XCUIElementTypeCell").size();
    }

}
