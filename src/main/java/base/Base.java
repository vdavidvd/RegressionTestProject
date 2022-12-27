package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

public class Base {

    public Properties prop;
    public WebDriver driver;

    @BeforeMethod
    public void setup(){
        prop = new Properties();
        try {
            prop.load(new FileInputStream("src/main/java/base/info.properties"));
        }catch (Exception ex){

        }
        if (prop.getProperty("browser").equalsIgnoreCase("Chrome")){
            driver = new ChromeDriver();
        }else if (prop.getProperty("browser").equalsIgnoreCase("Firefox")){
            driver = new FirefoxDriver();
        }else if (prop.getProperty("browser").equalsIgnoreCase("Edge")){
            driver = new EdgeDriver();
        }

        driver.manage().window().maximize();
        driver.get(prop.getProperty("url"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}