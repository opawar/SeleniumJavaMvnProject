package test;

import org.testng.annotations.DataProvider;

public class DataProviderClass {
	
	@DataProvider(name="dp")
	public static Object[][] dataProvider() {
		Object data[][] = new Object[1][2];
		data[0][0] = "hello";
		data[0][1] = "world";
		
		return data;
	}

}
