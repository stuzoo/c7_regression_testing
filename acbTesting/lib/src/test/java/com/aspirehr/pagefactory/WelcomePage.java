package com.aspirehr.pagefactory;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.aspirehr.util.SeleniumUtil;

public class WelcomePage extends DetailPage {
	WebDriver driver;	
	
	public WelcomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;

		// This initElements method will create all WebElements
		AjaxElementLocatorFactory ajaxFactory = new AjaxElementLocatorFactory(driver, 30);
		PageFactory.initElements(ajaxFactory, this);

	}

	public String getPageTitle() {
//		(new WebDriverWait(this.driver, 30))
//				.until(ExpectedConditions.textToBePresentInElement(pageTitle, "MEDICAL"));
		//sapMBarChild sapMTitle sapMTitleMaxWidth sapMTitleNoWrap sapMTitleStyleAuto sapUiSelectable
//		List<WebElement> elementList = SeleniumUtil.findElementListByClassName(driver, "sapMBarChild");
//		String pageTitle = "";
//		for (WebElement element : elementList){
//			String title = element.getText();
//			if(null!= title && !title.equals("")& title.toLowerCase().equals("welcome")){
//				pageTitle = element.getText();
//			}
//		}		
//		return pageTitle;
		
		List<WebElement> elementList = SeleniumUtil.findElementListByIdSuffix(driver, By.className("sapMBarChild"),
				"welcomePage-title", 2);
		String pageTitle = "";
		for (WebElement element : elementList) {
			System.out.println(element.getAttribute("id") + "::" + element.getText());
			if (element.getText() != null && !element.getText().trim().equals("")) {			
					pageTitle = element.getText();
					break;				
			}
		}
		return pageTitle;

	}
	
}
