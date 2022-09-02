package tests;

import PageObjects.Reviewstic.AllReviewPage;
import PageObjects.Reviewstic.ReviewsFragment;
import io.appium.java_client.ios.IOSDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.CSVReader;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Reviewistic_IOS_Interuption_Test extends TestBase{

    /*execute()
    getMouse()
    hideKeyboard(String keyName)
    hideKeyboard(String strategy, String keyName)
    findElement()
    lockDevice(int seconds)
    shake()
    swipe(int startx, int starty, int )*/

    @BeforeClass
    public void beforeClass() throws MalformedURLException {
        iOS_Iphone7_Reviewistic();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    @Test
    public void lockDeviceTest() {


        int temp =0;
        SoftAssert softA = new SoftAssert();
        ReviewsFragment review = new ReviewsFragment(driver);
        review.toReviewFrag();
        review.toAllReview();

        AllReviewPage allReviewPage = new AllReviewPage(driver);
        // Service , Price , Produk , All Review
        ((IOSDriver)driver).lockDevice(Duration.ofSeconds(5));
        for(int i=1 ;i<=5 ;i++){
            allReviewPage.toTabAll();
            allReviewPage.toRating(i+"");
            temp = CSVReader.getReviewCount("All" , i+"");
            softA.assertEquals(temp , allReviewPage.getTableRow());
        }
        ((IOSDriver)driver).lockDevice(Duration.ofSeconds(5));
        for(int i=1 ;i<=5 ;i++){
            allReviewPage.toTabService();
            allReviewPage.toRating(i+"");
            temp = CSVReader.getReviewCount("Service" , i+"");
            int rows = allReviewPage.getTableRow();
            System.out.println(rows +"___" + temp + "__"+i);
            softA.assertEquals(temp , rows);
        }
        ((IOSDriver)driver).lockDevice(Duration.ofSeconds(5));

        for(int i=1 ;i<=5 ;i++){
            allReviewPage.toTabProduk();
            allReviewPage.toRating(i+"");
            temp = CSVReader.getReviewCount("Produk" , i+"");
            softA.assertEquals(temp , allReviewPage.getTableRow());
        }
        ((IOSDriver)driver).lockDevice(Duration.ofSeconds(5));

        for(int i=1 ;i<=5 ;i++){
            allReviewPage.toTabPrice();
            allReviewPage.toRating(i+"");
            temp = CSVReader.getReviewCount("Price" , i+"");
            softA.assertEquals(temp , allReviewPage.getTableRow());
        }
        ((IOSDriver)driver).lockDevice(Duration.ofSeconds(5));
        softA.assertAll();



    }
}
