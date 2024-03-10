package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountSuccessPage {
	
	
	@FindBy(xpath="//div[@id='content']/h1")
	private WebElement registrationConfirmationText;
	
	
	public WebDriver driver;
	
	public AccountSuccessPage(WebDriver driver)
	{
		this.driver= driver;
		PageFactory.initElements(driver,this);
	}

	
	public String getregistrationConfirmationText()
	{
		return registrationConfirmationText.getText();
	}
	
}
