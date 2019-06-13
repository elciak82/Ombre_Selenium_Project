package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.AllProductsPage;
import pages.CartPage;
import pages.HeaderPage;
import pages.ProductPage;

import java.util.List;
import java.util.Map;

import static helpers.Configuration.getConfiguration;
import static helpers.Driver.initializeWebDriver;

public class CartTests {
    private static final int FIRST_PRODUCT = 0;
    private static final int SECOND_PRODUCT = 1;
    private static final int NUMBER_OF_PRODUCTS = 3;
    private AllProductsPage allProductsPage;
    private WebDriver driver;
    private HeaderPage headerPage;
    private CartPage cartPage;
    private ProductPage productPage;
    private List<Map<CartPage.PRODUCTS_IN_CART_HEADERS, String>> cartPageMap;

    @Before
    public void setUp() {
        driver = initializeWebDriver();
        driver.get(getConfiguration().getSiteURL());
        this.allProductsPage = new AllProductsPage(driver);
        this.headerPage = new HeaderPage(driver);
        this.cartPage = new CartPage(driver);
        this.productPage = new ProductPage(driver);
    }

    @Test
    public void addTwoProductsFromAllProductPageAndCheckCartTest() {
        String firstProductName = allProductsPage.getProductName(FIRST_PRODUCT);
        String secondProductName = allProductsPage.getProductPrice(FIRST_PRODUCT);

        String productName2 = allProductsPage.getProductName(SECOND_PRODUCT);
        String productPrice2 = allProductsPage.getProductPrice(SECOND_PRODUCT);
        allProductsPage.addProductToCart(FIRST_PRODUCT).addProductToCart(SECOND_PRODUCT);

        headerPage.openCartPage();
        cartPageMap = cartPage.getAllProductsFromCartMap();

        Assert.assertEquals("Product name isn't equal!", firstProductName, cartPageMap.get(0).get(CartPage.PRODUCTS_IN_CART_HEADERS.NAME));
        Assert.assertEquals("Product name isn't equal!", productName2,cartPageMap.get(1).get(CartPage.PRODUCTS_IN_CART_HEADERS.NAME) );

        Assert.assertEquals("Price doesn't match!", secondProductName, cartPageMap.get(0).get(CartPage.PRODUCTS_IN_CART_HEADERS.PRICE));
        Assert.assertEquals("Price doesn't match!", productPrice2,cartPageMap.get(1).get(CartPage.PRODUCTS_IN_CART_HEADERS.PRICE) );
    }

    @Test
    public void addProductFromProductPageAndCheckCartTest(){
        allProductsPage.selectProduct(FIRST_PRODUCT);
        String productName = productPage.getProductName();
        String productPrice = productPage.getProductPrice();
        productPage.addProductToCart();

        headerPage.openCartPage();
        cartPageMap = cartPage.getAllProductsFromCartMap();

        Assert.assertEquals("Product name isn't equal!", productName, cartPageMap.get(0).get(CartPage.PRODUCTS_IN_CART_HEADERS.NAME));
        Assert.assertEquals("Price doesn't match!", productPrice, cartPageMap.get(0).get(CartPage.PRODUCTS_IN_CART_HEADERS.PRICE));
    }

    @Test
    public void checkQuantityAndTotalPriceInCartTest(){
        int productPrice = Integer.parseInt(allProductsPage.getProductPrice(SECOND_PRODUCT));
        String allProductsPrice = Integer.toString(productPrice* NUMBER_OF_PRODUCTS);
        allProductsPage.addProductsToCart(NUMBER_OF_PRODUCTS, SECOND_PRODUCT);

        headerPage.openCartPage();
        cartPageMap = cartPage.getAllProductsFromCartMap();

        Assert.assertEquals("Total doesn't match!", allProductsPrice, cartPageMap.get(0).get(CartPage.PRODUCTS_IN_CART_HEADERS.TOTAL));
        Assert.assertEquals("Quantity doesn't match!", Integer.toString(NUMBER_OF_PRODUCTS), cartPageMap.get(0).get(CartPage.PRODUCTS_IN_CART_HEADERS.QUANTITY));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
