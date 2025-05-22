package models.components.global.footer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import models.components.ComponentCssSelector;

@ComponentCssSelector(value = ".column.information")
public class InformationColumnComponent extends FooterColumnComponent {

    public InformationColumnComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

}
