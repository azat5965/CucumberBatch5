@regressiontest @TEC-1010 @ui
Feature: Validating headers

  @TEC-2012
  Scenario: Validating Product Information headers
    Given User navigate to WedOrders application
    When User provides username "username" and password "password"
    And User click on Order part
    Then User validates headers with "TestCases" excel file expected result
    And User updates "TestCases" with "PASS"