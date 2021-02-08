package com.aspirehr.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import io.appium.java_client.android.AndroidDriver;

//import io.appium.java_client.android.AndroidDriver;

public class SeleniumUtil {

	public static void login(WebDriver driver, String username, String password, String link) {
		String URL = "https://" + username + ":" + password + "@" + link;
		driver.get(URL);
		if (driver instanceof AndroidDriver == false)
			driver.manage().window().maximize();// does not work for mobile
												// chrome.
	}

	public static void tempLogin(WebDriver driver, String url, String userID, String pwd, String clntNO)

	{
		driver.get(url);
		driver.manage().window().maximize();
		sleep(5000);
		WebElement useridFld = driver.findElement(By.id("USERNAME_FIELD-inner"));
		WebElement pwdFld = driver.findElement(By.id("PASSWORD_FIELD-inner"));
		WebElement clntFld = driver.findElement(By.id("CLIENT_FIELD-inner"));
		WebElement loginBtn = driver.findElement(By.id("LOGIN_LINK"));
		useridFld.sendKeys(userID);
		pwdFld.sendKeys(pwd);
		clntFld.sendKeys(clntNO);
		// loginBtn.click();
		// loginBtn.click();
		loginBtn.submit();

		// USERNAME_FIELD-inner
		// PASSWORD_FIELD-inner
		// CLIENT_FIELD-inner
		// LOGIN_LINK
	}

	public static void sleep(long millisec) {

		try {
			Thread.sleep(millisec);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static WebElement findInputElementByClassName(WebDriver driver, String className, String id, String value) {
		WebElement expectedElement = null;
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		// System.out.println("Finding elements");
		List<WebElement> elementsList = wait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className(className)));
		// System.out.println("Found elements");
		for (WebElement element : elementsList) {
			String elementId = element.getAttribute("id");
			// System.out.println(elementId);
			if (elementId.contains(id)) {
				expectedElement = element;

				break;
			}

		}
		// System.out.println("Finding elements");

		if (value != null || !value.isEmpty()) {
			wait.until(ExpectedConditions.attributeToBe(expectedElement, "value", value));
		}

		return expectedElement;

	}

