package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.AboutUsPage;
import pages.CareersPage;
import pages.HomePage;
import util.WebUtil;

import java.io.IOException;

public class HomePageFeatureSteps {

    private WebDriver driver = Hooks.driver;
    AboutUsPage aboutUsPage = new AboutUsPage(driver);
    CareersPage careersPage = new CareersPage(driver);


    @Given("User is on the LexisNexis Risk home page")
    public void user_is_on_the_lexis_nexis_risk_home_page() throws IOException {

        driver.get(WebUtil.getProperty("url"));

        HomePage homePage = new HomePage(driver);
        HomePage.handleCookiePopup();


    }
    @When("User navigates to the Careers page")
    public void user_navigates_to_the_careers_page() throws InterruptedException, IOException {
        HomePage.clickAboutUsLink();
        AboutUsPage.clickCareers();
        CareersPage.checkUserIsInCareersPage();



    }

    @When("User searches for {string} in the search box")
    public void user_searches_for_in_the_search_box(String string) {
        CareersPage.searchForJobs(string);

    }
    @Then("There should be at least one search result")
    public void there_should_be_at_least_one_search_result() {
        CareersPage.verifyMoreThanOneJobIsDisplayed();
    }

    @And("User clicks on Search all jobs")
    public void userClicksOnSearchAllJobs() {
        CareersPage.clickSearchAllJobs();
    }

}

