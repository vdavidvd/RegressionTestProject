package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.Utilities;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MyListeners implements ITestListener {

    ExtentReports extentReport;
    ExtentTest eTest;

    @Override
    public void onStart(ITestContext context) {
        extentReport = Utilities.generateExtentReport();
    }

    @Override
    public void onTestStart(ITestResult result) {
        eTest = extentReport.
                createTest(result.getInstance().getClass().getSimpleName() + " > "
                + result.getName());
        eTest.log(Status.INFO,result.getName()+" started execution");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        eTest.log(Status.INFO,result.getName()+" execution finished");
        eTest.log(Status.PASS,result.getName()+" got executed successfully");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        if (result.getName().equalsIgnoreCase("validateResetAppStateFunction")){
            eTest.log(Status.INFO,
                    "Test fails because Reset App State Function is not working properly in this version of application.");
        }
        eTest.log(Status.INFO,result.getThrowable());
        eTest.log(Status.INFO,result.getName()+" execution finished");
        eTest.log(Status.FAIL,result.getName()+" got executed failed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        eTest.log(Status.INFO,result.getThrowable());
        eTest.log(Status.INFO,result.getName()+" execution finished");
        eTest.log(Status.SKIP,result.getName()+" got executed skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReport.flush();
        try {
            Desktop.getDesktop().browse(
                    new File("ExtentReports/eReport.html").toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}