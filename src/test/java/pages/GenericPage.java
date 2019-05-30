package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.concurrent.TimeUnit;

public class GenericPage {
    WebDriver driver;

    GenericPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fluentWaitForElementDisplayed(WebElement elementToBeDisplayed) {
        new FluentWait<>(elementToBeDisplayed)
                .withTimeout(10, TimeUnit.SECONDS)
                .pollingEvery(500, TimeUnit.MILLISECONDS)
                .until(WebElement::isDisplayed);
    }
}
