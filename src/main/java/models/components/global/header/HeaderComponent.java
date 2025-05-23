package models.components.global.header;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import models.components.Component;
import models.components.ComponentCssSelector;

@ComponentCssSelector(value = ".header")
public class HeaderComponent extends Component {

    private final static By shoppingCartLinkSel = By.cssSelector("#topcartlink");

    public HeaderComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public void clickShoppingCartLinkElem() {
        WebElement shoppingCartLinkElem = component.findElement(shoppingCartLinkSel);
        scrollDownToElement(shoppingCartLinkElem);
        shoppingCartLinkElem.click();
    }
}
