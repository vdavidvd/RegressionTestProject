package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailsPage {

    WebDriver driver;

    public ProductDetailsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='inventory_details_price']")
    private WebElement productPrice;

    @FindBy(xpath = "//button[@id='back-to-products']")
    private WebElement backToProductsButton;

    public String getProductPrice(){
        return productPrice.getText();
    }

    public InventoryPage clickOnBackToProductsButton(){
        backToProductsButton.click();
        return new InventoryPage(driver);
    }
}