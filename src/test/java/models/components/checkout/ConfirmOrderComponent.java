package models.components.checkout;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import models.components.Component;
import models.components.ComponentCssSelector;

@ComponentCssSelector(value = "#opc-confirm_order")
public class ConfirmOrderComponent extends Component {

    public ConfirmOrderComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

}
