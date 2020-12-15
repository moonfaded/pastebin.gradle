package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page.NewPastebinPage;
import page.PastebinHomePage;

public class BringItOnTest {
  private final String PASTE_NAME = "how to gain dominance among developers";
  private final String CODE = "git config --global user.name  \"New Sheriff in Town\"\n"
                                  + "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n"
                                  + "git push origin master --force";
  private WebDriver driver;
  private NewPastebinPage newPage;

  @BeforeEach
  public void setupBrowser() {
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
  public void checkIfReceivedTitleMatchesPasteName() {
    Assertions.assertEquals(newPage.getPasteName(), PASTE_NAME);
  }

  @Test
  public void checkIfSyntaxIsHighlighted() {
    Assertions.assertTrue(newPage.hasBashButton());
  }

  @Test
  public void checkIfReceivedTextMatchesCode() {
    Assertions.assertTrue(newPage.getHighlightedText().contains(CODE));
  }

  @AfterEach
  public void closeBrowser() {
    driver.quit();
    driver = null;
  }
}
