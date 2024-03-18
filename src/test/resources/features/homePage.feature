Feature: Job Search

  Scenario: Search for automation tester jobs
    Given User is on the LexisNexis Risk home page
    When User navigates to the Careers page
    And User clicks on Search all jobs
    And User searches for "automation tester" in the search box
    Then There should be at least one search result
