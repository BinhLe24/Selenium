package models.components.product;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import models.components.Component;
import models.components.ComponentCssSelector;

@ComponentCssSelector(value = ".product-item")
public class ProductItemComponent extends Component {

    private final static By productTitleSel = By.cssSelector(".product-title");

    public ProductItemComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public WebElement productTitleElem() {
        return component.findElement(productTitleSel);
    }

}
