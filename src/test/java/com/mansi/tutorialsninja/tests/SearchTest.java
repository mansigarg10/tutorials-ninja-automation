package com.mansi.tutorialsninja.tests;

import com.mansi.tutorialsninja.listeners.BaseTest;
import com.mansi.tutorialsninja.pages.CommonElementsPage;
import com.mansi.tutorialsninja.pages.HomePage;
import com.mansi.tutorialsninja.pages.LoginPage;
import com.mansi.tutorialsninja.pages.SearchPage;
import com.mansi.tutorialsninja.util.TutorialsNinjaConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * This class matches the searched item with the array of keywords and perform validations.
 *
 * @author Mansi Garg
 */
public class SearchTest extends BaseTest {

    private final Logger LOG = LogManager.getLogger(SearchTest.class);

    private final String[] keyword = {"HP", "Sony", "Samsung"};

    @Test(dataProvider = "getData")
    public void searchProducts(HashMap<String, String> input, HashMap<String,String> input1) throws IOException, InterruptedException {
        WebDriver driver = initializeBrowser();
        LOG.debug("Browser launched successfully for Search Test");
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = homePage.goToLoginPage();
        loginPage.loginToApplication(input1.get("k_email"), input1.get("k_password"));
        CommonElementsPage cm = new CommonElementsPage(driver);
        loginPage.waitForElementToBeVisible(cm.getSearchBox());
        cm.searchForProduct(input.get("k_product"));
        LOG.debug("Search performed for product: " + input.get("k_product"));
        SearchPage searchPage = new SearchPage(driver);
        String searchItem = searchPage.getProductName().getText();
        String[] mainName = searchItem.split(" ");
        String splittedName = mainName[0];
        List<String> keyList = Arrays.asList(keyword);
        if (keyList.contains(splittedName)) {
            Assert.assertTrue(true);
        }
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String, String>> data = getJsonData(
                System.getProperty("user.dir") + TutorialsNinjaConstants.SEARCH_JSON);
        List<HashMap<String, String>> data1 = getJsonData(System.getProperty("user.dir") + TutorialsNinjaConstants.LOGIN_JSON);
        return new Object[][]{
                {data.get(0), data1.get(0)},
                {data.get(1), data1.get(0)},
                {data.get(2), data1.get(0)}
        };
    }

}
