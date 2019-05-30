package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HeaderPage extends GenericPage{
    public HeaderPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    @FindBy (css = "[href=\"/cart\"]")
    WebElement cartIcon;

    public CartPage openCartPage(){
        GenericPage genericPage = new GenericPage(driver);
        genericPage.fluentWaitForElementDisplayed(cartIcon);
        cartIcon.click();
        return new CartPage(driver);
    }
}
