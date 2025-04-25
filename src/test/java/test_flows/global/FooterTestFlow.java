package test_flows.global;

import org.openqa.selenium.WebDriver;

import models.components.global.footer.FooterColumnComponent;

public class FooterTestFlow {

    private final WebDriver driver;

    public FooterTestFlow(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyFooterComponent() {
        verifyInformationColumn();
        verifyCustomerServiceColumn();
        verifyAccountColumn();
        verifyFllowUsColumn();
    }

    private void verifyInformationColumn() {

    }

    private void verifyCustomerServiceColumn() {

    }

    private void verifyAccountColumn() {

    }

    private void verifyFllowUsColumn() {

    }

    private void testFooterColumn(FooterColumnComponent footerColumnComponent) {
        System.out.println(footerColumnComponent.headerElem().getText());
        footerColumnComponent.linksElem().forEach(link -> {
            System.out.println(link.getText());
            System.out.println(link.getDomAttribute("href"));
        });
    }

}
