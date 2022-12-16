package Utilities;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportGenerator {
	public static ExtentReports getExtentReport() {
		ExtentReports extentReport=new ExtentReports();
		File extentReportFile=new File(System.getProperty("user.dir")+"\\extentReports\\reports.html");
		ExtentSparkReporter sparkReporter=new ExtentSparkReporter(extentReportFile);
		extentReport.attachReporter(sparkReporter);
		return extentReport;
		
	}

}
