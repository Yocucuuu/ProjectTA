package tests;

import PageObjects.MoneyTracker.*;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.List;

public class MoneyTracker_iOS_FeatureTest extends TestBase{

//( - ) make new expense category without name , name TEST ex
//( + ) add new expense
//( + ) delete expense Category
//
//( - ) make new income category , name TEST in
//( + ) add new income
//( + ) delete income Category
//
//( - ) add new member without name
//( + ) add new Member
//( - ) edit member name with empty value
//( + ) edit member name
//( + ) add expense by member
//( + ) add income by member
//
//( - ) add new account without name
//( - ) add new account without curencies
//( + ) add new account with usd currencies
//( + ) add expenses from 2 different account (USD & Rupiah)

    String testExpenseName = "addNewExpense";
    String testIncomeName = "addNewIncome";
    String testMemberName = "TestMember";
    String MYSELF = "Myself";
    @BeforeClass
    public void beforeClass() throws MalformedURLException {
        iOS_Iphone7_MoneyTracker();
    }

    @AfterMethod
    public void afterMethod() throws Exception {
       driver.resetApp();
    }

    @Test(priority = 0)
    public void make_new_expense_category_without_name() {
        SettingFragment setting = new SettingFragment(driver);
        setting.tapSettingFrag();

        setting.toMenuCategories();

        CategoriesSetting cat =  new CategoriesSetting(driver);
        cat.tapAddButton();

        AddExpenseCategory addPage = new AddExpenseCategory(driver);
        addPage.inputCategoriesName("");
        addPage.done();
        Assert.assertEquals("Please Enter Category Name", addPage.getErrorMessage());

    }

    @Test(priority = 1)
    public void add_new_expense() throws InterruptedException {
        SettingFragment setting = new SettingFragment(driver);
        setting.tapSettingFrag();

        setting.toMenuCategories();

        CategoriesSetting cat =  new CategoriesSetting(driver);
        cat.tapAddButton();

        AddExpenseCategory addPage = new AddExpenseCategory(driver);
        addPage.inputCategoriesName( testExpenseName);
        addPage.done();
        Assert.assertTrue(cat.findExpenseCategories(testExpenseName));
    }

    @Test(priority = 2)
    public void delete_expense_Category() throws InterruptedException {
        SettingFragment setting = new SettingFragment(driver);
        setting.tapSettingFrag();
        setting.toMenuCategories();

        CategoriesSetting cat =  new CategoriesSetting(driver);
        cat.selectExpenseCategories(testExpenseName);

        DetailCategories detail = new DetailCategories(driver);
        detail.toEditPage();
        detail.tapDelete();

        Assert.assertFalse(cat.findExpenseCategories(testExpenseName));
    }

    @Test(priority = 3)
    public void make_new_income_category_without_name() {
        SettingFragment setting = new SettingFragment(driver);
        setting.tapSettingFrag();
        setting.toMenuCategories();

        CategoriesSetting cat =  new CategoriesSetting(driver);
        cat.toTabIncome();
        cat.tapAddButton();


        AddExpenseCategory addPage = new AddExpenseCategory(driver);
        addPage.inputCategoriesName("");
        addPage.done();
        Assert.assertEquals("Please Enter Category Name", addPage.getErrorMessage());

    }
    @Test(priority = 4)
    public void add_new_income() {
        SettingFragment setting = new SettingFragment(driver);
        setting.tapSettingFrag();

        setting.toMenuCategories();

        CategoriesSetting cat =  new CategoriesSetting(driver);
        cat.toTabIncome();
        cat.tapAddButton();

        AddExpenseCategory addPage = new AddExpenseCategory(driver);
        addPage.inputCategoriesName( testIncomeName);
        addPage.done();
        Assert.assertTrue(cat.findIncomeCategories(testIncomeName));

    }
    @Test(priority = 5)
    public void delete_income_Category() throws InterruptedException {
        SettingFragment setting = new SettingFragment(driver);
        setting.tapSettingFrag();
        setting.toMenuCategories();

        CategoriesSetting cat =  new CategoriesSetting(driver);
        cat.toTabIncome();
        cat.selectExpenseCategories(testIncomeName);

        DetailCategories detail = new DetailCategories(driver);
        detail.toEditPage();
        detail.tapDelete();


        Assert.assertFalse(cat.findIncomeCategories(testIncomeName));

    }
    @Test(priority = 6)
    public void add_new_member_without_name() {
        SettingFragment setting = new SettingFragment(driver);
        setting.tapSettingFrag();
        setting.toMenuMembers();

        MembersSetting memberPage =  new MembersSetting(driver);
        memberPage.tapAdd();

        DetailMember detail = new DetailMember(driver);
        detail.done();
        Assert.assertEquals(detail.getErrorMessage(),"Please Enter Member Name");
    }
    @Test(priority = 7)
    public void add_new_Member() {
        SettingFragment setting = new SettingFragment(driver);
        setting.tapSettingFrag();
        setting.toMenuMembers();

        MembersSetting memberPage =  new MembersSetting(driver);
        memberPage.tapAdd();

        DetailMember detail = new DetailMember(driver);
        detail.inputMemberName(testMemberName);
        detail.done();

        Assert.assertTrue(detail.verifyName(testMemberName));

    }
    @Test(priority = 8)
    public void edit_member_name_with_empty_value() {
        SettingFragment setting = new SettingFragment(driver);
        setting.tapSettingFrag();
        setting.toMenuMembers();

        MembersSetting memberPage =  new MembersSetting(driver);
        memberPage.selectMembers(testMemberName);

        DetailMember detail = new DetailMember(driver);
        detail.toEditPage();
        detail.editMemberName("");
        detail.doneEdit();
        Assert.assertEquals(detail.getErrorMessage(),"Please Enter Member Name");

    }
    @Test(priority = 9)
    public void edit_member_name() {
        SettingFragment setting = new SettingFragment(driver);
        setting.tapSettingFrag();
        setting.toMenuMembers();

        MembersSetting memberPage =  new MembersSetting(driver);
        memberPage.selectMembers(testMemberName);

        DetailMember detail = new DetailMember(driver);
        detail.toEditPage();
        detail.editMemberName(testMemberName+"2");
        detail.doneEdit();

        Assert.assertTrue(detail.verifyName(testMemberName+"2"));
    }

