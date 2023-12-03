#Author: Saif Ansari
#Keywords Summary : Validate Cars Guide Homepage
@HomePageValidation
Feature: Validate Cars Guide Homepage 

  @ValidateCarsGuideLogoSection
  Scenario: Validate Cars guide Logo is present
	Given when user is on the CarsGuide HomePage
	Then user is able to see the CarsGuide Logo
	Then search for "BMW"

 #Test from Saif
