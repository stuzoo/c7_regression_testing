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

public class MedicalPage extends DetailPage {
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
	
	private static final String REGEX_PLAN_DETAILS = "__text\\d+-cont\\d+_\\d+";//__text170-cont0_1
	
//__input57-idFSA-0-inner

	public MedicalPage(WebDriver driver) {
		super(driver);
		this.driver = driver;

		// This initElements method will create all WebElements
		AjaxElementLocatorFactory ajaxFactory = new AjaxElementLocatorFactory(driver, 30);
		PageFactory.initElements(ajaxFactory, this);

	}

	// public ArrayList<String> getPlanDetails() {
	// List<WebElement> planForm =
	// SeleniumUtil.findElementListByClassName(this.driver,
	// "sapUiFormResGridCont");
	// ArrayList<String> planDtsList = new ArrayList<String>();
	// for (WebElement element : planForm) {
	// List<WebElement> planDetails =
	// element.findElements(By.className("sapUiRespGridSpanL6"));
	// for (WebElement formFields : planDetails) {
	// if (formFields.getText() != null &&
	// !formFields.getText().trim().equals("")) {
	// planDtsList.add(formFields.getText());
	// }
	// }
	// }
	// return planDtsList;
	// }

//	public ArrayList<String> getPlanDetails() {
//		List<WebElement> planForm = SeleniumUtil.findElementListByClassName(this.driver, "sapUiRFLContainer");
//		ArrayList<String> planDtsList = new ArrayList<String>();
//		for (WebElement element : planForm) {
//			List<WebElement> planDetails = element.findElements(By.className("sapUiRFLContainerContent"));
//			for (WebElement formFields : planDetails) {
//				if (formFields.getText() != null && !formFields.getText().trim().equals("")) {
//					planDtsList.add(formFields.getText());
//				}
//			}
//		}
//		return planDtsList;
//	}
	
	public ArrayList<String> getPlanDetails() {
		Pattern regEx = Pattern.compile(REGEX_PLAN_DETAILS);		
		List<WebElement> planForm = SeleniumUtil.findElementListByIdRegex(driver, By.className("sapUiRFLContainer"), regEx);
		ArrayList<String> planDtsList = new ArrayList<String>();
		for (WebElement element : planForm) {			
				if (element.getText() != null && !element.getText().trim().equals("")) {					
					planDtsList.add(element.getText().replaceAll("\\s", ""));
				}
			}		
		return planDtsList;
	}

	public ArrayList<String> checkNotEnrolled() {
		List<WebElement> planForm = SeleniumUtil.findElementListByClassName(this.driver, "notEnrolled");
		ArrayList<String> planDtsList = new ArrayList<String>();
		for (WebElement element : planForm) {
			planDtsList.add(element.getText());
		}
		return planDtsList;
	}

//	public void selectPlan(int planNo) {
//		try {
//			// List<WebElement> planList =
//			// SeleniumUtil.findElementListByClassName(driver,
//			// "sapMRbHoverable");
//			planNo = planNo - 1;
//			String planOptId = "-idHealth-" + planNo + "-selectSingle-Button";
//			List<WebElement> planList = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMLIBSelectS"),
//					planOptId, 3);
//			// for (WebElement plan : planList) {
//			// (new WebDriverWait(this.driver,
//			// 30)).until(ExpectedConditions.elementToBeClickable(plan));
//			// plan.click();
//			// plan.click();
//			// break;
//			// }
//
//			WebElement plan = planList.get(0);
//			if (null != plan) {
//				// (new WebDriverWait(this.driver,
//				// 30)).until(ExpectedConditions.elementToBeClickable(plan));
//				plan.click();
//				plan.click();
//			}
//		} catch (Exception e) {
//			System.err.println("Error occured while selecting Plan:" + e.getMessage());
//		}
//	}
	
	public void selectPlan(int planNo) {
		List<WebElement> planList = SeleniumUtil.findElementListByClassName(driver, "sapMRbHoverable");
		// for(WebElement plan : planList){
		// (new WebDriverWait(this.driver, 30))
		// .until(ExpectedConditions.elementToBeClickable(plan));
		// plan.click();
		// plan.click();
		// break;
		// }

		WebElement plan = planList.get(planNo - 1);
		if (null != plan) {
			(new WebDriverWait(this.driver, 30)).until(ExpectedConditions.elementToBeClickable(plan));
			plan.click();
			plan.click();
		}

	}

