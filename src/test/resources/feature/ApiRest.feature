Feature: Base test to cucumber feature
  Scenario: Receive a get request
    Given the follow request:
      | testExampleRequest.json |
    When get endpoint is called "test" environment
    Then server will send status code of 200