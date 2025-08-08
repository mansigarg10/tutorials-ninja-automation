package com.mansi.tutorialsninja.pages;

import com.mansi.tutorialsninja.util.AbstractComponents;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * This class contains the common web elements.
 *
 * @author Mansi Garg
 */
@Getter
@Setter
public class CommonElementsPage extends AbstractComponents {

    private final WebDriver driver;

    public CommonElementsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "div[id='search'] input")
    private WebElement searchBox;

    @FindBy(css = "span[class='input-group-btn'] button")
    private WebElement searchIcon;

    @FindBy(css = "div[id='cart'] span[id='cart-total']")
    private WebElement viewCart;

    @FindBy(xpath = "//p[@class='text-right']//a[1]")
    private WebElement viewCartSecond;

    @FindBy(css = "p[class='text-right'] a:last-child")
    private WebElement checkoutFromCart;

    @FindBy(xpath = "//h2[text()='My Account']")
    private WebElement myAccount;

    public void goForCheckout() {
        getViewCart().click();
        getCheckoutFromCart().click();
    }

    public void searchForProduct(String productName){
        searchBox.sendKeys(productName);
        searchIcon.click();
    }

}
