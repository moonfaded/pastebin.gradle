Feature: Create new paste

  Scenario: Create paste with parameters
    Given I opened pastebin page
    When I fill paste parameters
      | Paste name | Code                 |
      | helloweb   | Hello from WebDriver |
    And I set 10 minutes expiration time
    And I click Create Paste button


  Scenario: Create paste with parameters and check the results
    Given I opened pastebin page
    When I fill paste parameters
      | Paste name                             | Code                                                                                                                                                |
      | how to gain dominance among developers | git config --global user.name  "New Sheriff in Town" \n git reset $(git commit-tree HEAD^{tree} -m "Legacy code") \n git push origin master --force |
    And I set 10 minutes expiration time
    And I set syntax highlighting as bash
    And I click Create Paste button
    Then Created paste match parameters
