package api.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener, ISuiteListener {
	
	public ExtentSparkReporter sparkReporter; // for look and feel of your report
	public ExtentReports extent; //for common info like username, OS etc
	public ExtentTest test; //for making entires to the report
	
	String reportName;
	
	public void onStart(ISuite suite) {
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); //timestamp
		reportName = "Test-Report-"+timeStamp+".html";
		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/reports/"+reportName); // location
		sparkReporter.config().setDocumentTitle("API Automation Project");
		sparkReporter.config().setReportName("API Testing");
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "Test Public API");
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Enviornment", "QA");
		extent.setSystemInfo("User", "Roshan");
		
	}
	
	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.PASS, "Test Passed..");	
		
	}
	
	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.FAIL, "Test Failed..");	
		test.log(Status.FAIL, result.getThrowable().getMessage());
		
	}
	
	public void onTestSkipped(ITestResult result) {
		
		test = extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.SKIP, "Test Skipped..");
		test.log(Status.SKIP, result.getThrowable().getMessage());
	}
	
	public void onFinish(ISuite suite) {
		extent.flush();
	}

}
