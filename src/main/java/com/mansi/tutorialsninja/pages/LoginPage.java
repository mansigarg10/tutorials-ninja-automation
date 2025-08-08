package com.mansi.tutorialsninja.pages;

import com.mansi.tutorialsninja.util.AbstractComponents;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * This class contains web elements of Login page.
 *
 * @author Mansi Garg
 */
@Getter
@Setter
public class LoginPage extends AbstractComponents {

    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "input-email")
    private WebElement emailBox;

    @FindBy(id = "input-password")
    private WebElement password;

    @FindBy(css = "input[value='Login']")
    private WebElement loginButton;

    @FindBy(css = "#logo h1 a")
    private WebElement appLogo;

    public void loginToApplication(String email, String pass) {
        emailBox.sendKeys(email);
        password.sendKeys(pass);;
        loginButton.click();
    }

}
