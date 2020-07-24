package com.uksbs.test.StepDefinitions;

import com.uksbs.test.PageObjects.LoginPage;
import com.uksbs.test.framework.Helpers.BuilderURL;
import com.uksbs.test.framework.hooks.Screenshot.SetBrowser;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

public class loginRegistrationStepDefinitions {

    private LoginPage loginPage;

    public loginRegistrationStepDefinitions(LoginPage loginPage, BuilderURL builderURL){
        this.loginPage = loginPage;
    }

    @Given("^User is on the home page$")
    public void User_is_on_the_home_page() throws Throwable {
        SetBrowser.openURL();
    }

    @When("^user enters the email \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void user_enters_the_email_and_password(String email, String password) throws Throwable {
        loginPage.loginMethod(email,password);
    }

    @When("^clicks on login button$")
    public void clicks_on_login_button() throws Throwable {
        loginPage.cliclOnLogin();
    }

    @Then("^the error message \"([^\"]*)\" should be displayed$")
    public void the_error_message_should_be_displayed(String errorMessage) throws Throwable {
        Assert.assertTrue(loginPage.getErrorMessage().contains(errorMessage));
    }

    @Then("^the user should be successfully logged in$")
    public void theUserShouldBeSuccessfullyLoggedIn() {

    }
}
