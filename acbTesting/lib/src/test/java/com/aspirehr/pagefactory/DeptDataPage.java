package com.aspirehr.pagefactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aspirehr.util.SeleniumUtil;

public class DeptDataPage extends DetailPage {
	WebDriver driver;

	@FindBy(id = "depFamsa")
	WebElement deptSelectParent;

	@FindBy(id = "depFamsa-select")
	WebElement deptSelect;

	@FindBy(id = "depFasex")
	WebElement genderRBGroup;

	@FindBy(id = "depFirst-inner")
	WebElement deptFName;

	@FindBy(id = "depLast-inner")
	WebElement deptLName;

	@FindBy(id = "depDate-inner")
	WebElement deptDOB;

	@FindBy(id = "depPeridUS-inner")
	WebElement ssn;	
	
	@FindBy(id = "depPeridUSOpt-inner")
	WebElement ssnOpt;

	@FindBy(id = "depFamst")
	WebElement maritalStatusDD;

	@FindBy(id = "depTelnr-inner")
	WebElement telephoneNo;

	@FindBy(id = "depDisability-CbBg")
	WebElement disablityCb;

	@FindBy(id = "depDisDt-inner")
	WebElement disabilityDt;

	@FindBy(id = "depStudent-CbBg")
	WebElement studentCb;

	@FindBy(id = "depMedicare-CbBg")
	WebElement medicareCb;

	@FindBy(id = "depSmoker-CbBg")
	WebElement smokerCb;

	@FindBy(id = "depMil-CbBg")
	WebElement militaryCb;

	@FindBy(id = "depFinInd-CbBg")
	WebElement deptFinIndepCb;

	@FindBy(id = "depStras-inner")
	WebElement deptStreet;

	@FindBy(id = "depOrt01-inner")
	WebElement deptCity;

	@FindBy(id = "depState")
	WebElement deptState;

	@FindBy(id = "depState-select")
	WebElement deptStateSel;
	// depState-select
	@FindBy(id = "depZipcode-inner")
	WebElement deptZipcode;
	
	@FindBy(id = "CONFDEPS--confirmEnroll-inner")
	WebElement confirmBtn;
	
	private static final String REGEX_DEPTID = "(__text[0123456789]+)(-idDep-[0123456789]+)"; // __text172-idDep-1

	public DeptDataPage(WebDriver driver) {
		super(driver);
		this.driver = driver;

		// This initElements method will create all WebElements
		AjaxElementLocatorFactory ajaxFactory = new AjaxElementLocatorFactory(driver, 30);
		PageFactory.initElements(ajaxFactory, this);

	}

	public ArrayList<String> getDeptList() {
		List<WebElement> deptListElements = SeleniumUtil.findElementListByClassName(this.driver, "sapMListTblCell");
		ArrayList<String> deptList = new ArrayList<String>();
		for (WebElement element : deptListElements) {
			if (element.getAttribute("id").contains("cell0") && element.getText() != null
					&& !element.getText().trim().equals("")) {
				if (element.getAttribute("id").contains("cell0")) {
					deptList.add(element.getText());
				}
			} else if (element.getAttribute("id").contains("nodata")) {
				deptList.add(element.getText());
			}
		}
		return deptList;
	}

	public void selectDependent(int deptNo) {
		// __item8-idDep-0-selectSingle-RB
		try {
			List<WebElement> deptList = SeleniumUtil.findElementListByClassName(driver, "sapMRbHoverable");
			// for (WebElement plan : planList) {
			// (new WebDriverWait(this.driver,
			// 30)).until(ExpectedConditions.elementToBeClickable(plan));
			// plan.click();
			// plan.click();
			// break;
			// }
			WebElement dept = deptList.get(deptNo - 1);
			if (null != dept) {
				(new WebDriverWait(this.driver, 30)).until(ExpectedConditions.elementToBeClickable(dept));
				dept.click();
				dept.click();
			}
		} catch (Exception e) {
			System.err.println("Error occured while selecting Dependent:" + e.getMessage());
		}
	}
	
