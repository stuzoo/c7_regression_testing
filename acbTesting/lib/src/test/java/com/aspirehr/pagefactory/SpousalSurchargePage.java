package com.aspirehr.pagefactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aspirehr.util.SeleniumUtil;

public class SpousalSurchargePage extends DetailPage {
	WebDriver driver;
	private static final String REGEX_BENPERCENTAGE = "(__item[0123456789]+)(-idBen-)([0123456789]+)(_cell)([123456789]+)";// -idBen-0_cell4
	private static final String REGEX_PLAN_COST = "([0123456789.,-]+)(\\s+USD)";
	private static final String REGEX_PLAN_DETAILS = "__text\\d+-cont\\d+_\\d+";// __text170-cont0_1

	public SpousalSurchargePage(WebDriver driver) {
		super(driver);
		this.driver = driver;

		// This initElements method will create all WebElements
		AjaxElementLocatorFactory ajaxFactory = new AjaxElementLocatorFactory(driver, 30);
		PageFactory.initElements(ajaxFactory, this);

	}

	public void setSPSurchgAttestation(String status) {
		switch (status) {
		case "attest1":
			System.out.println("attest1");
			WebElement attest1 = driver.findElement(By.id("itemAttest-CB"));
			if (!attest1.isSelected()) // select the checkbox if not previously checked
			{
				driver.findElement(By.id("itemAttest-CbBg")).click();
			}
			break;
		case "attest2":
			System.out.println("attest2");
			WebElement attest2 = driver.findElement(By.id("itemnonAttest-CB"));
			if (!attest2.isSelected()) // select the checkbox if not previously checked
			{
				driver.findElement(By.id("itemnonAttest-CbBg")).click();
			}
			break;
		}

	}

	public String getSPSurchgAttestationValue() {
		String SPSurchgAttestionValue = "";
		WebElement attest1 = driver.findElement(By.id("itemAttest-CB"));
		WebElement attest2 = driver.findElement(By.id("itemnonAttest-CB"));
		if (attest1.isSelected())
			SPSurchgAttestionValue = "attest1";
		else if (attest2.isSelected())
			SPSurchgAttestionValue = "attest2";
		return SPSurchgAttestionValue;
	}
	
	public void  saveSPSurchgttestationValue() {		
		WebElement attest1 = driver.findElement(By.id("oSave-content"));		
		attest1.click();
	}
	
	public void  cancelSPSurchgPopup() {		
		WebElement cancelBtn = driver.findElement(By.id("__button1-content"));		
		cancelBtn.click();
	}
}
