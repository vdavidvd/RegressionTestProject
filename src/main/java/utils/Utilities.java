package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Utilities {

    @DataProvider
    public Object[][] provideLoginCredentials(){
        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(
                    "src/main/resources/sauceDemoTestData.xlsx");
        } catch (IOException e) {
            e.printStackTrace();
        }
        XSSFSheet sheet = workbook.getSheet("Login");
        int rows = sheet.getLastRowNum();
        int cells = sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[rows][cells];

        for (int r = 1; r <= rows; r++){
            for (int c = 0; c < cells; c++){
                switch (sheet.getRow(r).getCell(c).getCellType()){
                    case STRING:
                        data[r-1][c] = sheet.getRow(r).getCell(c).getStringCellValue();
                        break;
                    case NUMERIC:
                        data[r-1][c] = (int)sheet.getRow(r).getCell(c).getNumericCellValue();
                        break;
                    case BOOLEAN:
                        data[r-1][c] = sheet.getRow(r).getCell(c).getBooleanCellValue();
                        break;
                }
            }
        }
        return data;
    }

    public static ExtentReports generateExtentReport(){
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(
                "ExtentReports/eReport.html");
        sparkReporter.config().setReportName("Swag Labs Automation Test Project");
        sparkReporter.config().setDocumentTitle("Swag Labs | QA");

        ExtentReports extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);

        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream("src/main/java/base/info.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        extentReports.setSystemInfo("Application URL",prop.getProperty("url"));
        extentReports.setSystemInfo("Browser Name",prop.getProperty("browser"));
        extentReports.setSystemInfo("Operating System",
                System.getProperty("os.name"));
        extentReports.setSystemInfo("Operating System Version",
                System.getProperty("os.version"));
        extentReports.setSystemInfo("Selenium Version","4.6.0");
        extentReports.setSystemInfo("Java Version",System.getProperty("java.version"));
        extentReports.setSystemInfo("Tested By","David");
        return extentReports;
    }
}
