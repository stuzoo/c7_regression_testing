package com.aspirehr.pagefactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
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

public class RptLifeEvntChngPage extends DetailPage {
	WebDriver driver;
	@FindBy(id = "eventDate-inner")
	WebElement lifeEvtDt;

	@FindBy(id = "idLifeevents")
	WebElement lifeEventDD;

	@FindBy(id = "led0--depFamsa")
	WebElement deptSelectParent;

	@FindBy(id = "depFamsa-select")
	WebElement deptSelect;

	@FindBy(id = "led0--depFasex")
	WebElement genderRBGroup;

	@FindBy(id = "led0--depFirst-inner")
	WebElement deptFName;

	@FindBy(id = "led0--depLast-inner")
	WebElement deptLName;

	@FindBy(id = "led0--depDate-inner")
	WebElement deptDOB;

	@FindBy(id = "led0--depPeridUS-inner")
	WebElement ssn;

	@FindBy(id = "led0--depFamst")
	WebElement maritalStatusDD;

	@FindBy(id = "led0--depTelnr-inner")
	WebElement telephoneNo;

	@FindBy(id = "led0--depDisability-CbBg")
	WebElement disablityCb;

	@FindBy(id = "led0--depDisDt-inner")
	WebElement disabilityDt;

	@FindBy(id = "led0--depStudent-CbBg")
	WebElement studentCb;

	@FindBy(id = "led0--depMedicare-CbBg")
	WebElement medicareCb;

	@FindBy(id = "led0--depSmoker-CbBg")
	WebElement smokerCb;

	@FindBy(id = "led0--depMil-CbBg")
	WebElement militaryCb;

	@FindBy(id = "led0--depFinInd-CbBg")
	WebElement deptFinIndepCb;

	@FindBy(id = "led0--depStras-inner")
	WebElement deptStreet;

	@FindBy(id = "led0--depOrt01-inner")
	WebElement deptCity;

	@FindBy(id = "led0--depState")
	WebElement deptState;

	// depState-select
	@FindBy(id = "led0--depZipcode-inner")
	WebElement deptZipcode;

	@FindBy(id = "adjBtnAddAttachment")
	WebElement addAttachment;
	
	@FindBy(id = "fUpload-fu")
	WebElement fileBrowse;

	@FindBy(id = "adjBtnUpload-inner")
	WebElement fileUpload;

	@FindBy(id = "CONFLIFE--confirmEnroll-inner")
	WebElement confirmBtn;

	// private static final String REGEX_BENPERCENTAGE =
	// "(__item[0123456789]+)(-idBen-)([0123456789]+)(_cell)([123456789]+)";//
	// -idBen-0_cell4

	private static final String REGEX_DEPTID = "(__text[0123456789]+)(-ledDepList--)(idDep-[0123456789]+)"; // __text36-ledDepList--idDep-1

	public RptLifeEvntChngPage(WebDriver driver) {
		super(driver);
		this.driver = driver;

		// This initElements method will create all WebElements
		AjaxElementLocatorFactory ajaxFactory = new AjaxElementLocatorFactory(driver, 30);
		PageFactory.initElements(ajaxFactory, this);

	}

	public void selectLifeEvent(int lifeEventOpt) {
		// -idLifeevents-2-selectSingle-RB
		lifeEventOpt = lifeEventOpt - 1;
		String rbIdSuffix = "-idLifeevents-" + lifeEventOpt + "-selectSingle-Button";

		List<WebElement> elements = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMRbHoverable"),
				rbIdSuffix, 2);
		for (WebElement element : elements) {
			// element.findElement(By.className("sapMRbBInn")).click();
			element.click();
			break;
		}

	}

	public void selectLifeEventByName(String lifeEventOpt) {
		lifeEventDD.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<WebElement> listItems = SeleniumUtil.findElementListByClassName(driver, "sapMSelectListItem");
		for (WebElement listItem : listItems) {
			if (listItem.getText().equals(lifeEventOpt)) {
				listItem.click();
				break;
			}
		}
	}

	public void setLifeEventDate(String MMMdYYYYDate) {
		// eventDate-inner
		lifeEvtDt.sendKeys(MMMdYYYYDate);
//		lifeEvtDt.click();
		lifeEvtDt.sendKeys(Keys.RETURN);
//		List<WebElement> elements = SeleniumUtil.findElementListByClassName(driver, "sapMInputValHelp");
//		for (WebElement element : elements) {
//			element.click();
//			//element.click();
//			break;
//		}
		// lifeEvtDt.submit();
	}

	public void addDependents(Map<String, String> deptDetails) {
		// new
		// Select(deptSelect).selectByVisibleText(deptDetails.get("relationship"));
		String relationship = deptDetails.get("relationship");
		List<WebElement> dropdown = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMSltWithArrow"),
				"depFamsa");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dropdown.get(0).click();
		// deptSelectParent.click();
		dropdown.clear();

		List<WebElement> listItems = SeleniumUtil.findElementListByClassName(driver, "sapMSelectListItemBase");
		for (WebElement listItem : listItems) {
			if (listItem.getText().equals(relationship)){
				listItem.click();				
				break;
			}
		}
		
