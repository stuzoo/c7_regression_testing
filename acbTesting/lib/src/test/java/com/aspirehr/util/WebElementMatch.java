package com.aspirehr.util;

import java.util.List;
import java.util.regex.Matcher;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

/**
 * Wrapper class for {@link org.openqa.selenium.WebElement}. Used by
 * {@link SeleniumUtil} as the underlying return type for the various
 * findElementBy... methods, this class includes the index where this element
 * was in the original {@link org.openqa.selenium.By By}... element list, as
 * well as the {@link java.util.regex.Matcher Matcher} (if a regex was used).
 * 
 * @author scollenburg
 *
 */
public class WebElementMatch implements WebElement {

	final WebElement wrappedElement;
	final int index;
	final Matcher matcher;

	/**
	 * @param wrappedElement
	 */
	public WebElementMatch(WebElement wrappedElement) {
		super();
		this.wrappedElement = wrappedElement;
		this.index = 0;
		this.matcher = null;
	}

	/**
	 * @param wrappedElement
	 * @param index
	 * @param matcher
	 */
	public WebElementMatch(WebElement wrappedElement, int index, Matcher matcher) {
		super();
		this.wrappedElement = wrappedElement;
		this.index = index;
		this.matcher = matcher;
	}

	public WebElement getWrappedElement() {
		return wrappedElement;
	}

	public int getIndex() {
		return index;
	}

	public Matcher getMatcher() {
		return matcher;
	}

	@Override
	public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
		return wrappedElement.getScreenshotAs(target);
	}

	@Override
	public void click() {
		wrappedElement.click();
	}

	@Override
	public void submit() {
		wrappedElement.submit();
	}

	@Override
	public void sendKeys(CharSequence... keysToSend) {
		wrappedElement.sendKeys(keysToSend);
	}

	@Override
	public void clear() {
		wrappedElement.clear();
	}

	@Override
	public String getTagName() {
		return wrappedElement.getTagName();
	}

	@Override
	public String getAttribute(String name) {
		return wrappedElement.getAttribute(name);
	}

	@Override
	public boolean isSelected() {
		return wrappedElement.isSelected();
	}

	@Override
	public boolean isEnabled() {
		return wrappedElement.isEnabled();
	}

	@Override
	public String getText() {
		return wrappedElement.getText();
	}

	@Override
	public List<WebElement> findElements(By by) {
		return wrappedElement.findElements(by);
	}

	@Override
	public WebElement findElement(By by) {
		return wrappedElement.findElement(by);
	}

	@Override
	public boolean isDisplayed() {
		return wrappedElement.isDisplayed();
	}

	@Override
	public Point getLocation() {
		return wrappedElement.getLocation();
	}

	@Override
	public Dimension getSize() {
		return wrappedElement.getSize();
	}

	@Override
	public Rectangle getRect() {
		return wrappedElement.getRect();
	}

	@Override
	public String getCssValue(String propertyName) {
		return wrappedElement.getCssValue(propertyName);
	}

}
