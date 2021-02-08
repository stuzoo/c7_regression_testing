package com.aspirehr.pagefactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aspirehr.util.SeleniumUtil;

public class MasterListPage {
	/**
	 * 
	 * All WebElements are identified by @FindBy annotation
	 * 
	 */

	WebDriver driver;

	// @FindBy(id = "__xmlview1--list-listUl")
	// WebElement pageTitle;
	//
	// @FindBy(id = "__xmlview2--totalLabel")
	// WebElement totalCost;
	private static final String REGEX_LINKID = "(__item[0123456789]+-)(__xmlview[0123456789]+--list-[0123456789]+)(-titleText-inner)"; // __item0-__xmlview1--list-5-titleText-inner

	public MasterListPage(WebDriver driver) {

		this.driver = driver;

		// This initElements method will create all WebElements
		AjaxElementLocatorFactory ajaxFactory = new AjaxElementLocatorFactory(driver, 30);
		PageFactory.initElements(ajaxFactory, this);

	}
	
	public void setDriver(WebDriver driver){
		this.driver = driver;
	}

	// Get the Master List
	public ArrayList<String> getMasterList() {
		List<WebElement> masterListElements = SeleniumUtil.findElementListByClassName(this.driver, "sapMTextMaxLine");
		ArrayList<String> masterList = new ArrayList<>();
		for (WebElement element : masterListElements) {
			masterList.add(element.getText());
		}
		return masterList;
	}

	public String clickMasterListItem(String linkName) {
		String linkId = "";
		List<WebElement> masterListElements = SeleniumUtil.findElementListByClassName(this.driver, "sapMTextMaxLine");
		for (WebElement element : masterListElements) {
			if (element.getText().equals(linkName)) {
				(new WebDriverWait(this.driver, 30)).until(ExpectedConditions.elementToBeClickable(element));
				// System.out.println("Master List Item
				// Clicked:"+element.getText());
				
				
				linkId = element.getAttribute("id");
				element.click();
				
				break;
			}
			// else{
			// System.out.println("Master List Item :"+element.getText());
			// }
		}
		return linkId;
	}
	
	public String clickMasterListItemByIdSuffix(String linkIDSuffix, String linkName) {
		String linkId = "";
		List<WebElement> masterListElements = SeleniumUtil.findElementListByIdSuffix(driver,
				By.className("sapMTextMaxLine"), linkIDSuffix);
		// ByClassName(this.driver, "sapMTextMaxLine");
		for (WebElement element : masterListElements) {
			if (element.getText().equals(linkName)) {
				(new WebDriverWait(this.driver, 30)).until(ExpectedConditions.elementToBeClickable(element));
				//System.out.println("Master List Item Clicked:" + element.getText());
				element.click();
				linkId = element.getAttribute("id");
				break;
			}
			// else{
			// System.out.println("Master List Item :"+element.getText());
			// }
		}
		return linkId;
	}
	
	public HashMap<String, String> getSelMasterListDtsFromLinkName(String linkName) {
		
		String linkId = "";
		List<WebElement> masterListElements = SeleniumUtil.findElementListByClassName(this.driver, "sapMTextMaxLine");
		for (WebElement element : masterListElements) {
			if (element.getText().equals(linkName)) {
				(new WebDriverWait(this.driver, 30)).until(ExpectedConditions.elementToBeClickable(element));				
				linkId = element.getAttribute("id");
				break;
			}
			// else{
			// System.out.println("Master List Item :"+element.getText());
			// }
		}
		
		HashMap<String, String> selMasterListDetails = new HashMap<String, String>();
		String linkItemNo = "";
		String xmlViewNo = "";
		Pattern regex = Pattern.compile(REGEX_LINKID);
		Matcher m;
		m = regex.matcher(linkId);
		if (m.matches()) {
			linkItemNo = m.group(1);
			xmlViewNo = m.group(2);
		}
		String planAmtFldID = linkItemNo + xmlViewNo + "-number";// __item0-__xmlview1--list-5-number
		String planNameFldId = "__attribute0-" + xmlViewNo;// __attribute0-__xmlview1--list-5
		String enrolStatusFldId = xmlViewNo.substring(0, 10) + "---" + xmlViewNo; // __xmlview1---__xmlview1--list-5
		String deptCoverageFldId = "__attribute1-" + xmlViewNo; // __attribute1-__xmlview1--list-5

		WebElement plnAmtFld = null;
		WebElement planNameFld = null;
		WebElement enrolStatusFld = null;
		WebElement deptCoverageFld = null;

		try {
			plnAmtFld = driver.findElement(By.id(planAmtFldID));
		} catch (Exception e) {			
		}
		try {
			planNameFld = driver.findElement(By.id(planNameFldId));
		} catch (Exception e) {			
		}
		try {
			enrolStatusFld = driver.findElement(By.id(enrolStatusFldId));
		} catch (Exception e) {			
		}
		try {
			deptCoverageFld = driver.findElement(By.id(deptCoverageFldId));
		} catch (Exception e) {			
		}

		if (plnAmtFld != null)
			selMasterListDetails.put("planamt", plnAmtFld.getText());
		else
			selMasterListDetails.put("planamt", "");
		if (planNameFld != null)
			selMasterListDetails.put("planname", planNameFld.getText());
		else
			selMasterListDetails.put("planname", "");
		if (enrolStatusFld != null)
			selMasterListDetails.put("planstatus", enrolStatusFld.getText());
		else
			selMasterListDetails.put("planstatus", "");
		if (deptCoverageFld != null)
			selMasterListDetails.put("plancoverage", deptCoverageFld.getText());
		else
			selMasterListDetails.put("plancoverage", "");

		return selMasterListDetails;

	}

