package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutStepOnePage {

    WebDriver driver;

    public CheckoutStepOnePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "first-name")
    private WebElement firstName;

    @FindBy(id = "last-name")
    private WebElement lastName;

    @FindBy(id = "postal-code")
    private WebElement zipCode;

    @FindBy(id = "continue")
    private WebElement continueButton;

    public void setCheckoutData(String fname, String lname, String zip){
        firstName.sendKeys(fname);
        lastName.sendKeys(lname);
        zipCode.sendKeys(zip);
        continueButton.click();
    }
}
