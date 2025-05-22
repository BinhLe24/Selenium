package models.components.order;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class ComputerEssentialComponent extends BaseItemDetailsCopmonent {

    private final static By allOptionSel = By.cssSelector(".option-list input");

    public ComputerEssentialComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public abstract String getProcessorType(String type);

    public abstract String getRAMType(String type);

    public void unselectDefaultOptions() {
        List<WebElement> allOptionElem = component.findElements(allOptionSel);
        allOptionElem.forEach(option -> {
            if (option.getDomAttribute("checked") != null) {
                option.click();
            }
        });
    }

    public String getHddType(String type) {
        return selectCompOption(type);
    }

    public String getOsType(String type) {
        return selectCompOption(type);
    }

    public String getSoftwareType(String type) {
        return selectCompOption(type);
    }

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
            throw new RuntimeException("The option:" + type + " is not existing to select!");
        }
    }
}
