Feature: Product Search
  As a user i can search products using the search bar

  Scenario: Search products related to carne
    When I search "carne"
    When I press search_action
    Then I should see "Plato2" 