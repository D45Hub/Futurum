Feature: Use Case Factory Upgrade
    As a USER
    I want generate more resources manually.

    Scenario: Generate Resources manually.
        When the USER taps on the screen.
        Then calculate the generated amount of resources
        And add the amount of resources to USER
        And play background animation
        And update the label witht the amount of resources
