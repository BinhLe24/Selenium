package api_learning;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import driver.DriverFactory;
import support.ui.WaitForElementEnabled;
import url.Urls;

public class DynamicControl implements Urls {

    public static void main(String[] args) {
        // Get a chrome session
        WebDriver driver = DriverFactory.getChromDriver();

        // Define parents locators
        By checkboxFormSel = By.cssSelector("#checkbox-example");
        By inputFormSel = By.id("input-example");

        try {
            // Navigate to target base
            driver.get(baseUrl.concat(dynamicControlSlug));

            // Checkbox form interaction
            WebElement checkboxFormElem = driver.findElement(checkboxFormSel);
            WebElement checkboxElem = checkboxFormElem.findElement(By.tagName("input"));
            if (!checkboxElem.isSelected())
                checkboxElem.click();

            // DEBUG PURPOSE ONLY
            Thread.sleep(2000);

            // Input form interaction
            WebElement inputFormElem = driver.findElement(inputFormSel);
            WebElement inputFieldElem = inputFormElem.findElement(By.tagName("input"));
            
            WebElement enableButtonElem = inputFormElem.findElement(By.tagName("button"));

            if (!inputFieldElem.isEnabled()) {
                enableButtonElem.click();
            }

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(new WaitForElementEnabled(By.cssSelector("#input-example input")));
            inputFieldElem.sendKeys("Toi ten la Binh");

        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.quit();
    }

}