//		Select dropdown = new Select(listItems.get(0));
//		dropdown.selectByVisibleText(relationship);

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
		listItems = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMInputBaseInner"),
				"depFirst-inner");
		listItems.get(0).sendKeys(deptDetails.get("fname"));
		// deptFName.sendKeys(deptDetails.get("fname"));
		SeleniumUtil.sleep(2000);
		listItems.clear();
		listItems = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMInputBaseInner"), "depLast-inner");
		listItems.get(0).sendKeys(deptDetails.get("lname"));
		// deptLName.sendKeys(deptDetails.get("lname"));
		SeleniumUtil.sleep(2000);
		listItems.clear();
		listItems = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMInputBaseInner"), "depDate-inner");
		listItems.get(0).sendKeys(deptDetails.get("dob"));
		// deptDOB.sendKeys(deptDetails.get("dob"));
		SeleniumUtil.sleep(2000);
		// gender

		listItems.clear();
		listItems = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMRbG"), "depFasex");
		List<WebElement> elements = listItems.get(0).findElements(By.className("sapMRbBInn"));
		// List<WebElement> elements =
		// genderRBGroup.findElements(By.className("sapMRbBInn"));
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

		listItems.clear();
		listItems = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMInputBaseInner"),
				"depPeridUS-inner");
		listItems.get(0).sendKeys(deptDetails.get("ssn"));
		// ssn.sendKeys(deptDetails.get("ssn"));

		if (deptDetails.get("maritalStatus") != null) {
			String maritalStatus = deptDetails.get("maritalStatus");
			listItems.clear();
			listItems = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMSltWithArrow"), "depFamst");
			listItems.get(0).click();
			// maritalStatusDD.click();
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
			listItems.clear();
			listItems = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMInputBaseInner"),
					"depTelnr-inner");
			listItems.get(0).sendKeys(deptDetails.get("telephoneNo"));
			// telephoneNo.sendKeys(deptDetails.get("telephoneNo"));
		}

		if (deptDetails.get("street") != null) {
			listItems.clear();
			listItems = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMInputBaseInner"),
					"depStras-inner");
			listItems.get(0).clear();
			listItems.get(0).sendKeys(deptDetails.get("street"));
			// deptStreet.clear();
			// deptStreet.sendKeys(deptDetails.get("street"));
		}
		if (deptDetails.get("city") != null) {
			listItems.clear();
			listItems = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMInputBaseInner"),
					"depOrt01-inner");
			listItems.get(0).clear();
			listItems.get(0).sendKeys(deptDetails.get("city"));
			// deptCity.clear();
			// deptCity.sendKeys(deptDetails.get("city"));
		}
		if (deptDetails.get("state") != null) {
			listItems.clear();
			listItems = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMSltWithArrow"), "depState");
			String state = deptDetails.get("state");
			listItems.get(0).click();
			// deptState.click();
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
			listItems.clear();
			listItems = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMInputBaseInner"),
					"depZipcode-inner");
			listItems.get(0).clear();
			listItems.get(0).sendKeys(deptDetails.get("zipcode"));
			// deptZipcode.clear();
			// deptZipcode.sendKeys(deptDetails.get("zipcode"));
		}

		if (deptDetails.get("isDisable").equals("true")) {
			listItems.clear();
			listItems = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMCbBg"), "depDisability-CbBg");
			listItems.get(0).click();
			// disablityCb.click();
		}
		if (!deptDetails.get("disbDt").equals("")) {
			listItems.clear();
			listItems = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMInputBaseInner"),
					"depDisDt-inner");
			listItems.get(0).sendKeys(deptDetails.get("disbDt"));
			// disabilityDt.sendKeys(deptDetails.get("disbDt"));
		}
		if (deptDetails.get("isStudent").equals("true")) {
			listItems.clear();
			listItems = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMCbBg"), "depStudent-CbBg");
			listItems.get(0).click();
			// studentCb.click();
		}
		if (deptDetails.get("isMedicare").equals("true")) {
			listItems.clear();
			listItems = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMCbBg"), "depMedicare-CbBg");
			listItems.get(0).click();
			// medicareCb.click();
		}
		if (deptDetails.get("isSmoker").equals("true")) {
			listItems.clear();
			listItems = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMCbBg"), "depSmoker-CbBg");
			listItems.get(0).click();
			// smokerCb.click();
		}
		if (deptDetails.get("isMilitary").equals("true")) {
			listItems.clear();
			listItems = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMCbBg"), "depMilitary-CbBg");
			listItems.get(0).click();
			// militaryCb.click();
		}
		if (deptDetails.get("isFinIndep").equals("true")) {
			listItems.clear();
			listItems = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMCbBg"), "depFinInd-CbBg");
			listItems.get(0).click();
			// deptFinIndepCb.click();
		}

		SeleniumUtil.sleep(2000);

		saveDependent();

	}

