package models.components.checkout;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import models.components.Component;
import models.components.ComponentCssSelector;

@ComponentCssSelector(value = "#opc-payment_info")
public class PaymentInformationComponent extends Component {

    public PaymentInformationComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

}
