package stepdefs;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page.NewPastebinPage;
import page.PastebinHomePage;

import java.util.List;
import java.util.Map;

public class CreatePastebinStepDefs {

  PastebinHomePage pastebinHomePage;
  NewPastebinPage newPastebinPage;
  private WebDriver driver;

  @Before
  public void setUp() {
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    driver.manage().window().maximize();
  }

  @Given("I opened pastebin page")
  public void openPastebinPage() {
    pastebinHomePage = new PastebinHomePage(driver).openPage();
  }

  @When("I fill paste parameters")
  public void fillPasteParameters(DataTable table) {
    List<Map<String, String>> list = table.asMaps();
    pastebinHomePage
            .addCode(list.get(0).get("Code"))
            .addPasteNameOrTitle(list.get(0).get("Paste name"));
  }

  @And("I set 10 minutes expiration time")
  public void set10MinExpirationTime() {
    pastebinHomePage.selectPasteExpirationAsTenMinutes();
  }

  @And("I set syntax highlighting as bash")
  public void setSyntaxHighlightingAsBash() {
    pastebinHomePage.selectSyntaxHighlightingAsBash();
  }

  @And("I click Create Paste button")
  public void clickCreatePasteButton() {
    pastebinHomePage.createNewPaste();
  }

  @Then("Created paste match parameters")
  public void receivedTitleMatchesPasteName() {
    newPastebinPage = new NewPastebinPage(driver);
    Assertions.assertEquals(newPastebinPage.getPasteName(), "how to gain dominance among developers");
    Assertions.assertTrue(newPastebinPage.hasBashButton());
    Assertions.assertTrue(newPastebinPage.getHighlightedText().contains("git config --global user.name  \"New Sheriff in Town\"\n"
            + "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n"
            + "git push origin master --force"));
  }

  @After
  public void tearDown() {
    if (driver != null) {
      driver.quit();
    }
  }
}
