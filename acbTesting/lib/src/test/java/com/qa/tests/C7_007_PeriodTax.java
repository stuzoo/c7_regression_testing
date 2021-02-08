package com.qa.tests;

import static com.aspirehr.util.SeleniumUtil.login;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

import java.util.ArrayList;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aspirehr.pagefactory.ADnDPage;
import com.aspirehr.pagefactory.BasicLifePage;
import com.aspirehr.pagefactory.DentalPage;
import com.aspirehr.pagefactory.DependentLifePage;
import com.aspirehr.pagefactory.DeptDataPage;
import com.aspirehr.pagefactory.DetailPage;
import com.aspirehr.pagefactory.EnrollmentInstPage;
import com.aspirehr.pagefactory.FSADeptCarePage;
import com.aspirehr.pagefactory.FSAEmpHlthCarePage;
import com.aspirehr.pagefactory.FlexCreditPage;
import com.aspirehr.pagefactory.MassMutalPSPage;
import com.aspirehr.pagefactory.MasterListPage;
import com.aspirehr.pagefactory.MedicalPage;
import com.aspirehr.pagefactory.MiscHealthClubPage;
import com.aspirehr.pagefactory.PersonalDataPage;
import com.aspirehr.pagefactory.ReviewnEnrollPage;
import com.aspirehr.pagefactory.STDPage;
import com.aspirehr.pagefactory.Savings401KCatchupPage;
import com.aspirehr.pagefactory.Savings401KPlanPage;
import com.aspirehr.pagefactory.SuppADnDPage;
import com.aspirehr.pagefactory.SuppLifePage;
import com.aspirehr.pagefactory.VisionPage;
import com.aspirehr.pagefactory.WelcomePage;

