Feature: Use Case Factory Upgrade
  As a USER
  I want generate more resources manually.

  Scenario: Generate Resources manually.
    Given the USER is on the Resource View
    When the USER taps on the screen
    Then calculate the generated amount of resources
    And add the amount of resources to USER
    And update the label with the amount of resources
