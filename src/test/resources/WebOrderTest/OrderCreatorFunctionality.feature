@regressiontest @TEC-1002
Feature: Order Creation Functionality

  Background: Common steps for all scenarios

  @TEC-2003 @ui
  Scenario: Creating order with valid data
    Given User navigate to WedOrders application
    When User provides username "Tester" and password "test"
    Then User validates that application "is" logged in
    When User click on Order part
    And User adds new order with data

      | Quantity | Customer name | Street      | City        | Zip   | State | Card Nr | Expire Date |
      | 2        | John Doe      | 123 Doe St. | Des Plaines | 60006 | IL    | 1234567 | 12/21       |
          #One value of list
          # data.get(0).get("Quantity"); ->2
          #data.get(0).get("Customer name"); -> David Clark
          #data.get(0).get("Zip"); -> 60086


    Then User click on Process button and validates "New order has been successfully added." message
    When User click View All Orders parts
    Then User created order is added to list with data
      | Quantity | Customer name | Street      | City        | Zip   | State | Card Nr | Expire Date |
      | 2        | John Doe      | 123 Doe St. | Des Plaines | 60006 | IL    | 1234567 | 12/21       |


  @TEC-2015 @ui
  Scenario: Creating multiple order
    Given User navigate to WedOrders application
    When User provides username "Tester" and password "test"
    Then User validates that application "is" logged in
    When User click on Order part
    And User creates all order from "TestData2" excel file
    Then User click on Process button and validates "New order has been successfully added." message
    When User click View All Orders parts
    Then User validates that orders from "TestData2" excel file is created