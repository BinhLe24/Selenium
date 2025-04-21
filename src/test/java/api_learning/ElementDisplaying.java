package api_learning;

import driver.DriverFactory;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementDisplaying {
    public static void main(String[] args) {
        // Get a chrome session
        WebDriver driver = DriverFactory.getChromDriver();

        driver.get("https://the-internet.herokuapp.com/login");

        List<WebElement> elementList = driver.findElements(By.tagName("input"));

        if (!elementList.isEmpty()) {
            Assert.fail("The field is still displaying on the page");
        }

        // Quit the browser session
        driver.quit();

        // Imlicit wait, Explicit wait and Fluent wait

    }
}
