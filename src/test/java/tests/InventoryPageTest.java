package tests;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

import java.util.*;

public class InventoryPageTest extends Base {

    LoginPage loginPage;
    InventoryPage inventoryPage;

    @Test(priority = 0)
    public void validatingPriceLowToHigh(){
        loginPage = new LoginPage(driver);
        loginPage.login(prop.getProperty("username"), prop.getProperty("password"));

        inventoryPage = new InventoryPage(driver);

        List<WebElement> originalPrices = inventoryPage.getProductPrices();
        List<Double> originalPricesSorted = new ArrayList<>();
        for (WebElement originalPrice:originalPrices) {
            originalPricesSorted.add(Double.parseDouble(originalPrice.getText().replace("$","")));
        }

        inventoryPage.selectOptionFromSortFunctionByVisibleText(prop.getProperty("priceLow-High"));
        List<Double> sortedPrices = new ArrayList<>();
        for (WebElement sortedPrice:inventoryPage.getProductPrices()) {
            sortedPrices.add(Double.parseDouble(sortedPrice.getText().replace("$","")));
        }

        Collections.sort(originalPricesSorted);
        Assert.assertEquals(sortedPrices.get(0),originalPricesSorted.get(0));
    }

    @Test(priority = 1)
    public void validatingPriceHighToLow(){
        loginPage = new LoginPage(driver);
        loginPage.login(prop.getProperty("username"), prop.getProperty("password"));

        inventoryPage = new InventoryPage(driver);

        List<WebElement> originalPrices = inventoryPage.getProductPrices();
        List<Double> originalPricesSorted = new ArrayList<>();
        for (WebElement originalPrice:originalPrices) {
            originalPricesSorted.add(Double.parseDouble(originalPrice.getText().replace("$","")));
        }

        inventoryPage.selectOptionFromSortFunctionByVisibleText(prop.getProperty("priceHigh-Low"));
        List<Double> sortedPrices = new ArrayList<>();
        for (WebElement sortedPrice:inventoryPage.getProductPrices()) {
            sortedPrices.add(Double.parseDouble(sortedPrice.getText().replace("$","")));
        }

        Collections.sort(originalPricesSorted, Collections.reverseOrder());
        Assert.assertEquals(sortedPrices.get(0),originalPricesSorted.get(0));
    }

    @Test(priority = 2)
    public void validatingSocialAppsLinks() throws InterruptedException {
        loginPage = new LoginPage(driver);
        loginPage.login(prop.getProperty("username"), prop.getProperty("password"));

        inventoryPage = new InventoryPage(driver);
        String originalWindowId = driver.getWindowHandle();

        inventoryPage.twSocialIcon().click();
        inventoryPage.getWinHandlesForLinks();
        Thread.sleep(5000);
        Assert.assertEquals(driver.getTitle(),"Sauce Labs (@saucelabs) / Twitter");

        driver.switchTo().window(originalWindowId);
        inventoryPage.fbSocialIcon().click();
        inventoryPage.getWinHandlesForLinks();

        Thread.sleep(5000);
        Assert.assertEquals(driver.getTitle(),"Sauce Labs | Facebook");

        driver.switchTo().window(originalWindowId);
        inventoryPage.igSocialIcon().click();
        inventoryPage.getWinHandlesForLinks();

        Assert.assertEquals(driver.getTitle(),"Sauce Labs | LinkedIn");
    }

    @Test(priority = 3)
    public void validatingInitialStateOfCart(){
        loginPage = new LoginPage(driver);
        loginPage.login(prop.getProperty("username"), prop.getProperty("password"));

        inventoryPage = new InventoryPage(driver);

        boolean result;
        try {
            result = inventoryPage.getCartNumberOfProducts().isDisplayed();
            Assert.assertTrue(result);
        }catch (Exception ex){
            result = false;
            Assert.assertFalse(result);
        }
    }

    @Test(priority = 4)
    public void validateResetAppStateFunction(){
        loginPage = new LoginPage(driver);
        loginPage.login(prop.getProperty("username"), prop.getProperty("password"));

        inventoryPage = new InventoryPage(driver);

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

    @Test(priority = 5)
    public void validateProductsDetails() {
        loginPage = new LoginPage(driver);
        loginPage.login(prop.getProperty("username"), prop.getProperty("password"));

        inventoryPage = new InventoryPage(driver);

        List<String> list = new ArrayList<>();
        for (WebElement el:inventoryPage.getProductPrices()) {
            list.add(el.getText());
        }
        int i = 0;

        for (WebElement el:inventoryPage.getProductCaptions()) {
            if (i == 0){
                el.click();
                Assert.assertEquals(driver.findElement(By.xpath("//div[@class='inventory_details_price']")).getText(),
                        list.get(i));
                i++;
            }else {
                inventoryPage.getProductCaptions().get(i).click();
                Assert.assertEquals(driver.findElement(By.xpath("//div[@class='inventory_details_price']")).getText(),
                        list.get(i));
                i++;
            }
            driver.findElement(By.id("back-to-products")).click();
        }
    }
}
