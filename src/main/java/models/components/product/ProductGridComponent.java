package models.components.product;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import models.components.Component;
import models.components.ComponentCssSelector;

@ComponentCssSelector(value = ".product-grid")
public class ProductGridComponent extends Component {

    public ProductGridComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public List<ProductItemComponent> productItemCoponents() {
        return findComponents(ProductItemComponent.class, driver);
    }

}
