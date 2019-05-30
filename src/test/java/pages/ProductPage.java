package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage extends GenericPage{
    ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "add to cart")
    WebElement addToCart;

    @FindBy(className = "price")
    WebElement price;

    public void addProductToCart(){
        addToCart.click();
    }

    public String getPrice(){
        return price.getText();
    }
}