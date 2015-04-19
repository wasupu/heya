# language: en
Feature: Start simulation
  In order to simulate robot in a artificial wold
  As a wasupu robot designer
  I want to start simulation

  Scenario: Start simulation
    When I send the start simulation command
    Then the response code should be 201
