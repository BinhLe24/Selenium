package models.components.global;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import models.components.Component;
import models.components.ComponentCssSelector;

@ComponentCssSelector(value = ".top-menu")
public class TopMenuComponent extends Component {

    public TopMenuComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public List<MainCatComponent> mainCatComponents() {
        return findComponents(MainCatComponent.class, driver);
    }

    @ComponentCssSelector(value = ".top-menu > li")
    public static class MainCatComponent extends Component {

        public MainCatComponent(WebDriver driver, WebElement component) {
            super(driver, component);
        }

        public WebElement mainCatLinkItem() {
            return component.findElement(By.tagName("a"));
        }

        public List<CatItemComponent> catItemComponents() {
            Actions actions = new Actions(driver);
            actions.moveToElement(component).perform();

            return findComponents(CatItemComponent.class, driver);
        }

    }

    @ComponentCssSelector(value = ".sublist.firstLevel li a")
    public static class CatItemComponent extends Component {

        public CatItemComponent(WebDriver driver, WebElement component) {
            super(driver, component);
        }

    }

}
