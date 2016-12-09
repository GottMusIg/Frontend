Feature: NavigationPanel

Scenario: Clicked About button
Given I enter the HomePage
When I clicked the About button
Then I navigate to "([^\"]*)"
