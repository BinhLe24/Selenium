package pom_tests;

import org.openqa.selenium.WebDriver;

import driver.DriverFactory;
import models.pages.LoginPageMod01;

public class LoginTestMod01 {

    public static void main(String[] args) {
        // Get a chrome session
        WebDriver driver = DriverFactory.getChromDriver();

        try {
            // Navigate to the target page
            driver.get("https://the-internet.herokuapp.com/login");

            LoginPageMod01 loginPage = new LoginPageMod01(driver);

            loginPage.username().sendKeys("tomsmith");
            loginPage.password().sendKeys("SuperSecretPassword!");
            loginPage.loginBtn().click();

            // DEBUG PURPOSE ONLY
            Thread.sleep(2000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Quit the browser session
        driver.quit();

    }
}
