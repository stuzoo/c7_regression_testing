package com.aspirehr.pagefactory;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.aspirehr.util.SeleniumUtil;

public class CBAdminPage {
	WebDriver driver;
	@FindBy(id = "__component0---object--page-title")
	WebElement pageTitle;

	@FindBy(id = "__component0---worklist--empnumber-inner")
	WebElement empNum;

	@FindBy(id = "__component0---worklist--firstname-inner")
	WebElement firstName;

	@FindBy(id = "__component0---worklist--lastname-inner")
	WebElement lastName;

	@FindBy(id = "__component0---worklist--birthdate-inner")
	WebElement dob;

	@FindBy(id = "__button0-__clone0")
	WebElement adminLink1;

	@FindBy(id = "__button3")
	WebElement adminLink2;

	@FindBy(id = "__button4")
	WebElement proxyLink;

	@FindBy(id = "__button2-inner")
	WebElement searchBtn;

	private String winHandleBefore;

	public static final String REGEX_CB_IFRAME = "(remote_iframe_)([a-z,A-Z,0-9,-]+)"; // remote_iframe_74a413a8-5f6c-4fad-8e31-8ee7d974f4c4"

	public static final String REGEX_EMPNUM = "(__item\\d+-__clone\\d+)_cell0";// __item0-__clone12_cell0
	public static final String REGEX_FIRSTNAME = "__item\\d+-__clone\\d+_cell2";
	public static final String REGEX_LASTNAME = "__item\\d+-__clone\\d+_cell3";
	public static final String REGEX_DOB = "__item\\d+-__clone\\d+_cell6";

	public CBAdminPage(WebDriver driver) {

		this.driver = driver;

		// This initElements method will create all WebElements
		AjaxElementLocatorFactory ajaxFactory = new AjaxElementLocatorFactory(driver, 30);
		PageFactory.initElements(ajaxFactory, this);
		// driver.get(URL);
		// driver.manage().window().maximize();
	}

	public String getPageTitle() {
		String pageTitleStr = pageTitle.getText();
		if (pageTitleStr != null)
			return pageTitleStr;
		else
			return "";
	}

	public void enterEmployeeNumber(String perNum) {
		empNum.clear();
		empNum.sendKeys(perNum);
	}

	public void enterFirstName(String fName) {
		firstName.clear();
		firstName.sendKeys(fName);
	}

	public void enterLastName(String lName) {
		lastName.clear();
		lastName.sendKeys(lName);
	}

	public void enterDOB(String birthDate) {
		dob.clear();
		dob.sendKeys(birthDate);
	}

	public void clearAllInput() {
		empNum.clear();
		firstName.clear();
		lastName.clear();
		dob.clear();
	}

	public void clickSearchButton() {
		searchBtn.click();
	}

	public List<String> getEmployeeNumList() {
		List<String> empNumList = new ArrayList<>();
		List<WebElement> elements = SeleniumUtil.findElementListByIdRegex(driver, By.className("sapMListTblCell"),
				Pattern.compile(REGEX_EMPNUM));
		for (WebElement element : elements) {
			empNumList.add(element.getText());
		}
		return empNumList;
	}

	public List<String> getFirstNameList() {
		List<String> empFirstNameList = new ArrayList<>();
		List<WebElement> elements = SeleniumUtil.findElementListByIdRegex(driver, By.className("sapMListTblCell"),
				Pattern.compile(REGEX_FIRSTNAME));
		for (WebElement element : elements) {
			empFirstNameList.add(element.getText());
		}
		return empFirstNameList;
	}

	public List<String> getLastNameList() {
		List<String> empLastNameList = new ArrayList<>();
		List<WebElement> elements = SeleniumUtil.findElementListByIdRegex(driver, By.className("sapMListTblCell"),
				Pattern.compile(REGEX_LASTNAME));
		for (WebElement element : elements) {
			empLastNameList.add(element.getText());
		}
		return empLastNameList;
	}

	public List<String> getDOBList() {
		List<String> empDOBList = new ArrayList<>();
		List<WebElement> elements = SeleniumUtil.findElementListByIdRegex(driver, By.className("sapMListTblCell"),
				Pattern.compile(REGEX_DOB));
		for (WebElement element : elements) {
			empDOBList.add(element.getText());
		}
		return empDOBList;
	}

	public void selectRowByEmpNum(String empNum) {
		// List<String> empNumList = new ArrayList<>();
		String colId = "";
		List<WebElement> elements = SeleniumUtil.findElementListByIdRegex(driver, By.className("sapMListTblCell"),
				Pattern.compile(REGEX_EMPNUM));
		for (WebElement element : elements) {
			String pernr = element.getText();
			if (!pernr.isEmpty() && pernr.equalsIgnoreCase(empNum)) {
				colId = element.getAttribute("id");
				break;
			}
		}
		Pattern pattern = Pattern.compile(REGEX_EMPNUM);
		Matcher m = pattern.matcher(colId);
		if (m.matches()) {
			// resultList.add(e);
			String rowID = m.group(1);// Beneficiary No in the list
			WebElement rowElem = driver.findElement(By.id(rowID));
			rowElem.click();

		}
	}

	public boolean isAdminLinkVisInSearchScreen() {
		boolean adminEnabled;
		try {
			adminEnabled = adminLink1.isEnabled();
		} catch (Exception e) {
			System.out.println("No Admin Edit link in search screen");
			adminEnabled = false;
			adminEnabled = false;
		}
		return adminEnabled;
	}

	public boolean isAdminLinkVisible() {
		boolean adminEnabled;
		try {
			adminEnabled = adminLink2.isEnabled();
		} catch (Exception e) {
			System.out.println("No Admin Edit link in proxy page");
			adminEnabled = false;
		}
		return adminEnabled;
	}

	public boolean isProxyLinkVisible() {
		boolean proxyEnabled = proxyLink.isEnabled();
		if (proxyEnabled) {
			return true;
		}
		return false;
	}

	public WebDriver clickProxyLink() {

		// Store the current window handle
		winHandleBefore = driver.getWindowHandle();

		// Perform the click operation that opens new window
		proxyLink.click();

		// Switch to new window opened
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}

		return driver;

		// Perform the actions on new window

		// // Close the new window, if that window no more required
		// driver.close();
		//
		// // Switch back to original browser (first window)
		// driver.switchTo().window(winHandleBefore);

		// Continue with original browser (first window)
	}
	
	public WebDriver clickAdminLink() {

		// Store the current window handle
		winHandleBefore = driver.getWindowHandle();

		// Perform the click operation that opens new window
		adminLink2.click();

		// Switch to new window opened
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}

		return driver;

		// Perform the actions on new window

		// // Close the new window, if that window no more required
		// driver.close();
		//
		// // Switch back to original browser (first window)
		// driver.switchTo().window(winHandleBefore);

		// Continue with original browser (first window)
	}

}
