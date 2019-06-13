package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartPage extends GenericPage{

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    @FindBy (css = "remove from cart")
    WebElement trash;

    @FindBy (css = "tbody td")
    List<WebElement> productData;

    @FindBy (css = "thead th")
    List<WebElement> productHeaders;


    public enum PRODUCTS_IN_CART_HEADERS{
        ID,	NAME, PRICE, QUANTITY, TOTAL
    }

    private List<List<String>> getAllProductsFromCartList(){
        List<WebElement> webElementsProductsInCartRows = new ArrayList<>(driver.findElement(By.tagName("tbody")).findElements(By.tagName("tr")));
        List<List<String>> stringsProductsInCart = new ArrayList<>();

        for (WebElement row: webElementsProductsInCartRows){
            List<String> stringProductInCart = new ArrayList<>();
            for(WebElement cell: row.findElements(By.tagName("td"))){
                List<WebElement> inputs = cell.findElements(By.tagName("input"));
                if(inputs.size()>0){
                    stringProductInCart.add(inputs.get(0).getAttribute("value"));
                } else {
                    stringProductInCart.add(cell.getText());
                }
            }
            stringsProductsInCart.add(stringProductInCart);
        }
        System.out.println(stringsProductsInCart);
        return stringsProductsInCart;
    }

    WebElement findElementByTagName (String elementTagName){
        return driver.findElement(By.tagName(elementTagName));
    }

    WebElement findElementByCss (String elementCss) {
        return driver.findElement(By.cssSelector(elementCss));
    }

    public Map<Integer, Map<PRODUCTS_IN_CART_HEADERS, String>> getAllProductsFromCartMap_DRAFT(){
        Map<Integer, Map<PRODUCTS_IN_CART_HEADERS, String>> allProductsFromCartMap = new HashMap<>();
        List<List<String>> productDataList = getAllProductsFromCartList();

        if (productDataList.size() > 0) {
            for (int i = 0; i < productDataList.size(); i++) {
                Map<PRODUCTS_IN_CART_HEADERS, String> productsFromCartMap = new HashMap<>();
                productsFromCartMap.put(PRODUCTS_IN_CART_HEADERS.ID, productDataList.get(i).get(0));
                productsFromCartMap.put(PRODUCTS_IN_CART_HEADERS.NAME, productDataList.get(i).get(1));
                productsFromCartMap.put(PRODUCTS_IN_CART_HEADERS.PRICE, productDataList.get(i).get(2));
                productsFromCartMap.put(PRODUCTS_IN_CART_HEADERS.QUANTITY, productDataList.get(i).get(3));
                productsFromCartMap.put(PRODUCTS_IN_CART_HEADERS.TOTAL, productDataList.get(i).get(4));
                allProductsFromCartMap.put(i, productsFromCartMap);
            }
        }
        System.out.println(allProductsFromCartMap);
        return allProductsFromCartMap;
    }

    public List<Map<PRODUCTS_IN_CART_HEADERS, String>> getAllProductsFromCartMap(){
        List<Map<PRODUCTS_IN_CART_HEADERS, String>> allProductsFromCartMap = new ArrayList<>();
        List<WebElement> webElementsProductsInCartRows = new ArrayList<>(driver.findElement(By.tagName("tbody")).findElements(By.tagName("tr")));

        for (WebElement row: webElementsProductsInCartRows){
            Map<PRODUCTS_IN_CART_HEADERS, String> productsFromCartMap = new HashMap<>();
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if(cells.size()>0) {
                productsFromCartMap.put(PRODUCTS_IN_CART_HEADERS.ID, cells.get(0).getText());
                productsFromCartMap.put(PRODUCTS_IN_CART_HEADERS.NAME, cells.get(1).getText());
                productsFromCartMap.put(PRODUCTS_IN_CART_HEADERS.PRICE, cells.get(2).getText());
                productsFromCartMap.put(PRODUCTS_IN_CART_HEADERS.QUANTITY, cells.get(3).findElement(By.tagName("input")).getAttribute("value"));
                productsFromCartMap.put(PRODUCTS_IN_CART_HEADERS.TOTAL, cells.get(4).getText());
                allProductsFromCartMap.add(productsFromCartMap);
            }
        }
//        System.out.println(allProductsFromCartMap);
        return allProductsFromCartMap;
    }
}