public void changeDependent(Map<String,String> deptDetails){

	// new
	// Select(deptSelect).selectByVisibleText(deptDetails.get("relationship"));
	String relationship = deptDetails.get("relationship");
	List<WebElement> listItems = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMSltWithArrow"),
			"depFamsa", 3);
	listItems.get(0).click();
	// deptSelectParent.click();
	listItems.clear();

	listItems = SeleniumUtil.findElementListByClassName(driver, "sapMSelectListItemBase");
	for (WebElement listItem : listItems) {
		if (listItem.getText().equals(relationship))
			listItem.click();		
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
	listItems = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMInputBaseInner"),
			"depFirst-inner");
	listItems.get(0).clear();
	listItems.get(0).sendKeys(deptDetails.get("fname"));
	// deptFName.sendKeys(deptDetails.get("fname"));
	SeleniumUtil.sleep(2000);
	listItems.clear();
	listItems = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMInputBaseInner"), "depLast-inner");
	listItems.get(0).clear();
	listItems.get(0).sendKeys(deptDetails.get("lname"));
	// deptLName.sendKeys(deptDetails.get("lname"));
	SeleniumUtil.sleep(2000);
	listItems.clear();
	listItems = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMInputBaseInner"), "depDate-inner");
	listItems.get(0).clear();
	listItems.get(0).sendKeys(deptDetails.get("dob"));
	// deptDOB.sendKeys(deptDetails.get("dob"));
	SeleniumUtil.sleep(2000);
	// gender
	listItems.clear();
	listItems = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMRbG"), "depFasex");
	List<WebElement> elements = listItems.get(0).findElements(By.className("sapMRbBInn"));
	// List<WebElement> elements =
	// genderRBGroup.findElements(By.className("sapMRbBInn"));
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

	listItems.clear();
	listItems = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMInputBaseInner"),
			"depPeridUS-inner");
	listItems.get(0).clear();
	listItems.get(0).sendKeys(deptDetails.get("ssn"));
	// ssn.sendKeys(deptDetails.get("ssn"));

	if (deptDetails.get("maritalStatus") != null) {
		String maritalStatus = deptDetails.get("maritalStatus");
		listItems.clear();
		listItems = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMSltWithArrow"), "depFamst");
		listItems.get(0).click();
		// maritalStatusDD.click();
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
		listItems.clear();
		listItems = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMInputBaseInner"),
				"depTelnr-inner");
		listItems.get(0).clear();
		listItems.get(0).sendKeys(deptDetails.get("telephoneNo"));
		// telephoneNo.sendKeys(deptDetails.get("telephoneNo"));
	}

	if (deptDetails.get("street") != null) {
		listItems.clear();
		listItems = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMInputBaseInner"),
				"depStras-inner");
		listItems.get(0).clear();
		listItems.get(0).sendKeys(deptDetails.get("street"));
		// deptStreet.clear();
		// deptStreet.sendKeys(deptDetails.get("street"));
	}
	if (deptDetails.get("city") != null) {
		listItems.clear();
		listItems = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMInputBaseInner"),
				"depOrt01-inner");
		listItems.get(0).clear();
		listItems.get(0).sendKeys(deptDetails.get("city"));
		// deptCity.clear();
		// deptCity.sendKeys(deptDetails.get("city"));
	}
	if (deptDetails.get("state") != null) {
		listItems.clear();
		listItems = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMSltWithArrow"), "depState");
		String state = deptDetails.get("state");
		listItems.get(0).click();
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
		listItems.clear();
		listItems = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMInputBaseInner"),
				"depZipcode-inner");
		listItems.get(0).clear();
		listItems.get(0).sendKeys(deptDetails.get("zipcode"));
		// deptZipcode.clear();
		// deptZipcode.sendKeys(deptDetails.get("zipcode"));
	}

