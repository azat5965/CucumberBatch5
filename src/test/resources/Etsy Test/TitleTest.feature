@regressiontest @TEC-1003
Feature: Validating Title for each page

  @TEC-2004 @ui
  Scenario Outline: Validaitng titles
    Given User navigates to Etsy application
    When User "<department>" part
    Then User validates "<title>" title
      Examples:
      | department            | title                        |
      | Jewelry & Accessories | Jewelry & Accessories \| Etsy |
      | Clothing & Shoes      | Clothing & Shoes \| Etsy      |
      | Home & Living         | Home & Living \| Etsy         |
      | Wedding & Party       | Wedding & Party \| Etsy       |
      | Toys & Entertainment  | Toys & Entertainment \| Etsy  |
      | Art & Collectibles    | Art & Collectibles \| Etsy   |
