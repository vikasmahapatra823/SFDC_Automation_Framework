package commands;

import org.openqa.selenium.*;
import constants.ApplicationConstant;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class SeleniumCommand {

    public String methodFailureMessage = null;
    public static WebDriver driver;

    public WebElement findElement(By locator) throws Exception {
        WebElement element;
        try {
         element =  driver.findElement(locator);
        }
        catch (Exception e){
            methodFailureMessage = "findElement method failed because of exception" + e.getMessage();
            throw new Exception(methodFailureMessage);
        }
        return element;
    }

    public List<WebElement> findElements(By locator) throws Exception {
        List<WebElement> elements;
                try{
                    elements = driver.findElements(locator);
                }
                catch (Exception e){
                    methodFailureMessage = "findElement method failed because of exception" + e.getMessage();
                    throw new Exception(methodFailureMessage);
                }
                return elements;
    }

    public void getURL(String url){
        driver.get(url);
    }

}