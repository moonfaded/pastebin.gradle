package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PastebinHomePage extends AbstractPage {
  private static final String PASTEBIN_HOMEPAGE_URL = "https://pastebin.com";

  @FindBy(id = "postform-text")
  private WebElement pasteInput;

  @FindBy(xpath = "//span[text()='None']")
  private WebElement syntaxHighlightingMenu;

  @FindBy(xpath = "//li[contains(text(),'Bash')]")
  private WebElement syntaxHighlightingBashOption;

  @FindBy(xpath = "//span[text()='Never']")
  private WebElement pasteExpirationMenu;

  @FindBy(xpath = "//li[contains(text(),'10 Minutes')]")
  private WebElement pasteExpirationTenMinutesOption;

  @FindBy(id = "postform-name")
  private WebElement pasteNameOrTitle;

  @FindBy(xpath = "//button[contains(text(),'Create New Paste')]")
  private WebElement newPasteButton;

  public PastebinHomePage(WebDriver driver) {
    super(driver);
  }

  public PastebinHomePage openPage() {
    driver.get(PASTEBIN_HOMEPAGE_URL);
    return this;
  }

  public PastebinHomePage addCode(String pasteCode) {
    pasteInput.sendKeys(pasteCode);
    return this;
  }

  public PastebinHomePage selectSyntaxHighlightingAsBash() {
    Utilities.selectFromTheDropdownList(syntaxHighlightingMenu, syntaxHighlightingBashOption, driver);
    return this;
  }

  public PastebinHomePage selectPasteExpirationAsTenMinutes() {
    Utilities.selectFromTheDropdownList(pasteExpirationMenu, pasteExpirationTenMinutesOption, driver);
    return this;
  }

  public PastebinHomePage addPasteNameOrTitle(String pasteName) {
    pasteNameOrTitle.sendKeys(pasteName);
    return this;
  }

  public NewPastebinPage createNewPaste() {
    newPasteButton.click();
    return new NewPastebinPage(driver);
  }
}
