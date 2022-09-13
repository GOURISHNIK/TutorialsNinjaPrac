package resources;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;


public class Base {
	
	public WebDriver driver;
	public Properties prop;
	
	 
	public WebDriver initializeDriver() throws IOException {
		
		prop = new Properties();
		String propPath = System.getProperty("user.dir")+"\\src\\main\\java\\resources\\data.properties";
		FileInputStream fis = new FileInputStream(propPath);
		
		prop.load(fis);
		String browserName = prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome")) {
			
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		
		}else if(browserName.equalsIgnoreCase("firefox")){
			
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			
		}else if (browserName.equalsIgnoreCase("IE")) {
		
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		
		
		return driver;
	}

	public String takeScreenshot(String testName,WebDriver driver) throws IOException {
		/* Timestamp timestamp1 = new Timestamp(System.currentTimeMillis());
		 String tmsp1 = timestamp1.toString().replace(":","_");
		 System.out.println(tmsp1);*/
		
		File SourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destinationFilePath = "./reports/screenshots/"+testName+".png";
		FileUtils.copyFile(SourceFile,new File(destinationFilePath));
		
		return destinationFilePath;
	
	}
	


	public void brokennLinks() throws IOException {
		
		/*//creates a timestamp 
		 Calendar cal = Calendar.getInstance();
         java.util.Date time = cal.getTime();
         String timestamp = time.toString().replace(":","_").replace(" ","-");
         //done creating timestamp*/
		
		 Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		 String tmsp = timestamp.toString().replace(":","_");
		 System.out.println(tmsp);

		
        File f1 = new File(System.getProperty("user.dir")+"\\broken-links\\"+tmsp+"_linksStatus.txt");
        FileWriter fw = new FileWriter(f1);
        BufferedWriter bw = new BufferedWriter(fw);
              
        HttpURLConnection huc = null;
        int respCode = 200;

        List<WebElement> links = driver.findElements(By.tagName("a"));
        
        Iterator<WebElement> it = links.iterator();
        
        while(it.hasNext()){
            
            //String url = it.next().getAttribute("href");
            WebElement urlLink = it.next();
            String url12 = urlLink.getAttribute("href");
            String linkName = urlLink.getText();
        
            if(url12 == null || url12.isEmpty()){
            	System.out.println("URL is either not configured for anchor tag or it is empty");
                continue;
            }
            
            try {
                huc = (HttpURLConnection)(new URL(url12).openConnection());
                
                huc.setRequestMethod("HEAD");
                
                huc.connect();
                
                respCode = huc.getResponseCode();
                
                if(respCode >= 400){
                	//System.out.println(); 
                	//System.out.println(linkName+" link");
                    //System.out.println(url+" is a broken link");
                	bw.write(linkName+" link ");
                	bw.newLine();
                	bw.write(url12+" is a broken link");
                   	
                }
                else{
                	//System.out.println();
                	//System.out.println(linkName+" link");
                    //System.out.println(url+" is a valid link");
                	bw.write(linkName+" link ");
                	bw.newLine();
                	bw.write(url12+" is a valid link");
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
        
        bw.close();
		
		
	}
	
}
