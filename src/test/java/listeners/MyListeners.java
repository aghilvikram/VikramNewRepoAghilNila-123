package listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Utilities.ExtentReportGenerator;
import extReportDemo.ExtendReportWithScreenShots;

public class MyListeners extends ExtendReportWithScreenShots implements ITestListener {
	ExtentReports report=ExtentReportGenerator.getExtentReport();
	ExtentTest etest;

	public void onTestStart(ITestResult result) {
		String testName = result.getName();
	    etest = report.createTest(testName);
	    etest.log(Status.INFO, testName+"Test Started");
		
	}

	public void onTestSuccess(ITestResult result) {
		String testName = result.getName();
		 etest.log(Status.PASS, testName+"Test Success");
	}

	public void onTestFailure(ITestResult result) {
		String testName = result.getName();
		 etest.log(Status.FAIL, testName+"Test Failed");
		 WebDriver driver = null;
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchFieldException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 
			try {
				etest.addScreenCaptureFromPath(captureScreenshot2(testName,driver),testName);
				etest.log(Status.INFO, result.getThrowable());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	public void onTestSkipped(ITestResult result) {
		String testName = result.getName();
		 etest.log(Status.SKIP, testName+"Test Skipped");
	}

	public void onFinish(ITestContext context) {
		report.flush();
		File ereportFile=new File(System.getProperty("user.dir")+"\\extentReports\\reports.html");
		try {
			Desktop.getDesktop().browse(ereportFile.toURI());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

}
