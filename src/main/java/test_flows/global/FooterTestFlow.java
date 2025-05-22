package test_flows.global;

import java.security.SecureRandom;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import models.components.global.TopMenuComponent;
import static models.components.global.TopMenuComponent.MainCatComponent;
import static models.components.global.TopMenuComponent.CatItemComponent;
import models.components.global.footer.FooterColumnComponent;
import models.components.global.footer.FooterComponent;
import models.pages.BasePage;

public class FooterTestFlow {

    private final WebDriver driver;

    public FooterTestFlow(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyFooterComponent() {
        BasePage basePage = new BasePage(driver);
        FooterComponent footerComponent = basePage.footerComponent();
        verifyInformationColumn(footerComponent.informationColumnComponent());
        verifyCustomerServiceColumn(footerComponent.customerServiceColumnComponent());
        // verifyAccountColumn(footerComponent.accountColumnComponent());
        // verifyFllowUsColumn(footerComponent.followUsColumnComponent());
    }

    private void verifyInformationColumn(FooterColumnComponent footerColumnComponent) {
        List<String> expectedLinkTexts = Arrays.asList("Sitemap", "Shipping & Returns", "Privacy Notice",
                "Conditions of Use", "About us", "Contact us");
        List<String> expectedHrefs = Arrays.asList("/sitemap", "/shipping-returns", "/privacy-policy",
                "/conditions-of-use", "/about-us", "/contactus");
        verifyFooterColumn(footerColumnComponent, expectedLinkTexts, expectedHrefs);
    }

    private void verifyCustomerServiceColumn(FooterColumnComponent footerColumnComponent) {
        List<String> expectedLinkTexts = Arrays.asList("Search", "News", "Blog",
                "Recently viewed products", "Compare products list", "New products");
        List<String> expectedHrefs = Arrays.asList("/search", "/news", "/blog",
                "/recentlyviewedproducts", "/compareproducts", "/newproducts");
        verifyFooterColumn(footerColumnComponent, expectedLinkTexts, expectedHrefs);
    }

    private void verifyAccountColumn(FooterColumnComponent footerColumnComponent) {
        List<String> expectedLinkTexts = new ArrayList<>();
        List<String> expectedHrefs = new ArrayList<>();
        verifyFooterColumn(footerColumnComponent, expectedLinkTexts, expectedHrefs);
    }

    private void verifyFllowUsColumn(FooterColumnComponent footerColumnComponent) {
        List<String> expectedLinkTexts = new ArrayList<>();
        List<String> expectedHrefs = new ArrayList<>();
        verifyFooterColumn(footerColumnComponent, expectedLinkTexts, expectedHrefs);
    }

    private void verifyFooterColumn(FooterColumnComponent footerColumnComponent, List<String> expectedLinkTexts,
            List<String> expectedHrefs) {
        List<String> actualLinkTexts = new ArrayList<>();
        List<String> actualHrefs = new ArrayList<>();

        for (WebElement link : footerColumnComponent.linksElem()) {
            actualLinkTexts.add(link.getText().trim());
            actualHrefs.add(link.getDomAttribute("href"));

        }
        if (actualLinkTexts.isEmpty() || actualHrefs.isEmpty()) {
            Assert.fail("[ERR] Texts or hyperlinks is empty in footer column!");
        }

        // Verify link text
        Assert.assertEquals("[ERR] Actual and Expected link tests are different!", expectedLinkTexts,
                actualLinkTexts);

        // Verify href text
        Assert.assertEquals("[ERR] Actual and expected hyperlinks are different!", expectedHrefs, actualHrefs);

    }

    public void verifyProductCatFooterComponent() {
        BasePage basePage = new BasePage(driver);
        TopMenuComponent topMenuComponent = basePage.topMenuComponent();
        List<MainCatComponent> mainCatsElem = topMenuComponent.mainCatComponents();

        if (mainCatsElem.isEmpty()) {
            Assert.fail("[ERR] There is no item on top menu");
        }

        // Random pickup an item
        MainCatComponent randomMainItemElem = mainCatsElem.get(new SecureRandom().nextInt(mainCatsElem.size()));
        String randomCatHref = randomMainItemElem.mainCatLinkItem().getDomAttribute("href");

        // Get sublist
        List<CatItemComponent> catItemComps = randomMainItemElem.catItemComponents();

        if (catItemComps.isEmpty()) {
            randomMainItemElem.mainCatLinkItem().click();
        } else {
            int randomIndex = new SecureRandom().nextInt(catItemComps.size());
            CatItemComponent randomCatItemComps = catItemComps.get(randomIndex);
            randomCatHref = randomCatItemComps.getComponent().getDomAttribute("href");
            randomCatItemComps.getComponent().click();
        }

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.urlContains(randomCatHref));
        } catch (TimeoutException e) {
            Assert.fail("[ERR] Target page is not matched!");
        }

        // Verify footer component
        verifyFooterComponent();

    }

}
