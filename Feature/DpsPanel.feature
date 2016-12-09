Feature: DpsPanel

Scenario: Data in DpsPanel
Given I enter the HomePage
When HomePage is called
Then element having wicket:id "class" should have partial text as "Demon Hunter"
