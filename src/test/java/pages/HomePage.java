package pages;

import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import stepDefinitions.Hooks;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.WebUtil;

import java.io.IOException;
import java.time.Duration;

public class HomePage extends Base{

    private static WebDriver driver = Hooks.driver;

    @FindBy(linkText = "About Us")
    private static WebElement aboutUsLink;

    @FindBy(xpath="//div[@id='onetrust-banner-sdk']//button[@id='onetrust-accept-btn-handler']")
    private static WebElement CookieAlertAcceptAllButton;

    public HomePage(WebDriver driver) {
        super(driver);
        String actualPageTitle = driver.getTitle();
        String expectedPageTitle = "LexisNexis Risk Solutions | Transform Your Risk Decision Making";
        try {
            expectedPageTitle = WebUtil.getProperty("homePagePageTitle");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(actualPageTitle, expectedPageTitle, actualPageTitle);


    }

    public static void clickAboutUsLink() {
        aboutUsLink.click();
    }

    public static void handleCookiePopup() {

        // Wait for the cookie popup to appear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            // Wait until the "Accept" button is clickable, and then click it
            WebElement acceptButton = wait.until(ExpectedConditions.elementToBeClickable(CookieAlertAcceptAllButton));
            acceptButton.click();
        } catch (NoSuchElementException e) {
            // If the "Accept" button is not found, it means the cookie alert did not appear
            System.out.println("Cookie popup did not appear or Accept button is not found");
            throw new AssertionError("Accept button not found. Test failed.");
        } catch (Exception e) {
            // Handle any other exceptions
            System.out.println("An error occurred: " + e.getMessage());
            throw e; // Rethrow the exception to fail the test
        }
    }
}
