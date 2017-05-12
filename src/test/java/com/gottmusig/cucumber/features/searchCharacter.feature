Feature: To test searching a chracter

  Scenario: Find a character via character search form
    Given I navigated to the GottMusIg website
    And I choose the location "de_DE"
    And I choose the realm "Krag'Jin"
    And I look for "Taurosso"
    And I click the "Search" button
    Then I see the character panel
