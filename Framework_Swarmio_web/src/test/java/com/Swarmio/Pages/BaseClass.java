package com.Swarmio.Pages;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.Swarmio.Utility.BrowserFactory;
import com.Swarmio.Utility.ConfigDataProvider;
import com.Swarmio.Utility.ExcelDataProvider;
import com.Swarmio.Utility.Helper;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class BaseClass {

	public WebDriver driver;
	public ExcelDataProvider excel;
	public ConfigDataProvider config;
	public ExtentReports report;
	public ExtentTest logger;
	
	
	
	@BeforeSuite
	public void setUpexcelSuite()
	{
		Reporter.log("Setting up reports and test is getting ready", true);
		
		excel = new ExcelDataProvider();
		config = new ConfigDataProvider();
		
		ExtentSparkReporter extent = new ExtentSparkReporter(new File(System.getProperty("user.dir")+"/Reports/Swarmio_"+Helper.getCurrentDateTime()+".html"));
		report = new ExtentReports();
		report.attachReporter(extent);
		
		Reporter.log("Setting Done - Test can be started", true);
		
	}
	
	
	
	@BeforeClass
	public void setup() throws InterruptedException
	{
		Reporter.log("Tring to start Broswser and Getting application ready", true);
		
		driver = BrowserFactory.startApplication(driver,config.getBrowser(),config.getstageURL());
		System.out.println(driver.getTitle());
		
		Reporter.log("Browser and application is up and running ", true);
	}
	
/*	@AfterClass
	public void tearDown()
	{
		BrowserFactory.quitBrowser(driver);
	}
	*/
	
	
	@AfterMethod
	public void tearDownMethod(ITestResult result) throws FileNotFoundException, IOException
	{
		
		Reporter.log("Test is about to end", true);
		
		if(result.getStatus()==ITestResult.FAILURE)
		{
			
			logger.fail("Test Failed", MediaEntityBuilder.createScreenCaptureFromBase64String(Helper.captureScreenshots(driver)).build());
		}
		else if(result.getStatus()== ITestResult.SUCCESS)
		{
			logger.pass("Test Passed", MediaEntityBuilder.createScreenCaptureFromBase64String(Helper.captureScreenshots(driver)).build());

		}
		
		
		report.flush();
		
		Reporter.log("Test completed >> Report Generated", true);
	}
}
