package api_learning;

import driver.DriverFactory;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FormInteractionMultipleMatching {
    public static void main(String[] args) {
        // Get a chrome session
        WebDriver driver = DriverFactory.getChromDriver();

        try {
            // Navigate to the target page
            driver.get("https://the-internet.herokuapp.com/login");

            // Define selector values
            By loginInputFieldSel = By.tagName("input");

            // Interaction
            List<WebElement> loginFormFlieldsElem = driver.findElements(loginInputFieldSel);
            final int USERNAME_INDEX = 0;
            final int PASSWORD_INDEX = 1;
            if (!loginFormFlieldsElem.isEmpty()) {
                loginFormFlieldsElem.get(USERNAME_INDEX).sendKeys("tomsmith");
                loginFormFlieldsElem.get(PASSWORD_INDEX).sendKeys("SuperSecretPassword");
            } else
            {
                Assert.fail("There is no element");
            }

            // DEBUG PURPOSE ONLY
            Thread.sleep(2000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Quit the browser session
        driver.quit();

        // Imlicit wait, Explicit wait and Fluent wait

    }
}
