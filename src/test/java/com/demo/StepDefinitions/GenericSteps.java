package com.demo.StepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GenericSteps
{

   public void waitForElementTobeVisible(WebDriver driver , By locator ){
       WebDriverWait wait = new WebDriverWait(driver, 30);
       wait.until(ExpectedConditions.presenceOfElementLocated(locator));
   }


   public String getText(WebDriver driver , By locator){
       return driver.findElement(locator).getText();
   }

    public void enterValue(WebElement ele, String value){
       ele.sendKeys(value);
    }

    public void click(WebElement ele){
       ele.click();
    }
}
