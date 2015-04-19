# language: en
Feature: Stop simulation
  In order to simulate robot in a artificial wold
  As a wasupu robot designer
  I want to know the status of the simulation

  Scenario: Get simulation status
    When I send the get simulation status command
    Then the response code should be 200
    And there 1 robot active in the simulation
