package com.aspirehr.pagefactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aspirehr.util.SeleniumUtil;

public class PersonalDataPage extends DetailPage {
	/**
	 * 
	 * All WebElements are identified by @FindBy annotation
	 * 
	 */

	WebDriver driver;
	// @FindBy(id = "__xmlview2--detailPage-title-inner")
	// WebElement pageTitle;

	@FindBy(id = "PernrName")
	WebElement empName;

	@FindBy(id = "PernrDOB")
	WebElement dob;

	// Input fields in "Change" mode.
	@FindBy(id = "Permstras-inner")
	WebElement permStIP;

	@FindBy(id = "Permlocat-inner")
	WebElement permSt2IP;

	@FindBy(id = "Permcity-inner")
	WebElement permCityIP;

	@FindBy(id = "Permstate")
	WebElement permStateIP;
	@FindBy(id = "Permprovince")
	WebElement permProvinceIP;

	@FindBy(id = "Permzipcode-inner")
	WebElement permZipIP;
	@FindBy(id = "Permpostalcode-inner")
	WebElement permPostcodeIP;
	
	

	// Text field in Dislay mode.
	@FindBy(id = "Permstras")
	WebElement permSt;

	@FindBy(id = "Permlocat")
	WebElement permSt2;

	@FindBy(id = "Permcity")
	WebElement permCity;

	@FindBy(id = "Permstatetxt")
	WebElement permState;
	
	@FindBy(id = "Permprovince")
	WebElement permProvince;

	@FindBy(id = "Permzipcode")
	WebElement permZip;
	
	@FindBy(id = "Permpostalcode")
	WebElement permPostCode;
	

	// Text field in Display mode after Changes Saved.
	@FindBy(id = "v1--Permstras")
	WebElement permStChnged;

	@FindBy(id = "v1--Permlocat")
	WebElement permSt2Chnged;

	@FindBy(id = "v1--Permcity")
	WebElement permCityChnged;

	@FindBy(id = "v1--Permstatetxt")
	WebElement permStateChnged;

	@FindBy(id = "v1--Permzipcode")
	WebElement permZipChnged;

	@FindBy(id = "mailStras")
	WebElement mailSt;
	
	@FindBy(id = "mailStras2")
	WebElement mailSt2;

	@FindBy(id = "mailCity")
	WebElement mailCity;

	@FindBy(id = "mailState")
	WebElement mailState;

	@FindBy(id = "mailZipcode")
	WebElement mailZip;
	
	// Text field in Display mode after Changes Saved.
	@FindBy(id = "v1--mailStras")
	WebElement mailStChnged;
	
	@FindBy(id = "v1--mailStras2")
	WebElement mailSt2Chnged;

	@FindBy(id = "v1--mailCity")
	WebElement mailCityChnged;

	@FindBy(id = "v1--mailState")
	WebElement mailStateChnged;

	@FindBy(id = "v1--mailZipcode")
	WebElement mailZipChnged;

	// Org Information Elements
	@FindBy(id = "Btrtltext")
	WebElement persSubArea;

	@FindBy(id = "Abkrstext")
	WebElement payrollArea;

	@FindBy(id = "Ansvhtext")
	WebElement workContract;

	@FindBy(id = "Bareatext")
	WebElement benArea;

	@FindBy(id = "Bengrtext")
	WebElement v1stProgGrouping;

	@FindBy(id = "Bstattext")
	WebElement v2ndProgGrouping;

	@FindBy(id = "Ansal")
	WebElement annualSalary;

	public PersonalDataPage(WebDriver driver) {
		super(driver);
		this.driver = driver;

		// This initElements method will create all WebElements
		AjaxElementLocatorFactory ajaxFactory = new AjaxElementLocatorFactory(driver, 30);
		PageFactory.initElements(ajaxFactory, this);

	}

	// public String getPageTitle() {
	//// (new WebDriverWait(this.driver, 30))
	//// .until(ExpectedConditions.textToBePresentInElement(pageTitle, "PERSONAL
	// DATA"));
	// List<WebElement> elementList =
	// SeleniumUtil.findElementListByIdSuffix(driver,
	// By.className("sapMBarChild"), "detailPage-title", 2);
	// String pageTitle = "";
	// for(WebElement element : elementList){
	// System.out.println(element.getAttribute("id")+"::"+element.getText());
	// if(element.getText()!= null && !element.getText().trim().equals("")){
	// if (element.getText().toLowerCase().equals("personal data")){
	// pageTitle = element.getText();
	// break;
	// }
	// }
	// }
	// return pageTitle;
	//
	// }

