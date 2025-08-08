package com.mansi.tutorialsninja.pages;

import com.mansi.tutorialsninja.util.AbstractComponents;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/* This class contains web elements of Shopping Cart page.
 *
 * @author Mansi Garg
 */
@Getter
@Setter
public class ShoppingCartPage extends AbstractComponents {

    private WebDriver driver;

    public ShoppingCartPage(WebDriver driver){
        super(driver);
        this.driver= driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='table-responsive']//tbody//td[2]")
    private List<WebElement> productInShoppingCart;

}
