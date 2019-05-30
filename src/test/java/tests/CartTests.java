package tests;

import org.junit.After;
import org.junit.Assert;
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
    public static final int PRODUCT_INDEX_ONE = 5;
    public static final int PRODUCT_INDEX_TWO = 2;
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
        String productName1 = allProductsPage.getProductName(PRODUCT_INDEX_ONE);
        String productPrice1 = allProductsPage.getProductPrice(PRODUCT_INDEX_ONE);
        allProductsPage.addProductToCart(PRODUCT_INDEX_ONE);

        String productName2 = allProductsPage.getProductName(PRODUCT_INDEX_TWO);
        String productPrice2 = allProductsPage.getProductPrice(PRODUCT_INDEX_TWO);
        allProductsPage.addProductToCart(PRODUCT_INDEX_TWO);

        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.openCartPage();

        CartPage cartPage = new CartPage(driver);
        List<Map<CartPage.PRODUCTS_IN_CART_HEADERS, String>> cartPageMap = cartPage.getAllProductsFromCartMap2();

        Assert.assertEquals("Koszyk zawiera inny produkt!", productName1, cartPageMap.get(0).get(CartPage.PRODUCTS_IN_CART_HEADERS.NAME));
        Assert.assertEquals("Koszyk zawiera inny produkt!", productName2,cartPageMap.get(1).get(CartPage.PRODUCTS_IN_CART_HEADERS.NAME) );

        Assert.assertEquals("Cena się nie zgadza!", productPrice1, cartPageMap.get(0).get(CartPage.PRODUCTS_IN_CART_HEADERS.PRICE));
        Assert.assertEquals("Cena się nie zgadza!", productPrice2,cartPageMap.get(1).get(CartPage.PRODUCTS_IN_CART_HEADERS.PRICE) );

//        System.out.println(cartPageMap.get(0).get(CartPage.PRODUCTS_IN_CART_HEADERS.NAME));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
