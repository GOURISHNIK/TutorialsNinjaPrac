package utilities;

import java.sql.Timestamp;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporter {

	static ExtentReports extentReport;
	
	public static ExtentReports getExtentReport() {
		
		/* Timestamp timestamp1 = new Timestamp(System.currentTimeMillis());
		 String tmsp1 = timestamp1.toString().replace(":","_");
		 System.out.println(tmsp1);*/
		 

		//String extentReportPath =System.getProperty("user.dir")+"\\reports\\extentreport.html";
		String extentReportPath = "G:\\SeleniumTrainingWorkspace\\TutorialsNinjaPrac\\reports\\extentreport.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(extentReportPath);
		reporter.config().setReportName("ReportName");
		reporter.config().setDocumentTitle("Test Results");
		
		extentReport = new ExtentReports();
		extentReport.attachReporter(reporter);
		extentReport.setSystemInfo("Operating System","Windows 10");
		extentReport.setSystemInfo("Tested By","Nikhil K");
		
		return extentReport;
		
	}
}
