package com.aspirehr.pagefactory;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aspirehr.util.SeleniumUtil;

public class CurrentEnrollmentPage extends DetailPage {
	WebDriver driver;
	private static final String REGEX_DEPTLIST = "__item\\d+-idDependants-(\\d+)-selectMulti-CbBg";
	private static final String SUFFIX_PLANOPTIONLIST = "-idHealth-planno-selectSingle-RB";
	// private static final String REGEX_DEPTLIST =
	// "(.*?)-idDependants-(\\d+)-selectMulti-CbBg";
	private static final String REGEX_PLANOPTIONLIST = "__item\\d+-idHealth-\\d+-selectSingle-Button";
	private static final String REGEX_PLANOPTIONLISTIP = "__item\\d+-idHealth-\\d+-selectSingle-RB";
	private static final String REGEX_DEPTLISTIP = "__item\\d+-idDependants-\\d+-selectMulti-CB";

	private static final String REGEX_INS_PLANOPTIONRB = "__item\\d+-idInsurance-\\d+-selectSingle-RB";
	private static final String REGEX_FSA_PLANOPTIONRB = "__item\\d+-idFSA-\\d+-selectSingle-RB";
	private static final String REGEX_FSA_PLANOPTIONIP = "__input\\d+-idFSA-\\d+-inner";

	private static final String REGEX_PLAN_DETAILS = "__text\\d+-cont\\d+_\\d+";// __text170-cont0_1

	private static final String REGEX_REC_STATUS_COLUMN = "__icon\\d+-col0-row\\d+";

	// __input57-idFSA-0-inner

	public CurrentEnrollmentPage(WebDriver driver) {
		super(driver);
		this.driver = driver;

		// This initElements method will create all WebElements
		AjaxElementLocatorFactory ajaxFactory = new AjaxElementLocatorFactory(driver, 30);
		PageFactory.initElements(ajaxFactory, this);

	}

	public String getCurrEnrollPageRawText() {		
		WebElement currEnrolTable = driver.findElement(By.id("idTableCurrEn-listUl"));
		String currEnrolRawText = currEnrolTable.getText();
		return currEnrolRawText;
	}

	public ArrayList<String> getPlanDetails() {
		Pattern regEx = Pattern.compile(REGEX_PLAN_DETAILS);
		List<WebElement> planForm = SeleniumUtil.findElementListByIdRegex(driver, By.className("sapUiRFLContainer"),
				regEx);
		ArrayList<String> planDtsList = new ArrayList<String>();
		for (WebElement element : planForm) {
			if (element.getText() != null && !element.getText().trim().equals("")) {
				planDtsList.add(element.getText().replaceAll("\\s", ""));
			}
		}
		return planDtsList;
	}	
}
