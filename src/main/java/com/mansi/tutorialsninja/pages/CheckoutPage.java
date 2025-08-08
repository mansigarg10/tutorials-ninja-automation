package com.mansi.tutorialsninja.pages;

import com.mansi.tutorialsninja.util.AbstractComponents;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * This class contains web elements of Checkout page.
 *
 * @author Mansi Garg
 */
@Getter
@Setter
public class CheckoutPage extends AbstractComponents {

    private final WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "firstname")
    private WebElement firstName;

    @FindBy(name = "lastname")
    private WebElement lastName;

    @FindBy(name = "address_1")
    private WebElement address;

    @FindBy(name = "city")
    private WebElement city;

    @FindBy(name = "postcode")
    private WebElement postcode;

    @FindBy(name = "country_id")
    private WebElement country;

    @FindBy(css = "select[name='country_id'] option")
    private List<WebElement> countries;

    @FindBy(id = "input-payment-zone")
    private WebElement region;

    @FindBy(css = "select[name='zone_id'] option")
    private List<WebElement> regions;

    @FindBy(id = "button-payment-address")
    private WebElement continueBillingDetails;

    @FindBy(id = "button-shipping-address")
    private WebElement continueDeliveryDetails;

    @FindBy(id = "button-shipping-method")
    private WebElement continueDeliveryMethod;

    @FindBy(name = "agree")
    private WebElement acceptPolicy;

    @FindBy(id = "button-payment-method")
    private WebElement continuePayment;

    @FindBy(id = "button-confirm")
    private WebElement confirmOrder;

    public void checkoutProcess() {
        continueBillingDetails.click();
        continueDeliveryDetails.click();
        continueDeliveryMethod.click();
        acceptPolicy.click();
        continuePayment.click();
        confirmOrder.click();
    }

}
