package com.demo.StepDefinitions;

import java.util.Properties;

import com.cucumber.demo.DriverInitializer;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginSteps extends GenericSteps
{
    WebDriver webDriver = null;

    private static Properties properties = null;

    @Before
    public void setUp()
    {
        try {
            properties = new Properties();
            properties.load(DriverInitializer.class.getClassLoader()
                    .getResourceAsStream("locator.properties"));
        }catch (Exception e) {
            e.printStackTrace();
        }
        webDriver = DriverInitializer.getDriver("chrome");
        webDriver.manage().window().maximize();
        webDriver.get("https://www.facebook.com/");
    }

    public static String getProperty(String key)
    {
        return properties == null ? null : properties.getProperty(key, "");
    }

    @Given("^user navigates to the fb page$")
    public void userLoginsInToTheFbPage() throws Throwable
    {
        waitForElementTobeVisible(webDriver, By.xpath(getProperty("headerText")));
        assertThat(getText(webDriver,By.xpath(getProperty("headerText")))).isEqualTo("Facebook");
    }

    @Given("^user enters \"([^\"]*)\" in to \"([^\"]*)\" field$")
    public void userEntersInToField(String value, String fieldName) throws Throwable
    {
        WebElement element = webDriver.findElement(By.id(getProperty(fieldName)));
        enterValue(element,value);

    }

    @When("^user clicks \"([^\"]*)\"$")
    public void userClicks(String arg1) throws Throwable
    {
        waitForElementTobeVisible(webDriver, By.xpath(getProperty(arg1)));
        WebElement element = webDriver.findElement(By.xpath(getProperty(arg1)));
        click(element);

    }

    @Then("^the header text displays \"([^\"]*)\"$")
    public void theHeaderTextDisplays(String text) throws Throwable
    {
        WebDriverWait wait = new WebDriverWait(webDriver, 30);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(),'you’ve entered is incorrect.')]")));
        WebElement webElement = webDriver.findElement(By.xpath("//div[contains(text(),'you’ve entered is incorrect.')]"));
        assertThat(webElement.getText()).isEqualTo(text);
    }

    @After

    public void quit(){
        webDriver.quit();
    }
}