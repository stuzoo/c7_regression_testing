package com.aspirehr.pagefactory;

import java.text.DecimalFormat;
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

public class ReviewnEnrollPage extends DetailPage {
	/**
	 * 
	 * All WebElements are identified by @FindBy annotation
	 * 
	 */

	WebDriver driver;
	@FindBy(id = "__xmlview2--detailPage-title-inner")
	WebElement pageTitle;

	@FindBy(id = "__xmlview2--totalLabel")
	WebElement totalCost;

	// @FindBy(id = "__item52-idTable-0_cell7")
	// WebElement plannedEnrollments;
	// @FindBy(id = "__item54-idTableUnchanged-0_cell7")
	// WebElement stoppedEnrollments;
	// @FindBy(id = "__item53-idTableStopped-0_cell7")
	// WebElement unchangedEnrollments;

	@FindBy(id = "__xmlview2--ENROLL")
	WebElement enrollBtn;

	@FindBy(id = "_checkB-CbBg")
	WebElement termsCBox;

	@FindBy(id = "emailPrimary-inner")
	WebElement primaryEmail;

	@FindBy(id = "emailSecondary-inner")
	WebElement secondaryEmail;

	@FindBy(id = "confirmEnroll")
	WebElement confirmBtn;

	@FindBy(id = "cancelEnroll")
	WebElement cancelBtn;

	// private static final String REGEX_TOTAL_COST =
	// "(\\w+[:]\\s)(\\W+)(\\s\\w+)";
	private static final String REGEX_TOTAL_COST = "(Total Employee Cost:\\s+)([0123456789.,-]+)(\\s+USD)";
	private static final String REGEX_TOTAL_COST1 = "(Per period on\\s+)([0123456789/:]+)(\\s+)([0123456789.,-]+)(\\s+USD)"; // Per
																																// period
																																// on:
																																// 04/17/2017
																																// 357.84
																																// USD
	private static final String REGEX_TOTAL_COST2 = "(Total Cost Per Period on\\s+)([0123456789/:]+)(\\s+)([$0123456789.,-]+)";// Total
																																// Cost
																																// Per
																																// Period
																																// on
																																// 01/01/2019:
																																// $229.87
	private static final String REGEX_PLAN_COST = "(\\s*)([0123456789.,-]+)([\\s*USD]?)"; // 41.67
																							// USD
	// (A) 1,000.00 USD
	private static final String REGEX_PLAN_COST1 = "(\\s*)([0123456789.,-]+)(\\s?USD\\s?\\(?[A]?\\)?\\s?)([$0123456789.,-]*)([\\s*USD]?)";
	private static final String REGEX_PLAN_COST2 = "(\\s*)([$0123456789.,-]+)(\\s?\\(?[A]?\\)?\\s?)([$0123456789.,-]*)"; // $10.51
																															// (A)
																															// $200.00
	private static final String REGEX_DEPTLIST = "__item\\d+-idDependants-(\\d+)-selectMulti-CbBg";
	// Per period on: 04/17/2017 357.84 USD
	// private static final String REGEX_PLAN_COST =
	// "(\\s*)([0123456789.,-]+)(\\s*USD?)";

	public ReviewnEnrollPage(WebDriver driver) {
		super(driver);
		this.driver = driver;

		// This initElements method will create all WebElements
		AjaxElementLocatorFactory ajaxFactory = new AjaxElementLocatorFactory(driver, 30);
		PageFactory.initElements(ajaxFactory, this);

	}

	// public String getPageTitle() {
	// List<WebElement> elementList =
	// SeleniumUtil.findElementListByIdSuffix(driver,
	// By.className("sapMBarChild"),
	// "detailPage-title", 2);
	// String pageTitle = "";
	// for (WebElement element : elementList) {
	// System.out.println(element.getAttribute("id") + "::" +
	// element.getText());
	// if (element.getText() != null && !element.getText().trim().equals("")) {
	// pageTitle = element.getText();
	// break;
	// }
	// }
	// return pageTitle;
	//
	// }
	
	public String getAttestationDetails() {
		//List<WebElement> elemList = SeleniumUtil.findElementListByClassName(this.driver, "sapMListTblCell");
		WebElement attestationTable = driver.findElement(By.id("idAssert-listUl"));
		return attestationTable.getText();
	}
	
	public String getPlannedEnrollmentPlanDetails() {
		//List<WebElement> elemList = SeleniumUtil.findElementListByClassName(this.driver, "sapMListTblCell");
		WebElement plannedEnrolTable = driver.findElement(By.id("idTable-listUl"));
		return plannedEnrolTable.getText();
	}
	
	
	public String getUnChangedEnrollmentsPlanDetails() {
		//List<WebElement> elemList = SeleniumUtil.findElementListByClassName(this.driver, "sapMListTblCell");
		WebElement unChangedEnrolTable = driver.findElement(By.id("idTableUnchanged-listUl"));
		return unChangedEnrolTable.getText();
	}


