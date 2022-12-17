package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutStepTwoPage {

    WebDriver driver;

    public CheckoutStepTwoPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "finish")
    private WebElement finishButton;

    @FindBy(xpath = "//div[@class='summary_total_label']")
    private WebElement totalPrice;

    public void clickFinishButton(){
        finishButton.click();
    }

    public double getTotalProductPrice(){
        return Double.parseDouble(totalPrice.getText().replace("Total: $",""));
    }
}