//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class C7_007_PeriodTax {
	private static WebDriver driver;
	private static MasterListPage masterListPage;

	private static EnrollmentInstPage enrolInstPage;
	private static PersonalDataPage persDataPage;
	private static DeptDataPage deptDataPage;

	private static FlexCreditPage flexCreditPage;

	private static MedicalPage medicalPage;

	private static DentalPage dentalPage;
	private static VisionPage visionPage;

	private static ADnDPage aDnDPage;
	private static SuppADnDPage suppADnDPage;
	private static STDPage stdPage;
	private static BasicLifePage basicLifePage;
	private static DependentLifePage dependentLifePage;
	private static SuppLifePage suppLifePage;

	private static FSAEmpHlthCarePage fsaEmpHlthCarePage;
	private static FSADeptCarePage fsaDeptCareSpendingPage;

	private static Savings401KPlanPage savings401kPage;
	private static Savings401KCatchupPage savings401kCatchupPage;
	private static MiscHealthClubPage miscHealthClubPage;
	private static MassMutalPSPage massMutalPSPage;

	private static ReviewnEnrollPage reviewnEnrollPage;
	private static WelcomePage welcomePage;

	@BeforeClass
	public static void setup() {
		// System.setProperty("webdriver.gecko.driver",
		// "c:/Gecko/geckodriver.exe");
		// System.setProperty("webdriver.chrome.driver",
		// "C:/ChromeDriver/chromedriver.exe");
		FirefoxProfile ffProfile = new FirefoxProfile();
		ffProfile.setPreference("network.automatic-ntlm-auth.trusted-uris", "ahr-e68.erpsolutions.local:8000");
		// driver = new FirefoxDriver(ffProfile);
		// driver = new ChromeDriver();
		System.setProperty("webdriver.chrome.driver", "/Users/stu/Drivers/chromedriver");
		WebDriver driver = new ChromeDriver();
		// driver = new InternetExplorerDriver();
		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// driver.manage().window().maximize();
		// driver.get(
		// "http://clbe07:Test11@ahr-e68.erpsolutions.local:8000/sap/bc/ui5_ui5/ahrclben/cloud/index.html");
		String tmpUrl = "https://ahr-e68.erpsolutions.local:1443/sap/bc/ui5_demokit";
		String URL = "10.20.4.216:8443/sap/bc/ui5_ui5/ahrclben/cloud/index.html";
		// String URL =
		// "ahr-e68demo.erpsolutions.local:8000/sap/bc/ui5_ui5/ahrclben/cloud/index.html";
		String uid = "sbrock";
		String pwd = "aspire";

		// this is for IE
		// SeleniumUtil.tempLogin(driver, tmpUrl, uid, pwd, "010");
		// try {
		// Thread.sleep(5000);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// driver.get("http://" +URL);

		login(driver, uid, pwd, URL);
		masterListPage = new MasterListPage(driver);
		enrolInstPage = new EnrollmentInstPage(driver);
		persDataPage = new PersonalDataPage(driver);
		deptDataPage = new DeptDataPage(driver);
		flexCreditPage = new FlexCreditPage(driver);

		medicalPage = new MedicalPage(driver);
		dentalPage = new DentalPage(driver);
		visionPage = new VisionPage(driver);

		aDnDPage = new ADnDPage(driver);
		suppADnDPage = new SuppADnDPage(driver);
		stdPage = new STDPage(driver);
		basicLifePage = new BasicLifePage(driver);
		dependentLifePage = new DependentLifePage(driver);
		suppLifePage = new SuppLifePage(driver);
		fsaEmpHlthCarePage = new FSAEmpHlthCarePage(driver);
		fsaDeptCareSpendingPage = new FSADeptCarePage(driver);

		savings401kPage = new Savings401KPlanPage(driver);
		savings401kCatchupPage = new Savings401KCatchupPage(driver);
		miscHealthClubPage = new MiscHealthClubPage(driver);
		massMutalPSPage = new MassMutalPSPage(driver);

		reviewnEnrollPage = new ReviewnEnrollPage(driver);

		welcomePage = new WelcomePage(driver);
	}

	@Test(priority = 0)
	public void test1_MasterListPage_Loaded() {
		// Verify CB Master List page loaded with appropriate Enrollment Option.
		ArrayList<String> masterList = masterListPage.getMasterList();
		System.out.println("Master List Size::" + masterList.size());
		int masterListSize = masterList.size();
		//assertTrue(masterList.contains("New Hire Enrollment")); // E68
		// assertTrue(masterList.contains("Complete New Hire")); // Q68
     //		assertTrue(masterList.contains("Complete Anytime Enrollment")); // E68
	//	assertFalse(masterList.contains("Complete Open Enrollment"));
		Assert.assertEquals(true, masterList.contains("Complete Anytime Enrollment"));
		assertTrue(masterListSize == 7); // E68
		// assertTrue(masterListSize == 6); // Q68
		for (String masterListLink : masterList) {
			System.out.println(masterListLink);
		}
	}

	@Test(priority = 1, dependsOnMethods = { "test1_MasterListPage_Loaded" })
	public void test01_CreditDefaulting() {
		// Navigate to New Hire Enrollment--> Personal Data and verify Data
		masterListPage.clickMasterListItem("New Hire Enrollment"); // E68
		masterListPage.clickMasterListItem("New Hire Enrollment");
		// masterListPage.clickMasterListItem("Complete New Hire"); // Q68
		try {
			Thread.sleep(3000);

			masterListPage.clickMasterListItem("Personal Data");
			Thread.sleep(3000);
			String detPageTitle = persDataPage.getPageTitle();
			String empName = persDataPage.getName();
			String dob = persDataPage.getDOB();
			Map<String, String> permAddress = persDataPage.getPermAddress();
			Map<String, String> mailAddress = persDataPage.getMailingAddress();
			assertTrue(detPageTitle.equals("PERSONAL DATA"));
			assertTrue(empName.equals("Federer Roger"));
			assertTrue(dob.equals("07/24/1956"));
			assertTrue(permAddress.get("state").equals(""));
			assertTrue(permAddress.get("zip").equals("76039"));

			assertTrue(mailAddress.get("state").equals("Texas"));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// @Test(priority = 2, dependsOnMethods = { "test20_NewHire_PersonalData" })
	// public void test21_EnrolledPlan() {
	// int enrolledPlanCount = masterListPage.getEnrolledPlanCount();
	// assertTrue(enrolledPlanCount == 5);
	// }
	//
	// @Test(priority = 3, dependsOnMethods = { "test20_NewHire_PersonalData" })
	// public void test22_PendingPlan() {
	// int pendingPlanCount = masterListPage.getPendingPlanCount();
	// assertTrue(pendingPlanCount == 0);
	// }
	//
	// @Test(priority = 4, dependsOnMethods = { "test20_NewHire_PersonalData" })
	// public void test23_AvailablePlanToEnroll() {
	// int availablePlanCount = masterListPage.getAvailablePlanToEnrollCount();
	// assertTrue(availablePlanCount == 6);
	// }
	//
	// @Test(priority = 5, dependsOnMethods = { "test20_NewHire_PersonalData" })
	// public void test24_verifyTotalCostBeforeEnrollment() {
	// // masterListPage.clickMasterListItem("Review and Enroll");
	// // masterListPage.clickMasterListItem("Review and Enroll");
	// // try {
	// // Thread.sleep(3000);
	// // } catch (InterruptedException e) {
	// // // TODO Auto-generated catch block
	// // e.printStackTrace();
	// // }
	// // float totalCost = reviewnEnrollPage.getTotalCost();
	// // System.out.println("totalCost::" + totalCost);
	// // float tmp = (float) -25.89;
	// // assertTrue(totalCost == tmp);
	// test71_ReviewTotalCostAddition();
	// }

	// @Test(priority = 6, dependsOnMethods = { "test20_NewHire_PersonalData" })
	// public void test30_DeptListData() {
	// // masterListPage.clickMasterListItem("New Hire Enrollment");
	// try {
	// masterListPage.clickMasterListItem("Dependents and Beneficiaries");
	// Thread.sleep(3000);
	// ArrayList<String> deptList = deptDataPage.getDeptList();
	// System.out.println("Dept List:" + deptList.toString());
	// Boolean foundSpouse = false;
	// for (String dept : deptList) {
	// if (dept.equals("Jennifer Federer")) {
	// foundSpouse = true;
	// break;
	// }
	// }
	// assertTrue(foundSpouse);
	// } catch (InterruptedException e) {
	// e.printStackTrace();
	// }
	// }

	// @Test( priority=7 , dependsOnMethods = { "test20_NewHire_PersonalData" })
	// public void test31_FlexCredits() {
	// try {
	// masterListPage.clickMasterListItem("Flex Credits");
	// Thread.sleep(3000);
	// ArrayList<String> flexCreditDts = flexCreditPage.getFlexCreditDetails();
	// System.out.println("Flex Credits:" + flexCreditDts.toString());
	// Boolean foundFlxCreditAmt = false;
	// for (String flexCredit : flexCreditDts) {
	// if (flexCredit.equals("-100.00 USD")) {
	// foundFlxCreditAmt = true;
	// break;
	// }
	// }
	// assertTrue(foundFlxCreditAmt);
	//
	// // //Begin of Review total cost test reuse
	// //
	// // // Check the running Header Total cost match with the sum up of
	// // // individual EE cost.
	// // float flexCreditPageTotalCost =
	// // flexCreditPage.getTotalCostFromHeader();//
	// // test80_RunningTotalCost(flexCreditPageTotalCost);
	// //
	// // masterListPage.clickMasterListItem("Flex Credits");
	// // masterListPage.clickMasterListItem("Flex Credits");
	// //
	// // // End of Review total cost test reuse
	//
	// // Check the running Header Total cost match with the Detail Page
	// // NewTotalCost variable after the previous plan selection.
	// float currentTotalCost = flexCreditPage.getTotalCostFromHeader();
	// assertTrue(currentTotalCost == DetailPage.newTotalCost); // Initial
	// // Detail
	// // Page
	// // newTotalCost
	// // will
	// // be
	// // set
	// // from
	// // ReviewnEnroll
	// // page.
	//
	// DetailPage.currentTotalCost = flexCreditPage.getTotalCostFromHeader();
	// DetailPage.currentPlanCost = flexCreditPage.getPlanCostFromHeader();
	// System.out.println(
	// "Curr Tot Cost:" + DetailPage.currentTotalCost + "//Curr Plan Cost:" +
	// DetailPage.currentPlanCost);
	//
	// } catch (InterruptedException e) {
	// e.printStackTrace();
	// }
	// }
	//
	// @Test( priority=8 , dependsOnMethods = { "test31_FlexCredits" })
	// public void test32_FlexCreditEditnSave() {
	// try {
	// flexCreditPage.clickUpdate();// sapMListTblSelCol
	// flexCreditPage.selectPlan(1);
	// DetailPage.newPlanCost = flexCreditPage.getSelectedPlanCost(1);
	// flexCreditPage.savePlan();
	// System.out.println("Pagetitle::" + medicalPage.getPageTitle());
	// Thread.sleep(3000);
	// assertTrue(medicalPage.getPageTitle().equals("MEDICAL"));
	//
	// // Begin of running total cost
	// float newTotalCost = DetailPage.currentTotalCost -
	// DetailPage.currentPlanCost + DetailPage.newPlanCost;
	// DetailPage.newTotalCost = newTotalCost;
	// // End of running total cost
	//
	// } catch (InterruptedException e) {
	// e.printStackTrace();
	// }
	// }

//	@Test(priority = 9, dependsOnMethods = { "test20_NewHire_PersonalData" })
//	public void test33_PlanDetails() {
//		try {
//			masterListPage.clickMasterListItem("Medical");
//			masterListPage.clickMasterListItem("Medical");
//			Thread.sleep(3000);
//			ArrayList<String> planDetails = medicalPage.getPlanDetails();
//			Boolean foundPlanOption = false;
//			for (String planDetail : planDetails) {
//				if (planDetail.equals("POSPOption")) {
//					foundPlanOption = true;
//					break;
//				}
//			}
//			assertTrue(foundPlanOption);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}

//	@Test(priority = 10, dependsOnMethods = { "test33_PlanDetails" })
//	public void test34_EnrollPlanPending() {
//		try {
//			medicalPage.clickUpdate();// sapMListTblSelCol
//			medicalPage.selectPlan(1);
//			DetailPage.newPlanCost = medicalPage.getSelectedPlanCost(1);
//			System.out.println("New Plan Cost::" + DetailPage.newPlanCost);
//			medicalPage.savePlan();
//			Thread.sleep(3000);
//			assertTrue(dentalPage.getPageTitle().equals("DENTAL"));
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}

//	@Test(priority = 11, dependsOnMethods = { "test34_EnrollPlanPending" })
//	public void test35_PlanUndoPending() {
//		try {
//			masterListPage.clickMasterListItem("Medical");
//			masterListPage.clickMasterListItem("Medical");
//			assertTrue(medicalPage.isUndoPendingBtnVisible());
//			if (medicalPage.isUndoPendingBtnVisible()) {
//				medicalPage.undoPendingPlan();
//				Thread.sleep(3000);
//				String undoPendingPlanMessage = medicalPage.getUndoPendingMessage();
//				medicalPage.closeMessageBox();
//				Thread.sleep(2000);
//				assertTrue(undoPendingPlanMessage.equals("Pending record removed"));
//			}
//		} catch (Exception e) {
//
//		}
//	}
	
//	@Test(priority = 12, dependsOnMethods = { "test20_NewHire_PersonalData" })
//	public void test36_PlanDetails() {
//		try {
//			masterListPage.clickMasterListItem("Medical");
//			masterListPage.clickMasterListItem("Medical");
//			Thread.sleep(3000);
//			ArrayList<String> planDetails = medicalPage.getPlanDetails();
//			Boolean foundPlanOption = false;
//			for (String planDetail : planDetails) {
//				if (planDetail.equals("POSPOption")) {
//					foundPlanOption = true;
//					break;
//				}
//			}
//			assertTrue(foundPlanOption);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}

//	@Test(priority = 13, dependsOnMethods = { "test33_PlanDetails" })
//	public void test37_EnrollPlanPending() {
//		try {
//			medicalPage.clickUpdate();// sapMListTblSelCol
//			medicalPage.selectPlan(1);
//			DetailPage.newPlanCost = medicalPage.getSelectedPlanCost(1);			
//			medicalPage.savePlan();
//			Thread.sleep(3000);
//			assertTrue(dentalPage.getPageTitle().equals("DENTAL"));			
//
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}

//	@Test(priority = 14, dependsOnMethods = { "test34_EnrollPlanPending" })
//	public void test38_PlanUndoPending() {
//		try {
//			masterListPage.clickMasterListItem("Medical");
//			masterListPage.clickMasterListItem("Medical");
//
//			assertTrue(medicalPage.isUndoPendingBtnVisible());
//			if (medicalPage.isUndoPendingBtnVisible()) {
//				medicalPage.undoPendingPlan();
//				Thread.sleep(3000);
//				String undoPendingPlanMessage = medicalPage.getUndoPendingMessage();
//				medicalPage.closeMessageBox();
//				Thread.sleep(2000);
//				assertTrue(undoPendingPlanMessage.equals("Pending record removed"));
//			}
//		} catch (Exception e) {
//
//		}
//	}

}
