package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage extends GenericPage{
    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[title='add to cart']")
    WebElement addToCart;

    @FindBy(css = "[class='price'] span")
    WebElement price;

    @FindBy(className = "name")
    WebElement name;

    public void addProductToCart(){
        addToCart.click();
    }

    public String getProductPrice(){
        return price.getText();
    }

    public String getProductName(){
        return name.getText();
    }
}