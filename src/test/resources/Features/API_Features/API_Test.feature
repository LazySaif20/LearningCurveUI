@Saif
Feature: Create Sample Feature file
  I want to use this template for my feature file

  @SampleTest @GET
  Scenario Outline: Get all posts from API
    When GET call to "<uri>"
    Then response is "<Response>"

    Examples: 
      | uri   | Response |
      | /data |      200 |

  @validateDataCount @Ignore
  Scenario Outline: Validate the number of data returned in response is as expected.
    When GET call to "<uri>"
    Then validate the count returned matches the expected number of records <numberOfRecords>

    Examples: 
      | uri   | numberOfRecords |
      | /data |              12 |

  @getCategoryNameOfThe5thRecord
  Scenario Outline: Validate the category name of a certain record is as expected.
    When GET call to "<uri>"
    Then validat the category name of the <RecordNumber> th category is as expected "<categoryName>"

    Examples: 
      | uri   | categoryName             | RecordNumber |
      | /data | Herzog, Casper and Lakin |            5 |

  @setValues @POST
  Scenario Outline: POST a new user in db
    Given we have certain data for the users
    When POST the data to "<uri>"
    Then validate the user data is present "<uri>"

    Examples: 
      | uri   |
      | /data |

  @UpdateData @PUT @UpdateWholeData
  Scenario Outline: UPDATE a user in db
    Given we have certain data for the users
    When PUT the data to the expected user <userID>, "<uri>"
    Then validate the updated data is present for the user <userID>, "<uri>"

    Examples: 
      | uri    | userID |
      | /data/ |     11 |

  @UpdateData @PATCH @UpdateName
  Scenario Outline: UPDATE a user's name in db
    Given get the data to be updated for the user
    When PATCH the data to the expected user <userID>, "<uri>"
    Then validate the updated data is present for the user <userID>, "<uri>"

    Examples: 
      | uri    | userID |
      | /data/ |      1 |

  @QueryPathParam
  Scenario Outline: GET user data from response with given PATH and QUERY Parameeters
    When GET call is made with Query as "<query>" and Path as "<path>" Params
    Then validate the user details are as expected as <color>, <year>, "<name>", <id>, "<pantone_value>", <CategoryID> and "<CategoryName>"

    Examples: 
      | path  | query             | color      | year | name         | id | pantone_value | CategoryID | CategoryName             |
      | data | name,Nigel Casper | 1378387981 | 1947 | Nigel Casper | 32 | Spock         |         28 | Herzog, Casper and Lakin |
