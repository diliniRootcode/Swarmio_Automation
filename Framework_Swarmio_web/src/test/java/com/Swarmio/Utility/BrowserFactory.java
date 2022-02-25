package com.Swarmio.Utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory {
	
	
	
	public static WebDriver startApplication(WebDriver driver, String browserName, String appURL) throws InterruptedException
	{
		if(browserName.contentEquals("Chrome"))
		{
			
			System.setProperty("webdriver.chrome.driver","./Drivers/chromedriver.exe");
			driver = new ChromeDriver();
			
		}
		else if(browserName.equals("Firefox"))
		{
			System.setProperty("webdriver.chrome.driver","./Drivers/geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if(browserName.equals("IE"))
		{
			
		}
		else
		{
			System.out.println("We do not support this browser");
		}

		driver.manage().window().maximize();
		driver.get(appURL);
		Thread.sleep(10000);
		return driver;
	}
	
	public static void quitBrowser(WebDriver driver)
	{
		driver.quit();
	}

}
