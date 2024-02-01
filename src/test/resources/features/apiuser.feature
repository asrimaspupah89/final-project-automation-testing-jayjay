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

  @api
  Scenario: Test get data profile user normal
    Given prepare url for "GET_PROFILE_USER"
    When hit api get profile user by id "60d0fe4f5311236168a109cd"
    Then validation status code is equals 200
    Then validation response body get profile user
    Then validation response json with JSONSchema "get_profile_user_normal.json"

  @api
  Scenario: Test get data profile user failed not found
    Given prepare url for "GET_PROFILE_USER"
    When hit api get profile user by id "65bb2ac113492cde6cfbd2d8"
    Then validation status code is equals 404
    Then validation response body get profile user with message "RESOURCE_NOT_FOUND"
    Then validation response json with JSONSchema "get_profile_user_not_found.json"

  @api
  Scenario: Test create new user normal
    Given prepare url for "CREATE_NEW_USER"
    When hit api post create new user
    Then validation status code is equals 200
    Then validation response body post create new user
    Then validation response json with JSONSchema "post_create_user_normal.json"

  @api
  Scenario: Test update user normal
    Given prepare url for "UPDATE_USER"
    And hit api get profile user by id "65bb07a7a88f6f519cb0cc55"
    When hit api update profile user by id "65bb07a7a88f6f519cb0cc55"
    Then validation status code is equals 200
    Then validation response body update user
    Then validation response json with JSONSchema "update_profile_user_normal.json"

  @api
  Scenario: Test delete user normal
    Given prepare url for "DELETE_USER"
    And hit api get profile user by id "65bb2c2213492c3665fbd2f2"
    When hit api delete user for id "65bb2c2213492c3665fbd2f2"
    Then validation status code is equals 200
    Then validation response body delete user
    Then validation response json with JSONSchema "delete_user_normal.json"
    Then hit api get profile user by id "65bb2c2213492c3665fbd2f2"
    Then validation response body get profile user with message "RESOURCE_NOT_FOUND"
