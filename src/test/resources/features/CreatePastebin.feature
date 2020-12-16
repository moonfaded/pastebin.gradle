Feature: Create new paste

  Scenario: Create paste with parameters
    Given I opened pastebin page
    When I fill paste parameters
      | Code                 | Paste name |
      | Hello from WebDriver | helloweb   |
    And I set 10 minutes expiration time
    And I click Create Paste button


  Scenario: Create paste with parameters and check the results
    Given I opened pastebin page
    When I fill paste parameters
      | Code                                                                                                                                                | Paste name                             |
      | git config --global user.name  "New Sheriff in Town" \n git reset $(git commit-tree HEAD^{tree} -m "Legacy code") \n git push origin master --force | how to gain dominance among developers |
    And I set 10 minutes expiration time
    And I set syntax highlighting as bash
    And I click Create Paste button
    Then Created paste match parameters
