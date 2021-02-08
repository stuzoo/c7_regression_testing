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

public class FSADeptCarePage extends DetailPage {
	WebDriver driver;	
	
	public FSADeptCarePage(WebDriver driver) {
		super(driver);
		this.driver = driver;

		// This initElements method will create all WebElements
		AjaxElementLocatorFactory ajaxFactory = new AjaxElementLocatorFactory(driver, 30);
		PageFactory.initElements(ajaxFactory, this);

	}
	
//	public ArrayList<String> getPlanDetails() {
//		List<WebElement> planForm = SeleniumUtil.findElementListByClassName(this.driver, "sapUiFormResGridCont");		
//		ArrayList<String> planDtsList = new ArrayList<String>();
//		for (WebElement element : planForm) {
//			List<WebElement> planDetails = element.findElements(By.className("sapUiRespGridSpanL6"));
//			for(WebElement formFields :planDetails){
//			if (formFields.getText() != null &&  !formFields.getText().trim().equals("")) {
//				planDtsList.add(formFields.getText());
//			}
//		}
//		}
//		return planDtsList;
//	}
	
	public ArrayList<String> getPlanDetails() {
		List<WebElement> planForm = SeleniumUtil.findElementListByClassName(this.driver, "sapUiRFLContainer");
		ArrayList<String> planDtsList = new ArrayList<String>();
		for (WebElement element : planForm) {
			List<WebElement> planDetails = element.findElements(By.className("sapUiRFLContainerContent"));
			for (WebElement formFields : planDetails) {
				if (formFields.getText() != null && !formFields.getText().trim().equals("")) {
					planDtsList.add(formFields.getText());
				}
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
	
	public void selectPlan(){
		List<WebElement> planList = SeleniumUtil.findElementListByClassName(driver, "sapMRbHoverable");		
		for(WebElement plan : planList){
			(new WebDriverWait(this.driver, 30))
			.until(ExpectedConditions.elementToBeClickable(plan));
			plan.click();
			//plan.click();
			break;
		}
	}

	public void enterAnnualEmpContribution(String contribution) {
		List<WebElement> inputFldList = SeleniumUtil.findElementListByClassName(driver, "sapMListTblCell");
		for (WebElement inputFld : inputFldList) {
			//sapMInput sapMInputBase sapMInputBaseWidthPadding
			String inputFldId = inputFld.getAttribute("id");
			if (inputFldId.contains("-idFSA-0_cell5")) {
				WebElement input = inputFld.findElement(By.className("sapMInputBaseInner"));
				input.clear();
				input.sendKeys(contribution);	
//				input.sendKeys("");
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
	
	public String getSelectedPlanCost() {
		String fsaDeptCost = "";
		try {
			Pattern regEx = Pattern.compile(REGEX_FSA_PLANOPTIONIP);
			List<WebElement> planList = SeleniumUtil.findElementListByIdRegex(driver,
					By.className("sapMInputBaseInner"), regEx, 2);
			int count = -1;
			for (WebElement plan : planList) {
				// (new WebDriverWait(this.driver,
				// 30)).until(ExpectedConditions.elementToBeClickable(plan));
				fsaDeptCost = plan.getAttribute("value");
				if (fsaDeptCost != null && !fsaDeptCost.isEmpty()) {
					break;
				}
			}

		} catch (Exception e) {
			System.err.println("Error occured while Finding the Selected Plan Cost:" + e.getMessage());
		}

		return fsaDeptCost;
	}

	

}
