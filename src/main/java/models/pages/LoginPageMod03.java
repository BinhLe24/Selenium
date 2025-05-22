package models.pages;

import org.openqa.selenium.WebDriver;

import models.components.LoginFormComponent;

public class LoginPageMod03 {

    private final WebDriver driver;

    public LoginPageMod03(WebDriver driver) {
        this.driver = driver;
    }

    public LoginFormComponent loginFormComponent() {
        return new LoginFormComponent(driver);
    }

}
