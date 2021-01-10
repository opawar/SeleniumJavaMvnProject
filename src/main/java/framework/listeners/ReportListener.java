package framework.listeners;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import framework.bo.ReportBO;
import framework.utilities.CommonUtils;

public class ReportListener implements IReporter {
	
	private String passed = "PASSED";
	private String failed = "FAILED";
	private String skipped = "SKIPPED";
	private String reportPath = "./output/extent.html";
	
	Logger logger = LogManager.getLogger(ReportListener.class);

	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		logger.info("----------------generateReport----------------");
		
		List<ReportBO> reportBoList = new ArrayList<ReportBO>();
		
		Iterator<ISuite> itr = suites.iterator();
		
		while(itr.hasNext()) {
			ISuite suite = itr.next();
			for (Map.Entry<String, ISuiteResult> resultMap : suite.getResults().entrySet()) {
				IResultMap passedTests = resultMap.getValue().getTestContext().getPassedTests();
				IResultMap failedTests = resultMap.getValue().getTestContext().getFailedTests();
				IResultMap skippedTests = resultMap.getValue().getTestContext().getSkippedTests();
				
				for (ITestResult result : passedTests.getAllResults()) {
					reportBoList.add(generateReportBO(result));
				}
				
				for (ITestResult result : failedTests.getAllResults()) {
					reportBoList.add(generateReportBO(result));
				}
				
				for (ITestResult result : skippedTests.getAllResults()) {
					reportBoList.add(generateReportBO(result));
				}
			}
		}
		
		createExtentReport(reportBoList);
		logger.info("Report generation completed!!!");
		
		try {
			insertResultsToDb(reportBoList);
			logger.info("Added test results to db!!!");
		}
		catch(Exception e) {
			logger.info("Error while adding test results to db");
			e.printStackTrace();
		}
	}
	
	public ReportBO generateReportBO(ITestResult result) {
		ReportBO reportBO = new ReportBO();
		
		reportBO.setTestCaseName(result.getMethod().getMethodName());
		reportBO.setParameters(Arrays.toString(result.getParameters()));
		reportBO.setDescription(result.getMethod().getDescription());
		reportBO.setModule(result.getInstanceName());
		reportBO.setStartTime(CommonUtils.convertMillisecondsToDateFormat(result.getStartMillis()));
		reportBO.setEndTime(CommonUtils.convertMillisecondsToDateFormat(result.getEndMillis()));
		
		if(result.getStatus() != 1) {
			String stacktrace = CommonUtils.getFormattedStacktraceAsString(result.getThrowable(), "\n", "<br>");
			reportBO.setStacktrace(stacktrace);
			
			if(result.getStatus() == 2) {
				reportBO.setStatus(failed);
			}
			else {
				reportBO.setStatus(skipped);
			}
		}
		else {
			reportBO.setStatus(passed);
		}
		
		return reportBO;
	}
	
	public void createExtentReport(List<ReportBO> reportBoList) {
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
		ExtentReports reporter = new ExtentReports();
		reporter.attachReporter(sparkReporter);
		
		for (ReportBO reportBO : reportBoList) {
			addExtentTest(reporter, reportBO);
		}
	}
	
	public void addExtentTest(ExtentReports reporter, ReportBO reportBO) {
		String msg = getReportMessage("Description", reportBO.getDescription())
		+ getReportMessage("Module", reportBO.getModule())
		+ getReportMessage("Parameters", reportBO.getParameters())
		+ getReportMessage("Start Time", reportBO.getStartTime())
		+ getReportMessage("End Time", reportBO.getEndTime());
		
		ExtentTest test = reporter.createTest(reportBO.getTestCaseName());
		
		if(reportBO.getStatus() == failed) {
			msg += getReportMessage("Error", reportBO.getStacktrace());
			test.log(Status.FAIL, msg);
		}
		else if(reportBO.getStatus() == skipped) {
			msg += getReportMessage("Error", reportBO.getStacktrace());
			test.log(Status.SKIP, msg);
		}
		else {
			test.log(Status.PASS, msg);
		}
		
		reporter.flush();
	}
	
	public String getReportMessage(String field, Object value) {
		String breakString = "<br><br>";
		return "<b>" + field + ":</b> " + value + breakString;
	}
	
	public void insertResultsToDb(List<ReportBO> reportBoList) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/selenium","root","admin");
		Statement statement = connection.createStatement();
		
		for (ReportBO reportBO : reportBoList) {
			String query = "INSERT INTO seleniumtable (TestCaseName, Description, Module, Parameters, Status, StartTime, EndTime, Error) VALUES ('"
					+ reportBO.getTestCaseName() +"', '" + reportBO.getDescription() + "', '" + reportBO.getModule() + "', '" + reportBO.getParameters() + "',"
							+ " '" + reportBO.getStatus() + "', '" + reportBO.getStartTime() + "', '" + reportBO.getEndTime() + "', 'test')";
			statement.executeUpdate(query);
		}
		
		statement.close();
		connection.close();
	}
	
}
