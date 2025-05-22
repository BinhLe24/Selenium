package api_learning;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import driver.DriverFactory;
import support.ui.WaitMoreThanOneTab;

import url.Urls;

public class ExplicitWait {

    public static void main(String[] args) {
        // Get a chrome session
        WebDriver driver = DriverFactory.getChromDriver();

        try {
            // Navigate to target base
            driver.get(Urls.baseUrl.concat(Urls.loginSlug));

            // Dropdown locator and elements
            By abcSel = By.cssSelector("#Teo");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOfElementLocated(abcSel));
            // wait.until(ExpectedConditions.visibilityOf(driver.findElement(abcSel)));

            // windowns/tabs -> waitUntil tabs > 1
            wait.until(new WaitMoreThanOneTab());

            // wait.until(new WaitUntilSth(By.cssSelector("#abc")));

        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.quit();
    }

}
