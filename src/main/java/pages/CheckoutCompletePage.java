package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutCompletePage {

    WebDriver driver;

    public CheckoutCompletePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//img[@class='pony_express']")
    private WebElement imageOfCompletion;

    @FindBy(xpath = "//span[@class='title']")
    private WebElement checkoutComplete;

    public WebElement getImageOfCompletion(){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOf(imageOfCompletion));
    }

    public String checkoutCompleteMessage(){
        return checkoutComplete.getText();
    }
}
