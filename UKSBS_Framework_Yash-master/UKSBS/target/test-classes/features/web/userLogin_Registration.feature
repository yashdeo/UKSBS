@regression
Feature: Login and registration functionality on the website
  In order to login to the system
  As a user
  the login option should be provided or the facility to register the user should be provided

  Background: User is on the home page
    Given User is on the home page

  Scenario Outline: Login using the incorrect user data
    When user enters the email "<email>" and password "<password>"
    And clicks on login button
    Then the error message "<authenticationErrorMessage>" should be displayed

    Examples:
      | email           | password | authenticationErrorMessage                            |
      | test@tester.com | test123  | Your email/password combination doesn't seem to work. |


  Scenario Outline: Login using the correct user data
    When user enters the email "<email>" and password "<password>"
    And clicks on login button
    Then the user should be successfully logged in

    Examples:
      | email        | password |
      | yash@deo.com | yashdeo  |
