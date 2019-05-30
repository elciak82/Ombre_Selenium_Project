package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.AllProductsPage;
import pages.CartPage;
import pages.HeaderPage;

import java.util.List;
import java.util.Map;

import static helpers.Configuration.getConfiguration;
import static helpers.Driver.initializeWebDriver;

public class CartTests {
    public static final int PRODUCT_INDEX = 0;
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = initializeWebDriver();
        driver.get(getConfiguration().getSiteURL());

    }

    @Test
    public void addProductFromAllProductPageAndCheckCartTest() {
//        CartPage cartPage = new CartPage(driver);
        AllProductsPage allProductsPage = new AllProductsPage(driver);
        allProductsPage.getProductName(PRODUCT_INDEX);
        allProductsPage.getProductPrice(PRODUCT_INDEX);
        allProductsPage.addProductToCart(PRODUCT_INDEX);

        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.openCartPage();

        CartPage cartPage = new CartPage(driver);
        List<Map<CartPage.PRODUCTS_IN_CART_HEADERS, String>> cartPageMap = cartPage.getAllProductsFromCartMap2();
        System.out.println(cartPageMap.get(0).get(CartPage.PRODUCTS_IN_CART_HEADERS.NAME));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
