package com.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageClass {
	
	static WebDriver driver;
	static WebDriverWait wait;
	
	public static void launchNaukriLoginPage() {
		System.setProperty("webdriver.chrome.driver", "E:/Om/EclipseWorkspace/Workspace12162020/SeleniumJavaMvnProject/src/main/resources/chromedriver.exe");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 30);
		driver.get("https://www.naukri.com/nlogin/login");
	}
	
	public static void enterUsername(String username) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(ObjectClass.USERNAME_TEXTBOX));
		driver.findElement(ObjectClass.USERNAME_TEXTBOX).sendKeys(username);
	}
	
	public static void enterPassword(String password) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(ObjectClass.PASSWORD_TEXTBOX));
		driver.findElement(ObjectClass.PASSWORD_TEXTBOX).sendKeys(password);
	}
	
	public static void clickOnLoginButton() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(ObjectClass.LOGIN_BUTTON));
		driver.findElement(ObjectClass.LOGIN_BUTTON).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(ObjectClass.HOMEPAGE_SEARCH_TEXTBOX));
		driver.quit();
	}

}
