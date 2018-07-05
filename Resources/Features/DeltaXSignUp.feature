#Author: Ramarajan Subburaj
#Email: ramarajanengg@gmail.com

Feature: User Registration
 
 @P1Flow
  Scenario: 1. Verify Successful Login
    Given I am in Registration Form
    When I enter Name, Dept, Username, Password, Email and Contact Number
    And Click on Submit
    Then Sign Up should be successful
  
  @P1Flow  
  Scenario: 2. Verify registered user cannot sign up again
  	Given the user is already registered
  	When I try to register again
  	Then Sign Up should not be successful
 
  @P1Flow	
  Scenario: 3. Verify Successful Sign up with Required Fields only
  	Given I am in Registration Form
  	When I enter only required fields
  	Then Sign Up should be successful
  	
  @ErrorValidations
  Scenario: 4. Verify Mandatory validations on fields
  	Given I am in Registration Form
  	When Invalid or bad data entered on fields
  	Then Sign Up should not be successful
  	
  @ErrorValidations
  Scenario: 5. Verify unsuccessful sign up when password and confirm password do not match
  	Given I am in Registration Form
  	When Password and Confirm Password do not match
  	And Click on Submit
  	Then Sign Up should not be successful
  	
  	
  