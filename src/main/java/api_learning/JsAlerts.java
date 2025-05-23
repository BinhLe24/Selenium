package api_learning;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import driver.DriverFactory;
import url.Urls;

public class JsAlerts implements Urls {

    private final static By jsAlertSel = By.cssSelector("[onclick=\"jsAlert()\"]");
    private final static By jsAlertConfirmSel = By.cssSelector("[onclick=\"jsConfirm()\"]");
    private final static By jsAlertPromptSel = By.cssSelector("[onclick=\"jsPrompt()\"]");
    private final static By jsResultSel = By.cssSelector("#result");

    public static void main(String[] args) {
        // Get a chrome session
        WebDriver driver = DriverFactory.getChromDriver();

        try {
            // Navigate to target base
            driver.get(baseUrl.concat(jsAlertSlug));

            // JS_ALERT | OK
            handelJsAlert(driver, jsAlertSel, true, null);
            System.out.println("Result: " + driver.findElement(jsResultSel).getText());

            // JS_CONFIRM | OK
            handelJsAlert(driver, jsAlertConfirmSel, true, null);
            System.out.println("Result: " + driver.findElement(jsResultSel).getText());

            // JS_CONFIRM | Cancel
            handelJsAlert(driver, jsAlertConfirmSel, false, null);
            System.out.println("Result: " + driver.findElement(jsResultSel).getText());

            // JS_PROMPT | OK
            handelJsAlert(driver, jsAlertPromptSel, true, "ABC");
            System.out.println("Result: " + driver.findElement(jsResultSel).getText());

            // JS_PROMPT | Cancel
            handelJsAlert(driver, jsAlertPromptSel, false, "A2");
            System.out.println("Result: " + driver.findElement(jsResultSel).getText());

        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.quit();
    }

    private static void handelJsAlert(WebDriver driver, By triggerJsAlertBtn, Boolean isClick, String input) {
        WebElement triggerJsAlertBtnElem = driver.findElement(triggerJsAlertBtn);
        triggerJsAlertBtnElem.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        System.out.println(alert.getText());
        if (input != null) {
            alert.sendKeys(input);
        }
        if (isClick == true) {
            alert.accept();
        } else {
            alert.dismiss();
        }

    }
}
