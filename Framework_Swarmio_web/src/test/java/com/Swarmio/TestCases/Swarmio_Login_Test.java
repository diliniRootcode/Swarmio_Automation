package com.Swarmio.TestCases;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.support.PageFactory;

import com.Swarmio.Pages.BaseClass;
import com.Swarmio.Pages.LoginPage;
import com.Swarmio.Utility.Helper;


public class Swarmio_Login_Test extends BaseClass {
	
	
	@Test(priority = 1 )
	public void CheckIncorrectEmailValidation() throws InterruptedException, FileNotFoundException, IOException
	{
		logger= report.createTest("Login to Swarmio with incorrect email");
		
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		
		loginPage.checkValidationForIncorrectEmail(excel.getStringData("Login", 1, 0));
		
		String emailValidation = loginPage.GetIncorrectEmailValidationtext();
		
		logger.pass("Validation captured - " + emailValidation);
		
		Helper.captureScreenshots(driver);
	}
	
	@Test(priority = 2 )
	public void CheckEmptyPasswordValidation() throws InterruptedException, FileNotFoundException, IOException
	{
		logger= report.createTest("Login to Swarmio without password");
		
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		
		loginPage.checkValidationForEmptyPassword();
		
		String passwordValidation = loginPage.GetEmptyPassswordValidationtext();
		
		logger.pass("Validation captured - " + passwordValidation);
		
		Helper.captureScreenshots(driver);
	}
	
	@Test(priority = 3 )
	public void LogintoApplication() throws InterruptedException, FileNotFoundException, IOException
	{
		
		logger= report.createTest("Login to Swarmio");
		
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		
		logger.info("Starting Application");
		
		loginPage.loginToSwarmio(excel.getStringData("Login", 0, 0), excel.getStringData("Login", 0, 1));
		
		logger.pass("Login success");
		
		
		Helper.captureScreenshots(driver);
	}
}
