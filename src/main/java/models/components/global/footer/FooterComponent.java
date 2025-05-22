package models.components.global.footer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import models.components.Component;
import models.components.ComponentCssSelector;

@ComponentCssSelector(value = ".footer")
public class FooterComponent extends Component {

    public FooterComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public InformationColumnComponent informationColumnComponent() {
        return findComponent(InformationColumnComponent.class, driver);
    }

    public CustomerServiceColumnComponent customerServiceColumnComponent() {
        return findComponent(CustomerServiceColumnComponent.class, driver);
    }

    public AccountColumnComponent accountColumnComponent() {
        return findComponent(AccountColumnComponent.class, driver);
    }

    public FollowUsColumnComponent followUsColumnComponent() {
        return findComponent(FollowUsColumnComponent.class, driver);
    }

}
