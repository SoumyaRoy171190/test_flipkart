package com.browserstack.test_flipkart;

import java.util.List;
import java.util.Map;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestFlipkartModule {
	FlipkartObjectsPage obj = new FlipkartObjectsPage();
	
	@BeforeTest
	@Parameters("browser")
	public void initiateBrowser(String browser) {
		obj.stratSession(browser);
	}
		
	@Test
	public void testFlipkart() {
		
		obj.loginToFlipkart();
		obj.searchProductInMobileSection();
		obj.checkFlipkartAssuredAndSelectBrand();
		List<Map<String, String>> productInfoList = obj.findListResults();
		printResult(productInfoList);
	}
	
	public static void printResult(List<Map<String, String>> productInfoList) {
		for(Map<String, String> mapList :  productInfoList) {
			for(Map.Entry<String, String> entryMap : mapList.entrySet()) 
				System.out.println(entryMap.getKey() + " :: " + entryMap.getValue());
		}
	}
}
