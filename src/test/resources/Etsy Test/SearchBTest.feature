@smoketest @regressiontest @ui @TEC-1005
Feature: validating Search box

  Background: Running two steps for all scenarios
    Given User navigates to Etsy application
    When User searches for "carpet"

  @TEC-2208
  Scenario: Validating search box is giving right output

    Then  User validates the result contains
      | carpet    | runner      |
      | rug       | oval rug    |
      | area rugs | turkish rug |

  @TEC-2009
  Scenario: Validating searching result wih selecting price range
    And   User select over $1000 option
    Then  User validates that all prices are over 1000
