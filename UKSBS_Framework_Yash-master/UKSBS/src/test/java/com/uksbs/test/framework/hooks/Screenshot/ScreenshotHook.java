package com.uksbs.test.framework.hooks.Screenshot;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static com.uksbs.test.framework.hooks.Screenshot.SetBrowser.getWebDriver;


public class ScreenshotHook {

    private static final Logger LOG = LoggerFactory.getLogger(com.uksbs.test.framework.hooks.Screenshot.ScreenshotHook.class);

   @After
    public void embedScreenshot(Scenario scenario) {
        try {

            if (scenario.isFailed()) {
                scenario.write(getWebDriver().getCurrentUrl());
                byte[] screenShot = ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenShot, "image/png");
            }

        } catch (WebDriverException | ClassCastException wde) {
            LOG.error(wde.getMessage());
        } finally {

            getWebDriver().quit();

        }
    }
}
