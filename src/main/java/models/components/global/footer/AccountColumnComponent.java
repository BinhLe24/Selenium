package models.components.global.footer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import models.components.ComponentCssSelector;

@ComponentCssSelector(value = ".column.my-account")
public class AccountColumnComponent extends FooterColumnComponent {

    public AccountColumnComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

}
