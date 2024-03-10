package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {

	public WebDriver driver;

	@FindBy(linkText = "HP LP3065")
	private WebElement searchedElementText;

	@FindBy(xpath = "//div[@id='content']/h2/following-sibling::p")
	private WebElement invalidSearchedElementText;

	public SearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	public Boolean getSearchedProductText()
	{
		return searchedElementText.isDisplayed();
	}
	
	public String getInvalidSearchedElementText()
	{
		return invalidSearchedElementText.getText();
	}
}
