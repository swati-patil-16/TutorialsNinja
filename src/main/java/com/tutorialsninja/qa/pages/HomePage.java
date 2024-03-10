package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	@FindBy(xpath="//span[text()='My Account']")
	private WebElement myAccountDropMenu;
	
	@FindBy(xpath="//a[text()='Login']")
	private WebElement LoginOption;
	
	@FindBy(xpath="//a[text()='Register']")
	private WebElement RegisterOption;
	
	@FindBy(xpath = "//input[@name='search']")
	private WebElement searchField;

	@FindBy(xpath = "//div[@id='search']/descendant::button")
	private WebElement searchButton;
	

	public WebDriver driver;
	
	public HomePage(WebDriver driver)
	{
		this.driver= driver;
		PageFactory.initElements(driver,this);
	}
	
	public void clickOnMyAccount()
	{
		myAccountDropMenu.click();
	}
	public LoginPage selectLoginOption()
	{
		LoginOption.click();
		return new LoginPage(driver);
	}
	
	public LoginPage navigateToLoginPage()
	{
		myAccountDropMenu.click();
		LoginOption.click();
		return new LoginPage(driver);
		
	}
	
	public RegisterPage selectRegisterOption()
	{
		RegisterOption.click();
		return new RegisterPage(driver);
	}
	
	public RegisterPage navigateToRegisterPage()
	{
		myAccountDropMenu.click();
		RegisterOption.click();
		return new RegisterPage(driver);
		
	}
	

	public void enterDataInSearchField(String searchData)
	{
		searchField.sendKeys(searchData);
	}
	
	public SearchPage clickSearchButton()
	{
		searchButton.click();
		return new SearchPage(driver);
	}
	
	
}
