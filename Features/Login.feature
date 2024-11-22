Feature: Login into nopCommerce Admin site

  @sanity
  Scenario: Successful login with valid credentials
    Given user launches Chrome browser,
    When user opens URL "https://admin-demo.nopcommerce.com/login"
    And user enters Email as "admin@yourstore.com" and Password as "admin"
    Then page title should be "Your store. Login"
    And clicks on Login button
    Then page title should be "Dashboard / nopCommerce administration"
    When user clicks on Logout link
    And close browser

  @regression
  Scenario Outline: Login Data Driven
    Given user launches Chrome browser
    When user opens URL "https://admin-demo.nopcommerce.com/login"
    And user enters Email as "<email>" and Password as "<password>"
    Then page title should be "Your store. Login"
    And clicks on Login button
    Then page title should be "Dashboard / nopCommerce administration"
    When user clicks on Logout link
    And close browser

    Examples: 
      | email                   | password  |
      | admin@yourstore.com     | admin     |
      | demoadmin@yourstore.com | demoadmin |