	public void selectDependentByName(String deptName) {
		// __text36-ledDepList--idDep-1
		Pattern pattern = Pattern.compile(REGEX_DEPTID);
		Matcher m;
		String deptRBId = "";
		List<WebElement> elementList = SeleniumUtil.findElementListByClassName(driver, "sapMText");
		for (WebElement element : elementList) {
			if (element.getText().equalsIgnoreCase(deptName)) {
				deptRBId = element.getAttribute("id");
				break;
			}
		}
		if (!deptRBId.equals("")) {
			m = pattern.matcher(deptRBId);
			if (m.matches()) {
				String deptId = m.group(2);// idDep-X in the list. X=
											// [0123456789]
				if (!deptId.equals("")) {
					String deptIdSuffix = deptId + "-selectSingle-Button"; //__item34-idDep-0-selectSingle-Button
					elementList.clear();
					elementList = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMRbBOut"),
							deptIdSuffix, 2);
					if (elementList.get(0) != null) {
						elementList.get(0).click();
					}
				}
			}
		}
	}
	
	public List<String> getRelationshipValues(){
		List<String> relationships = new ArrayList<>();		
		deptSelectParent.click();
		List<WebElement> listItems = SeleniumUtil.findElementListByClassName(driver, "sapMSelectListItemBase");
		for (WebElement listItem : listItems) {			
			relationships.add(listItem.getText());
		}
		return relationships;
	}

	public void addDependents(Map<String, String> deptDetails) {
		// new
		// Select(deptSelect).selectByVisibleText(deptDetails.get("relationship"));
		try {
		String relationship = deptDetails.get("relationship");
		Thread.sleep(3000);
//		deptSelectParent.click();
//		deptSelect.click();
		List<WebElement> dropdown = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMSltWithArrow"),
				"depFamsa");
		dropdown.get(0).click();
		// deptSelectParent.click();
		dropdown.clear();
		List<WebElement> listItems = SeleniumUtil.findElementListByClassName(driver, "sapMSelectListItem");
		for (WebElement listItem : listItems) {
			if (listItem.getText().equals(relationship)){
				listItem.click();
				break;
			}
		}

		// Select selectFld = new Select(deptSelect);
		// //deptSelect.click();
		// selectFld.selectByIndex(2);
		// List<WebElement> options = selectFld.getOptions();
		//
		// for(WebElement option : options){
		// if(option.getText().equals(relationship))
		// selectFld.selectByValue(relationship);
		// }
		SeleniumUtil.sleep(2000);
		// new Select(deptSel).selectByValue(deptDetails.get("relationship"));
		deptFName.sendKeys(deptDetails.get("fname"));
		SeleniumUtil.sleep(2000);
		deptLName.sendKeys(deptDetails.get("lname"));
		SeleniumUtil.sleep(2000);

		deptDOB.sendKeys(deptDetails.get("dob"));
		SeleniumUtil.sleep(2000);
		// gender
		List<WebElement> elements = genderRBGroup.findElements(By.className("sapMRbBInn"));
		// List<WebElement> elements =
		// SeleniumUtil.findElementListByClassName(driver, "sapMRbBInn");
		switch (deptDetails.get("gender")) {
		case "Male":
			elements.get(0).click();
			break;
		case "Female":
			elements.get(2).click();
			break;
		case "Undeclared":
			elements.get(1).click();
			break;
		default:
			elements.get(0).click();
		}
		SeleniumUtil.sleep(2000);

		if (deptDetails.get("ssn") != null) {
			ssn.sendKeys(deptDetails.get("ssn"));
		}

		if (deptDetails.get("maritalStatus") != null) {
			String maritalStatus = deptDetails.get("maritalStatus");
			maritalStatusDD.click();
			listItems.clear();
			listItems = SeleniumUtil.findElementListByClassName(driver, "sapMSelectListItemBase");
			for (WebElement listItem : listItems) {
				if (listItem.getText().equals(maritalStatus)) {
					listItem.click();
					break;
				}
			}
		}
		if (deptDetails.get("telephoneNo") != null) {
			telephoneNo.sendKeys(deptDetails.get("telephoneNo"));
		}

		if (deptDetails.get("street") != null) {
			deptStreet.clear();
			deptStreet.sendKeys(deptDetails.get("street"));
		}
		if (deptDetails.get("city") != null) {
			deptCity.clear();
			deptCity.sendKeys(deptDetails.get("city"));
		}
		if (deptDetails.get("state") != null) {
			String state = deptDetails.get("state");
			deptState.click();
			// SeleniumUtil.sleep(2000);
			// Select select = new Select(deptStateSel);
			// select.deselectAll();
			// select.selectByValue(state);
			// select.selectByVisibleText(state);
			// List<WebElement> options = select.getOptions();
			// for (WebElement option : options) {
			// if (option.getText().equals(state))
			// option.click();
			// }
			// options.set(2, options.get(2));
			// options.get(2).click();
			listItems.clear();
			listItems = SeleniumUtil.findElementListByClassName(driver, "sapMSelectListItemBase");
			for (WebElement listItem : listItems) {
				if (listItem.getText().equals(state)) {
					listItem.click();
					break;
				}
			}
		}
		if (deptDetails.get("zipcode") != null) {
			deptZipcode.clear();
			deptZipcode.sendKeys(deptDetails.get("zipcode"));
		}
		if (deptDetails.get("isDisable") != null) {
			if (deptDetails.get("isDisable").equals("true"))
				disablityCb.click();
//			else
//				disablityCb.clear();
			if (!deptDetails.get("disbDt").equals(""))
				disabilityDt.sendKeys(deptDetails.get("disbDt"));
		}
		if (deptDetails.get("isStudent").equals("true"))
			studentCb.click();

		if (deptDetails.get("isMedicare").equals("true"))
			medicareCb.click();

		if (deptDetails.get("isSmoker").equals("true"))
			smokerCb.click();

		if (deptDetails.get("isMilitary").equals("true"))
			militaryCb.click();

		if (deptDetails.get("isFinIndep").equals("true"))
			deptFinIndepCb.click();

		SeleniumUtil.sleep(2000);

		clickSaveDependentBtn();
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

	public void changeDependents(Map<String, String> deptDetails) {
		// new
		// Select(deptSelect).selectByVisibleText(deptDetails.get("relationship"));

		// Relationship field will be read-only and cannot be changed - 4/26
		// String relationship = deptDetails.get("relationship");
		// deptSelectParent.click();
		// List<WebElement> listItems =
		// SeleniumUtil.findElementListByClassName(driver,
		// "sapMSelectListItemBase");
		// for (WebElement listItem : listItems) {
		// if (listItem.getText().equals(relationship))
		// listItem.click();
		// }

		// Select selectFld = new Select(deptSelect);
		// //deptSelect.click();
		// selectFld.selectByIndex(2);
		// List<WebElement> options = selectFld.getOptions();
		//
		// for(WebElement option : options){
		// if(option.getText().equals(relationship))
		// selectFld.selectByValue(relationship);
		// }
		// SeleniumUtil.sleep(2000);
		// new Select(deptSel).selectByValue(deptDetails.get("relationship"));
		deptFName.clear();
		deptFName.sendKeys(deptDetails.get("fname"));
		SeleniumUtil.sleep(2000);
		deptLName.clear();
		deptLName.sendKeys(deptDetails.get("lname"));
		SeleniumUtil.sleep(2000);
		deptDOB.clear();
		deptDOB.sendKeys(deptDetails.get("dob"));
		SeleniumUtil.sleep(2000);
		// gender
		List<WebElement> elements = genderRBGroup.findElements(By.className("sapMRbBInn"));
		// List<WebElement> elements =
		// SeleniumUtil.findElementListByClassName(driver, "sapMRbBInn");
		switch (deptDetails.get("gender")) {
		case "Male":
			elements.get(0).click();
			break;
		case "Female":
			elements.get(2).click();
			break;
		case "Undeclared":
			elements.get(1).click();
			break;
		default:
			elements.get(0).click();
		}
		SeleniumUtil.sleep(2000);

		if (deptDetails.get("ssn") != null) {
			ssn.clear();
			ssn.sendKeys(deptDetails.get("ssn"));
		}

		if (deptDetails.get("maritalStatus") != null) {
			String maritalStatus = deptDetails.get("maritalStatus");
			maritalStatusDD.click();
			List<WebElement> listItems = SeleniumUtil.findElementListByClassName(driver, "sapMSelectListItemBase");
			for (WebElement listItem : listItems) {
				if (listItem.getText().equals(maritalStatus)) {
					listItem.click();
					break;
				}
			}
		}
		if (deptDetails.get("telephoneNo") != null) {
			telephoneNo.clear();
			telephoneNo.sendKeys(deptDetails.get("telephoneNo"));
		}

		if (deptDetails.get("street") != null) {
			deptStreet.clear();
			deptStreet.sendKeys(deptDetails.get("street"));
		}
		if (deptDetails.get("city") != null) {
			deptCity.clear();
			deptCity.sendKeys(deptDetails.get("city"));
		}
		if (deptDetails.get("state") != null) {
			String state = deptDetails.get("state");
			deptState.click();
			List<WebElement> listItems = SeleniumUtil.findElementListByClassName(driver, "sapMSelectListItemBase");
			for (WebElement listItem : listItems) {
				if (listItem.getText().equals(state)) {
					listItem.click();
					break;
				}
			}
		}
		if (deptDetails.get("zipcode") != null) {
			deptZipcode.clear();
			deptZipcode.sendKeys(deptDetails.get("zipcode"));
		}

//		if (deptDetails.get("isDisable") != null) {
//			if (deptDetails.get("isDisable").equals("true"))
//				disablityCb.click();
//			else
//				disablityCb.clear();
//			if (!deptDetails.get("disbDt").equals("")) {
//				disabilityDt.clear();
//				disabilityDt.sendKeys(deptDetails.get("disbDt"));
//			}
//		}
//		if (deptDetails.get("isStudent").equals("true"))
			studentCb.click();
//		else
//			studentCb.clear();
//
//		if (deptDetails.get("isMedicare").equals("true"))
			medicareCb.click();
//		else
//			medicareCb.clear();
//		if (deptDetails.get("isSmoker").equals("true"))
//			smokerCb.click();
//		else
//			smokerCb.clear();
//		if (deptDetails.get("isMilitary").equals("true"))
//			militaryCb.click();
//		else
//			militaryCb.clear();
//		if (deptDetails.get("isFinIndep").equals("true"))
			deptFinIndepCb.click();
//		else
//			deptFinIndepCb.clear();

		SeleniumUtil.sleep(2000);

		clickSaveDependentBtn();

	}

	public void clickNewDependentBtn() {
		List<WebElement> elementList = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMBtnEmphasized"),
				"DEPCREATE-inner", 2);
		for (WebElement element : elementList) {
			(new WebDriverWait(this.driver, 30)).until(ExpectedConditions.elementToBeClickable(element));
			element.click();
			break;
		}
	}

	public void clickSaveDependentBtn() {
		List<WebElement> elementList = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMBtnEmphasized"),
				"LEDEPSAVE-inner", 2);
		for (WebElement element : elementList) {
			(new WebDriverWait(this.driver, 30)).until(ExpectedConditions.elementToBeClickable(element));
			element.click();
			break;
		}
	}

	public void clickSave() {
		List<WebElement> elementList = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMBtnEmphasized"),
				"--DEPSAVE-inner", 2);
		for (WebElement element : elementList) {
			(new WebDriverWait(this.driver, 30)).until(ExpectedConditions.elementToBeClickable(element));
			element.click();
			break;
		}
	}

	public void clickChange() {
		List<WebElement> elementList = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMBtnEmphasized"),
				"--DEPEDIT-inner", 2);
		for (WebElement element : elementList) {
			(new WebDriverWait(this.driver, 30)).until(ExpectedConditions.elementToBeClickable(element));
			element.click();
			break;
		}
	}
	
	public boolean isChangeBtnVisible() {
		List<WebElement> elementList = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMBtnEmphasized"),
				"--DEPEDIT-inner", 2);
		if(elementList.size() >0)
			return true;
		else
			return false;
		
	}
	
	public boolean isNewDeptBtnVisible() {
		List<WebElement> elementList = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMBtnEmphasized"),
				"--DEPCREATE-inner", 2);
		if(elementList.size() >0)
			return true;
		else
			return false;
		
	}	
	
	public String checkSSNRequired(){
		String value = "";
		value = ssn.getAttribute("aria-required");
		return value;
	}
	
	public void confirmTermsnCondns() {
//		(new WebDriverWait(this.driver, 30)).until(ExpectedConditions.elementToBeClickable(termsCBox));
//		termsCBox.click(); 	
		confirmBtn.click();		
	}

}
