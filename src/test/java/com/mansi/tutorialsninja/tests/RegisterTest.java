package com.mansi.tutorialsninja.tests;

import com.mansi.tutorialsninja.listeners.BaseTest;
import com.mansi.tutorialsninja.pages.RegisterPage;
import com.mansi.tutorialsninja.utils.TutorialsNinjaTestConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * This class validates the Register functionality.
 *
 * @author Mansi Garg
 */
public class RegisterTest extends BaseTest {

    private final Logger LOG = LogManager.getLogger(RegisterTest.class);

    @Test(groups = {"smoke"})
    public void register() throws IOException {
        WebDriver driver = initializeBrowser();
        LOG.debug("Browser launched successfully for Register Test");
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.registerToApplication();
        Assert.assertTrue(registerPage.getWarningMessage().getText().equalsIgnoreCase(TutorialsNinjaTestConstants.REGISTER_WARNING_MSG));
    }

}
