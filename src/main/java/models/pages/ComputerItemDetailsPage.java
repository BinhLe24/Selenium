package models.pages;

import org.openqa.selenium.WebDriver;

import models.components.order.ComputerEssentialComponent;

public class ComputerItemDetailsPage extends BasePage {

    public ComputerItemDetailsPage(WebDriver driver) {
        super(driver);
    }

    public <T extends ComputerEssentialComponent> T computerComp(Class<T> computerEssentialCompClass) {
        return findComponent(computerEssentialCompClass, driver);
    }
}
