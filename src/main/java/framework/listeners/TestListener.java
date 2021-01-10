package framework.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import framework.utilities.CommonUtils;

public class TestListener implements IInvokedMethodListener {
	
	Logger logger = LogManager.getLogger(TestListener.class);

	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		logger.info("----------------beforeInvocation----------------");
		ThreadContext.put("testName", method.getTestMethod().getMethodName());
	}

	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		logger.info("----------------afterInvocation----------------");
		if(testResult.getStatus() != 1) {
			logger.error(CommonUtils.getStacktraceAsString(testResult.getThrowable()));			
		}
	}

}
