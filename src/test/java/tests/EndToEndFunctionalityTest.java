package tests;

import base.Base;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class EndToEndFunctionalityTest extends Base {

    LoginPage loginPage;
    InventoryPage inventoryPage;
    CartPage cartPage;
    CheckoutStepOnePage checkoutStepOnePage;
    CheckoutStepTwoPage checkoutStepTwoPage;
    CheckoutCompletePage checkoutCompletePage;

    @Test
    public void endToEndTest(){
        loginPage = new LoginPage(driver);
        loginPage.login(prop.getProperty("username"), prop.getProperty("password"));

        inventoryPage = new InventoryPage(driver);

        inventoryPage.getAddToCartButtons().get(0).click();
        inventoryPage.getCartButton().click();

        cartPage = new CartPage(driver);
        double expectedPrice = cartPage.getProductPrice();
        cartPage.getCheckoutButton().click();

        checkoutStepOnePage = new CheckoutStepOnePage(driver);
        checkoutStepOnePage.setCheckoutData(prop.getProperty("firstName")
                ,prop.getProperty("lastName"),
                prop.getProperty("zip"));

        checkoutStepTwoPage = new CheckoutStepTwoPage(driver);
        Assert.assertEquals(checkoutStepTwoPage.getTotalProductPrice(),
                expectedPrice+2.40);
        checkoutStepTwoPage.clickFinishButton();

        checkoutCompletePage = new CheckoutCompletePage(driver);
        Assert.assertTrue(checkoutCompletePage.getImageOfCompletion().isDisplayed());

        Assert.assertEquals(checkoutCompletePage.checkoutCompleteMessage().toLowerCase()
                ,"Checkout: Complete!".toLowerCase());
    }
}