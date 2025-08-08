package com.mansi.tutorialsninja.pages;

import com.mansi.tutorialsninja.util.AbstractComponents;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * This class contains web elements of Order Success page.
 *
 * @author Mansi Garg
 */
@Getter
@Setter
public class OrderSuccessPage extends AbstractComponents {

    private final WebDriver driver;

    public OrderSuccessPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Success']")
    private WebElement successBreadCrumb;

}
