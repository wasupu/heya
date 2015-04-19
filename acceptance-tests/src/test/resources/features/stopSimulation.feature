# language: en
Feature: Stop simulation
  In order to simulate robot in a artificial wold
  As a wasupu robot designer
  I want to stop simulation

  Scenario: Stop simulation
    When I send the stop simulation command
    Then the response code should be 204
