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
public class C7_002_Corequisite {
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
		String uid = "c702";
		String pwd = "c7testing";

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
	public void test01_EnrollinMed() {
		
		masterListPage.clickMasterListItem("Complete Anytime Enrollment"); 
		
	
		try {
			Thread.sleep(5000);
			Thread.sleep(5000);
			masterListPage.clickMasterListItem("Extended Medical Plan");
			Thread.sleep(3000);
			
			medicalPage.clickUpdate();// sapMListTblSelCol
			medicalPage.selectPlan(1);

			medicalPage.savePlan();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test(priority = 2, dependsOnMethods = { "test01_EnrollinMed" })
public void test02_EnrollinFSA() {
		
	
		try {
			Thread.sleep(5000);
			Thread.sleep(5000);
			masterListPage.clickMasterListItem("Health Care Spending Account");
			Thread.sleep(3000);
			
			fsaEmpHlthCarePage.clickUpdate();// sapMListTblSelCol
//			fsaEmpHlthCarePage.selectPlan(1);

			fsaEmpHlthCarePage.savePlan();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
