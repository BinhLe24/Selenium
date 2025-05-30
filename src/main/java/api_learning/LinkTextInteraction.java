package api_learning;

import driver.DriverFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LinkTextInteraction {
    public static void main(String[] args) {
        // Get a chrome session
        WebDriver driver = DriverFactory.getChromDriver();

        try {
            // Navigate to the target page
            driver.get("https://the-internet.herokuapp.com/login");

            // Define selector values
            // By powerByLinkTextSel = By.linkText("Elemental Selenium");
            By powerByLinkTextSel = By.partialLinkText("Elemental");

            // Find elements
            WebElement powerByLinkTextElem = driver.findElement(powerByLinkTextSel);
            

            // Interaction
           powerByLinkTextElem.click();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Quit the browser session
        driver.quit();

        // Imlicit wait, Explicit wait and Fluent wait

    }
}
