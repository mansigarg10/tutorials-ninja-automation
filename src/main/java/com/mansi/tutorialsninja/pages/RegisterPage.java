package com.mansi.tutorialsninja.pages;

import com.mansi.tutorialsninja.util.AbstractComponents;
import com.mansi.tutorialsninja.util.TutorialsNinjaConstants;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.Map;

/**
 * This class contains web elements of Register page.
 *
 * @author Mansi Garg
 */
@Getter
@Setter
public class RegisterPage extends AbstractComponents {

    private final WebDriver driver;

    private  String file = System.getProperty("user.dir") + TutorialsNinjaConstants.REGISTER_JSON;

    public RegisterPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "input-firstname")
    private WebElement firstName;

    @FindBy(id = "input-lastname")
    private WebElement lastName;

    @FindBy(id = "input-email")
    private WebElement emailAddress;

    @FindBy(id = "input-telephone")
    private WebElement telephoneNumber;

    @FindBy(id = "input-password")
    private WebElement password;

    @FindBy(id = "input-confirm")
    private WebElement confirmPassword;

    @FindBy(xpath = "//label[@class='radio-inline'][1]")
    private WebElement subscriptionYes;

    @FindBy(xpath = "//label[@class='radio-inline'][2]")
    private WebElement subscriptionNo;

    @FindBy(css = "input[name=agree]")
    private WebElement privacyPolicy;

    @FindBy(css = "input[type=submit]")
    private WebElement continueButton;

    @FindBy(xpath = "//div[text()='Warning: E-Mail Address is already registered!']")
    private WebElement warningMessage;

    public void registerToApplication() throws IOException {
        HomePage homePage = new HomePage(driver);
        homePage.goToRegisterPage();
        Map<String, String> data = getJsonData(file);
        getFirstName().sendKeys(data.get("k_firstName"));
        getLastName().sendKeys(data.get("k_lastName"));
        getEmailAddress().sendKeys(data.get("k_email"));
        getTelephoneNumber().sendKeys(data.get("k_telephone"));
        getPassword().sendKeys(data.get("k_password"));
        getConfirmPassword().sendKeys(data.get("k_passwordConfirm"));
        getSubscriptionYes().click();
        getPrivacyPolicy().click();
        getContinueButton().click();
        new AccountSuccessPage(driver);
    }

}
