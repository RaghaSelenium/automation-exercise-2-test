package stepDefinitions;


import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class Hooks {

    public static WebDriver driver;


    @Before
    /**
     * Delete all cookies at the start of each scenario to avoid
     * shared state between tests
     */
    public void openBrowser() throws MalformedURLException {

        initialiseBrowser();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


    }

    public void initialiseBrowser() {

        if (driver == null) {

            // Currently it supports only chrome on Mac

            // Setup Chrome options
            ChromeOptions options = new ChromeOptions();

            // Add the argument to clear cache
            options.addArguments("--incognito");
            options.addArguments("--disable-cache");

            // Initialize the ChromeDriver
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
        }

    }

    @After
    public void embedScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "Screenshot");
            } catch (WebDriverException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    @AfterAll
    public static void tearDown(){
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
