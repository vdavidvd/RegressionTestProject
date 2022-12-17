package tests;

import base.Base;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

public class LoginPageTest extends Base {

    LoginPage loginPage;
    InventoryPage inventoryPage;

    @Test(priority = 0)
    public void loginWithProperParameters(){
        loginPage = new LoginPage(driver);
        loginPage.login(prop.getProperty("username"),prop.getProperty("password"));

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
    }

    @Test(priority = 1)
    public void loginWithoutAnyParameters(){
        loginPage = new LoginPage(driver);
        loginPage.login("","");
        String expectedText = "Epic sadface: Username is required";

        Assert.assertEquals(loginPage.getLoginErrorMessage(),expectedText);
    }

    @Test(priority = 2)
    public void loginWithOnlyUsername(){
        loginPage = new LoginPage(driver);
        loginPage.login(prop.getProperty("username"),"");
        String expectedText = "Epic sadface: Password is required";

        Assert.assertEquals(loginPage.getLoginErrorMessage(),expectedText);
    }

    @Test(priority = 3)
    public void loginWithOnlyPassword(){
        loginPage = new LoginPage(driver);
        loginPage.login("",prop.getProperty("password"));
        String expectedText = "Epic sadface: Username is required";

        Assert.assertEquals(loginPage.getLoginErrorMessage(),expectedText);
    }

    @Test(priority = 4)
    public void logoutTest(){
        loginPage = new LoginPage(driver);
        loginPage.login(prop.getProperty("username"), prop.getProperty("password"));

        inventoryPage = new InventoryPage(driver);

        driver.findElement(By.id("react-burger-menu-btn")).click();
        driver.findElement(By.id("logout_sidebar_link")).click();

        Assert.assertTrue((loginPage.getUsername().isDisplayed()&&loginPage.getPassword().isDisplayed()));
    }
}
