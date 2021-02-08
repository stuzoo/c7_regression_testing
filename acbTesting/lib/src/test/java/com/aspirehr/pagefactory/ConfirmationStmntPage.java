package com.aspirehr.pagefactory;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aspirehr.util.SeleniumUtil;

public class ConfirmationStmntPage extends DetailPage {
	WebDriver driver;
	@FindBy(id = "statementDate-inner")
	WebElement stmntDt;
	@FindBy(id = "myStatementLink")
	WebElement stmntLink;
	
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

	public ConfirmationStmntPage(WebDriver driver) {
		super(driver);
		this.driver = driver;

		// This initElements method will create all WebElements
		AjaxElementLocatorFactory ajaxFactory = new AjaxElementLocatorFactory(driver, 30);
		PageFactory.initElements(ajaxFactory, this);

	}
	
	public void setStatementDateDate(String MMMdYYYYDate) {
		// eventDate-inner
		stmntDt.sendKeys(MMMdYYYYDate);
//		lifeEvtDt.click();
		stmntDt.sendKeys(Keys.RETURN);
//		List<WebElement> elements = SeleniumUtil.findElementListByClassName(driver, "sapMInputValHelp");
//		for (WebElement element : elements) {
//			element.click();
//			//element.click();
//			break;
//		}
		// lifeEvtDt.submit();
	}
	
	public String getPrintURL() {
		String url = "";
		url = stmntLink.getAttribute("href");		
		return url;
	}
	
	

}