	public static WebElement findElementByLinkText(WebDriver driver, String text) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		// System.out.println("Finding elements");
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(text)));
		// System.out.println("Found element");

		return element;

	}

	public static List<WebElement> findElementListByClassName(WebDriver driver, String className) {
		WebElement expectedElement = null;
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		// System.out.println("Finding elements with className = " + className);
		List<WebElement> elementsList = wait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className(className)));
		// System.out.println("Found elements");
		// for (WebElement element : elementsList) {
		// String elementId = element.getAttribute("id");
		// //System.out.println(elementId);
		// }

		return elementsList;

	}

	public static List<WebElement> findElementListByStartsWithText(WebDriver driver, By by, String substring,
			int patienceLevel) {
		int patience = patienceLevel;
		if (patience < 1)
			patience = 1;

		ArrayList<WebElement> resultList = new ArrayList<WebElement>();

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);

		List<WebElement> elementsList = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
		return elementsList;

		// patience
		// for loop over elementsList
		// buf = e.getText()
		// if buf == null, set to ""
		// if buf.tolowercase.startswith(substring.tolowercase())..

		// if resultList.length() > 0 return resultList;
		// else Thread.sleep(500);
		// end of patience loop
	}

	/**
	 * Search list of elements return by a {@link org.openqa.selenium.By By} for an
	 * id with a given suffix. Case is ignored.
	 * 
	 * @param driver
	 *            {@link org.openqa.selenium.WebDriver}
	 * @param by
	 *            {@link org.openqa.selenium.By}
	 * @param suffix
	 *            {@link java.lang.String}
	 * @return List&lt;WebElement&gt;. List will be <code>null</code> if the
	 *         parameters are null / blank. List will be non-null but empty (
	 *         <code>size() == 0</code>) if no matches were found. Underlying return
	 *         type is {@link WebElementMatch} wrapper which contains the index of
	 *         matched element & the matcher results (e.g. to obtain group
	 *         information).
	 */
	public static List<WebElement> findElementListByIdSuffix(WebDriver driver, By by, String suffix) {
		return findElementListByIdSuffix(driver, by, suffix, 1);
	}

	/**
	 * Search list of elements return by a {@link org.openqa.selenium.By By} for an
	 * id with a given suffix. Case is ignored.
	 * 
	 * @param driver
	 *            {@link org.openqa.selenium.WebDriver}
	 * @param by
	 *            {@link org.openqa.selenium.By}
	 * @param suffix
	 *            {@link java.lang.String}
	 * @param patienceLevel
	 *            Number of times to search for a match. A 500ms sleep() is done
	 *            between each search.
	 * @return List&lt;WebElement&gt;. List will be <code>null</code> if the
	 *         parameters are null / blank. List will be non-null but empty (
	 *         <code>size() == 0</code>) if no matches were found. Underlying return
	 *         type is {@link WebElementMatch} wrapper which contains the index of
	 *         matched element & the matcher results (e.g. to obtain group
	 *         information).
	 */
	public static List<WebElement> findElementListByIdSuffix(WebDriver driver, By by, String suffix,
			int patienceLevel) {
		int patience = patienceLevel;
		if (patience < 1)
			patience = 1;

		// TODO: Convert return to WebElementMatch
		// lastMatchedElement = null;
		// lastMatchedElementList = new ArrayList<WebElement>();
		ArrayList<WebElement> resultList = new ArrayList<WebElement>();

		if (by == null || suffix == null || suffix.length() == 0)
			return null;

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(15, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);

		// List<WebElement> elementsList =
		// wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
		String _suffix = suffix.toLowerCase();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		if (driver instanceof AndroidDriver == true) {
			jse.executeScript(
					"$('#__xmlview2--detailPage-cont').scrollTop($('#__xmlview2--detailPage-cont')[0].scrollHeight/5);");
		}
		// patience
		do {
			List<WebElement> elementsList = null;
			try {
				elementsList = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}

			String buf = "";
			int suffixPosition = -1;
			int offset = 0;

			if (elementsList != null) {
				// for loop over elementsList
				for (WebElement e : elementsList) {
					buf = e.getAttribute("id");
					if (buf == null)
						buf = "";
					else
						buf = buf.toLowerCase();

					suffixPosition = buf.length() - _suffix.length();
					// if (suffixPosition >= 0)
					if (suffixPosition >= 0) {
						if (buf.substring(suffixPosition).equals(_suffix)) {
							WebElementMatch eMatch = new WebElementMatch(e, offset, null);
							resultList.add(eMatch);
						}
					}
					offset++;
				}
			}
			// if resultList.length() > 0 return resultList;
			if (resultList.size() > 0) {
				return resultList;
			}
			// else Thread.sleep(500);
			if (patience >= 1)
				try {
					if (driver instanceof AndroidDriver == true) {
						// ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(false);",
						// elementsList);
						// jse.executeScript("window.scrollBy(0,100);");
						// jse.executeScript("javascript:window.scrollBy(0,200);");
						// jse.executeScript("$('#__xmlview2--detailPage-cont').scrollTop
						// = $('#__xmlview2--detailPage-cont').scrollHeight");
						jse.executeScript(
								"$('#__xmlview2--detailPage-cont').scrollTop($('#__xmlview2--detailPage-cont')[0].scrollHeight/5);");
					}
					Thread.sleep(500);
				} catch (Exception e1) {
					System.err.println(e1.getMessage());
				}
			// end of patience loop
		} while (patience-- > 0);

		return resultList;
	}

	/**
	 * Search list of elements return by a {@link org.openqa.selenium.By By} for an
	 * id fully {@link java.util.regex.Matcher#matches matching} regular expression.
	 * If you wish to ignore case, you must specify that in the
	 * {@link java.util.regex.Pattern Pattern}.
	 * 
	 * @param driver
	 *            {@link org.openqa.selenium.WebDriver}
	 * @param by
	 *            {@link org.openqa.selenium.By}
	 * @param regex
	 *            {@link java.util.regex.Pattern}
	 * @return List&lt;WebElement&gt;. List will be <code>null</code> if the
	 *         parameters are null / blank. List will be non-null but empty (
	 *         <code>size() == 0</code>) if no matches were found. Underlying return
	 *         type is {@link WebElementMatch} wrapper which contains the index of
	 *         matched element & the matcher results (e.g. to obtain group
	 *         information).
	 */
	public static List<WebElement> findElementListByIdRegex(WebDriver driver, By by, Pattern regex) {
		return findElementListByIdRegex(driver, by, regex, 1);
	}

	/**
	 * Search list of elements return by a {@link org.openqa.selenium.By By} for an
	 * id fully {@link java.util.regex.Matcher#matches matching} regular expression.
	 * If you wish to ignore case, you must specify that in the
	 * {@link java.util.regex.Pattern Pattern}.
	 * 
	 * @param driver
	 *            {@link org.openqa.selenium.WebDriver}
	 * @param by
	 *            {@link org.openqa.selenium.By}
	 * @param regex
	 *            {@link java.util.regex.Pattern}
	 * @param patienceLevel
	 *            Number of times to search for a match. A 500ms sleep() is done
	 *            between each search.
	 * @return List&lt;WebElement&gt;. List will be <code>null</code> if the
	 *         parameters are null / blank. List will be non-null but empty (
	 *         <code>size() == 0</code>) if no matches were found. Underlying return
	 *         type is {@link WebElementMatch} wrapper which contains the index of
	 *         matched element & the matcher results (e.g. to obtain group
	 *         information).
	 */
	public static List<WebElement> findElementListByIdRegex(WebDriver driver, By by, Pattern regex, int patienceLevel) {
		int patience = patienceLevel;
		if (patience < 1)
			patience = 1;

		// TODO: Convert return to WebElementMatch
		// lastMatchedElement = null;
		// lastMatchedElementList = new ArrayList<WebElement>();
		ArrayList<WebElement> resultList = new ArrayList<WebElement>();

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);

		List<WebElement> elementsList = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
		// return elementsList;

		// patience
		do {
			String buf = "";
			Matcher m;
			int offset = 0;

			// for loop over elementsList
			for (WebElement e : elementsList) {
				// buf = e.getAttribute("id")
				buf = e.getAttribute("id");
				if (buf == null)
					buf = "";
				// else
				// buf = buf.toLowerCase();

				// if regex.matcher(buf).matches() ... // match
				m = regex.matcher(buf);
				if (m.matches()) {
					// resultList.add(e);
					WebElementMatch eMatch = new WebElementMatch(e, offset, m);
					resultList.add(eMatch);

				}
				offset++;
			}
			// if resultList.length() > 0 return resultList;
			if (resultList.size() > 0) {
				return resultList;
			}
			// else Thread.sleep(500);
			if (patience > 1)
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
				}
			// end of patience loop

		} while (patience-- > 0);

		return resultList;

	}

	public static void clickButton(WebDriver driver, WebElement element) {
		if (driver instanceof InternetExplorerDriver)
			element.sendKeys(Keys.ENTER); // This works for IE.
		else			
			element.click(); // This works for Chrome and Firefox
	}

	public static void clickEnrollButton(WebDriver driver, WebElement element) {
		if (driver instanceof InternetExplorerDriver)
			element.sendKeys(Keys.ENTER); // This works for IE.
		else
			element.click(); // This works for Chrome and Firefox		
	}
}
