package project;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class PageClass {
	
	static WebDriver driver;
	static Logger logger = LogManager.getLogger(PageClass.class);
	
	public static void launchDriver() {
		logger.info("launching driver...");
		System.setProperty("webdriver.chrome.driver", "D:/Om/Workspaces/PhotonWorkspace/SeleniumProject/src/main/resources/chromedriver.exe");
		driver = new ChromeDriver();
		logger.info("driver launched");
	}
	
	public static void closeDriver() {
		logger.info("quitting driver...");
		driver.quit();
		logger.info("driver closed");
	}

	public static void launchGooglePage(String username, String password) throws Exception {
		logger.info("username: " + username + ", password: " + password);
		logger.info("launching google page...");
		driver.get("http://www.google.com");
		Thread.sleep(5000);
		logger.info("google page launched");
	}

}