	public float getTotalCost() {
		// (new WebDriverWait(this.driver,
		// 30)).until(ExpectedConditions.textToBePresentInElement(totalCost,
		// "Reference Check"));
		String totalCostText = "";
		List<WebElement> elementList = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMText"),
				"totalLabel", 2);
		String pageTitle = "";
		for (WebElement element : elementList) {
			// System.out.println(element.getAttribute("id") + "::" +
			// element.getText());
			if (element.getText() != null && !element.getText().trim().equals("")) {
				totalCostText = element.getText();
				break;
			}
		}
		System.out.println("totalCostText before processing::" + totalCostText);
		String totalCostAmtTxt = "";
		float totCostAmt = 0;
		StringBuilder sb = new StringBuilder();
		sb.setLength(0);
		sb.append(totalCostText);
		Pattern p = Pattern.compile(REGEX_TOTAL_COST2);
		Matcher m = p.matcher(sb);
		while (m.find()) {
			totalCostAmtTxt = m.group(4);
			// System.out.println(totalCostAmtTxt);
			if (totalCostAmtTxt.contains(",")) {
				// System.out.println("yes");
				totalCostAmtTxt = totalCostAmtTxt.replaceAll("[,]*", "");
				// System.out.println("After replacement of , -
				// "+totalCostAmtTxt);
			}
			totalCostAmtTxt = totalCostAmtTxt.replace("$", "");
			if (totalCostAmtTxt.contains("-")) {
				totalCostAmtTxt = totalCostAmtTxt.replace("-", "");
				totalCostAmtTxt = "-".concat(totalCostAmtTxt);
			}
			totCostAmt = Float.parseFloat(totalCostAmtTxt);
		}