	public HashMap<String, String> getSelectedMasterListDetails(String linkId) {
		HashMap<String, String> selMasterListDetails = new HashMap<String, String>();
		String linkItemNo = "";
		String xmlViewNo = "";
		Pattern regex = Pattern.compile(REGEX_LINKID);
		Matcher m;
		m = regex.matcher(linkId);
		if (m.matches()) {
			linkItemNo = m.group(1);
			xmlViewNo = m.group(2);
		}
		String planAmtFldID = linkItemNo + xmlViewNo + "-number";// __item0-__xmlview1--list-5-number
		String planNameFldId = "__attribute0-" + xmlViewNo;// __attribute0-__xmlview1--list-5
		String enrolStatusFldId = xmlViewNo.substring(0, 10) + "---" + xmlViewNo; // __xmlview1---__xmlview1--list-5
		String deptCoverageFldId = "__attribute1-" + xmlViewNo; // __attribute1-__xmlview1--list-5

		WebElement plnAmtFld = null;
		WebElement planNameFld = null;
		WebElement enrolStatusFld = null;
		WebElement deptCoverageFld = null;

		try {
			plnAmtFld = driver.findElement(By.id(planAmtFldID));
		} catch (Exception e) {			
		}
		try {
			planNameFld = driver.findElement(By.id(planNameFldId));
		} catch (Exception e) {			
		}
		try {
			enrolStatusFld = driver.findElement(By.id(enrolStatusFldId));
		} catch (Exception e) {			
		}
		try {
			deptCoverageFld = driver.findElement(By.id(deptCoverageFldId));
		} catch (Exception e) {			
		}

		if (plnAmtFld != null)
			selMasterListDetails.put("planamt", plnAmtFld.getText());
		else
			selMasterListDetails.put("planamt", "");
		if (planNameFld != null)
			selMasterListDetails.put("planname", planNameFld.getText());
		else
			selMasterListDetails.put("planname", "");
		if (enrolStatusFld != null)
			selMasterListDetails.put("planstatus", enrolStatusFld.getText());
		else
			selMasterListDetails.put("planstatus", "");
		if (deptCoverageFld != null)
			selMasterListDetails.put("plancoverage", deptCoverageFld.getText());
		else
			selMasterListDetails.put("plancoverage", "");

		return selMasterListDetails;

	}

	public void clickBackButton() {
		List<WebElement> masterListElements = SeleniumUtil.findElementListByIdSuffix(driver,
				By.className("sapMBtnBack"), "masterPage-navButton-inner", 2);
		for (WebElement element : masterListElements) {
			(new WebDriverWait(this.driver, 30)).until(ExpectedConditions.elementToBeClickable(element));
			element.click();
			break;
		}
	}



	public int getEnrolledPlanCount() {
		int enrolledPlanCount = 0;
		try {
			List<WebElement> elemList = SeleniumUtil.findElementListByClassName(this.driver, "sapMObjStatusSuccess");

			for (WebElement element : elemList) {
				if (element.getText().contains("Enrolled")) {
					enrolledPlanCount++;
				}
			}
			// if(elemList != null && !elemList.isEmpty())
			// enrolledPlanCount = elemList.size();
		} catch (Exception e) {
			System.err.println("Error while checking Pending Plans:" + e.getMessage());
			enrolledPlanCount = 0;
		}
		return enrolledPlanCount;

	}

	public int getPendingPlanCount() {
		int pendingPlanCount = 0;
		try {
			List<WebElement> elemList = SeleniumUtil.findElementListByClassName(this.driver, "sapMObjStatusWarning");

			for (WebElement element : elemList) {
				if (element.getText().contains("Pending")) {
					pendingPlanCount++;
				}
			}
			// if(elemList != null && !elemList.isEmpty())
			// pendingPlanCount = elemList.size();
		} catch (Exception e) {
			System.err.println("Error while checking Pending Plans:" + e.getMessage());
			pendingPlanCount = 0;
		}
		return pendingPlanCount;
	}

	public int getAvailablePlanToEnrollCount() {
		int availablePlanCount = 0;
		try {
			List<WebElement> elemList = SeleniumUtil.findElementListByClassName(this.driver, "sapMObjStatusNone");

			for (WebElement element : elemList) {
				if (element.getText().contains("Enroll ")) {
					availablePlanCount++;
				}
			}
		} catch (Exception e) {
			System.err.println("Error while checking Available Plans:" + e.getMessage());
		}
		// if(elemList != null && !elemList.isEmpty())
		// availablePlanCount = elemList.size();
		return availablePlanCount;
	}

	public int getReEnrollPlanCount() {
		int reEnrollPlanCount = 0;
		try {
			List<WebElement> elemList = SeleniumUtil.findElementListByClassName(this.driver, "sapMObjStatusWarning");

			for (WebElement element : elemList) {
				if (element.getText().contains("Re-enroll")) {
					reEnrollPlanCount++;
				}
			}
			// if(elemList != null && !elemList.isEmpty())
			// pendingPlanCount = elemList.size();
		} catch (Exception e) {
			System.err.println("Error while checking ReEnroll Plans:" + e.getMessage());
			reEnrollPlanCount = 0;
		}
		return reEnrollPlanCount;
	}

}
