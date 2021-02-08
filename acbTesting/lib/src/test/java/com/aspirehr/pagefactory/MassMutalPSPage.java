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
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aspirehr.util.SeleniumUtil;

public class MassMutalPSPage extends DetailPage {
	WebDriver driver;	
	@FindBy(id = "savingPetInput-inner")
	WebElement regContributionAmt;
	
	private static final String REGEX_BENPERCENTAGE = "(__item[0123456789]+)(-idBen-)([0123456789]+)(_cell)([123456789]+)";//-idBen-0_cell4
	private static final String REGEX_PLAN_DETAILS = "__text\\d+-cont\\d+_\\d+";//__text170-cont0_1
	
	public MassMutalPSPage(WebDriver driver) {
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
	
	public void selectPlan(){
		List<WebElement> planList = SeleniumUtil.findElementListByClassName(driver, "sapMRbHoverable");		
		for(WebElement plan : planList){
			(new WebDriverWait(this.driver, 30))
			.until(ExpectedConditions.elementToBeClickable(plan));
			plan.click();
			plan.click();
			break;
		}
	}

	public void enterRegularContribution(String amount) {
		regContributionAmt.clear();
		regContributionAmt.sendKeys(amount);		
	}
	
	public void setBeneficiaryPercentage(HashMap<String, Map<String, String>> benList) {
		List<WebElement> inputFldList = SeleniumUtil.findElementListByClassName(driver, "sapMListTblCell");
		Pattern pattern = Pattern.compile(REGEX_BENPERCENTAGE);
		Matcher m;
		for (WebElement inputFld : inputFldList) {
			//sapMInput sapMInputBase sapMInputBaseWidthPadding
			String inputFldId = inputFld.getAttribute("id");
//			if (inputFldId.contains("-idBen-0_cell4") || inputFldId.contains("-idBen-1_cell3")) {
//				WebElement input = inputFld.findElement(By.className("sapMInputBaseInner"));
//				input.clear();
//				input.sendKeys("100");	
////				input.sendKeys("");
//			}
			
			m = pattern.matcher(inputFldId);
			if (m.matches()) {
				// resultList.add(e);
				String benNo = m.group(3);// Beneficiary No in the list
				String cellNo = m.group(5); // Cell No to find Primary or contingent percentage
				HashMap<String, String> benPercentage = (HashMap<String, String>) benList.get(benNo);
				if (cellNo.equals("3")) {
					 WebElement input =
							 inputFld.findElement(By.className("sapMInputBaseInner"));
					 input.clear();
					 input.click();
					 input.sendKeys(benPercentage.get("Primary"));
				} else if(cellNo.equals("4")){
					WebElement input =
							 inputFld.findElement(By.className("sapMInputBaseInner"));
					 input.clear();
					 input.click();
					 input.sendKeys(benPercentage.get("Contingent"));
				}

			}
		}
	}
	

}
