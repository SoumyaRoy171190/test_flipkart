package com.browserstack.test_flipkart;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserOperations {

	WebDriver driver;

	public BrowserOperations() {
		
	}
	
	public void initiateBrowser(String value) {
		if(value.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "D:\\browser-jars\\chromedriver3.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}else if(value.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "D:\\browser-jars\\geckodriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}else if(value.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", "D:\\browser-jars\\msedgedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}
	}

	public void navigateToURL(String url) {
		driver.get(url);
		staticWait(3000);
	}

	public void clickElement(String locator, String name) {
		try {
			staticWait(1000);
			WebElement elem = driver.findElement(By.xpath(locator));
			elem.click();
			System.out.println("Web Element " + name + " is clicked");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void enterTextInElement(String locator, String name, String value) {
		try {
			staticWait(1000);
			WebElement elem = driver.findElement(By.xpath(locator));
			elem.sendKeys(value);
			;
			System.out.println(value + " has been entered in element  " + name);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void staticWait(int millisSeconds) {
		try {
			Thread.sleep(millisSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void performCheckBxOperations(String locator, String name) {
		try {
			staticWait(1000);
			WebElement elem = driver.findElement(By.xpath(locator));
			if (!elem.isSelected()) {
				//new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(elem)).click();
				JavascriptExecutor js =  (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click", elem);
			}
			elem.click();
			System.out.println("Web Element " + name + " is checked");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int findTotalIndexCountFromListResult(String locator) {

		int count = 0;
		try {
			staticWait(2000);
			count = driver.findElements(By.xpath(locator)).size();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	public String getElementText(String locator) {

		String val = "";
		try {
			staticWait(1000);
			WebElement elem = driver.findElement(By.xpath(locator));
			val = elem.getText().trim();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return val;
	}

	public String getElementAttribute(String locator, String attr_type) {
		String val = "";
		try {
			staticWait(1000);
			WebElement elem = driver.findElement(By.xpath(locator));
			val = elem.getAttribute(attr_type).trim();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return val;
	}

}
