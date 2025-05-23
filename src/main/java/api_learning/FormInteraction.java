package api_learning;

import driver.DriverFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FormInteraction {
    public static void main(String[] args) {
        // Get a chrome session
        WebDriver driver = DriverFactory.getChromDriver();

        try {
            // Navigate to the target page
            driver.get("https://the-internet.herokuapp.com/login");

            // Define selector values
            By usernameSel = By.id("username");
            By passwordSel = By.cssSelector("#password");
            By loginBtnSel = By.cssSelector("[type='submit']");

            // Find elements
            WebElement usernameElem = driver.findElement(usernameSel);
            WebElement passwordElem = driver.findElement(passwordSel);
            WebElement loginBtnElem = driver.findElement(loginBtnSel);

            // Get attribute value
            System.out.println("Login btn type: " + loginBtnElem.getDomAttribute("type"));
            System.out.println("Login btn background color: " + loginBtnElem.getCssValue("background-color"));

            // Interaction
            usernameElem.sendKeys("tomsmith");
            passwordElem.sendKeys("SuperSecretPassword!");
            loginBtnElem.click();

            // Go back to previous page
            driver.navigate().back();

            // Refresh page
            driver.navigate().refresh();

            // Re-interact with element again
            usernameElem = driver.findElement(usernameSel);
            passwordElem = driver.findElement(passwordSel);
            loginBtnElem = driver.findElement(loginBtnSel);
            usernameElem.sendKeys("tomsmith");
            passwordElem.sendKeys("SuperSecretPassword!");
            loginBtnElem.click();

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
