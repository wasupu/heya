#language: en
Feature: Heartbeat
  In order to check that the server is up
  As a client application
  I want to know the server status

  Scenario: GET Heartbeat
    When I request a heartbeat
    Then the response code should be 200