Feature: Login

  @single
  Scenario Outline: Successful Login to the page
    Given user navigates to the fb page
    And user enters "<username>" in to "username" field
    And user enters "<password>" in to "password" field
    When user clicks "loginButton"
    Then the header text displays "The password you’ve entered is incorrect. Forgot Password?"

    Examples:
      | username          | password |
      | suruchi@gmail.com | test     |