//	if (deptDetails.get("isDisable").equals("true")) {
//		listItems.clear();
//		listItems = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMCbBg"), "depDisability-CbBg");
//		if(listItems.get(0).isSelected() == false)
//		listItems.get(0).click();
//		// disablityCb.click();
//		if (!deptDetails.get("disbDt").equals("")) {
//			listItems.clear();
//			listItems = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMInputBaseInner"),
//					"depDisDt-inner");
//			listItems.get(0).clear();
//			listItems.get(0).sendKeys(deptDetails.get("disbDt"));
//			// disabilityDt.sendKeys(deptDetails.get("disbDt"));
//		}
//	}
//	
//	if (deptDetails.get("isStudent").equals("true")) {
//		listItems.clear();
//		listItems = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMCbBg"), "depStudent-CbBg");
//		if(listItems.get(0).isSelected() == false)
//			listItems.get(0).click();
//		// studentCb.click();
//	}
//	if (deptDetails.get("isMedicare").equals("true")) {
//		listItems.clear();
//		listItems = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMCbBg"), "depMedicare-CbBg");
//		if(listItems.get(0).isSelected() == false)
//		listItems.get(0).click();
//		// medicareCb.click();
//	}
//	if (deptDetails.get("isSmoker").equals("true")) {
//		listItems.clear();
//		listItems = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMCbBg"), "depSmoker-CbBg");
//		if(listItems.get(0).isSelected() == false)
//		listItems.get(0).click();
//		// smokerCb.click();
//	}
//	if (deptDetails.get("isMilitary").equals("true")) {
//		listItems.clear();
//		listItems = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMCbBg"), "depMilitary-CbBg");
//		if(listItems.get(0).isSelected() == false)
//		listItems.get(0).click();
//		// militaryCb.click();
//	}
//	if (deptDetails.get("isFinIndep").equals("true")) {
//		listItems.clear();
//		listItems = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMCbBg"), "depFinInd-CbBg");
//		if(listItems.get(0).isSelected() == false)
//		listItems.get(0).click();
//		// deptFinIndepCb.click();
//	}

	SeleniumUtil.sleep(2000);

	saveDependent();


}

	public void saveDependent() {
		List<WebElement> elementList = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMBtnEmphasized"),
				"LEDEPSAVE-inner", 2);
		for (WebElement element : elementList) {
			(new WebDriverWait(this.driver, 30)).until(ExpectedConditions.elementToBeClickable(element));
			element.click();
			break;
		}
	}
	
	public void CancelDependent() {
		List<WebElement> elementList = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMBtnEmphasized"),
				"DEPCANCEL-inner", 2);
		for (WebElement element : elementList) {
			(new WebDriverWait(this.driver, 30)).until(ExpectedConditions.elementToBeClickable(element));
			element.click();
			break;
		}
	}

	public void selectDependentByIndex(int deptNo) {

		String deptIdSuffix = "idDep-" + deptNo + "-selectSingle-Button";
		List<WebElement> elementList = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMRbBOut"),
				deptIdSuffix, 2);
		if (elementList.get(0) != null) {
			elementList.get(0).click();
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
				String deptId = m.group(3);// idDep-X in the list. X=
											// [0123456789]
				if (!deptId.equals("")) {
					String deptIdSuffix = deptId + "-selectSingle-Button";
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
	
	public ArrayList<String> getDeptList() {	
		//List<WebElement> deptListElements = SeleniumUtil.findElementListByClassName(this.driver, "sapMListTblCell");
		Pattern pattern = Pattern.compile(REGEX_DEPTID);
		Matcher m;
		String deptRBId;
		ArrayList<String> deptList = new ArrayList<String>();
		List<WebElement> deptListElements = SeleniumUtil.findElementListByClassName(driver, "sapMText");
		for (WebElement element : deptListElements) {
			deptRBId = element.getAttribute("id");
			m = pattern.matcher(deptRBId);
			if (m.matches()) {
				deptList.add(element.getText());	
			}			
		}
		return deptList;
	}

	public void uploadPDFFile() {
		String workingDir = System.getProperty("user.dir");
		try {
			List<WebElement> elements = SeleniumUtil.findElementListByClassName(driver, "sapUiFupInputMask");
			for (WebElement element : elements) {
				element.click();
				break;
			}
			SeleniumUtil.sleep(3000);
			Runtime.getRuntime()
					.exec(workingDir + "\\src\\main\\java\\com\\aspirehr\\testcase\\resources\\fileuploadscript.exe");
			SeleniumUtil.sleep(5000);
			// List<WebElement> elementList =
			// SeleniumUtil.findElementListByIdSuffix(driver,
			// By.className("sapMBtnEmphasized"), "adjBtnUpload-inner", 2);
			// for (WebElement element : elementList) {
			// (new WebDriverWait(this.driver,
			// 30)).until(ExpectedConditions.elementToBeClickable(element));
			// element.click();
			// break;
			// }
			fileUpload.click();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void uploadJPGFile() {
		String workingDir = System.getProperty("user.dir");
		try {
			List<WebElement> elements = SeleniumUtil.findElementListByClassName(driver, "sapUiFupInputMask");
			for (WebElement element : elements) {
				element.click();
				break;
			}
			SeleniumUtil.sleep(3000);
			Runtime.getRuntime()
					.exec(workingDir + "\\src\\main\\java\\com\\aspirehr\\testcase\\resources\\jpguploadscript.exe");
			SeleniumUtil.sleep(5000);
			// List<WebElement> elementList =
			// SeleniumUtil.findElementListByIdSuffix(driver,
			// By.className("sapMBtnEmphasized"), "adjBtnUpload-inner", 2);
			// for (WebElement element : elementList) {
			// (new WebDriverWait(this.driver,
			// 30)).until(ExpectedConditions.elementToBeClickable(element));
			// element.click();
			// break;
			// }
			fileUpload.click();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void uploadInvalidFile() {
		String workingDir = System.getProperty("user.dir");
		try {
			List<WebElement> elements = SeleniumUtil.findElementListByClassName(driver, "sapUiFupInputMask");
			for (WebElement element : elements) {
				element.click();
				break;
			}
			SeleniumUtil.sleep(3000);
			Runtime.getRuntime()
					.exec(workingDir + "\\src\\main\\java\\com\\aspirehr\\testcase\\resources\\invaliduploadscript.exe");
			SeleniumUtil.sleep(5000);
			// List<WebElement> elementList =
			// SeleniumUtil.findElementListByIdSuffix(driver,
			// By.className("sapMBtnEmphasized"), "adjBtnUpload-inner", 2);
			// for (WebElement element : elementList) {
			// (new WebDriverWait(this.driver,
			// 30)).until(ExpectedConditions.elementToBeClickable(element));
			// element.click();
			// break;
			// }
			fileUpload.click();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void clickAddAttachmentBtn() {
		addAttachment.click();
	}

	public void clickAddAddnlDeptBtn() {
		List<WebElement> elementList = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMBtnEmphasized"),
				"LEDEPADD-inner", 2);
		for (WebElement element : elementList) {
			(new WebDriverWait(this.driver, 30)).until(ExpectedConditions.elementToBeClickable(element));
			element.click();
			break;
		}
	}
	
	public void clickChangeBtn() {
		List<WebElement> elementList = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMBtnEmphasized"),
				"DEPEDIT-inner", 2);
		for (WebElement element : elementList) {
			(new WebDriverWait(this.driver, 30)).until(ExpectedConditions.elementToBeClickable(element));
			element.click();
			break;
		}
	}

	public void clickRemoveBtn() {
		List<WebElement> elementList = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMBtnEmphasized"),
				"REMOVE-inner", 2);
		for (WebElement element : elementList) {
			(new WebDriverWait(this.driver, 30)).until(ExpectedConditions.elementToBeClickable(element));
			element.click();
			break;
		}
	}

	public void submitChanges() {
		List<WebElement> elementList = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMBtnEmphasized"),
				"ADJUSTMENT-inner", 2);
		for (WebElement element : elementList) {
			(new WebDriverWait(this.driver, 30)).until(ExpectedConditions.elementToBeClickable(element));
			element.click();
			break;
		}
	}

	public void confirmTermsnCondns() {
		// (new WebDriverWait(this.driver,
		// 30)).until(ExpectedConditions.elementToBeClickable(termsCBox));
		// termsCBox.click();
		confirmBtn.click();
	}

}
