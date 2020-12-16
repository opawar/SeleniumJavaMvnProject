package com.test;

import org.openqa.selenium.By;

public class ObjectClass {
	
	public static By USERNAME_TEXTBOX = By.id("usernameField");
	public static By PASSWORD_TEXTBOX = By.id("passwordField");
	public static By LOGIN_BUTTON = By.xpath("//button[contains(@data-ga-track,'login')]");
	public static By HOMEPAGE_SEARCH_TEXTBOX = By.id("qsb-keyskill-sugg");

}
