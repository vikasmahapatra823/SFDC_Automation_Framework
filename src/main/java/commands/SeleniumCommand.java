package commands;

import org.openqa.selenium.*;
import constants.ApplicationConstant;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class SeleniumCommand {

    public String methodFailureMessage = null;
    public WebDriver driver;

    public static WebDriver getWebdriver() {

        return ApplicationConstant.driverMap.get(Thread.currentThread().threadId());
    }


    public WebElement findElement(By locator) throws Exception {
        WebElement element;
        try {
            element = getWebdriver().findElement(locator);
        } catch (Exception e) {
            methodFailureMessage = "FindElement method failed because of exception " + e.getMessage();
            throw new Exception(methodFailureMessage);
        }
        return element;
    }

    public void clickElement(By locator) {
        driver.findElement(locator).click();
    }

    public void enterText(By locator, String text) {
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }

    public String getElementText(By locator) {
        return driver.findElement(locator).getText();
    }

    public boolean isElementDisplayed(By locator) {
        return driver.findElement(locator).isDisplayed();
    }

    public WebElement waitForElementToBeVisible(By locator, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForElementToBeClickable(By locator, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }


    public void openUrl(String url) {
        driver.get(url);
    }

    public void maximizeWindow() {
        driver.manage().window().maximize();
    }

    public void closeBrowser() {
        driver.quit();
    }

    public boolean verifyText(By locator, String expectedText) {
        return getElementText(locator).equals(expectedText);
    }

    public boolean verifyElementExists(By locator) {
        return !driver.findElements(locator).isEmpty();
    }


    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    public void dismissAlert() {
        driver.switchTo().alert().dismiss();
    }

    public String getAlertText() {
        return driver.switchTo().alert().getText();
    }

//    public static void getByteScreenshot() throws Exception {
//        File src = ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.FILE);
//        byte[] fileContent = FileUtils.readFileToByteArray(src);
//        ApplicationConstant.currentScenario.attach(fileContent, "image/png", "");
//    }

    public void scrollToElement(By locator) {
        WebElement element = driver.findElement(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }


    public void browserNavigation(String action, String URL) {

        try {
            String browserAction = action.toUpperCase();

            switch (browserAction) {

                case "BACK":
                    getWebdriver().navigate().back();
                    break;

                case "FORWARD":
                    getWebdriver().navigate().forward();
                    break;

                case "REFRESH":
                    getWebdriver().navigate().refresh();

                case "NAVIGATE":
                    getWebdriver().navigate().to(URL);

                default:
                    methodFailureMessage = "Selected action is not Present in list";
                    System.out.println(methodFailureMessage);
                    break;
            }
        } catch (Exception e) {
            methodFailureMessage = "Unable to Perform Action" + action + "because of an Exception" + e.getMessage();

        }


    }

    public void keyPress(By element, String key) throws Exception {
        try {
            switch (key.toLowerCase()) {
                case "new tab":
                    findElement(element).sendKeys(Keys.CONTROL + "t");
                    break;
                case "previous tab":
                    findElement(element).sendKeys(Keys.CONTROL + "" + Keys.SHIFT + "" + Keys.TAB);
                    break;
                case "next tab":
                    findElement(element).sendKeys(Keys.CONTROL + "" + Keys.TAB);
                    break;
                case "close tab":
                    findElement(element).sendKeys(Keys.CONTROL + "" + "w");
                    break;
                case "reopen recently closed tab":
                    findElement(element).sendKeys(Keys.CONTROL + "" + Keys.SHIFT + "" + "t");
                    break;
                case "enter":
                    findElement(element).sendKeys(Keys.ENTER);
                    break;
                case "tab":
                    findElement(element).sendKeys(Keys.TAB);
                    break;
                case "down":
                    findElement(element).sendKeys(Keys.DOWN);
                    break;
                case "control click":
                    WebElement webElement = findElement((element));
                    Actions actions = new Actions(getWebdriver());
                    actions.keyDown(Keys.LEFT_CONTROL).click(webElement).keyUp(Keys.LEFT_CONTROL).build().perform();
                    break;
                case "page_down":
                    findElement(element).sendKeys(Keys.PAGE_DOWN);
                    break;
                default:
                    findElement(element).sendKeys("Keys" + "." + key);
                    break;
            }

        } catch (Exception e) {
            methodFailureMessage = "Unable to press " + key
                    + " due to an Exception." + e.getMessage();
            System.out.println(methodFailureMessage);
//            ApplicationConstants.currentScenario.log("Unable to press specified key");
        }
    }
}