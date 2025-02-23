package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import stepDefinitions.Hooks;
import util.WebUtil;


import java.io.IOException;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class CareersPage extends Base {


    @FindBy(linkText = "Search jobs")
    private static WebElement searchAllJobsLink;

    @FindBy(xpath = "//input[@class='search-box-input ais-search-box--input']")
    private static WebElement searchBox;

    @FindBy(xpath = "//button[@class='btn search-box-button']")
    private static WebElement searchButton;


    // Locate the search results element

    @FindBy(className = "ais-hits--item")
    private static WebElement searchResultItems;

    @FindBy(xpath = "//button[@class='btn search-box-button']")
    private static WebElement searchButtons;


    // Locate the search results element

    @FindBy(className = "search-results")
    private static WebElement searchResults;

    @FindBy(xpath = "//div[contains(text(), 'Results \"automation tester\"')]")
    private static WebElement searchResultsTextArea;

    public CareersPage(WebDriver driver) {
        super(driver);

    }

    private static WebDriver driver = Hooks.driver;

    public static void checkUserIsInCareersPage() throws IOException {
        String actualPageTitle = driver.getTitle();
        String expectedPageTitle = WebUtil.getProperty("careersPagePageTitle");
        Assert.assertEquals(actualPageTitle, expectedPageTitle, actualPageTitle);

    }

    public static void clickSearchAllJobs() {
        searchAllJobsLink.click();
    }

    public static void searchForJobs(String text) {
        WebUtil.typeIntoTextField(searchBox, text);
        searchButton.click();
    }

    public static void verifyMoreThanOneJobIsDisplayed() {

        // Wait for the search results  popup to appear

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(searchResultsTextArea));

        // getting the list size for given search term
        int listSize = searchResults.findElements(By.className("ais-hits--item")).size();

        assertTrue(listSize > 0, "Test failed: No search results found for the given term.");

        // Asserting that the list size matches the number mentioned in the result text
        assertEquals(WebUtil.extractNumber(WebUtil.getText(searchResultsTextArea)), listSize);
    }

}