		return totCostAmt;

	}

	public float getPlannedEnrollmentEECosts() {
		List<WebElement> elemList = SeleniumUtil.findElementListByClassName(this.driver, "sapMListTblCell");
		ArrayList<String> plannedEnrollmentEECosts = new ArrayList<String>();
		float plannedEnrTotalCost = 0;
		for (WebElement element : elemList) {
			if (element.getAttribute("id").contains("-idTable-") && element.getText() != null
					&& !element.getText().trim().equals("")) {
				if (element.getAttribute("id").contains("cell7")) {
					String costAmt = "";
					StringBuilder sb = new StringBuilder();
					sb.setLength(0);
					sb.append(element.getText());
					Pattern p = Pattern.compile(REGEX_PLAN_COST2);
					Matcher m = p.matcher(sb);
					while (m.find()) {
						costAmt = m.group(2);
						if (costAmt.contains(","))
							costAmt = costAmt.replaceAll("[,]*", "");
						costAmt = costAmt.replace("$", "");
					}
					plannedEnrollmentEECosts.add(costAmt);
					if (!costAmt.equals(""))
						plannedEnrTotalCost = plannedEnrTotalCost + Float.parseFloat(costAmt);
				}
			}
		}

		return plannedEnrTotalCost;
	}

	public float getStoppedEnrollmentEECosts() {
		List<WebElement> elemList = SeleniumUtil.findElementListByClassName(this.driver, "sapMListTblCell");
		ArrayList<String> stoppedEnrollmentEECosts = new ArrayList<String>();
		float stoppedEnrTotalCost = 0;
		for (WebElement element : elemList) {
			if (element.getAttribute("id").contains("-idTableStopped-") && element.getText() != null
					&& !element.getText().trim().equals("")) {
				if (element.getAttribute("id").contains("cell7")) {
					String costAmt = "";
					StringBuilder sb = new StringBuilder();
					sb.setLength(0);
					sb.append(element.getText());
					Pattern p = Pattern.compile(REGEX_PLAN_COST);
					Matcher m = p.matcher(sb);
					while (m.find()) {
						costAmt = m.group(2);
						if (costAmt.contains(","))
							costAmt = costAmt.replaceAll("[,]*", "");
					}
					stoppedEnrollmentEECosts.add(costAmt);
					if (!costAmt.equals(""))
						stoppedEnrTotalCost = stoppedEnrTotalCost + Float.parseFloat(costAmt);
				}
			}
		}
		return stoppedEnrTotalCost;
	}

	public float getUnchnagedEnrollmentEECosts() {
		List<WebElement> elemList = SeleniumUtil.findElementListByClassName(this.driver, "sapMListTblCell");
		ArrayList<String> unchangedEnrollmentEECosts = new ArrayList<String>();
		float unchangedEnrTotalCost = 0;
		for (WebElement element : elemList) {
			if (element.getAttribute("id").contains("-idTableUnchanged-") && element.getText() != null
					&& !element.getText().trim().equals("")) {
				if (element.getAttribute("id").contains("cell7")) {
					String costAmt = "";
					StringBuilder sb = new StringBuilder();
					sb.setLength(0);
					sb.append(element.getText());
					Pattern p = Pattern.compile(REGEX_PLAN_COST);
					Matcher m = p.matcher(sb);
					while (m.find()) {
						costAmt = m.group(2);
						if (costAmt.contains(","))
							costAmt = costAmt.replaceAll("[,]*", "");
					}
					unchangedEnrollmentEECosts.add(costAmt);
					if (!costAmt.equals(""))
						unchangedEnrTotalCost = unchangedEnrTotalCost + Float.parseFloat(costAmt);

				}
			}
		}
		return unchangedEnrTotalCost;
	}

	public float addTotalEECosts() {
		List<WebElement> elemList = SeleniumUtil.findElementListByClassName(this.driver, "sapMListTblCell");
		ArrayList<String> plannedEnrollmentEECosts = new ArrayList<String>();
		float totalEECost = 0;
		System.out.println("Cell Count::" + elemList.size());
		for (WebElement element : elemList) {
			if (!element.getAttribute("id").contains("Stopped")) { // should not
																	// add
																	// Stopped
																	// plan cost
				if (element.getAttribute("id").contains("cell7") || element.getAttribute("id").contains("cell8")) {
					if (element.getText() != null && !element.getText().trim().equals("")) {
						// if (element.getAttribute("id").contains("cell7")) {
						String costAmt = "";
						StringBuilder sb = new StringBuilder();
						sb.setLength(0);
						sb.append(element.getText());
						Pattern p = Pattern.compile(REGEX_PLAN_COST2);
						Matcher m = p.matcher(sb);
						while (m.find()) {
							costAmt = m.group(2);
							if (costAmt.contains(","))
								costAmt = costAmt.replaceAll("[,]*", "");
							costAmt = costAmt.replace("$", "");
						}
						plannedEnrollmentEECosts.add(costAmt);
						// System.out.println("costAmt::"+costAmt);
						if (!costAmt.equals("")) {
							// System.out.println(costAmt);
							totalEECost = totalEECost + Float.parseFloat(costAmt);
						}
					}
				}
			}
			// }
		}

		return totalEECost;
	}

	public void enrollPlans() {
		// (new WebDriverWait(this.driver,
		// 30)).until(ExpectedConditions.elementToBeClickable(enrollBtn));
		List<WebElement> elementList = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMBtnInverted"),
				"ENROLL", 2);
		for (WebElement element : elementList) {
			try {
			(new WebDriverWait(this.driver, 30)).until(ExpectedConditions.elementToBeClickable(element));
			// element.click();
			SeleniumUtil.clickEnrollButton(driver, element);
			break;
			} catch(Exception e) {
				
			}
		}
		// enrollBtn.click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		confirmTermsnCondns();

	}

	private void confirmTermsnCondns() {

		(new WebDriverWait(this.driver, 30)).until(ExpectedConditions.elementToBeClickable(termsCBox));
		termsCBox.click();

		primaryEmail.sendKeys("ssivasubramanian@aspirehr.com");
		secondaryEmail.sendKeys("ssivasubramanian@aspirehr.com");
		confirmBtn.click();
		// List<WebElement> elementList =
		// SeleniumUtil.findElementListByClassName(driver, "sapMBarChild");
		// for (WebElement element : elementList) {
		// System.out.println(element.getAttribute("id")+": "+
		// element.getText());
		// if (element.getText().toLowerCase().startsWith("confirm")) {
		// element.click();
		// element.click();
		// break;
		// }
		// }
		// *[@id="__button5"]

		// sapMBarChild sapMBtn sapMBtnBase
	}

	public String getErrorMessage() {
		String errorMessage = "";
		List<WebElement> elementList = SeleniumUtil.findElementListByIdSuffix(driver,
				By.className("sapMDialogScrollCont"), "-scrollCont", 2);
		for (WebElement element : elementList) {
			errorMessage = element.getText();
			break;
		}
		return errorMessage;
	}

	public void closeMessageBox() {
		List<WebElement> elementList = SeleniumUtil.findElementListByClassName(driver, "sapMBtnContent");
		for (WebElement element : elementList) {
			// element.click();
			try {
				(new WebDriverWait(this.driver, 30)).until(ExpectedConditions.elementToBeClickable(element));
				SeleniumUtil.clickButton(driver, element);
				break;
			} catch (Exception e) {
				System.out.println("Error while closing the message box:"+e.getMessage());
			}
		}
	}
	
	public void clickBack() {
		WebElement backBtn = driver.findElement(By.id("MAINPrint-inner"));
		backBtn.click();
	}

	public String limit2Decimal(float decNumber) {
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		String numberStr = df.format(decNumber);
		if (numberStr.contains(",")) {
			// System.out.println("yes");
			numberStr = numberStr.replaceAll("[,]*", "");
			// System.out.println("After replacement of , -
			// "+totalCostAmtTxt);
		}
		return numberStr;
	}

	public String getConfirmation() {
		String confirmationText = "";
		List<WebElement> elementList = SeleniumUtil.findElementListByClassName(driver, "sapUiFormTitleH5");
		for (WebElement element : elementList) {
			if (element.getTagName().equalsIgnoreCase("h5")) {
				confirmationText = element.getText();
				break;
			}
		}
		return confirmationText;
	}

	public Map<String, String> getPlannedMedical() {
		// TODO Auto-generated method stub
		List<WebElement> elemList = SeleniumUtil.findElementListByClassName(this.driver, "sapMListTblCell");
		ArrayList<String> plannedEnrollmentEECosts = new ArrayList<String>();
		Map<String, String> medPlanDetails = new HashMap<String, String>();
		String slifPlan = "";
		String elemId = "";
		for (WebElement element : elemList) {
			if (element.getAttribute("id").contains("-idTable-") && element.getText() != null
					&& !element.getText().trim().equals("")) {
				if (element.getAttribute("id").contains("cell2")) {
					String plan = element.getText();
					if (plan.contains("PPO - Indemnity Plan")) {
						slifPlan = plan;
						elemId = element.getAttribute("id");
						medPlanDetails.put("plan", plan);
						break;
					}
				}
			}
		}

		WebElement eePreCost = driver.findElement(By.id(elemId.replaceFirst("cell2", "cell7")));
		WebElement eePostCost = driver.findElement(By.id(elemId.replaceFirst("cell2", "cell8")));
		WebElement empContr = driver.findElement(By.id(elemId.replaceFirst("cell2", "cell9")));
		medPlanDetails.put("eepre", eePreCost.getText());
		medPlanDetails.put("eepost", eePostCost.getText());
		medPlanDetails.put("empcost", empContr.getText());

		return medPlanDetails;
	}

	public Map<String, String> getPlannedSLIFPlan() {
		// TODO Auto-generated method stub
		List<WebElement> elemList = SeleniumUtil.findElementListByClassName(this.driver, "sapMListTblCell");
		ArrayList<String> plannedEnrollmentEECosts = new ArrayList<String>();
		Map<String, String> slifPlanDetails = new HashMap<String, String>();
		String slifPlan = "";
		String elemId = "";
		for (WebElement element : elemList) {
			if (element.getAttribute("id").contains("-idTable-") && element.getText() != null
					&& !element.getText().trim().equals("")) {
				if (element.getAttribute("id").contains("cell2")) {
					String plan = element.getText();
					if (plan.contains("Supplemental Life Insurance")) {
						slifPlan = plan;
						elemId = element.getAttribute("id");
						slifPlanDetails.put("plan", plan);
						break;
					}
				}
			}
		}

		WebElement eePreCost = driver.findElement(By.id(elemId.replaceFirst("cell2", "cell7")));
		WebElement eePostCost = driver.findElement(By.id(elemId.replaceFirst("cell2", "cell8")));
		WebElement empContr = driver.findElement(By.id(elemId.replaceFirst("cell2", "cell9")));
		slifPlanDetails.put("eepre", eePreCost.getText());
		slifPlanDetails.put("eepost", eePostCost.getText());
		slifPlanDetails.put("empcost", empContr.getText());

		return slifPlanDetails;
	}

	public String getPlannedSADDPlan() {
		// TODO Auto-generated method stub
		List<WebElement> elemList = SeleniumUtil.findElementListByClassName(this.driver, "sapMListTblCell");
		ArrayList<String> plannedEnrollmentEECosts = new ArrayList<String>();
		String saddPlan = "";
		for (WebElement element : elemList) {
			if (element.getAttribute("id").contains("-idTable-") && element.getText() != null
					&& !element.getText().trim().equals("")) {
				if (element.getAttribute("id").contains("cell5")) {
					String plan = element.getText();
					if (plan.contains("Alternate Coverage")) {
						saddPlan = plan;
						break;
					}
				}
			}
		}
		return saddPlan;
	}
}
