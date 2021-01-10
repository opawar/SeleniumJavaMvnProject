package framework.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ISuite;
import org.testng.ISuiteListener;

import framework.utilities.CommonUtils;

public class SuiteListener implements ISuiteListener {
	
	Logger logger = LogManager.getLogger(SuiteListener.class);
	
	public void onStart(ISuite suite) {
		logger.info("----------------onStart----------------");
		
//		String path = "./output";
//		logger.info("deleting output directory...");
//		CommonUtils.deleteFileOrDirectory(path);
//		logger.info("output directory deleted.");
		
		logger.info("deleting ./output/extent.html...");
		CommonUtils.deleteFileOrDirectory("./output/extent.html");
		logger.info("./output/extent.html deleted.");
		
		logger.info("deleting test-output...");
		CommonUtils.deleteFileOrDirectory("./test-output");
		logger.info("test-output deleted.");
	}

	public void onFinish(ISuite suite) {
		logger.info("----------------onFinish----------------");
	}
	
}
