package tests;

import PageObjects.GymRecord.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class GymRecord_Function_Test extends TestBase{
    Home home;
    DetailExercise detailExercise;
    DetailProgram detailProgram;
    MyDatabase myDatabase;
    NewExercise newExercise;
    NewProgram newProgram;

    @BeforeClass
    public void beforeClass() throws MalformedURLException {
        iOS_Iphone7_GymRecord_setup();

    }

    @BeforeMethod
    public void beforeMethod() {

    }
    @AfterMethod
    public void afterMethod() {

    }


    @Test(groups = "negative")
    public void addNewExerciseWithoutName() {
        home = new Home(driver);;
        home.toMyDatabase();

        myDatabase = new MyDatabase(driver);
        myDatabase.tapNewExercise();

        newExercise = new NewExercise(driver);
        newExercise.chooseCategory("Arms");
        newExercise.fillExerciseTittle("");





    }
    @Test
    public void addNewExercise() {
    }
    @Test
    public void editNewAddedExercise() {
    }

    @Test()
    public void addProgramWithoutSuperset() {
    }

    @Test()
    public void editAddedProgramNameAndSubtitle() {
    }

    @Test()
    public void editAddedProgramWithSuperset() {
    }

    @Test()
    public void addWorkoutWithAddedProgram() {
    }
    @Test()
    public void editAddedProgramName() {
    }
    @Test()
    public void addExerciseToProgram() {
    }
    @Test()
    public void addSetsandRepsToWorkoutToday() {
    }

    @Test()
    public void addSetsandRepsToWorkoutYesterday() {
    }

    @Test
    public void editExercise() {
    }
    @Test
    public void deleteExercise() {
    }
    @Test
    public void editProgram() {
    }
    @Test
    public void deleteProgram() {
    }
    @Test
    public void deleteWorkout() {
    }


}
