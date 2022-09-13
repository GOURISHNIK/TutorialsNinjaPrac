package listeners;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import resources.Base;
import utilities.ExtentReporter;

public class Listeners extends Base implements ITestListener {
	
	public String concatinate = ".";
	ExtentReports extentReport = ExtentReporter.getExtentReport();
	ExtentTest extentTest;
	ThreadLocal<ExtentTest> extentTestThread = new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {
		extentTest = extentReport.createTest(result.getName());
		//extentTestThread.set(extentTest);

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.pass("passed");
		//extentTestThread.get().pass("passed");

		
		WebDriver driver = null;
		
		String testMethodName = result.getName()+"Passed";
		
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			String screenshotFilePath = takeScreenshot(testMethodName,driver);
			String sccreenshotss = concatinate+screenshotFilePath;
			//extentTestThread.get().addScreenCaptureFromPath("../reports/screenshots/twoTestPassed.png");
			extentTest.addScreenCaptureFromPath("\\reports\\screenshots\\twoTestPassed.png");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		extentTest.fail(result.getThrowable());
		//extentTestThread.get().fail(result.getThrowable());		
		WebDriver driver = null;
		
		String testMethodName = result.getName()+"Failed";
		
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			String screenshotFilePath = takeScreenshot(testMethodName,driver);
			System.out.println(screenshotFilePath);
			String sccreenshotss = concatinate+screenshotFilePath;
			System.out.println(sccreenshotss);
			
			extentTest.addScreenCaptureFromPath(sccreenshotss);
			//extentTestThread.get().addScreenCaptureFromPath(sccreenshotss);
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	
	}

	@Override
	public void onTestSkipped(ITestResult result) {

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {

	}

	@Override
	public void onFinish(ITestContext context) {
		extentReport.flush();
		
	}
	

}
