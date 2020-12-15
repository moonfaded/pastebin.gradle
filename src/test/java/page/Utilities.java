package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utilities {

  public static void waitForElementVisibility(WebElement element, WebDriver driver) {
    new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOf(element));
  }

  public static void waitForElementToBeClickable(WebElement element, WebDriver driver) {
    new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(element));
  }

  public static void clickOnTheVisibleElement(WebElement element, WebDriver driver) {
    new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOf(element)).click();
  }

  public static void clickOnTheClickableElement(WebElement element, WebDriver driver) {
    new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(element)).click();
  }

  public static void selectFromTheDropdownList(WebElement dropdownList, WebElement requiredOption, WebDriver driver) {
    clickOnTheVisibleElement(dropdownList, driver);
    clickOnTheClickableElement(requiredOption, driver);
  }
}
