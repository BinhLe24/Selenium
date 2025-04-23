package models.components.global.footer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import models.components.ComponentCssSelector;

@ComponentCssSelector(value = ".column.follow-us")
public class FollowUsColumnComponent extends FooterColumnComponent {

    public FollowUsColumnComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

}
