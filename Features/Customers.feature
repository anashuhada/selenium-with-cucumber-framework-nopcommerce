Feature: Customers

  Background: Below are the common steps for each scenario
    Given user launches Chrome browser
    When user opens URL "https://admin-demo.nopcommerce.com/login"
    And user enters Email as "admin@yourstore.com" and Password as "admin"
    And clicks on Login button
    Then user can view Dashboard
    When user clicks on Customers menu
    And clicks on Customers menu item

  @sanity
  Scenario: Add a new customer
    And clicks on Add new button
    Then user can view Add new customer page
    When user enters customer details
    And clicks on Save button
    Then user can view confirmation message "The new customer has been added successfully."
    And close browser

  @regression
  Scenario: Search customer by email
    And enters customer email
    When clicks on search button
    Then user should find email in the search table
    And close browser

  @regression
  Scenario: Search customer by name
    And enters customer first name
    And enters customer last name
    When clicks on search button
    Then user should find name in the search table
    And close browser
