package com.mansi.tutorialsninja.tests;

import com.mansi.tutorialsninja.listeners.BaseTest;
import com.mansi.tutorialsninja.pages.*;
import com.mansi.tutorialsninja.util.TutorialsNinjaConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * This class validates the checkout functionality.
 *
 * @author Mansi Garg
 */
public class CheckoutTest extends BaseTest {

    private final Logger LOG = LogManager.getLogger(CheckoutTest.class);

    private WebDriver driver;

    @BeforeMethod
    public void launchBrowser() throws IOException {
        driver = initializeBrowser();
        LOG.debug("Browser launched successfully for Checkout Test");
    }

    @Test(dataProvider = "getData")
    public void placeOrder(HashMap<String, String> input, HashMap<String, String> input1) throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = homePage.goToLoginPage();
        loginPage.loginToApplication(input.get("k_email"), input.get("k_password"));
        LOG.debug("Login successful with email: " + input.get("k_email"));
        CommonElementsPage cm = new CommonElementsPage(driver);
        loginPage.waitForElementToBeVisible(cm.getSearchBox());
        cm.searchForProduct(input1.get("k_product"));
        LOG.debug("Search performed for product: " + input1.get("k_product"));
        SearchPage searchPage = new SearchPage(driver);
        searchPage.getAddToCart().click();
        LOG.debug("Product added to cart: " + input1.get("k_product"));
        ProductDisplayPage productDisplayPage = new ProductDisplayPage(driver);
        productDisplayPage.changeQty();
        cm.goForCheckout();
        LOG.debug("Navigating to checkout page");
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.checkoutProcess();
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        String filePath = System.getProperty("user.dir") + TutorialsNinjaConstants.LOGIN_JSON;
        String searchJsonFile = System.getProperty("user.dir") + TutorialsNinjaConstants.SEARCH_JSON;
        List<HashMap<String, String>> data = getJsonData(filePath);
        List<HashMap<String, String>> data1 = getJsonData(searchJsonFile);
        return new Object[][]{{data.get(0),data1.get(0)}};
    }

}

