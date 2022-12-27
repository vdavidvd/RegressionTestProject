package tests;

import base.Base;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;
import utils.Utilities;

public class LoginPageTest extends Base {

    LoginPage loginPage;
    InventoryPage inventoryPage;

    @Test(priority = 0,dataProvider = "provideLoginCredentials",
            dataProviderClass = Utilities.class)
    public void loginWithProperParameters(String username, String password){
        loginPage = new LoginPage(driver);
        loginPage.login(username,password);

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
    }

    @Test(priority = 1)
    public void loginWithoutAnyParameters(){
        loginPage = new LoginPage(driver);
        loginPage.login("","");

        Assert.assertEquals(loginPage.getLoginErrorMessage(),
                prop.getProperty("invalidEmailLoginErrorMessage"));
    }

    @Test(priority = 2)
    public void loginWithOnlyUsername(){
        loginPage = new LoginPage(driver);
        loginPage.login(prop.getProperty("username"),"");

        Assert.assertEquals(loginPage.getLoginErrorMessage(),
                prop.getProperty("invalidPasswordLoginErrorMessage"));
    }

    @Test(priority = 3)
    public void loginWithOnlyPassword(){
        loginPage = new LoginPage(driver);
        loginPage.login("",prop.getProperty("password"));

        Assert.assertEquals(loginPage.getLoginErrorMessage(),
                prop.getProperty("invalidEmailLoginErrorMessage"));
    }

    @Test(priority = 4)
    public void logoutTest(){
        loginPage = new LoginPage(driver);
        inventoryPage = loginPage.login(prop.getProperty("username")
                , prop.getProperty("password"));
        loginPage = inventoryPage.logout();

        Assert.assertTrue((loginPage.getUsername().isDisplayed()&&
                loginPage.getPassword().isDisplayed()));
    }
}