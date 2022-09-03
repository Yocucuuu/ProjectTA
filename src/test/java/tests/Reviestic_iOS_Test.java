package tests;

import PageObjects.PageBase;
import PageObjects.Reviewstic.*;
import io.appium.java_client.*;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.ios.IOSTouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.Assertion;
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


    @BeforeClass
    public void beforeClass() throws MalformedURLException {
        iOS_Iphone7_Reviewistic();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void afterMethod() {
        driver.resetApp();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    @Test
    public void demo() {
        ((IOSDriver)driver).shake();
        ((IOSDriver)driver).hideKeyboard();
        ((IOSDriver)driver).isKeyboardShown();
        ((IOSDriver)driver).lockDevice();
        ((IOSDriver)driver).lockDevice();
        ((IOSDriver)driver).execute("","");


    }

    @Test(groups = "Reviews" , priority = 0)
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
            allReviewPage.toTabProduk();
            allReviewPage.toRating(i+"");
            temp = CSVReader.getReviewCount("Produk" , i+"");
            softA.assertEquals(temp , allReviewPage.getTableRow());
        }

        for(int i=1 ;i<=5 ;i++){
            allReviewPage.toTabPrice();
            allReviewPage.toRating(i+"");
            temp = CSVReader.getReviewCount("Price" , i+"");
            softA.assertEquals(temp , allReviewPage.getTableRow());
        }
        softA.assertAll();


    }

    @Test(groups = "Reviews" ,priority = 1 )  // dijamin error
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

        for(int i=1 ;i<=5 ;i++){
            allReviewPage.toTabService();
            allReviewPage.toRating(i+"");
            temp = CSVReader.getReviewCount("Service" , i+"");
            int rows = allReviewPage.getTableRow();
            System.out.println(rows +"___" + temp + "__"+i);
            softA.assertEquals(temp , rows);
        }

        for(int i=1 ;i<=5 ;i++){
            allReviewPage.toTabProduk();
            allReviewPage.toRating(i+"");
            temp = CSVReader.getReviewCount("Produk" , i+"");
            softA.assertEquals(temp , allReviewPage.getTableRow());
        }

        for(int i=1 ;i<=5 ;i++){
            allReviewPage.toTabPrice();
            allReviewPage.toRating(i+"");
            temp = CSVReader.getReviewCount("Price" , i+"");
            softA.assertEquals(temp , allReviewPage.getTableRow());
        }
        softA.assertAll();




    }

    @Test(groups = "Reviews" ,priority = 2)
    public void searchTransactionWithID14() {
        SoftAssert  softA =  new SoftAssert();
        TransactionFragment transactionFrag =  new TransactionFragment(driver);
        transactionFrag.toTransactionFrag();
        transactionFrag.searchTransaction("#14");
        driver.findElementByAccessibilityId("#14");



    }

    @Test(groups = "Transaction" , priority = 3)
    public void searchTransactionWithPrefix1 () {
        SoftAssert  softA =  new SoftAssert();
        TransactionFragment transactionFrag =  new TransactionFragment(driver);
        transactionFrag.toTransactionFrag();
        transactionFrag.searchTransaction("#1");
        driver.findElementByAccessibilityId("#14");
        driver.findElementByAccessibilityId("#1");
        driver.findElementByAccessibilityId("#10");


    }

    @Test(groups = "Transaction" ,priority = 4)
    public void validateReviewedTransactionData() {
        SoftAssert  softA =  new SoftAssert();
        String id = "#2";
        TransactionFragment transactionFrag =  new TransactionFragment(driver);
        transactionFrag.toTransactionFrag();
        transactionFrag.searchTransaction(id);
        driver.findElementByAccessibilityId(id).click();

        DetailTransaction detail = new DetailTransaction(driver);
        String  review = detail.getReview();
        String  price = detail.getPriceRating();
        String  service = detail.getServiceRating();
        String  product = detail.getProductRating();
        System.out.println(review);
        System.out.println(price);
        System.out.println(service);
        System.out.println(product);
        ReviewisticTransaction trans = CSVReader.getTransactionByID(id.substring(1));

        softA.assertEquals(review , trans.review);
        softA.assertEquals(price, trans.ratingPrice);
        softA.assertEquals(product , trans.ratingProduk);
        softA.assertEquals(service , trans.ratingService);

        softA.assertAll();


    }

    @Test(groups = "Transaction", priority = 5)
    public void validateUnreviewedTransactionData() {
        SoftAssert  softA =  new SoftAssert();
        String id = "#3";
        TransactionFragment transactionFrag =  new TransactionFragment(driver);
        transactionFrag.toTransactionFrag();
        transactionFrag.searchTransaction(id);
        driver.findElementByAccessibilityId(id).click();

        DetailTransaction detail = new DetailTransaction(driver);
        String  review = detail.getReview();
        softA.assertEquals(review , "Belum ada review");
        softA.assertAll();

    }

    @Test(groups = "Transaction",priority = 6 )
    public void validateDetailTransaction() {
        SoftAssert  softA =  new SoftAssert();
        String id = "#2";
        TransactionFragment transactionFrag =  new TransactionFragment(driver);
        transactionFrag.toTransactionFrag();
        transactionFrag.searchTransaction(id);
        driver.findElementByAccessibilityId(id).click();

        DetailTransaction detail = new DetailTransaction(driver);

        List<MobileElement> listDetail= detail.getDetailTransaction();
        ReviewisticTransaction trans = CSVReader.getTransactionByID(id.substring(1));

        softA.assertEquals(listDetail.size(), 2); // eror yayyyyyyy  , di table e harus e cuma ada 2 transaksi

        softA.assertAll();

    }

    @Test(groups = "Promo" , priority = 7)
    public void addPromowithEmptyField() {
        PromoFragment promo = new PromoFragment(driver);
        promo.toPromoFrag();


        promo.clickAddPromo();
        promo.clearAllField();
        promo.submitAddPromo();
        promo.confirmErrorMessage();

    }

    @Test(groups = "Promo",priority = 8 )
    public void addPromoWithEmptyDesc() {
        PromoFragment promo = new PromoFragment(driver);
        promo.toPromoFrag();


        promo.clickAddPromo();
        promo.clearAllField();
        promo.sendName("Demo Voucher Name");
        promo.submitAddPromo();
        promo.confirmErrorMessage();
        // harus e muncul toast
    }

    @Test(groups = "Promo",priority = 9 )
    public void addPromoWithEmptyName() {
        PromoFragment promo = new PromoFragment(driver);
        promo.toPromoFrag();


        promo.clickAddPromo();
        promo.clearAllField();
        promo.sendDesc("Demo Voucher Desc" );
        promo.submitAddPromo();
        promo.confirmErrorMessage();
        // harus e muncul toast
    }

    @Test(groups = "Promo" ,priority = 10)
    public void addPromoWithInvalidDate() {
        PromoFragment promo = new PromoFragment(driver);
        promo.toPromoFrag();


        promo.clickAddPromo();
        promo.clearAllField();
        promo.sendDesc("Demo Voucher Desc" );
        promo.sendName("Demo Voucher Name" );
        promo.openDatePicker();
        promo.decreaseMonth();
        promo.selectDate();
        promo.closeDatePicker();

        promo.submitAddPromo();
        promo.confirmErrorMessage();
        // harus e muncul toast , tapi malah success

    }

    @Test(groups = "Promo",priority = 11 )
    public void successAddPromo() {

        PromoFragment promo = new PromoFragment(driver);
        promo.toPromoFrag();

        promo.clickAddPromo();
        promo.clearAllField();
        promo.sendDesc("Demo Voucher Desc" );
        promo.sendName("Demo Voucher Name" );
        promo.openDatePicker();
        promo.increaseMonth();
        promo.selectRandomDate();
        promo.closeDatePicker();
        promo.submitAddPromo();



    }

    @Test(groups = "Promo" ,priority = 12 , dependsOnMethods = "successAddPromo") // iki yo error pisan
    public void validateTransactionInsertedPromo() {

        // pasti sg terakhir
        PromoFragment promo = new PromoFragment(driver);
        promo.getLastInsertedVoucher().click();
        List<IOSElement> listTransaction = ((IOSDriver)driver).findElementsByClassName("XCUIElementTypeCell");
        Assert.assertEquals(listTransaction.size(),0);




    }
}