    @Test(priority = 10)
    public void add_expense_by_member() throws InterruptedException {
        SettingFragment setting = new SettingFragment(driver);
        setting.tapSettingFrag();
        setting.toMenuMembers();

        MembersSetting memberPage =  new MembersSetting(driver);
        memberPage.selectMembers(MYSELF);

        Thread.sleep(500);
        String s = String.format(" //XCUIElementTypeImage[contains(@name,'%s')]","10,000");
        int before = 0;
        try {
            memberPage.waitForVisibility((MobileElement) driver.findElementByXPath(s));
            before = driver.findElementsByXPath(s).size();
        }catch (Exception ex){}

        DetailMember detail =  new DetailMember(driver);
        detail.addTransaction();



        AddTransaction transaction = new AddTransaction(driver);
        transaction.selectAccount("BCA");
        transaction.setValue(10000+"");
        transaction.tapAddAndDone();

        s = String.format(" //XCUIElementTypeImage[contains(@name,'%s')]","10,000");
        transaction.waitForVisibility((MobileElement) driver.findElementByXPath(s));
        int res = driver.findElementsByXPath(s).size();


        Assert.assertTrue(res>before);

    }
    @Test(priority = 11)
    public void add_income_by_member() throws InterruptedException {
        SettingFragment setting = new SettingFragment(driver);
        setting.tapSettingFrag();
        setting.toMenuMembers();

        MembersSetting memberPage =  new MembersSetting(driver);
        memberPage.selectMembers(MYSELF);

        Thread.sleep(500);
        String s = String.format(" //XCUIElementTypeImage[contains(@name,'%s')]","20,000");

        int before = 0;
        try {
            memberPage.waitForVisibility((MobileElement) driver.findElementByXPath(s));
            before = driver.findElementsByXPath(s).size();
        }catch (Exception ex){}

        DetailMember detail =  new DetailMember(driver);
        detail.addTransaction();



        AddTransaction transaction = new AddTransaction(driver);
        transaction.selectAccount("BCA");
        transaction.clikTabIncome();
        transaction.setValue(20000+"");
        transaction.tapAddAndDone();

        Thread.sleep(500);
        s = String.format(" //XCUIElementTypeImage[contains(@name,'%s')]","20,000");
        transaction.waitForVisibility((MobileElement) driver.findElementByXPath(s));
        int res = driver.findElementsByXPath(s).size();


        Assert.assertTrue(res>before);
    }


    @Test(priority = 15)
    public void add_expenses_from_2_different_account() throws InterruptedException {
        SettingFragment setting = new SettingFragment(driver);
        setting.tapSettingFrag();
        setting.toMenuMembers();

        MembersSetting memberPage =  new MembersSetting(driver);
        memberPage.selectMembers(MYSELF);


        Thread.sleep(1000);
        String s = String.format(" //XCUIElementTypeStaticText[contains(@name,'%s')]","Rp10,000");
        memberPage.waitForVisibility((MobileElement) driver.findElementByXPath(s));
        List<IOSElement> arrElement = driver.findElementsByXPath(s);
        for(int i =0 ;i<arrElement.size() ;i++){
            System.out.println(arrElement.get(i).getAttribute("name"));
            System.out.println();
        }

        DetailMember detail =  new DetailMember(driver);
        detail.addTransaction();


        AddTransaction transaction = new AddTransaction(driver);
        transaction.selectAccount("BCA");
        transaction.setValue(10000+"");
        transaction.tapAdd();

        transaction.selectAccount("DOLLAR");
        transaction.setValue(200+"");
        transaction.tapAddAndDone();

        Thread.sleep(500);
        s = String.format(" //XCUIElementTypeStaticText[contains(@name,'%s')]","Rp200");
        memberPage.waitForVisibility((MobileElement) driver.findElementByXPath(s));
        Assert.assertTrue(driver.findElementsByXPath(s).size() ==0);






    }








}
