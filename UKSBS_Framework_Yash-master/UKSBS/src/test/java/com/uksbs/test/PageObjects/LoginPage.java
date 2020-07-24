package com.uksbs.test.PageObjects;

import com.uksbs.test.framework.CommonMethods.CommonMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class LoginPage extends CommonMethods {

    public LoginPage() {
        PageFactory.initElements(webDriver, this);
    }

    //Elements needs to be identified using CSSSelectors


    @FindBy(css = "input[id='username']")
    private WebElement inputEmailAddress;

    @FindBy(css = "input[id='password']")
    private WebElement inputPassword;

    @FindBy(css = "button[id='sign-in-cta'")
    private WebElement buttonSignIn;

    @FindBy(css="ul[class='govuk-list govuk-error-summary__list'] li")
    private WebElement errorMessage;

   public void loginMethod(String emailAddress, String password){
       inputEmailAddress.sendKeys(emailAddress);
       inputPassword.sendKeys(password);
   }

   public void cliclOnLogin() throws InterruptedException {
       clickElement(buttonSignIn);
   }

   public String getErrorMessage(){
       return errorMessage.getText();
   }
}
