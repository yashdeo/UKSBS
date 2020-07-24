package com.uksbs.test.framework.hooks.Screenshot;


import com.uksbs.test.framework.Helpers.BuilderURL;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static com.google.common.base.StandardSystemProperty.FILE_SEPARATOR;

public class SetBrowser {

    private static Properties properties;
    private static RemoteWebDriver webDriver = null;

    public static WebDriver getWebDriver() {
        return webDriver;
    }

    @Before
    public static void selectBrowserDriver() {
        Properties prop = loadPropertiesFile("environment.properties");
        String profile = prop.getProperty("profile.path");

        if (profile.contains("dev")) {
            properties = loadPropertiesFile("profile/dev/config.properties");
        }

        if (profile.contains("FireFoxProfile")) {
            properties = loadPropertiesFile("FireFoxProfile/config.properties");
        }

        String browserName = properties.getProperty("browser");

        if (browserName.equals("Chrome")) {
            setChromeDriverPath();
            initializeChromeDriver();
        } else if (browserName.equals("FireFox")) {
            setFireFoxDriverPath();
            initializeFireFoxDriver();
        }
    }

    public static void openURL() {
        getWebDriver().navigate().to(properties.getProperty("site.url"));
    }

    private static void setChromeDriverPath() {
        System.setProperty("webdriver.chrome.driver", returnChromeDriverPath());
    }

    private static void setFireFoxDriverPath() {
        System.setProperty("webdriver.gecko.driver", returnFirefoxDriverPath());
    }

    private static String returnChromeDriverPath() {
        // return "tools" + FILE_SEPARATOR + "chromedriver" + FILE_SEPARATOR + "chromedriver.exe"; // need to check why not working (30/06/2020)
        return "Tools/chromedriver/chromedriver.exe";
    }

    private static String returnFirefoxDriverPath() {
        return "tools" + FILE_SEPARATOR + "geckodriver" + FILE_SEPARATOR + "win32" + FILE_SEPARATOR + "geckodriver.exe";
    }

    private static void initializeChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        webDriver = new ChromeDriver(options);
    }

    private static void initializeFireFoxDriver() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("start-maximized");
        webDriver = new FirefoxDriver(options);
    }

    private static Properties loadPropertiesFile(String filePath) {
        Properties prop = new Properties();
        try (InputStream resourceAsStream = BuilderURL.class.getClassLoader().getResourceAsStream(filePath)) {
            prop.load(resourceAsStream);
        } catch (IOException e) {
            System.err.println("Unable to load properties file : " + filePath);
        }
        return prop;
    }
}
