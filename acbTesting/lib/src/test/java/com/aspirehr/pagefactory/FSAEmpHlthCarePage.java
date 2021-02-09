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

public class FSAEmpHlthCarePage extends DetailPage {
	WebDriver driver;
	private static final String REGEX_PLAN_DETAILS = "__text\\d+-cont\\d+_\\d+";//__text170-cont0_1
	public FSAEmpHlthCarePage(WebDriver driver) {
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
	// for(WebElement formFields :planDetails){
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
	
//	public ArrayList<String> getPlanDetails() {
//		Pattern regEx = Pattern.compile(REGEX_PLAN_DETAILS);		
//		List<WebElement> planForm = SeleniumUtil.findElementListByIdRegex(driver, By.className("sapUiRFLContainer"), regEx);
//		ArrayList<String> planDtsList = new ArrayList<String>();
//		for (WebElement element : planForm) {			
//				if (element.getText() != null && !element.getText().trim().equals("")) {					
//					planDtsList.add(element.getText().replaceAll("\\s", ""));
//				}
//			}		
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

	public void selectPlan(int planNo) {
		List<WebElement> planList = SeleniumUtil.findElementListByClassName(driver, "sapMRbHoverable");
		

		WebElement plan = planList.get(planNo - 1);
		if (null != plan) {
			(new WebDriverWait(this.driver, 30)).until(ExpectedConditions.elementToBeClickable(plan));
			plan.click();
			plan.click();
		}

	}

	public void enterAnnualEmpContribution(String Contribution) {
		List<WebElement> inputFldList = SeleniumUtil.findElementListByClassName(driver, "sapMListTblCell");
		for (WebElement inputFld : inputFldList) {
			// sapMInput sapMInputBase sapMInputBaseWidthPadding
			String inputFldId = inputFld.getAttribute("id");
			if (inputFldId.contains("-idFSA-0_cell5")) {
				WebElement input = inputFld.findElement(By.className("sapMInputBaseInner"));
				input.clear();
				input.sendKeys(Contribution);
				// input.sendKeys("");
			}
		}
	}

	public int getSelectedPlan() {
		int selectedPlan = -1;
		try {
			Pattern regEx = Pattern.compile(REGEX_FSA_PLANOPTIONRB);
			List<WebElement> planList = SeleniumUtil.findElementListByIdRegex(driver, By.name("idFSA_selectGroup"),
					regEx, 2);
			int count = -1;
			for (WebElement plan : planList) {
				// (new WebDriverWait(this.driver,
				// 30)).until(ExpectedConditions.elementToBeClickable(plan));
				String checkedAttr = plan.getAttribute("checked");
				count++;
				if (null != checkedAttr && checkedAttr.equalsIgnoreCase("true")) {
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

	public String getSelectedPlanCost() {
		String fsaCost = "";
		try {
			Pattern regEx = Pattern.compile(REGEX_FSA_PLANOPTIONIP);
			List<WebElement> planList = SeleniumUtil.findElementListByIdRegex(driver,
					By.className("sapMInputBaseInner"), regEx, 2);
			int count = -1;
			for (WebElement plan : planList) {
				// (new WebDriverWait(this.driver,
				// 30)).until(ExpectedConditions.elementToBeClickable(plan));
				fsaCost = plan.getAttribute("value");
				if (fsaCost != null && !fsaCost.isEmpty()) {
					break;
				}
			}

		} catch (Exception e) {
			System.err.println("Error occured while Finding the Selected Plan Cost:" + e.getMessage());
		}

		return fsaCost;
	}

}
