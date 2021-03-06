package com.zerobank.stepdefinitions;

import com.zerobank.pages.*;
import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.ConfigurationReader;
import com.zerobank.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class AccountActivityStepDefs {

    @Given("the user is logged in")
    public void the_user_is_logged_in() throws InterruptedException {

//        Driver.get().get(ConfigurationReader.get("url"));
        new MainPage().signButton.click();
        new LoginPage().login(ConfigurationReader.get("username"),ConfigurationReader.get("password"));

        if(Driver.get().findElement(By.id("details-button")).isDisplayed()) {
//          For pass to security FOR CHROME ( 2 Steps )
            Driver.get().findElement(By.id("details-button")).click();
            Driver.get().findElement(By.id("proceed-link")).click();
        }
    }

    @When("the user clicks on Savings link on the Account Summary page")
    public void the_user_clicks_on_Savings_link_on_the_Account_Summary_page() throws InterruptedException {

        new AccountSummary().savings1.click();

    }

    @Then("the Account Activity page should be displayed")
    public void the_Account_Activity_page_should_be_displayed() {

        Assert.assertTrue(Driver.get().getTitle().contains("Account Activity"));

    }

    @Then("Account drop down should have Savings selected")
    public void account_drop_down_should_have_Savings_selected() throws InterruptedException {

        Select select = new Select(new AccountActivity().accOptionSelect);
        System.out.println("first option = " + select.getFirstSelectedOption().getText());
        Assert.assertEquals("Savings", select.getFirstSelectedOption().getText());
        Thread.sleep(2000);

    }

    @When("the user clicks on Brokerage link on the Account Summary page")
    public void the_user_clicks_on_Brokerage_link_on_the_Account_Summary_page() {

        new AccountSummary().brokerage.click();

    }

    @Then("Account	drop down should have Brokerage selected")
    public void account_drop_down_should_have_Brokerage_selected() {

        Select select = new Select(new AccountActivity().accOptionSelect);
        System.out.println("first option = " + select.getFirstSelectedOption().getText());
        Assert.assertEquals("Brokerage", select.getFirstSelectedOption().getText());

    }

    @When("the user clicks on Checking link on the Account Summary page")
    public void the_user_clicks_on_Checking_link_on_the_Account_Summary_page() {

        new AccountSummary().checking.click();

    }

    @Then("Account drop down should have Checking selected")
    public void account_drop_down_should_have_Checking_selected() {

        Select select = new Select(new AccountActivity().accOptionSelect);
        System.out.println("first option = " + select.getFirstSelectedOption().getText());
        Assert.assertEquals("Checking", select.getFirstSelectedOption().getText());

    }

    @When("the user clicks on Credit card link on the Account Summary page")
    public void the_user_clicks_on_Credit_card_link_on_the_Account_Summary_page() {

        new AccountSummary().creditCard.click();

    }

    @Then("Account drop down should have Credit Card selected")
    public void account_drop_down_should_have_Credit_Card_selected() {

        Select select = new Select(new AccountActivity().accOptionSelect);
        System.out.println("first option = " + select.getFirstSelectedOption().getText());
        Assert.assertEquals("Credit Card", select.getFirstSelectedOption().getText());

    }

    @When("the user clicks on Loan link on the Account Summary page")
    public void the_user_clicks_on_Loan_link_on_the_Account_Summary_page() {

        new AccountSummary().loan.click();

    }

    @Then("Account drop down should have Loan selected")
    public void account_drop_down_should_have_Loan_selected() {

        Select select = new Select(new AccountActivity().accOptionSelect);
        System.out.println("first option = " + select.getFirstSelectedOption().getText());
        Assert.assertEquals("Loan", select.getFirstSelectedOption().getText());

    }

//    2. part

    @When("The user access the Account Activity Page")
    public void the_user_access_the_Account_Activity_Page() {

        new AccountSummary().accountActivity.click();

    }

    @Then("Account activity page should have the title Zero - Account Activity")
    public void account_activity_page_should_have_the_title_Zero_Account_Activity() {

        Assert.assertEquals(Driver.get().getTitle(),"Zero - Account Activity");
    }

    @Then("Account drop down default option should be Savings")
    public void account_drop_down_default_option_should_be_Savings() {

        Select s = new Select(new AccountActivity().accOptionSelect);
        Assert.assertEquals(s.getFirstSelectedOption().getText(),"Savings");

    }

    @Then("Account drop down should have the following options")
    public void account_drop_down_should_have_the_following_options(List<String> dataTable) {

        List<String> actualOptions = BrowserUtils.getElementsText(new AccountActivity().accOptions);

        for (int i = 0; i < dataTable.size(); i++) {

            Assert.assertTrue(actualOptions.contains(dataTable.get(i)));

        }


    }

    @Then("Transactions table should have columns names")
    public void transactions_table_should_have_columns_names(List<String> dataTable) {

        List<String> actualOptions = BrowserUtils.getElementsText(new AccountActivity().headersTransaction);

        for (int i = 0; i < dataTable.size(); i++) {

            Assert.assertTrue(actualOptions.contains(dataTable.get(i)));

        }

    }

}
