package com.Swarmio.Utility;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Helper {

	//screenshots , alerts, frames, windows, sync issues, javascript executor 
	
	public static String captureScreenshots(WebDriver driver) throws IOException
	{
		String Base64StringofScreenshot="";
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		byte[] fileContent = FileUtils.readFileToByteArray(src);
	    Base64StringofScreenshot = "data:image/png;base64,"+Base64.getEncoder().encodeToString(fileContent);
		
		String screenshotPath = System.getProperty("user.dir")+"/Screenshots/Swarmio_"+getCurrentDateTime()+".png";
		
		
		try {
			FileHandler.copy(src, new File(screenshotPath));
			
			System.out.println("Screenshot captured");
			
		} catch (IOException e) {
			
			System.out.println("Unable to capture screenshot"+e.getMessage());
		}
		
		return Base64StringofScreenshot;
	}
	
	
	
	
	
	public static String getCurrentDateTime()
	{
		DateFormat customFormat = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
		Date currentdate = new Date();
		return customFormat.format(currentdate);
	}
	
}
