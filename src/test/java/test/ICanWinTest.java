package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page.NewPastebinPage;
import page.PastebinHomePage;

public class ICanWinTest {
  private final String PASTE_NAME = "helloweb";
  private final String CODE = "Hello from WebDriver";
  private WebDriver driver;

  @BeforeEach
  public void setupBrowser() {
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    driver.manage().window().maximize();
  }

  @Test
  public void createNewPasteHelloWeb() {
    NewPastebinPage newPage = new PastebinHomePage(driver)
                                  .openPage()
                                  .addCode(CODE)
                                  .selectPasteExpirationAsTenMinutes()
                                  .addPasteNameOrTitle(PASTE_NAME)
                                  .createNewPaste();
  }

  @AfterEach
  public void closeBrowser() {
    if (driver != null) {
      driver.quit();
    }
  }
}
