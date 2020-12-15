package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page.NewPastebinPage;
import page.PastebinHomePage;

public class BringItOnTest {
  private static final String PASTE_NAME = "how to gain dominance among developers";
  private static final String CODE = "git config --global user.name  \"New Sheriff in Town\"\n"
                                  + "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n"
                                  + "git push origin master --force";
  private static WebDriver driver;
  private static NewPastebinPage newPage;

  @BeforeAll
  static void setupBrowser() {
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    driver.manage().window().maximize();

    newPage = new PastebinHomePage(driver)
                  .openPage()
                  .addCode(CODE)
                  .selectSyntaxHighlightingAsBash()
                  .selectPasteExpirationAsTenMinutes()
                  .addPasteNameOrTitle(PASTE_NAME)
                  .createNewPaste();
  }

  @Test
  public void receivedTitleMatchesPasteName() {
    Assertions.assertEquals(newPage.getPasteName(), PASTE_NAME);
  }

  @Test
  public void syntaxIsHighlighted() {
    Assertions.assertTrue(newPage.hasBashButton());
  }

  @Test
  public void receivedTextMatchesCode() {
    Assertions.assertTrue(newPage.getHighlightedText().contains(CODE));
  }

  @AfterAll
  static void closeBrowser() {
    if (driver != null) {
      driver.quit();
    }
  }
}
