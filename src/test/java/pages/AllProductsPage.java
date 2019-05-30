package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AllProductsPage extends GenericPage{
    public AllProductsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    @FindBy(className = "product")
    List<WebElement> products;

    @FindBy(css = "[title=\"add to cart\"]")
    List<WebElement> addToCart;

    @FindBy(className = "name")
    List<WebElement> productName;

    @FindBy(css = "[class=\"price\"] span")
    List<WebElement> productPrice;

    public ProductPage selectProduct(int productIndex){
        products.get(productIndex).click();
        return new ProductPage(driver);
    }

    public void addProductToCart(int productIndex){
        addToCart.get(productIndex).click();
    }

    public String getProductPrice(int productIndex){
//        System.out.println(productPrice.get(productIndex).getText());
        return productPrice.get(productIndex).getText();
    }

    public String getProductName(int productIndex){
//        System.out.println(productName.get(productIndex).getText());
        return productName.get(productIndex).getText();
    }
}