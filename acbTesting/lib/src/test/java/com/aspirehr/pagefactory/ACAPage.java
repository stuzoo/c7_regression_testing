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

public class ACAPage extends DetailPage {
	WebDriver driver;
	private static final String REGEX_BENPERCENTAGE = "(__item[0123456789]+)(-idBen-)([0123456789]+)(_cell)([123456789]+)";// -idBen-0_cell4
	private static final String REGEX_PLAN_COST = "([0123456789.,-]+)(\\s+USD)";
	private static final String REGEX_PLAN_DETAILS = "__text\\d+-cont\\d+_\\d+";// __text170-cont0_1

	public ACAPage(WebDriver driver) {
		super(driver);
		this.driver = driver;

		// This initElements method will create all WebElements
		AjaxElementLocatorFactory ajaxFactory = new AjaxElementLocatorFactory(driver, 30);
		PageFactory.initElements(ajaxFactory, this);

	}

	public void setACAAttestation(String status) {
		switch (status) {
		case "enroll":
			System.out.println("enroll");
			WebElement enrollACA = driver.findElement(By.id("itemAttestSA-CB"));
			if (!enrollACA.isSelected()) // select the checkbox if not previously checked
			{
				driver.findElement(By.id("itemAttestSA-CbBg")).click();
			}
			break;
		case "waive":
			System.out.println("waive");
			WebElement waiveACA = driver.findElement(By.id("itemnonAttestSA-CB"));
			if (!waiveACA.isSelected()) // select the checkbox if not previously checked
			{
				driver.findElement(By.id("itemnonAttestSA-CbBg")).click();
			}
			break;
		}

	}

	public String getACAAttestationValue() {
		String ACAAttestationValue = "";
		WebElement enrollACAElem = driver.findElement(By.id("itemAttestSA-CB"));
		WebElement waiveACAElem = driver.findElement(By.id("itemnonAttestSA-CB"));
		if (enrollACAElem.isSelected())
			ACAAttestationValue = "enrolled";
		else if (waiveACAElem.isSelected())
			ACAAttestationValue = "waived";
		return ACAAttestationValue;
	}
}
