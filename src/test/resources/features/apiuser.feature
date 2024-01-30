#Author : Asri Maspupah
#Title  : Functionality testing for Api user management
#Date   : 31 January 2024
#Software Under Test  : Api Go Rest in the https://dummyapi.io/

Feature: Test Automation Rest API User Management

  @api
  Scenario: Test Get List Data User Normal
    Given prepare url for "GET_LIST_USERS"
    When hit api get list users
    Then  validation status code is equals 200
    Then  validation response body get list users
    Then  validation response json with JSONSchema "get_list_users_normal.json"