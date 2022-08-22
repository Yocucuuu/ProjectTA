package PageObjects.SejarahKita;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.touch.TouchActions;

import javax.swing.text.StyledEditorKit;
import java.time.Duration;

public class PlayingActivity extends SejarahKita_PageBase{

    @AndroidFindBy(id = "lbl_pertanyaan_playing_game_fragment")
    public MobileElement tvSoal;

    @AndroidFindBy(id ="et_jawaban_playing_game_fragment")
    public MobileElement inputJawaban;

    @AndroidFindBy(id ="btn_exit_playing_game_fragment")
    public MobileElement btnExit;

    @AndroidFindBy(id ="btn_positive_alert_dialog_layout")
    public MobileElement btnConfirmExit;

    @AndroidFindBy(id ="btn_negative_alert_dialog_layout")
    public MobileElement btnCancelExit;

    @AndroidFindBy(id="btn_jawab_playing_game_fragment")
    public MobileElement btnSubmit;
    
    @AndroidFindBy(id = "rb_nyawa_ranked_playing_game_fragment")
    public MobileElement lifeBar;

    @AndroidFindBy(id = "lbl_total_skor_game_ended_layout")
    public MobileElement tvScore;


    @AndroidFindBy(id = "btn_main_lagi_game_ended_layout")
    MobileElement btnMainlagi;

    @AndroidFindBy(id = "btn_lihat_leaderboard_game_ended_layout")
    MobileElement linkLeaderboard;

    @AndroidFindBy(id = "com.uc.sejarahkita_mobile:id/btn_show_answer_casual_playing_game_fragment")
    public MobileElement btnShowHint;

    @AndroidFindBy(id = "com.uc.sejarahkita_mobile:id/btn_lanjut_show_answer_layout")
    public MobileElement btnCloseHint;

    @AndroidFindBy(id = "com.uc.sejarahkita_mobile:id/lbl_jawaban_show_answer_casual_layout")
    public  MobileElement tvHint;




    public PlayingActivity(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public String getSoal(){
        return getText(tvSoal);
    }

    public void isiJawaban(String jawaban){
        click(inputJawaban);
        ((AndroidDriver)driver).getKeyboard().pressKey(jawaban);
//        ((AndroidDriver)driver).hideKeyboard();

    }
    public void exitGame(){
        click(btnExit);
        click(btnConfirmExit);
    }
    public void cancelExitGame(){
        click(btnExit);
        click(btnCancelExit);
    }
    public void tapBtnjawab(){
        click(btnSubmit);
    }
    public void backToHome(){ click(btnMainlagi);}
    public void toDashboardFragment(){click(linkLeaderboard);}
    public void openHintTray(){
        try{
            click(btnShowHint);
        }catch (Exception ex){
            System.out.println(" not casual mode");
        }
    }
    public void closeHintTray(){
        try{
            click(btnCloseHint);
        }catch (Exception ex){
            System.out.println("hint tray is not openned");
        }
    }
    public String getHint(){
        waitForVisibility(tvHint);
        return getAttribute(tvHint,"text");
    }

    public void scrollDown(){
        AndroidTouchAction actions = new AndroidTouchAction(driver);

        Dimension dimension = driver.manage().window().getSize();
        int scrollStart = (int) (dimension.getHeight() * 0.8);
        int scrollEnd = (int) (dimension.getHeight()*0.1);

        actions = new AndroidTouchAction(driver)
                .press(PointOption.point(0, scrollStart))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
                .moveTo(PointOption.point(0, scrollEnd))
                .release()
                .perform();
    }

    public Boolean keepPlaying(){
        Boolean stop =  false;
        try{
            driver.findElement(By.id(tvScore.getId())).isDisplayed();
            stop = true;
        }catch (Exception x){

        }
        return stop;
    }

}
