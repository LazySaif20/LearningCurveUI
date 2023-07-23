@tag
Feature: Create Sample Feature file
  I want to use this template for my feature file

  @SampleTest
  Scenario Outline: Get all posts from API
    Given get call to "<uri>"
    Then response is "<Response>"

    Examples: 
      | uri    | Response |
      | /posts |      200 |
