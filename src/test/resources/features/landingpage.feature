#Author : Asri Maspupah
#Title  : Functionality testing for Landing Feature
#Date   : 30 January 2024
#Software Under Test  : Swag Lab in the https://www.demoblaze.com/

Feature: Test Automation Web landing Page

  @web
  Scenario: Test web login normal
    Given user go to Product Store page "https://www.demoblaze.com/"
    And user click menu Log in
    When user input username "test.asri"
    And user input password "Test123!!"
    And user click button login
    Then User login successfully with welcome message "Welcome test.asri"