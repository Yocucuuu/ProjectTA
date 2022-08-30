package tests;

import PageObjects.PageBase;
import PageObjects.Reviewstic.AllReviewPage;
import PageObjects.Reviewstic.ReviewsFragment;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSTouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.CSVReader;

import java.net.MalformedURLException;
import java.sql.Array;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import utils.*;

import static utils.CSVReader.getReviewsticTransactionData;

public class Reviestic_iOS_Test extends TestBase {

    ReviewsFragment review;
    @BeforeClass
    public void beforeClass() throws MalformedURLException {
        iOS_Iphone7_Reviewistic();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    @Test(groups = "Reviews" )
    public void countReviewsEachTab() {
        int temp =0;
        SoftAssert softA = new SoftAssert();
        ReviewsFragment review = new ReviewsFragment(driver);
        review.toReviewFrag();
        review.toAllReview();

        AllReviewPage allReviewPage = new AllReviewPage(driver);
        // Service , Price , Produk , All Review

        for(int i=1 ;i<=5 ;i++){
            allReviewPage.toTabAll();
            allReviewPage.toRating(i+"");
            temp = CSVReader.getReviewCount("All" , i+"");
            softA.assertEquals(temp , allReviewPage.getTableRow());
        }

        for(int i=1 ;i<=5 ;i++){
            allReviewPage.toTabService();
            allReviewPage.toRating(i+"");
            temp = CSVReader.getReviewCount("Service" , i+"");
            int rows = allReviewPage.getTableRow();
            System.out.println(rows +"___" + temp + "__"+i);
            softA.assertEquals(temp , rows);
        }

        for(int i=1 ;i<=5 ;i++){
            allReviewPage.toTabPrice();
            allReviewPage.toRating(i+"");
            temp = CSVReader.getReviewCount("Produk" , i+"");
            softA.assertEquals(temp , allReviewPage.getTableRow());
        }

        for(int i=1 ;i<=5 ;i++){
            allReviewPage.toTabPrice();
            allReviewPage.toRating(i+"");
            temp = CSVReader.getReviewCount("All" , i+"");
            softA.assertEquals(temp , allReviewPage.getTableRow());
        }
        softA.assertAll();


    }

    @Test(groups = "Reviews" )  // dijamin error
    public void countReviewsEachTabAfterRefresh() throws InterruptedException {
        int temp =0;
        SoftAssert softA = new SoftAssert();
        ReviewsFragment review = new ReviewsFragment(driver);
        review.toReviewFrag();
        review.toAllReview();

        AllReviewPage allReviewPage = new AllReviewPage(driver);
        // Service , Price , Produk , All Review

        allReviewPage.waitForVisibility(allReviewPage.table);
        Thread.sleep(5000);
        // refresh table e
        try {
            new IOSTouchAction(driver)
                    .press(PointOption.point(200, 250))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(200)))
                    .moveTo(PointOption.point(200, 600))
                    .release()
                    .perform();
            System.out.println("refresh");
        } catch (Exception e) {
            System.err.println("swipeElementIOS(): TouchAction FAILED\n" + e.getMessage());

        }

        for(int i=1 ;i<=5 ;i++){
            allReviewPage.toTabAll();
            allReviewPage.toRating(i+"");
            temp = CSVReader.getReviewCount("All" , i+"");
            softA.assertEquals(temp , allReviewPage.getTableRow());
        }

//        for(int i=1 ;i<=5 ;i++){
//            allReviewPage.toTabService();
//            allReviewPage.toRating(i+"");
//            temp = CSVReader.getReviewCount("Service" , i+"");
//            int rows = allReviewPage.getTableRow();
//            System.out.println(rows +"___" + temp + "__"+i);
//            softA.assertEquals(temp , rows);
//        }
//
//        for(int i=1 ;i<=5 ;i++){
//            allReviewPage.toTabPrice();
//            allReviewPage.toRating(i+"");
//            temp = CSVReader.getReviewCount("Produk" , i+"");
//            softA.assertEquals(temp , allReviewPage.getTableRow());
//        }
//
//        for(int i=1 ;i<=5 ;i++){
//            allReviewPage.toTabPrice();
//            allReviewPage.toRating(i+"");
//            temp = CSVReader.getReviewCount("All" , i+"");
//            softA.assertEquals(temp , allReviewPage.getTableRow());
//        }
        softA.assertAll();




    }

    @Test(groups = "Reviews" )
    public void searchTransactionWithID14() {
    }

    @Test(groups = "Transaction" ,dependsOnGroups = "Reviews")
    public void searchTransactionWithPrefix1 () {
    }

    @Test(groups = "Transaction" )
    public void validateReviewedTransactionData() {
    }

    @Test(groups = "Transaction" )
    public void validateUnreviewedTransactionData() {
    }

    @Test(groups = "Transaction" )
    public void validateDetailTransaction() {
    }

    @Test(groups = "Promo" )
    public void addPromowithEmptyField() {
    }

    @Test(groups = "Promo" )
    public void addPromoWithEmptyDesc() {
    }

    @Test(groups = "Promo" )
    public void addPromoWithEmptyName() {
    }

    @Test(groups = "Promo" )
    public void addPromoWithInvalidDate() {
    }

    @Test(groups = "Promo" )
    public void successAddPromo() {
    }

    @Test(groups = "Promo" )
    public void validateTransactionInsertedPromo() {
    }
}
