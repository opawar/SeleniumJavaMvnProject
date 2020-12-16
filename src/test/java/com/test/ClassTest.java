package com.test;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ClassTest {
	
	@Test
	@Parameters({"username", "password"})
	public void loginToNaukri(String username, String password) {
		PageClass.launchNaukriLoginPage();
		PageClass.enterUsername(username);
		PageClass.enterPassword(password);
		PageClass.clickOnLoginButton();
	}

}
