package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

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

    @FindBy(xpath = "//a[.='Twitter']")
    private WebElement twSocialAppIcon;

    @FindBy(xpath = "//a[.='Facebook']")
    private WebElement fbSocialAppIcon;

    @FindBy(xpath = "//a[.='LinkedIn']")
    private WebElement igSocialAppIcon;

    @FindBy(xpath = "//a[@class='shopping_cart_link']//span")
    private WebElement cartNumberOfProducts;

    @FindBy(xpath = "//div[@class='inventory_item_description']//button")
    private List<WebElement> addToCartButtons;

    @FindBy(xpath = "//a[@class='shopping_cart_link']")
    private WebElement cartButton;

    public void getWinHandlesForLinks(){
        Set<String> winIds = driver.getWindowHandles();
        Iterator<String> itr = winIds.iterator();
        while (itr.hasNext()){
            driver.switchTo().window(itr.next());
        }
    }

    public void selectOptionFromSortFunctionByVisibleText(String option){
        Select select = new Select(selectFunction);
        select.selectByVisibleText(option);
    }

    public WebElement twSocialIcon(){
        return twSocialAppIcon;
    }

    public WebElement fbSocialIcon(){
        return fbSocialAppIcon;
    }

    public WebElement igSocialIcon(){
        return igSocialAppIcon;
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
