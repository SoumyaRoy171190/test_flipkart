package com.browserstack.test_flipkart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlipkartObjectsPage {

	private String flipkart_url = "https://www.flipkart.com/";
	private String itemName = "Samsung Galaxy S10";

	private String strUserNameInputBxXpath = "(//input[@type='text'])[2]";
	private String strPwdInputBxXpath = "//input[@type='password']";
	private String strLoginBtn = "//button//span[text()='Login']";

	private String strSearchItemInputBoxXpath = "//input[contains(@title,'Search for products, brands and more')]";
	@SuppressWarnings("unused")
	private String strSearchInMobilesVarLinkXpath = "//input[contains(@title,'Search for products, brands and more')]//following::ul[1]//a//div[text()='samsung galaxy s10']/div[contains(text(),'in Mobiles')]";
	
	private String strInputSearchBtnXpath = "//input[contains(@title,'Search for products, brands and more')]//following::button";

	private String strFlipkartAssuredCheckBxXpath = "//div[contains(@class,'YuAuf')]/section[3]//input";
	private String strBrandSamsungCheckBxXpath = "//div[text()='Brand']//following::div[text()='Samsung']/preceding-sibling::input";

	private String strListResultXpath = "(//div[@class='_1HmYoV _35HD7C'])[2]/div[@class='bhgxx2 col-12-12']";
	private String strSuffixProductLinkXpath = "//a";
	private String strProductNameXpath = "//a/div[2]/div[1]/div[1]";
	private String strProductPriceXpath = "//a/div[2]/div[2]/div[1]/div/div[1]";

	private BrowserOperations br_ops = new BrowserOperations();
	
	public void stratSession(String brouwser_value) {
		br_ops.initiateBrowser(brouwser_value);
	}

	public void loginToFlipkart() {
		br_ops.navigateToURL(flipkart_url);
		br_ops.enterTextInElement(strUserNameInputBxXpath, "UserName", "Soumya171190@gmail.com");
		br_ops.enterTextInElement(strPwdInputBxXpath, "Pwd", "soumya171190");
		br_ops.clickElement(strLoginBtn, "Login Btn");
		br_ops.staticWait(2000);
	}

	public void searchProductInMobileSection() {
		br_ops.enterTextInElement(strSearchItemInputBoxXpath, "Search Input Box", itemName);
		br_ops.staticWait(1000);
		//br_ops.clickElement(strSearchInMobilesVarLinkXpath, "In Mobiles link");
		br_ops.clickElement(strInputSearchBtnXpath, "Search Button");
	}

	public void checkFlipkartAssuredAndSelectBrand() {
		br_ops.staticWait(3000);
		br_ops.performCheckBxOperations(strFlipkartAssuredCheckBxXpath, "Flipkart Assured CheckBox");
		br_ops.performCheckBxOperations(strBrandSamsungCheckBxXpath, "Samsung Brand CheckBox");
	}

	public List<Map<String, String>> findListResults() {
		List<Map<String, String>> productList = new ArrayList<Map<String, String>>();
		int totalRowCount = br_ops.findTotalIndexCountFromListResult(strListResultXpath);
		for (int index = 1; index <= totalRowCount; index++) {
			String nameXpath = strListResultXpath + "[" + index + "]" + strProductNameXpath;
			if (br_ops.getElementText(nameXpath).contains("Samsung Galaxy S10")) {
				Map<String, String> prodDesc = new HashMap<String, String>();
				String productLinkXpath = strProductNameXpath + "[" + index + "]" + strSuffixProductLinkXpath;
				prodDesc.put("Product Link", br_ops.getElementAttribute(productLinkXpath, "href"));
				prodDesc.put("Product Name", br_ops.getElementText(nameXpath));
				String productPriceXpath = strProductNameXpath + "[" + index + "]" + strProductPriceXpath;
				prodDesc.put("Product Price", br_ops.getElementText(productPriceXpath));
				productList.add(prodDesc);
				if(productList.size()>5)
					break;
			}
		}
		return productList;
	}

}