	public String getName() {
		(new WebDriverWait(this.driver, 30)).until(ExpectedConditions.visibilityOf(empName));
		return empName.getText();

	}

	public String getDOB() {
		(new WebDriverWait(this.driver, 30)).until(ExpectedConditions.visibilityOf(dob));
		return dob.getText();
	}

	public Map<String, String> getPermAddress() {
		HashMap<String, String> permAddr = new HashMap<String, String>();
		(new WebDriverWait(this.driver, 30)).until(ExpectedConditions.visibilityOf(permSt));
		permAddr.put("street", permSt.getText());
		(new WebDriverWait(this.driver, 30)).until(ExpectedConditions.visibilityOf(permCity));
		permAddr.put("city", permCity.getText());
		// (new WebDriverWait(this.driver,
		// 30)).until(ExpectedConditions.visibilityOf(permState));		
		try {
			permAddr.put("state", permState.getText());
			(new WebDriverWait(this.driver, 30)).until(ExpectedConditions.visibilityOf(permZip));
			permAddr.put("zip", permZip.getText());
			} catch(Exception e) {
				permAddr.put("state", permProvince.getText());
				(new WebDriverWait(this.driver, 30)).until(ExpectedConditions.visibilityOf(permPostCode));
				permAddr.put("zip", permPostCode.getText());
			}

		return permAddr;
	}
	
	public Map<String, String> getPermAddrAfterChange() {
		HashMap<String, String> permAddr = new HashMap<String, String>();
		(new WebDriverWait(this.driver, 30)).until(ExpectedConditions.visibilityOf(permSt));
		permAddr.put("street", permSt.getText());
		(new WebDriverWait(this.driver, 30)).until(ExpectedConditions.visibilityOf(permSt2));
		permAddr.put("street2", permSt2.getText());
		(new WebDriverWait(this.driver, 30)).until(ExpectedConditions.visibilityOf(permCity));
		permAddr.put("city", permCity.getText());
		// (new WebDriverWait(this.driver,
		// 30)).until(ExpectedConditions.visibilityOf(permState));
		try {
		permAddr.put("state", permState.getText());
		(new WebDriverWait(this.driver, 30)).until(ExpectedConditions.visibilityOf(permZip));
		permAddr.put("zip", permZip.getText());
		} catch(Exception e) {
			permAddr.put("state", permProvince.getText());
			(new WebDriverWait(this.driver, 30)).until(ExpectedConditions.visibilityOf(permPostCode));
			permAddr.put("zip", permPostCode.getText());
		}

		return permAddr;
	}

	public Map<String, String> getMailingAddress() {
		HashMap<String, String> mailAddr = new HashMap<String, String>();
		(new WebDriverWait(this.driver, 30)).until(ExpectedConditions.visibilityOf(mailSt));
		if (mailSt != null)
			mailAddr.put("street", mailSt.getText());
		(new WebDriverWait(this.driver, 30)).until(ExpectedConditions.visibilityOf(mailCity));
		if (mailCity != null)
			mailAddr.put("city", mailCity.getText());
		(new WebDriverWait(this.driver, 30)).until(ExpectedConditions.visibilityOf(mailState));
		if (mailState != null)
			mailAddr.put("state", mailState.getText());
		(new WebDriverWait(this.driver, 30)).until(ExpectedConditions.visibilityOf(mailZip));
		if (mailState != null)
			mailAddr.put("zip", mailZip.getText());
		return mailAddr;
	}
	
