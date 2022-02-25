package com.Swarmio.Pages;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoginPage {
	
	WebDriver  driver;
	
			
	public LoginPage(WebDriver ldriver)
	{
		this.driver = ldriver;
		
	}
	
	@FindBy(id="email") WebElement email;
	
	@FindBy(id="password") WebElement password;
	
	@FindBy(id="login") WebElement loginBtn;
	
	@FindBy(className="text-black") List<WebElement> dashbordTitle;
	
	@FindBy(className="auto-login-dialog-btn-yes") WebElement loginYesbtn;
	
	@FindBy(className="text-red-500") List<WebElement> ValidationEmailPasssword;
	
	
	//From here we entering incorrect email and clicking on Login button
	public void checkValidationForIncorrectEmail(String username) throws InterruptedException
	{
		
		Thread.sleep(4000);
		email.clear();
		email.sendKeys(username);
		loginBtn.click();
		Thread.sleep(2000);
		
		
		String actual_msg =  ValidationEmailPasssword.get(0).getText();
		String expect = "Email must be a valid email";

		if (actual_msg.contains(expect)) {
			System.out.println("Validation passed = " + actual_msg);
		} else {
			System.out.println("Validation Failed = " + actual_msg);
		}
	
		Thread.sleep(5000);
	}
	//Getting invalid email validation message to extent report
	public String GetIncorrectEmailValidationtext() throws InterruptedException
	{
		return  ValidationEmailPasssword.get(0).getText();
	}
	
	//From here we checking for empty password validation message 
	public void checkValidationForEmptyPassword() throws InterruptedException
	{
		Thread.sleep(4000);
		loginBtn.click();
		Thread.sleep(2000);
		
		
		String actual_msg =  ValidationEmailPasssword.get(1).getText();
		String expect = "Password cannot be blank";

		if (actual_msg.contains(expect)) {
			System.out.println("Validation passed = " + actual_msg);
		} else {
			System.out.println("Validation Failed = " + actual_msg);
		}
	
		Thread.sleep(5000);
	}
	//Getting empty password validation message to extent report
		public String GetEmptyPassswordValidationtext() throws InterruptedException
		{
			return  ValidationEmailPasssword.get(1).getText();
		}
		
	// This is to login the swarmio with correct email and Password 
	public void loginToSwarmio(String username, String appmakerpassword) throws InterruptedException
	{
		Thread.sleep(4000);
		email.clear();
		email.sendKeys(username);
		password.clear();
		password.sendKeys(appmakerpassword);
		loginBtn.click();
		Thread.sleep(7000);
		
		String actual_msg =  dashbordTitle.get(0).getText();
		String expect = "Dashboard";

		if (actual_msg.contains(expect)) {
			System.out.println("Dashbord Title is  = " + actual_msg);
		} else {
			System.out.println("Dashbord Title is incorrect = " + actual_msg);
		}
		Thread.sleep(30000);
		
	}

}
