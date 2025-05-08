package models.components.order;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import models.components.Component;

public abstract class ComputerEssentialComponent extends Component {

    public ComputerEssentialComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public abstract String getProcessorType(String type);

    public abstract String getRAMType(String type);

    protected String selectCompOption(String type) {
        String selectorStr = "//label[contains(text(),\"" + type + "\")]";
        By optionSel = By.xpath(selectorStr);
        WebElement optionElem = null;

        try {
            optionElem = component.findElement(optionSel);
        } catch (Exception ignored) {
        }
        if (optionElem != null) {
            optionElem.click();
            return optionElem.getText();
        } else {
            throw new RuntimeException("The option:" + type + "is not existing to select!");
        }
    }
}
