package com.mansi.tutorialsninja.tests;

import com.mansi.tutorialsninja.listeners.BaseTest;
import com.mansi.tutorialsninja.pages.CommonElementsPage;
import com.mansi.tutorialsninja.pages.HomePage;
import com.mansi.tutorialsninja.pages.LoginPage;
import com.mansi.tutorialsninja.pages.ShoppingCartPage;
import com.mansi.tutorialsninja.util.TutorialsNinjaConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class validates the Add to Cart functionality.
 *
 * @author Mansi Garg
 */
public class AddToCartTest extends BaseTest {

    private final Logger LOG = LogManager.getLogger(AddToCartTest.class);

    private WebDriver driver;
    private List<String> productsList;

    @BeforeMethod
    public void launchBrowser() throws IOException {
        driver = initializeBrowser();
        LOG.debug("Browser launched successfully for Add to Cart Test");
        String[] requiredProducts = {"MacBook", "iPhone"};
        productsList = Arrays.asList(requiredProducts);
    }

    @Test(dataProvider = "getData")
    public void addProductsToCart(HashMap<String,String> input){
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage =  homePage.goToLoginPage();
        loginPage.loginToApplication(input.get("k_email"), input.get("k_password"));
        LOG.debug("Login successful with email: " + input.get("k_email"));
        loginPage.waitAndClickElement(loginPage.getAppLogo());
        loginPage.getAppLogo().click();
        loginPage.waitAndClickElement(homePage.getProducts().get(0));
        LOG.debug("Waiting for products to load on the home page");
        List<WebElement> products = homePage.productsOnHome();
        LOG.debug("Collecting all the products on the home page");
        List<WebElement> buttons = homePage.getAddToCart();
        for (int i = 0; i < products.size(); i++) {
            String[] formattedProduct = products.get(i).getText().split(" ");
            for (int j = 0; j < formattedProduct.length; j++) {
                if (productsList.contains(formattedProduct[0])) {
                    buttons.get(i).click();
                    LOG.debug("Product added to cart: " + formattedProduct[0]);
                }
            }
        }
        CommonElementsPage elements = new CommonElementsPage(driver);
        elements.getViewCart().click();
        elements.getViewCartSecond().click();
        boolean value = validationOfCartItems();
        Assert.assertTrue(value);
    }

    private boolean validationOfCartItems() {
        boolean value = false;
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        List<WebElement> productInCart = shoppingCartPage.getProductInShoppingCart();
        List<String> cartProducts = productInCart.stream()
                .map(element -> element.getText().split("\\*", 2)[0])
                .map(product -> product.replaceAll("\\s+", "")) // Remove spaces
                .collect(Collectors.toList());
        for (int i = 0; i < productInCart.size(); i++) {
            if (cartProducts.stream().anyMatch(productsList::contains)) {
                value = true;
            }
        }
        return value;
    }

    @DataProvider(name = "getData")
    public Object[][] getData() throws IOException {
        String file = System.getProperty("user.dir") + TutorialsNinjaConstants.LOGIN_JSON;
        List<HashMap<String, String>> data = getJsonData(file);
        return new Object[][]{{data.get(0)}};
    }

}
