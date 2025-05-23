package models.components.global.footer;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import models.components.Component;

public class FooterColumnComponent extends Component {

    private final static By headerSel = By.tagName("h3");
    private final static By linkSel = By.cssSelector("li a");

    public FooterColumnComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public WebElement headerElem() {
        return component.findElement(headerSel);
    }

    public List<WebElement> linksElem() {
        return component.findElements(linkSel);
    }
}
