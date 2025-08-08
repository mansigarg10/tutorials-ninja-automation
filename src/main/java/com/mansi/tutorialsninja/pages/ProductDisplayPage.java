package com.mansi.tutorialsninja.pages;

import com.mansi.tutorialsninja.util.AbstractComponents;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * This class contains web elements of Product Display page.
 *
 * @author Mansi Garg
 */
@Getter
@Setter
public class ProductDisplayPage extends AbstractComponents {

    private final WebDriver driver;

    public ProductDisplayPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "input-quantity")
    private WebElement qty;

    @FindBy(id = "button-cart")
    private WebElement addToCartButton;

    public void changeQty() {
        getQty().clear();
        getQty().sendKeys("3");
        addToCartButton.click();
    }

}
