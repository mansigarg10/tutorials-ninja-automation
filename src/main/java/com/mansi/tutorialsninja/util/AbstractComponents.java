package com.mansi.tutorialsninja.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Map;

/**
 * This class contains common methods.
 *
 * @author Mansi Garg
 */
@Getter
@Setter
public class AbstractComponents {

    private final WebDriver driver;

    public AbstractComponents(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "ul[class='breadcrumb'] li:nth-child(3) a")
    private WebElement breadCrumb;

    /**
     * This method reads a JSON file and returns its content as a Map.
     *
     * @param filePath the path to the JSON file
     * @return a Map containing the JSON data
     * @throws IOException if there is an error reading the file
     */
    public Map<String, String> getJsonData(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(filePath);
        return mapper.readValue(file, new TypeReference<Map<String, String>>(){});
    }

    /**
     * This method waits for an element to be visible on the page.
     *
     * @param element the WebElement to wait for
     */
    public void waitForElementToBeVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * This method waits for an element to be clickable and then clicks it.
     * If the element is not interactable, it scrolls to the element and retries clicking.
     *
     * @param element the WebElement to wait for and click
     */
    public void waitAndClickElement(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (Exception e) {
            // If the element is not interactable, scroll to it and retry
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", element);
            js.executeScript("arguments[0].click();", element);
        }
    }

    /**
     * This method scrolls the window down by 500 pixels.
     */
    public void scrollWindow(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500");
    }

}