	public Map<String, String> getMailingAddrAfterChange() {
		HashMap<String, String> mailAddr = new HashMap<String, String>();
		try{
		(new WebDriverWait(this.driver, 30)).until(ExpectedConditions.visibilityOf(mailStChnged));
		if (mailStChnged != null)
			mailAddr.put("street", mailStChnged.getText());
		(new WebDriverWait(this.driver, 30)).until(ExpectedConditions.visibilityOf(mailSt2Chnged));
		if (mailStChnged != null)
			mailAddr.put("street2", mailStChnged.getText());
		(new WebDriverWait(this.driver, 30)).until(ExpectedConditions.visibilityOf(mailCityChnged));
		if (mailCityChnged != null)
			mailAddr.put("city", mailCityChnged.getText());
		(new WebDriverWait(this.driver, 30)).until(ExpectedConditions.visibilityOf(mailStateChnged));
		if (mailStateChnged != null)
			mailAddr.put("state", mailStateChnged.getText());
		(new WebDriverWait(this.driver, 30)).until(ExpectedConditions.visibilityOf(mailZipChnged));
		if (mailZipChnged != null)
			mailAddr.put("zip", mailZipChnged.getText());
		
		} catch ( NoSuchElementException noElemExcep){
			noElemExcep.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return mailAddr;
	}

	public Map<String, String> getOrgInformation() {
		HashMap<String, String> orgInformation = new HashMap<String, String>();
		try {
			(new WebDriverWait(this.driver, 30)).until(ExpectedConditions.visibilityOf(persSubArea));
			orgInformation.put("persSubArea", persSubArea.getText());
		} catch (Exception e) {
			System.err.println("Error while reading Org Information" + e.getMessage());
		}
		try {
			(new WebDriverWait(this.driver, 30)).until(ExpectedConditions.visibilityOf(payrollArea));
			orgInformation.put("payrollArea", payrollArea.getText());
		} catch (Exception e) {
			System.err.println("Error while reading Org Information" + e.getMessage());
		}
		try {
			(new WebDriverWait(this.driver, 30)).until(ExpectedConditions.visibilityOf(workContract));
			orgInformation.put("workContract", workContract.getText());
		} catch (Exception e) {
			System.err.println("Error while reading Org Information" + e.getMessage());
		}
		try {
			(new WebDriverWait(this.driver, 30)).until(ExpectedConditions.visibilityOf(benArea));
			orgInformation.put("benArea", benArea.getText());
		} catch (Exception e) {
			System.err.println("Error while reading Org Information" + e.getMessage());
		}
		try {

			(new WebDriverWait(this.driver, 30)).until(ExpectedConditions.visibilityOf(v1stProgGrouping));
			orgInformation.put("v1stProgGrouping", v1stProgGrouping.getText());
		} catch (Exception e) {
			System.err.println("Error while reading Org Information" + e.getMessage());
		}
		try {

			(new WebDriverWait(this.driver, 30)).until(ExpectedConditions.visibilityOf(v2ndProgGrouping));
			orgInformation.put("v2ndProgGrouping", v2ndProgGrouping.getText());
		} catch (Exception e) {
			System.err.println("Error while reading Org Information" + e.getMessage());
		}
		try {
			(new WebDriverWait(this.driver, 30)).until(ExpectedConditions.visibilityOf(annualSalary));
			orgInformation.put("annualSalary", annualSalary.getText());
		} catch (Exception e) {
			System.err.println("Error while reading Org Information" + e.getMessage());
		}
		return orgInformation;
	}

	public boolean isChangeBtnVisible() {
		List<WebElement> elementList = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMBtnEmphasized"),
				"--PERSEDIT-inner", 2);
		if (elementList.size() > 0)
			return true;
		else
			return false;

	}

	public void clickChangeBtn() {
		List<WebElement> elementList = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMBtnEmphasized"),
				"--PERSEDIT-inner", 2);
		for (WebElement element : elementList) {
			element.click();
			break;
		}

	}

	public void enterPermAddrnSave(Map<String, String> permAddr) {
		// HashMap<String, String> permAddr = new HashMap<String, String>();
		permStIP.clear();
		permStIP.sendKeys(permAddr.get("street"));
		permSt2IP.clear();
		permSt2IP.sendKeys(permAddr.get("street2"));
		permCityIP.clear();
		permCityIP.sendKeys(permAddr.get("city"));
		selectStateByName(permAddr.get("state"));
		try {
		permZipIP.clear();
		permZipIP.sendKeys(permAddr.get("zip"));
		}catch(Exception e) {
			permPostcodeIP.clear();
			permPostcodeIP.sendKeys(permAddr.get("zip"));
		}
		// clickSaveBtn();

	}

	public void selectStateByName(String state) {
		try {
		permStateIP.click();
		} catch (Exception e) {
			permProvinceIP.click();
		}
		List<WebElement> listItems = SeleniumUtil.findElementListByClassName(driver, "sapMSelectListItemBase");
		for (WebElement listItem : listItems) {
			if (listItem.getText().equalsIgnoreCase(state)) {
				listItem.click();
				break;
			}
		}
	}

	public void clickSaveBtn() {
		List<WebElement> elementList = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMBtnContent"),
				"--PERSSAVE-content", 2);
		for (WebElement element : elementList) {
			element.click();
			// element.click();
			break;
		}
	}

}
