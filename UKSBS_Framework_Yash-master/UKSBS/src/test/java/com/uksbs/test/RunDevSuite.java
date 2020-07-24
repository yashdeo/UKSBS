package com.uksbs.test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "./src/test/resources",tags = {"@regression"}, monochrome = true, plugin = {
        "pretty", "html:target/cucumber-report/",
        "json:target/cucumber-report/cucumber.json",
},      glue = "com.uksbs.test")

public class RunDevSuite extends AbstractTestNGCucumberTests {
    }
