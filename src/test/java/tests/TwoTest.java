package tests;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import resources.Base;

public class TwoTest extends Base {
	public WebDriver driver;

	Logger logger;
	@Test
	public void twoTest() throws InterruptedException, IOException {
		driver = initializeDriver();
		
		logger = LogManager.getLogger(TwoTest.class.getName());
		
		System.out.println("this is twoTest");
		logger.debug("Log of twoTest");
		
		driver.get("http://tutorialsninja.com/demo");
		Thread.sleep(2000);
		Assert.assertTrue(true);

		/* Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		 String tmsp = timestamp.toString().replace(":","_");
		 System.out.println(tmsp);
		*/
		 
		 /*this is timestamp 
		 Calendar cal = Calendar.getInstance();
         java.util.Date time = cal.getTime();
         String timestamp = time.toString().replace(":","_").replace(" ","-");
         
         System.out.println(time);
         System.out.println(timestamp);
		  */
	
	}
	
	@AfterMethod
	public void closingBrowser() {
		driver.close();
	}
}
