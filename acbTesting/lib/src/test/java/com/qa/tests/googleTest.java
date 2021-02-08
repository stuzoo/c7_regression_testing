package com.qa.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.Assert;

public class googleTest {
	@Test
	public void GoogleTitleTest(){
	System.setProperty("webdriver.chrome.driver", "/Users/stu/Drivers/chromedriver");
	WebDriver driver = new ChromeDriver();
	driver.get("http://www.google.com");
	String title = driver.getTitle();
	
	System.out.println("title is" + title);
	
	Assert.assertEquals(title, "Google");
	
	driver.quit();
	
}
}