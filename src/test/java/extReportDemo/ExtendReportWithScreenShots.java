package extReportDemo;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExtendReportWithScreenShots {
	static WebDriver driver;
	 
	public static String captureScreenshot(String fileName) throws Exception {
		File sourceScreenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		 String screenshotFolderPath=System.getProperty("user.dir")+"\\Screenshots\\"+fileName+".png";
		FileUtils.copyFile(sourceScreenshot, new File(screenshotFolderPath));
		return screenshotFolderPath;
	}
	public static String captureScreenshot2(String testName,WebDriver driver) throws Exception {
		File sourceScreenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File destination=new File(System.getProperty("user.dir")+"\\Screenshots\\"+testName+".png");
		FileUtils.copyFile(sourceScreenshot, destination);
		return destination.getAbsolutePath();
	}
	public static String takeScreenshot() {
		String screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
		return screenshot;
	}
	
	public static void main(String[] args) throws Exception {
		ExtentReports extent=new ExtentReports();
		File file=new File(System.getProperty("user.dir")+"\\ExtendtReports\\eReport.html");
		ExtentSparkReporter sparkReporter=new ExtentSparkReporter(file);
		extent.attachReporter(sparkReporter);
		WebDriverManager.chromedriver().setup();
	    driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://tutorialsninja.com/demo/");
		//String path1 = captureScreenshot("TutorialsNinja");
		//driver.quit();
		
		ExtentTest eTest1 = extent.createTest("TestOne");
		eTest1.log(Status.INFO, "TestOne execution started");
		eTest1.addScreenCaptureFromBase64String(takeScreenshot());
		//eTest1.addScreenCaptureFromPath(path1,"TutorialsNinjaHomePageScreenShot");
		
		driver.findElement(By.name("search")).sendKeys("HP");
		driver.findElement(By.xpath("//span[@class='input-group-btn']/button[@type='button']")).click();
		
		eTest1.log(Status.INFO, "HP typed in search box field ended");
		String path2 = captureScreenshot("HPSearch");
		eTest1.addScreenCaptureFromPath(path2, "HP SEARCH RESULT SCREENSHOT");
		eTest1.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot("NEW"), "LOG LEVEL SCREENSHOT").build());
		extent.flush();
		driver.quit();
		Desktop.getDesktop().browse(file.toURI());
	}
	
}
