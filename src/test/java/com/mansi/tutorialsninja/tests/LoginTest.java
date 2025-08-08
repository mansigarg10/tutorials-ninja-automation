package com.mansi.tutorialsninja.tests;

import com.mansi.tutorialsninja.listeners.BaseTest;
import com.mansi.tutorialsninja.pages.CommonElementsPage;
import com.mansi.tutorialsninja.pages.HomePage;
import com.mansi.tutorialsninja.pages.LoginPage;
import com.mansi.tutorialsninja.util.TutorialsNinjaConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * This class validates the login functionality.
 *
 * @author Mansi Garg
 */
public class LoginTest extends BaseTest {

    private final Logger LOG = LogManager.getLogger(LoginTest.class);

    @Test(dataProvider = "getData", groups = {"smoke"})
    public void getLoggedIn(HashMap<String, String> input) throws IOException {
        WebDriver driver = initializeBrowser();
        LOG.debug("Browser launched successfully for Login Test");
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = homePage.goToLoginPage();
        loginPage.loginToApplication(input.get("k_email"), input.get("k_password"));
        LOG.debug("Login successful with email: " + input.get("k_email"));
        CommonElementsPage commonElementsPage = new CommonElementsPage(driver);
        commonElementsPage.waitForElementToBeVisible(commonElementsPage.getMyAccount());
        Assert.assertTrue(commonElementsPage.getMyAccount().isDisplayed());
    }

    @DataProvider(name = "getData")
    public Object[][] getData() throws IOException {
        String file = System.getProperty("user.dir") + TutorialsNinjaConstants.LOGIN_JSON;
        List<HashMap<String, String>> data = getJsonData(file);
        return new Object[][]{{data.get(0)}, {data.get(1)}};
    }

}
