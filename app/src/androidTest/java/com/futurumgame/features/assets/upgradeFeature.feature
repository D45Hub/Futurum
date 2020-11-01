Feature: Use Case Factory Upgrade
    As a USER
    I want to upgrade to upgrade the resource factories.

  Scenario: Upgrade Success
    Given the USER has enough resource
    When the USER presses the Upgrade button
    Then the amount of resources of USER are subtracted
    And the USER get notified that the upgrade was successful.

  Scenario: Upgrade Failed
    Given the USER has not enough resources
    When the USER presses the Upgrade button
    Then the USER gets notified that the upgrade failed.