package models.components.order;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import models.components.ComponentCssSelector;

@ComponentCssSelector(value = ".product-essential")
public class StandardComputerComponent extends ComputerEssentialComponent {

    private static final By productAttributeSel = By.cssSelector("select[id^= \"product_attribute\"]");

    public StandardComputerComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    @Override
    public String getProcessorType(String type) {
        final int PROCESSOR_DROPDOWN_INDEX = 0;
        WebElement processorDropdownElem = component.findElements(productAttributeSel).get(PROCESSOR_DROPDOWN_INDEX);
        return selectOption(processorDropdownElem, type);
    }

    @Override
    public String getRAMType(String type) {
        final int RAM_DROPDOWN_INDEX = 1;
        WebElement ramDropdownElem = component.findElements(productAttributeSel).get(RAM_DROPDOWN_INDEX);
        return selectOption(ramDropdownElem, type);
    }

    private String selectOption(WebElement dropdownElem, String type) {
        Select select = new Select(dropdownElem);
        List<WebElement> allOptions = select.getOptions();
        String fullOption = null;

        for (WebElement option : allOptions) {
            String currentOptionText = option.getText();
            String optionTextWithoutSpaces = currentOptionText.trim().replace(" ", "");
            if (optionTextWithoutSpaces.startsWith(type)) {
                fullOption = currentOptionText;
                break;
            }
        }
        if (fullOption == null) {
            throw new RuntimeException("[ERR] The option " + type + " is not existing to select!");
        }
        select.selectByVisibleText(fullOption);
        return fullOption;
    }

}
