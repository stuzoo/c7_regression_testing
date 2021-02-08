package com.aspirehr.pagefactory;

import java.util.HashMap;
import java.util.List;
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

public class DetailPage {
	WebDriver driver;
	@FindBy(id = "__xmlview2--detailPage-title")
	WebElement pageTitle;

	@FindBy(id = "__xmlview2--iconTabFilter1-tab")
	WebElement updateIconTab;

	@FindBy(id = "__xmlview2--SAVE")
	WebElement saveBtn;

	private static final String REGEX_TOTAL_COST = "(Total Employee Cost:\\s+)([0123456789.,-]+)(\\s+USD)";
	private static final String REGEX_TOTAL_COST1 = "(Per period on\\s+)([0123456789/:]+)(\\s+)([0123456789.,-]+)(\\s+USD)"; // Per
																																// period
																																// on
																																// 04/17/2017:
																																// 357.84
																																// USD
	private static final String REGEX_TOTAL_COST2 = "(Total Cost Per Period on\\s+)([0123456789/:]+)(\\s+)([$0123456789.,-]+)";//Total Cost Per Period on 01/01/2019: $229.87
	private static final String REGEX_PLAN_COST = "([0123456789.,-]+)([\\s+USD]?)";
	private static final String REGEX_BACKBTN = "__xmlview\\d+--idAppControl-MasterBtn";
	public static float currentTotalCost = 0;
	public static float currentPlanCost = 0;
	public static float newPlanCost = 0;
	public static float newTotalCost = 0;

	public static final String REGEX_INS_PLANOPTIONRB = "__item\\d+-idInsurance-\\d+-selectSingle-RB";
	public static final String REGEX_INS_UOC = "__input\\d+-idInsurance-\\d+-inner";
	public static final String REGEX_FSA_PLANOPTIONRB = "__item\\d+-idFSA-\\d+-selectSingle-RB";
	public static final String REGEX_FSA_PLANOPTIONIP = "__input\\d+-idFSA-\\d+-inner";

	public DetailPage(WebDriver driver) {

		this.driver = driver;

		// This initElements method will create all WebElements
		AjaxElementLocatorFactory ajaxFactory = new AjaxElementLocatorFactory(driver, 30);
		PageFactory.initElements(ajaxFactory, this);

	}
	
	public String getPageText() {
		// (new WebDriverWait(this.driver, 30))
		// .until(ExpectedConditions.textToBePresentInElement(pageTitle,
		// "MEDICAL"));
		List<WebElement> elementList = SeleniumUtil.findElementListByClassName(driver, "sapUiVltCell");
		String pageText = "";
		for (WebElement element : elementList) {
			//// System.out.println(element.getAttribute("id") + "::" +
			//// element.getText());
			if (element.getText() != null && !element.getText().trim().equals("")) {
				pageText = element.getText();
				break;
			}
		}
		return pageText;
	}

