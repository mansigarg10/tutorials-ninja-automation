package com.mansi.tutorialsninja.listeners;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mansi.tutorialsninja.util.TutorialsNinjaConstants;
import com.mansi.tutorialsninja.utils.HtmlFormatter;
import com.mansi.tutorialsninja.utils.TutorialsNinjaTestConstants;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * This class initializes and closes the browser after test execution is completed.
 * It also includes methods for taking screenshots and reading JSON data.
 *
 * @author Mansi Garg
 */
public class BaseTest {

    private final Logger LOG = LogManager.getLogger(BaseTest.class);

    public WebDriver driver;
    protected Properties prop = new Properties();
    public final String filePath = System.getProperty("user.dir") + TutorialsNinjaConstants.DATA_PROPERTIES;

    public WebDriver initializeBrowser() throws IOException {
        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);
        prop.load(fis);
        if (prop.getProperty("browser").equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (prop.getProperty("browser").equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else {
            driver = new EdgeDriver();
        }
        driver.get(prop.getProperty("URL"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    public List<HashMap<String, String>> getJsonData(String filePath) throws IOException {
        String jsonContent = FileUtils.readFileToString(new File(filePath), (Charset) null);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonContent,
                new TypeReference<List<HashMap<String, String>>>() {});
    }

    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destinationPath = new File(System.getProperty("user.dir")
                + TutorialsNinjaTestConstants.SCREENSHOT_CAPTURE_PATH + testCaseName + ".png");
        FileUtils.copyFile(src, destinationPath);
        return  TutorialsNinjaTestConstants.SCREENSHOT_CAPTURE_PATH + testCaseName + ".png";
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }

    @AfterSuite
    public void cleanHtmlReport() {
        try {
            Path htmlPath = Paths.get(System.getProperty("user.dir") + "/docs/index.html");
            HtmlFormatter.removeLeadingEmptyLines(htmlPath);
            HtmlFormatter.sanitizeImagePaths(htmlPath);
            LOG.info("HTML report formatting completed.");
        } catch (IOException e) {
            LOG.error("Failed to format HTML report: {}", e.getMessage());
        }
    }

}
