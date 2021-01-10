package test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import project.PageClass;

@Test
public class TestClass {
	
	@BeforeMethod
	public void launchDriver() {
		PageClass.launchDriver();
	}
	
	@AfterMethod
	public void closeDriver() {
		PageClass.closeDriver();
	}

	@Parameters({"username", "password"})
	@Test(description="To verify whether user is able to launch google page", groups={"test1"})
	public void launchGooglePage1(String username, String password) throws Exception {
		PageClass.launchGooglePage(username, password);
		throw new Exception("this is a custom exception");
	}
	
	@Parameters({"username", "password"})
	@Test(description="To verify whether user is able to launch google page", groups={"test1"}, dependsOnMethods="launchGooglePage1")
	public void launchGooglePag2(String username, String password) throws Exception {
		PageClass.launchGooglePage(username, password);
		throw new Exception("this is a custom exception");
	}
	
	@Parameters({"username", "password"})
	@Test(description="To verify whether user is able to launch google page", groups={"test3"})
	public void launchGooglePag3(String username, String password) throws Exception {
		PageClass.launchGooglePage(username, password);
	}
	
	@Parameters({"username", "password"})
	@Test(description="To verify whether user is able to launch google page", groups={"test4"}, dataProviderClass=test.DataProviderClass.class, dataProvider="dp")
	public void launchGooglePag4(String username, String password) throws Exception {
		PageClass.launchGooglePage(username, password);
	}
	
	@Parameters({"username", "password"})
	@Test(description="To verify whether user is able to launch google page", groups={"test1"}, dependsOnMethods="launchGooglePage1")
	public void launchGooglePag5(String username, String password) throws Exception {
		PageClass.launchGooglePage(username, password);
		throw new Exception("this is a custom exception");
	}
	
}
