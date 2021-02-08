package com.aspirehr.pagefactory;

import java.util.List;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.aspirehr.util.SeleniumUtil;

public class SFPage {
	WebDriver driver;
	@FindBy(id = "__input1-inner")
	WebElement useridFld;

	@FindBy(id = "__input2-inner")
	WebElement pwdFld;

	@FindBy(id = "__button2-inner")
	WebElement loginBtn;

	@FindBy(id = "customHeaderModulePickerBtn")
	WebElement homeBtn;
	
	@FindBy(id = "__xbutton0")
	WebElement homeBtn_Mobile;
	
	public static final String REGEX_CB_IFRAME = "(remote_iframe_)([a-z,A-Z,0-9,-]+)"; //remote_iframe_74a413a8-5f6c-4fad-8e31-8ee7d974f4c4" 

	public SFPage(WebDriver driver, String URL) {

		this.driver = driver;

		// This initElements method will create all WebElements
		AjaxElementLocatorFactory ajaxFactory = new AjaxElementLocatorFactory(driver, 30);
		PageFactory.initElements(ajaxFactory, this);
		driver.get(URL);
		driver.manage().window().maximize();
	}

	public void enterUserNamePassword(String userId, String password) {
		useridFld.sendKeys(userId);
		pwdFld.sendKeys(password);

	}

	public void clickLoginButton() {
		loginBtn.click();
	}

	public void navigateToEmployeeBenefits() {
		// TODO Auto-generated method stub
		homeBtn.click();
		try {
			Thread.sleep(3000);
			List<WebElement> links = SeleniumUtil.findElementListByClassName(driver, "sapMLIBTypeInactive");
			for (WebElement link : links) {
//				if (link.getText().equalsIgnoreCase("Employee Benefits (Q68)")) {
				if (link.getText().equalsIgnoreCase("Employee Benefits")) {
					link.click();
					break;
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public void navigateToEmployeeBenefitsinMobile() {
		// TODO Auto-generated method stub
		homeBtn_Mobile.click();
		try {
			Thread.sleep(3000);
			List<WebElement> links = SeleniumUtil.findElementListByClassName(driver, "sapMLIBTypeInactive");
			for (WebElement link : links) {
//				if (link.getText().equalsIgnoreCase("Employee Benefits (Q68)")) {
				if (link.getText().equalsIgnoreCase("Employee Benefits")) {
					link.click();
					break;
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public WebDriver focusBenefitsIFrame(){
		
		List<WebElement> iframes = SeleniumUtil.findElementListByIdRegex(driver, By.className("gadgets-gadget"), Pattern.compile(REGEX_CB_IFRAME));
		WebElement cbIframe = driver.findElement(By.tagName("iframe"));
		WebElement cbDiv = driver.findElement(By.className("gadgets-gadget-content"));
//		for(WebElement iframe: iframes){
//			if(iframe != null){
//				WebElement frame = (WebElement)iframe;
//				driver.switchTo().frame(frame); 	
//			}
//		}
		//driver.switchTo().frame(cbIframe);
		String iframeName = cbIframe.getAttribute("name");
		System.out.println("iframeName>>"+iframeName);
		//driver.switchTo().defaultContent();
		this.driver = driver.switchTo().frame(cbIframe);
		return this.driver;
	}

}
