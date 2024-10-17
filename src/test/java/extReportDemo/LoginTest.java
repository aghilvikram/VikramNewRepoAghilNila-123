package extReportDemo;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
//hi vikram new branch


public class LoginTest  {

	
//new comment
	WebDriver driver;

	@BeforeMethod
	public void setUp() throws Throwable {
		WebDriverManager.chromedriver().setup();
	    driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://tutorialsninja.com/demo/");
		driver.findElement(By.xpath("//span[@class='caret']")).click();
		driver.findElement(By.linkText("Login")).click();
	}

	@AfterMethod
	//ne more comment
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifyLoginWithValidCredentials() {
		 driver.findElement(By.id("input-email")).sendKeys("vprvikram@gmail.com");
		 driver.findElement(By.id("input-password")).sendKeys("Vikramaghil777@");
		 driver.findElement(By.xpath("//input[@type='submit']")).click();
		 Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());

	}

	@Test(priority = 2)
	public void verifyWithInvalidCredentials() {
		  driver.findElement(By.id("input-email")).sendKeys("vprviam@gmail.com");
		  driver.findElement(By.id("input-password")).sendKeys("ikramaghil777@");
		  driver.findElement(By.xpath("//input[@type='submit']")).click(); String
		  actualWarningMessage = driver.findElement(By.
		  xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
		  String expectedWarningMessage = "E-Mail Address and/or Password.";
			Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),
					"Expected warning message is not equal to actual one");

	}

	@Test(priority = 3, dependsOnMethods= {"verifyWithInvalidCredentials"})
	public void verifyWithValidPaaswordInvalidEmail() {
		
		driver.findElement(By.id("input-email")).sendKeys("vprviam@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys("Vikramaghil777@");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		String actualWarningMessage = driver
				.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
		String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),
				"Expected warning message is not equal to actual one");
		 

	}

	
}
