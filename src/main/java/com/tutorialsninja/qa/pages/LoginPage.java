package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	@FindBy(name="email")
	private WebElement emailAddressField;
	
	@FindBy(name="password")
	private WebElement passwordField;
	
	@FindBy(xpath="//input[@type='submit']")
	private WebElement LoginButton;
	
	@FindBy(xpath="//div[contains(@class,'alert')]")
	private WebElement emailPasswordNotMatchingWarning;
	

	public WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		this.driver= driver;
		PageFactory.initElements(driver,this);
	}
	
	public void enterEmailAddress(String email)
	{
		emailAddressField.sendKeys(email);
	}
	
	public void enterPassword(String password)
	{
		passwordField.sendKeys(password);
	}
	
	public AccountPage clickOnLoginButton()
	{
		LoginButton.click();
		return new AccountPage(driver);
	}
	
	
	public AccountPage login(String email, String password)
	{
		emailAddressField.sendKeys(email);
		passwordField.sendKeys(password);
		LoginButton.click();
		return new AccountPage(driver);
	}
	
	public String retrieveEmailPassWordNotMatchingWarningText()
	{
		return emailPasswordNotMatchingWarning.getText();
	}

}
