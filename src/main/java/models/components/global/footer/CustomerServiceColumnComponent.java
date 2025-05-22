package models.components.global.footer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import models.components.ComponentCssSelector;

@ComponentCssSelector(value = ".column.customer-service")
public class CustomerServiceColumnComponent extends FooterColumnComponent {

    public CustomerServiceColumnComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

}
