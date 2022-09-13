package tests;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.annotation.JsonAppend.Prop;

import pageobjects.AccountPage;
import pageobjects.LandingPage;
import pageobjects.LoginPage;
import resources.Base;

public class LoginTest extends Base {
	
	public WebDriver driver;
	String actualMsg;
	Logger logger;
	
	@Test(dataProvider = "getData")
	public void loginTest(String email,String password, String expectedMsg) throws InterruptedException {
		
		LandingPage landingPage = new LandingPage(driver);
		landingPage.myAccountDropdown().click();
		Thread.sleep(2000);
		landingPage.loginOption().click();
		Thread.sleep(2000);
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.emailField().sendKeys(email);
		loginPage.passwordField().sendKeys(password);
		loginPage.loginButton().click();
		
		AccountPage accountPage = new AccountPage(driver);
		
		try {
			if(accountPage.editOptionAvailable().isDisplayed())
			actualMsg = "Successfull";
		}catch (Exception e) {
			actualMsg = "Invalid";
		}
		
		Assert.assertEquals(actualMsg,expectedMsg);
	}
	
	@DataProvider
	public Object[][] getData() {
		Object[][] data = {{"arun.selenium11@gmail.com","Second@123","Successfull"}};
		return data;
		}
		
		
	@BeforeMethod
	public void setUp() throws IOException, InterruptedException {
		
		logger = LogManager.getLogger(LoginTest.class.getName());
		driver = initializeDriver();		
		logger.debug("browser launced");
		driver.get(prop.getProperty("url"));
		logger.debug("url opened");
		
		
		logger.debug("checking for Broken links and printing to file");
		
		//brokennLinks();
	      //Date d = new Date(0);
	      //String LinksUpdate = d.toString().replace(":", "_").replace(" ", "_") + ".txt";
/*		String timestamp = new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss").format(new Date(0));

		
        File f1 = new File(System.getProperty("user.dir")+"\\broken-links\\"+timestamp+"_linksStatus.txt");
        FileWriter fw = new FileWriter(f1);
        BufferedWriter bw = new BufferedWriter(fw);
              
        HttpURLConnection huc = null;
        int respCode = 200;

        List<WebElement> links = driver.findElements(By.tagName("a"));
        
        Iterator<WebElement> it = links.iterator();
        
        while(it.hasNext()){
            
            //String url = it.next().getAttribute("href");
            WebElement urlLink = it.next();
            String url = urlLink.getAttribute("href");
            String linkName = urlLink.getText();
        
            if(url == null || url.isEmpty()){
            	System.out.println("URL is either not configured for anchor tag or it is empty");
                continue;
            }
            
            try {
                huc = (HttpURLConnection)(new URL(url).openConnection());
                
                huc.setRequestMethod("HEAD");
                
                huc.connect();
                
                respCode = huc.getResponseCode();
                
                if(respCode >= 400){
                	//System.out.println(); 
                	//System.out.println(linkName+" link");
                    //System.out.println(url+" is a broken link");
                	bw.write(linkName+" link ");
                	bw.newLine();
                	bw.write(url+" is a broken link");
                   	
                }
                else{
                	//System.out.println();
                	//System.out.println(linkName+" link");
                    //System.out.println(url+" is a valid link");
                	bw.write(linkName+" link ");
                	bw.newLine();
                	bw.write(url+" is a valid link");
                	bw.newLine();
                	bw.newLine();
                    
                }
                    
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
        bw.close();*/
        logger.debug("Check complete for Broken links and printed to file > Links_Update.txt");
        

        
	}
	
	@AfterMethod
	public void tearDown() throws InterruptedException {
		Thread.sleep(2000);
		driver.quit();
	}
}
