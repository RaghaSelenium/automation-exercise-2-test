package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import stepDefinitions.Hooks;

import java.time.Duration;
import java.util.Set;

public class AboutUsPage extends Base {

    @FindBy(xpath="(.//*[normalize-space(text()) and normalize-space(.)='Press Room'])[1]/preceding::a[1]")
    private static WebElement careersHeader;


    public AboutUsPage(WebDriver driver) {
        super(driver);

    }
    private static WebDriver driver = Hooks.driver;

    public static void clickCareers() throws InterruptedException {

        careersHeader.click();
        // Get all window handles
        Set<String> windowHandles = driver.getWindowHandles();

// Switch to the new tab
        for (String windowHandle : windowHandles) {
            driver.switchTo().window(windowHandle);
        }


    }

}
