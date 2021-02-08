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

public class TobaccoUserPage extends DetailPage {
	WebDriver driver;
	private static final String REGEX_BENPERCENTAGE = "(__item[0123456789]+)(-idBen-)([0123456789]+)(_cell)([123456789]+)";// -idBen-0_cell4
	private static final String REGEX_PLAN_COST = "([0123456789.,-]+)(\\s+USD)";
	private static final String REGEX_PLAN_DETAILS = "__text\\d+-cont\\d+_\\d+";// __text170-cont0_1

	public TobaccoUserPage(WebDriver driver) {
		super(driver);
		this.driver = driver;

		// This initElements method will create all WebElements
		AjaxElementLocatorFactory ajaxFactory = new AjaxElementLocatorFactory(driver, 30);
		PageFactory.initElements(ajaxFactory, this);

	}

	public void setSmokerStatus(String status) {
		WebElement tobaccoCB = null;
		switch (status) {
		case "smoker":
			System.out.println("smoker");
			WebElement smokerElem = driver.findElement(By.id("Smoker-CB"));
			if (!smokerElem.isSelected()) // select the checkbox if not previously checked
			{
				driver.findElement(By.id("Smoker-CbBg")).click();
			}
			break;
		case "nonsmoker":
			System.out.println("nonsmoker");
			WebElement nonSmokeElem = driver.findElement(By.id("nonSmoker-CB"));
			if (!nonSmokeElem.isSelected()) // select the checkbox if not previously checked
			{
				driver.findElement(By.id("nonSmoker-CbBg")).click();
			}
			break;
		}

	}

	public String getSmokerStatus() {
		String smokerStatus = "";
		WebElement smokerElem = driver.findElement(By.id("Smoker-CB"));
		WebElement nonSmokerElem = driver.findElement(By.id("nonSmoker-CB"));
		if (smokerElem.isSelected())
			smokerStatus = "smoker";
		else if (nonSmokerElem.isSelected())
			smokerStatus = "nonsmoker";
		return smokerStatus;
	}
}