	public int getSelectedPlan() {
		int selectedPlan = -1;
		try {
			Pattern regEx = Pattern.compile(REGEX_PLANOPTIONLISTIP);
			List<WebElement> planList = SeleniumUtil.findElementListByIdRegex(driver, By.name("idHealth_selectGroup"),
					regEx, 2);
			int count = 0;
			for (WebElement plan : planList) {
				//(new WebDriverWait(this.driver, 30)).until(ExpectedConditions.elementToBeClickable(plan));
				String checkedAttr = plan.getAttribute("checked");
				count++;
				if (null != checkedAttr && checkedAttr.equalsIgnoreCase("true"))
				{		
					selectedPlan = count;
					break;					
				}	
				
			}
			
			return selectedPlan;
		} catch (Exception e) {
			System.err.println("Error occured while Finding the Selected Plan:" + e.getMessage());
		}
		return selectedPlan;
		
	}

	public void selectDependent(int dependentId) {
		try {
			dependentId = dependentId - 1;
			String deptUIId = "idDependants-" + dependentId + "-selectMulti-CbBg";
			List<WebElement> dependentList = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMCbMark"),
					deptUIId, 3);
			// findElementListByClassName(driver, "sapMCbMark");
			// SeleniumUtil.findElementListBySuffix();
			WebElement dependent = dependentList.get(0);
			if (null != dependent) {
				(new WebDriverWait(this.driver, 30)).until(ExpectedConditions.elementToBeClickable(dependent));
				dependent.click();
			}
		} catch (Exception e) {
			System.err.println("Error occured while selecting Dependent:" + e.getMessage());
		}

	}
	
	public int getSelectedDeptCount() {
		int selectedDeptCount = 0;
		try {
			Pattern regEx = Pattern.compile(REGEX_DEPTLISTIP);
			List<WebElement> deptList = SeleniumUtil.findElementListByIdRegex(driver, By.tagName("input"),
					regEx, 2);
			
			for (WebElement plan :deptList) {
				//(new WebDriverWait(this.driver, 30)).until(ExpectedConditions.elementToBeClickable(plan));
				String checkedAttr = plan.getAttribute("checked");				
				if (null != checkedAttr && checkedAttr.equalsIgnoreCase("true"))
				{					
					selectedDeptCount++;						
				}	
				
			}			
			return selectedDeptCount;
		} catch (Exception e) {
			System.err.println("Error occured while Finding the Selected Dependent Count:" + e.getMessage());
		}
		return selectedDeptCount;
		
	}

	public float getSelectedPlanCost(int planNo) {
		float newPlanCost = 0;
		String newPlanCostTxt = "";
		planNo = planNo - 1;
		String fldSuffix = "-idHealth-" + planNo;
		List<WebElement> elementList = SeleniumUtil.findElementListByIdSuffix(driver,
				By.className("sapMObjectNumberStatusNone"), fldSuffix, 2);

		for (WebElement element : elementList) {
			if (element.getText() != null && !element.getText().trim().equals("")) {
				newPlanCostTxt = element.getText();
				break;
			}
		}
		if (!newPlanCostTxt.equals(""))
			newPlanCost = Float.parseFloat(newPlanCostTxt);
		return newPlanCost;
	}

	public int getPlanOptionCount() {
		int planCount = 0;
		try{
		List<WebElement> planList = SeleniumUtil.findElementListByClassName(driver, "sapMRbHoverable");
		if (planList != null && !planList.isEmpty())
			planCount = planList.size();
		} catch (Exception e){
			planCount = 0;
		}		
		
		return planCount;
	}

	public int getDependentCount() {
		int deptCount = 0;
		try {
			Pattern regex = Pattern.compile(REGEX_DEPTLIST);
			List<WebElement> dependentList = SeleniumUtil.findElementListByIdRegex(driver, By.className("sapMCbMark"),
					regex, 2);// IdSuffix(driver, By.className("sapMCbMark"),
								// deptUIId, 3);
			// findElementListByClassName(driver, "sapMCbMark");
			// SeleniumUtil.findElementListBySuffix();
			if (dependentList != null && !dependentList.isEmpty())
				deptCount = dependentList.size();
		} catch (Exception e) {
			System.err.println("Error occured while getting Dependent List:" + e.getMessage());
		}
		return deptCount;
	}

	
	public String getPlanHeaderText() {
		WebElement element = SeleniumUtil.findInputElementByClassName(driver, "sapMListHdr", "idHealth-header", "");
		String headerText = element.getText();
		return headerText;
	}
}
