package com.uksbs.test.framework.CommonMethods;

import com.uksbs.test.framework.hooks.Screenshot.SetBrowser;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CommonMethods {
    public WebDriver webDriver;
    public WebDriverWait webDriverWait;
    private Long DRIVER_WAIT = 30L;

    public CommonMethods() {
        webDriver = SetBrowser.getWebDriver();
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        webDriver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
        webDriverWait = new WebDriverWait(webDriver, DRIVER_WAIT);
    }

    public void clickElement(By elementName) {
        webDriverWait.until(ExpectedConditions.visibilityOf(webDriver.findElement(elementName))).click();
    }

    public void clickElement(WebElement elementName) throws InterruptedException {
        //webDriverWait.until(ExpectedConditions.visibilityOf(elementName)); 24/07/2020
        webDriverWait.until(ExpectedConditions.elementToBeClickable(elementName)).click();
    }

    public void clickUsingJS(WebElement element){
        JavascriptExecutor executor = (JavascriptExecutor)webDriver;
        executor.executeScript("arguments[0].click();", element);
    }

    public WebElement waitTillElementVisible(WebElement element)  {
        return webDriverWait.until(ExpectedConditions.visibilityOf(element));
    }

    public boolean completeDOMLoad() {
        return ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete");
    }

    public boolean ifElementVisible(WebElement element)  {
        return webDriverWait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
    }

    public void clickOnElementUsingJavaScript(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) webDriver;
        executor.executeScript("arguments[0].click();", element);
    }

    public void scrollToView(WebElement element) {
        if (webDriver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", element);
        }
    }

    public void clickOnTheWebElementFromList(List<WebElement> elements, int location){
        webDriverWait.until(ExpectedConditions.visibilityOfAllElements(elements));
        elements.get(location).click();
    }

    public static void searchInAnElement(WebElement elementSearch,String searchCriteria){
        elementSearch.sendKeys(searchCriteria);
        elementSearch.sendKeys(Keys.ENTER);
    }
}
