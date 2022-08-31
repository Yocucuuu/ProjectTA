package tests;

import PageObjects.PageBase;
import PageObjects.Reviewstic.*;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSTouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.graalvm.compiler.debug.CSVUtil;
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
        SoftAssert  softA =  new SoftAssert();
        TransactionFragment transactionFrag =  new TransactionFragment(driver);
        transactionFrag.searchTransaction("14");
        driver.findElementByAccessibilityId("14");

    }

    @Test(groups = "Transaction" ,dependsOnGroups = "Reviews")
    public void searchTransactionWithPrefix1 () {
        SoftAssert  softA =  new SoftAssert();
        TransactionFragment transactionFrag =  new TransactionFragment(driver);
        transactionFrag.searchTransaction("1");
        driver.findElementByAccessibilityId("14");
        driver.findElementByAccessibilityId("1");
        driver.findElementByAccessibilityId("10");


    }

    @Test(groups = "Transaction" )
    public void validateReviewedTransactionData() {
        SoftAssert  softA =  new SoftAssert();
        String id = "2";
        TransactionFragment transactionFrag =  new TransactionFragment(driver);
        transactionFrag.searchTransaction("#"+id);
        driver.findElementByAccessibilityId(id).click();

        DetailTransaction detail = new DetailTransaction(driver);
        String  review = detail.getReview();
        String  price = detail.getPriceRating();
        String  service = detail.getServiceRating();
        String  product = detail.getProductRating();
        ReviewisticTransaction trans = CSVReader.getTransactionByID(id);

        softA.assertEquals(review , trans.review);
        softA.assertEquals(price, trans.ratingPrice);
        softA.assertEquals(product , trans.ratingProduk);
        softA.assertEquals(service , trans.ratingService);

        softA.assertAll();


    }

    @Test(groups = "Transaction" )
    public void validateUnreviewedTransactionData() {
        SoftAssert  softA =  new SoftAssert();
        String id = "2";
        TransactionFragment transactionFrag =  new TransactionFragment(driver);
        transactionFrag.searchTransaction("#"+id);
        driver.findElementByAccessibilityId(id).click();

        DetailTransaction detail = new DetailTransaction(driver);
        String  review = detail.getReview();
        softA.assertEquals(review , "Belum ada review");
        softA.assertAll();

    }

    @Test(groups = "Transaction" )
    public void validateDetailTransaction() {
        SoftAssert  softA =  new SoftAssert();
        String id = "2";
        TransactionFragment transactionFrag =  new TransactionFragment(driver);
        transactionFrag.searchTransaction("#"+id);
        driver.findElementByAccessibilityId(id).click();

        DetailTransaction detail = new DetailTransaction(driver);

        List<MobileElement> listDetail= detail.getDetailTransaction();
        ReviewisticTransaction trans = CSVReader.getTransactionByID(id);

        softA.assertEquals(listDetail.size(), 2); // eror yayyyyyyy

        softA.assertAll();

    }

    @Test(groups = "Promo" )
    public void addPromowithEmptyField() {
        PromoFragment promo = new PromoFragment(driver);
        promo.toPromoFrag();


        promo.clickAddPromo();
        promo.clearAllField();
        promo.submitAddPromo();

    }

    @Test(groups = "Promo" )
    public void addPromoWithEmptyDesc() {
        PromoFragment promo = new PromoFragment(driver);
        promo.toPromoFrag();


        promo.clickAddPromo();
        promo.clearAllField();
        promo.
        promo.submitAddPromo();
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
