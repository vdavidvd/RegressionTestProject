package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class InventoryPage {

    WebDriver driver;

    public InventoryPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//div[@id='inventory_container']//div[@class='inventory_item_label']//a")
    private List<WebElement> productsCaptions;

    @FindBy(xpath = "//div[@id='inventory_container']//div[@class='pricebar']/div")
    private List<WebElement> productsPrices;

    @FindBy(xpath = "//select[@class='product_sort_container']")
    private WebElement selectFunction;

    @FindBy(xpath = "//a[@class='shopping_cart_link']//span")
    private WebElement cartNumberOfProducts;

    @FindBy(xpath = "//div[@class='inventory_item_description']//button")
    private List<WebElement> addToCartButtons;

    @FindBy(xpath = "//a[@class='shopping_cart_link']")
    private WebElement cartButton;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement burgerMenu;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutButton;

    public LoginPage logout(){
        burgerMenu.click();
        logoutButton.click();
        return new LoginPage(driver);
    }

    public void selectOptionFromSortFunctionByVisibleText(String option){
        Select select = new Select(selectFunction);
        select.selectByVisibleText(option);
    }

    public List<WebElement> getProductCaptions(){
        return productsCaptions;
    }

    public List<WebElement> getProductPrices(){
        return productsPrices;
    }

    public WebElement getCartNumberOfProducts(){
        return cartNumberOfProducts;
    }

    public List<WebElement> getAddToCartButtons(){
        return addToCartButtons;
    }

    public WebElement getCartButton(){
        return cartButton;
    }
}