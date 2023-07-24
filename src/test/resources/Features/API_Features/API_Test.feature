@Saif
Feature: Create Sample Feature file
  I want to use this template for my feature file

  @SampleTest
  Scenario Outline: Get all posts from API
    Given GET call to "<uri>"
    Then response is "<Response>"

    Examples: 
      | uri   | Response |
      | /data |      200 |

  @validateDataCount
  Scenario Outline: Validate the number of data returned in response is as expected.
    Given GET call to "<uri>"
    Then validate the count returned matches the expected number of records <numberOfRecords>

    Examples: 
      | uri   | numberOfRecords |
      | /data |               12 |

  @getCategoryNameOfThe5thRecord
  Scenario Outline: Validate the category name of a certain record is as expected.
    Given GET call to "<uri>"
    Then validat the category name of the <RecordNumber> th category is as expected "<categoryName>"

    Examples: 
      | uri   | categoryName             | RecordNumber |
      | /data | Herzog, Casper and Lakin |            5 |

  @setValues
  Scenario Outline: Update a new user in db
    Given we have certain data for the users
    When POST the data to "<uri>"
    Then validate the user data is present "<uri>"

    Examples: 
      | uri   |
      | /data |
