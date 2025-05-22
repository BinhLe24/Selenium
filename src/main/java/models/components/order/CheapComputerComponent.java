package models.components.order;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import models.components.ComponentCssSelector;

@ComponentCssSelector(value = ".product-essential")
public class CheapComputerComponent extends ComputerEssentialComponent {

    public CheapComputerComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    @Override
    public String getProcessorType(String type) {
        return selectCompOption(type);
    }

    @Override
    public String getRAMType(String type) {
        return selectCompOption(type);
    }

}