	public String getPageTitle() {
		// (new WebDriverWait(this.driver, 30))
		// .until(ExpectedConditions.textToBePresentInElement(pageTitle,
		// "MEDICAL"));
		List<WebElement> elementList = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMBarChild"),
				"detailPage-title", 3);
		String pageTitle = "";
		for (WebElement element : elementList) {
			//// System.out.println(element.getAttribute("id") + "::" +
			//// element.getText());
			if (element.getText() != null && !element.getText().trim().equals("")) {
				pageTitle = element.getText();
				break;
			}
		}
		return pageTitle;
	}

	public void clickDetailPageNavBackBtn() {
		// List<WebElement> elementList =
		// SeleniumUtil.findElementListByIdSuffix(driver,
		// By.className("sapMFocusable"),
		// "idAppControl-MasterBtn-inner", 2);
		// try {
		// Pattern regex = Pattern.compile(REGEX_BACKBTN);
		// //__xmlview0--idAppControl-MasterBtn-inner
		// //List<WebElement> elementList =
		// SeleniumUtil.findElementListByIdRegex(driver,
		// By.className("sapMBtnDefault"), regex, 3);
		// List<WebElement> elementList =
		// SeleniumUtil.findElementListByIdRegex(driver,
		// By.className("sapMBarChild"), regex, 3);
		// //(driver,
		// By.className("sapMFocusable"),"idAppControl-MasterBtn-inner", 2);
		// System.out.println("Back btn size::"+elementList.size());
		// for (WebElement element : elementList) {
		// System.out.println("Back btn id::"+element.getAttribute("id"));
		// (new WebDriverWait(this.driver,
		// 30)).until(ExpectedConditions.elementToBeClickable(element));
		// element.click();
		// break;
		// }
		// }catch (Exception e) {
		// System.err.println("Error occured while getting back button:" +
		// e.getMessage());
		// }

		WebElement element = driver.findElement(By.id("__xmlview2--detailPage-navButton"));													
		element.click();
	}

	public void clickUpdate() {
		// TODO Auto-generated method stub
		// (new WebDriverWait(this.driver, 30))
		// .until(ExpectedConditions.elementToBeClickable(updateIconTab));
		List<WebElement> elementList = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMITBTab"),
				"iconTabFilter1-tab", 2);
		for (WebElement element : elementList) {
			(new WebDriverWait(this.driver, 30)).until(ExpectedConditions.elementToBeClickable(element));
			element.click();
			break;
		}
		// updateIconTab.click();
	}

	public boolean isUpdateBtnVisible() {
		boolean visibility = false;
		List<WebElement> elementList = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMITBTab"),
				"iconTabFilter1-tab", 2);
		for (WebElement element : elementList) {
			(new WebDriverWait(this.driver, 30)).until(ExpectedConditions.elementToBeClickable(element));
			visibility = true;
			break;
		}
		return visibility;
	}

	public void savePlan() {
		List<WebElement> elementList = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMBtnEmphasized"),
				"SAVE-inner", 2);
		for (WebElement element : elementList) {
			// element.click();
			SeleniumUtil.clickButton(driver, element);
			break;
		}
	}

	public void goToEnrollment() {
		List<WebElement> elementList = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMBtnEmphasized"),
				"SAVELAST-inner", 2);
		for (WebElement element : elementList) {
			(new WebDriverWait(this.driver, 30)).until(ExpectedConditions.elementToBeClickable(element));
			// element.click();
			SeleniumUtil.clickButton(driver, element);
			break;
		}
	}

	public void stopPlan() {
		List<WebElement> elementList = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMBtnEmphasized"),
				"STOP-inner", 2);
		for (WebElement element : elementList) {
			(new WebDriverWait(this.driver, 30)).until(ExpectedConditions.elementToBeClickable(element));
			// element.click();
			SeleniumUtil.clickButton(driver, element);
			break;
		}
	}

	public void undoStopPlan() {
		List<WebElement> elementList = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMBtnEmphasized"),
				"CONTINUE-inner", 2);
		for (WebElement element : elementList) {
			(new WebDriverWait(this.driver, 30)).until(ExpectedConditions.elementToBeClickable(element));
			// element.click();
			SeleniumUtil.clickButton(driver, element);
			break;
		}
	}

	public void undoPendingPlan() {
		List<WebElement> elementList = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMBtnEmphasized"),
				"UNPEND-inner", 2);
		for (WebElement element : elementList) {
			(new WebDriverWait(this.driver, 30)).until(ExpectedConditions.elementToBeClickable(element));
			// element.click();
			SeleniumUtil.clickButton(driver, element);
			break;
		}
	}

	public boolean isStopBtnVisible() {
		boolean stopBtnVisibility = false;
		try {
			List<WebElement> elementList = SeleniumUtil.findElementListByIdSuffix(driver,
					By.className("sapMBtnEmphasized"), "STOP-inner", 2);
			if (elementList == null || elementList.isEmpty())
				stopBtnVisibility = false;
			else
				stopBtnVisibility = true;
		} catch (Exception e) {
			stopBtnVisibility = false;
		}
		return stopBtnVisibility;
	}

	public boolean isUndoStopBtnVisible() {
		boolean undoStopBtnVisibility = false;
		try {
			List<WebElement> elementList = SeleniumUtil.findElementListByIdSuffix(driver,
					By.className("sapMBtnEmphasized"), "CONTINUE-inner", 2);
			if (elementList == null || elementList.isEmpty())
				undoStopBtnVisibility = false;
			else
				undoStopBtnVisibility = true;
		} catch (Exception e) {
			undoStopBtnVisibility = false;
		}
		return undoStopBtnVisibility;
	}

	public boolean isUndoPendingBtnVisible() {
		boolean undoStopBtnVisibility = false;
		try {
			List<WebElement> elementList = SeleniumUtil.findElementListByIdSuffix(driver,
					By.className("sapMBtnEmphasized"), "UNPEND-inner", 2);
			if (elementList == null || elementList.isEmpty())
				undoStopBtnVisibility = false;
			else
				undoStopBtnVisibility = true;
		} catch (Exception e) {
			undoStopBtnVisibility = false;
		}
		return undoStopBtnVisibility;
	}

	public String getStopPlanMessage() {
		String stopPlanMessage = "";
		List<WebElement> elementList = SeleniumUtil.findElementListByIdSuffix(driver,
				By.className("sapMDialogScrollCont"), "-scrollCont", 2);
		for (WebElement element : elementList) {
			stopPlanMessage = element.getText();
			break;
		}
		return stopPlanMessage;
	}

	public String getUndoPendingMessage() {
		String stopPlanMessage = "";
		List<WebElement> elementList = SeleniumUtil.findElementListByIdSuffix(driver,
				By.className("sapMDialogScrollCont"), "-scrollCont", 2);
		for (WebElement element : elementList) {
			stopPlanMessage = element.getText();
			break;
		}
		return stopPlanMessage;
	}

	public String getDeptSelectionErrMessage() {
		String deptSelectMessage = "";
		List<WebElement> elementList = SeleniumUtil.findElementListByIdSuffix(driver,
				By.className("sapMDialogScrollCont"), "-scrollCont", 3);
		for (WebElement element : elementList) {
			deptSelectMessage = element.getText();
			break;
		}
		return deptSelectMessage;
	}

	public String getErrorMessage() {
		String deptSelectMessage = "";
		List<WebElement> elementList = SeleniumUtil.findElementListByIdSuffix(driver,
				By.className("sapMDialogScrollCont"), "-scrollCont", 3);
		
		//List<WebElement> elementList = SeleniumUtil.findElementListByClassName(driver, "sapMMsgBoxText");
		for (WebElement element : elementList) {
			deptSelectMessage = element.getText();
			break;
		}
		return deptSelectMessage;
	}

	public void closeMessageBox() {
		
		List<WebElement> elementList = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMBtnDefault"),"-inner", 2);		
		//List<WebElement> elementList = SeleniumUtil.findElementListByClassName(driver, "sapMBtnContent");
		System.out.println("Message box buttons"+elementList);
		for (WebElement element : elementList) {
			try {
			(new WebDriverWait(this.driver, 30)).until(ExpectedConditions.elementToBeClickable(element));
			// element.click();
			SeleniumUtil.clickButton(driver, element);
			break;
			} catch(Exception e) {
				System.err.println(e.getMessage());
			}
		}
	}

	public float getTotalCostFromHeader() {
		// (new WebDriverWait(this.driver,
		// 30)).until(ExpectedConditions.textToBePresentInElement(totalCost,
		// "Reference Check"));
		String totalCostText = "";
		float totCostAmt = 0;
		try {
			List<WebElement> elementList = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMText"),
					"totalLabel", 2);
			String pageTitle = "";
			for (WebElement element : elementList) {
				//// System.out.println(element.getAttribute("id") + "::" +
				//// element.getText());
				if (element.getText() != null && !element.getText().trim().equals("")) {
					totalCostText = element.getText();
					break;
				}
			}
			//// System.out.println("totalCostText::" + totalCostText);
			String totalCostAmtTxt = "";

			StringBuilder sb = new StringBuilder();
			sb.setLength(0);
			sb.append(totalCostText);
			Pattern p = Pattern.compile(REGEX_TOTAL_COST2);
			Matcher m = p.matcher(sb);
			while (m.find()) {
				totalCostAmtTxt = m.group(4);
				// //System.out.println(totalCostAmtTxt);
				if (totalCostAmtTxt.contains(",")) {
					// //System.out.println("yes");
					totalCostAmtTxt = totalCostAmtTxt.replaceAll("[,]*", "");
					// //System.out.println("After replacement of , -
					// "+totalCostAmtTxt);
				}
				totalCostAmtTxt = totalCostAmtTxt.replace("$", "");

				totCostAmt = Float.parseFloat(totalCostAmtTxt);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return totCostAmt;

	}

	public String getPlanCostFromHeader() {
		String planCostText = "";
		String planCostAmtTxt = ""; // Excluding CURR string
		float planCostAmt = 0;
		try {
			List<WebElement> elementList = SeleniumUtil.findElementListByIdSuffix(driver,
					By.className("sapMObjectNumberStatusNone"), "objectHeader-number", 1);
			String pageTitle = "";
			for (WebElement element : elementList) {
				// System.out.println(element.getAttribute("id") + "::" +
				// element.getText());
				if (element.getText() != null && !element.getText().trim().equals("")) {
					planCostText = element.getText();
					break;
				}
			}
			
			//// System.out.println("planCostText::" + planCostText);

			//// Excluding Currency string.
			// StringBuilder sb = new StringBuilder();
			// sb.setLength(0);
			// sb.append(planCostText);
			// Pattern p = Pattern.compile(REGEX_PLAN_COST);
			// Matcher m = p.matcher(sb);
			// while (m.find()) {
			// planCostAmtTxt = m.group(1);
			// }

		} catch (Exception e) {
			System.out.println("Plan Amount Field not present in Detail Page Header");
		}

//		planCostAmtTxt = planCostAmtTxt.replaceAll("[,]*", "");
		planCostText = planCostText.replaceAll("[,]*", "");
		return planCostText;
	}

	public String getPlanStatusFromHeader() {
		List<WebElement> elementList = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMObjStatus"),
				"__status0", 1);
		String planStatus = "";
		for (WebElement element : elementList) {
			// System.out.println(element.getAttribute("id") + "::" +
			// element.getText());
			if (element.getText() != null && !element.getText().trim().equals("")) {
				planStatus = element.getText();
				break;
			}
		}
		return planStatus;
	}

	public String getPlanDateFromHeader() {
		List<WebElement> elementList = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMObjStatusNone"),
				"__status1", 1);
		String planDate = "";
		for (WebElement element : elementList) {
			// System.out.println(element.getAttribute("id") + "::" +
			// element.getText());
			if (element.getText() != null && !element.getText().trim().equals("")) {
				planDate = element.getText();
				break;
			}
		}
		return planDate;
	}

	public String getPlanNameFromHeader() {
		List<WebElement> elementList = SeleniumUtil.findElementListByIdSuffix(driver,
				By.className("sapMObjectAttributeDiv"), "__attribute2", 1);
		String planName = "";
		for (WebElement element : elementList) {
			// System.out.println(element.getAttribute("id") + "::" +
			// element.getText());
			if (element.getText() != null && !element.getText().trim().equals("")) {
				planName = element.getText();
				break;
			}
		}
		return planName;
	}

	public String getPlanCoverageFromHeader() {
		List<WebElement> elementList = SeleniumUtil.findElementListByIdSuffix(driver,
				By.className("sapMObjectAttributeDiv"), "__attribute3", 1);
		String planCoverage = "";
		for (WebElement element : elementList) {
			// System.out.println(element.getAttribute("id") + "::" +
			// element.getText());
			if (element.getText() != null && !element.getText().trim().equals("")) {
				planCoverage = element.getText();
				break;
			}
		}
		return planCoverage;
	}

	public HashMap<String, String> getPlanDetailsFromHeader() {
		HashMap<String, String> planDetailsFrmHdr = new HashMap<String, String>();
		// String planCost = getPlanCostFromHeader();
		planDetailsFrmHdr.put("planamt", getPlanCostFromHeader());
		planDetailsFrmHdr.put("planname", getPlanNameFromHeader());
		planDetailsFrmHdr.put("planstatus", getPlanStatusFromHeader());
		planDetailsFrmHdr.put("plancoverage", getPlanCoverageFromHeader());
		// planDetailsFrmHdr.put("plancoverage", "test");
		return planDetailsFrmHdr;
	}

}
