package tests;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;
import pages.ProductDetailsPage;

import java.util.*;

public class InventoryPageTest extends Base {

    LoginPage loginPage;
    InventoryPage inventoryPage;
    ProductDetailsPage productDetailsPage;

    @Test(priority = 0)
    public void validatingPriceLowToHigh(){
        loginPage = new LoginPage(driver);
        inventoryPage = loginPage.login(prop.getProperty("username"),
                prop.getProperty("password"));

        List<WebElement> originalPrices = inventoryPage.getProductPrices();
        List<Double> expectedPricesSorted = new ArrayList<>();
        for (WebElement originalPrice:originalPrices) {
            expectedPricesSorted.add(Double.parseDouble(originalPrice.getText()
                    .replace("$","")));
        }

        inventoryPage.selectOptionFromSortFunctionByVisibleText(
                prop.getProperty("priceLow-High"));
        List<Double> actualSortedPrices = new ArrayList<>();
        for (WebElement sortedPrice:inventoryPage.getProductPrices()) {
            actualSortedPrices.add(Double.parseDouble(sortedPrice.getText()
                    .replace("$","")));
        }

        Collections.sort(expectedPricesSorted);
        Assert.assertTrue(expectedPricesSorted.equals(actualSortedPrices));
    }

    @Test(priority = 1)
    public void validatingPriceHighToLow(){
        loginPage = new LoginPage(driver);
        inventoryPage = loginPage.login(prop.getProperty("username"),
                prop.getProperty("password"));

        List<WebElement> originalPrices = inventoryPage.getProductPrices();
        List<Double> expectedPricesSorted = new ArrayList<>();
        for (WebElement originalPrice:originalPrices) {
            expectedPricesSorted.add(Double.parseDouble(originalPrice.getText()
                    .replace("$","")));
        }

        inventoryPage.selectOptionFromSortFunctionByVisibleText(
                prop.getProperty("priceHigh-Low"));
        List<Double> actualSortedPrices = new ArrayList<>();
        for (WebElement sortedPrice:inventoryPage.getProductPrices()) {
            actualSortedPrices.add(Double.parseDouble(sortedPrice.getText()
                    .replace("$","")));
        }

        Collections.sort(expectedPricesSorted, Collections.reverseOrder());
        Assert.assertTrue(expectedPricesSorted.equals(actualSortedPrices));
    }

    @Test(priority = 2)
    public void validatingInitialStateOfCart(){
        loginPage = new LoginPage(driver);
        inventoryPage = loginPage.login(prop.getProperty("username"),
                prop.getProperty("password"));

        boolean result;
        try {
            result = inventoryPage.getCartNumberOfProducts().isDisplayed();
            Assert.assertTrue(result);
        }catch (Exception ex){
            result = false;
            Assert.assertFalse(result);
        }
    }

    @Test(priority = 3)
    public void validateResetAppStateFunction(){
        loginPage = new LoginPage(driver);
        inventoryPage = loginPage.login(prop.getProperty("username"),
                prop.getProperty("password"));

        for (WebElement product:inventoryPage.getAddToCartButtons()) {
            product.click();
        }
        driver.findElement(By.id("react-burger-menu-btn")).click();
        driver.findElement(By.xpath("//a[.='Reset App State']")).click();
        driver.findElement(By.id("react-burger-cross-btn")).click();

        for (WebElement product:inventoryPage.getAddToCartButtons()) {
            //Bug - app is not resting from remove to add to cart button
            Assert.assertEquals(product.getText(),"Add to cart");
        }
    }

    @Test(priority = 4)
    public void validateProductsDetails() {
        loginPage = new LoginPage(driver);
        inventoryPage = loginPage.login(prop.getProperty("username"),
                prop.getProperty("password"));

        List<String> list = new ArrayList<>();
        for (WebElement el:inventoryPage.getProductPrices()) {
            list.add(el.getText());
        }
        int i = 0;

        for (WebElement el:inventoryPage.getProductCaptions()) {
            if (i == 0){
                el.click();
                productDetailsPage = new ProductDetailsPage(driver);
                Assert.assertEquals(productDetailsPage.getProductPrice(),
                        list.get(i));
                i++;
              }else {
                inventoryPage.getProductCaptions().get(i).click();
                Assert.assertEquals(productDetailsPage.getProductPrice(),
                        list.get(i));
                i++;
            }
            productDetailsPage.clickOnBackToProductsButton();
        }
    }
}