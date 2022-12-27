package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    WebDriver driver;

    @FindBy(id = "user-name")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(xpath = "//div[@class='error-message-container error']//h3")
    private WebElement errorMessage;

    public String getLoginErrorMessage(){
        return errorMessage.getText();
    }

    public WebElement getUsername(){
        return username;
    }

    public WebElement getPassword(){
        return password;
    }

    public InventoryPage login(String uname, String pwd){
        username.sendKeys(uname);
        password.sendKeys(pwd);
        loginButton.click();
        return new InventoryPage(driver);
    }
}
