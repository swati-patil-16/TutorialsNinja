package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	
	@FindBy(id="input-firstname")
	private WebElement firstNameElementField;
	
	@FindBy(id="input-lastname")
	private WebElement lastNameElementField;
	
	@FindBy(id="input-email")
	private WebElement emailElementField;
	
	@FindBy(id="input-telephone")
	private WebElement telephoneElementField;
	
	@FindBy(id="input-password")
	private WebElement passwordElementField;
	
	@FindBy(id="input-confirm")
	private WebElement confirmElementField;
	
	@FindBy(name="newsletter")
	private WebElement newsletterElementField;
	
	@FindBy(name="agree")
	private WebElement agreeElementField;
	
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement continueElementField;
	
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement emailAddressAlreadyRegisteredWarning;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement warningMessageToEnterAllMandatoryFields;
	
	public WebDriver driver;
	
	public RegisterPage(WebDriver driver)
	{
		this.driver= driver;
		PageFactory.initElements(driver,this);
	}
	
	
	public AccountSuccessPage enterDetailsOnRegister(String firstName, String lastName, String email, 
			String telephone, String password, String confirmPassword)
	{
		firstNameElementField.sendKeys(firstName);
		lastNameElementField.sendKeys(lastName);
		emailElementField.sendKeys(email);
		telephoneElementField.sendKeys(telephone);
		passwordElementField.sendKeys(password);
		confirmElementField.sendKeys(confirmPassword);
		newsletterElementField.click();
		agreeElementField.click();
		continueElementField.click();
		return new AccountSuccessPage(driver);
	}
	
	public void enterFirstNameOnRegister(String firstName)
	{
		firstNameElementField.sendKeys(firstName);
		
	}
	public void enterLastNameOnRegister(String lastName)
	{
		lastNameElementField.sendKeys(lastName);
	}
	
	public void enteremailOnRegister(String email)
	{
		emailElementField.sendKeys(email);

	}
	public void enterTelephoneOnRegister(String telephone)
	{
		telephoneElementField.sendKeys(telephone);

	}
	public void enterPasswordOnRegister(String password)
	{
		passwordElementField.sendKeys(password);

	}
	public void enterconfirmOnRegister(String confirmPassword)
	{
		confirmElementField.sendKeys(confirmPassword);

	}
	public void selectNewsLetterOnRegister()
	{
		newsletterElementField.click();

	}
	
	public void selectAgreeOnRegister()
	{
		agreeElementField.click();

	}
	public AccountSuccessPage selectContinueOnRegister()
	{
		continueElementField.click();
		return new AccountSuccessPage(driver);

	}
	
	
	public String emailAddressAlreadyRegisteredWarning()
	{
		return emailAddressAlreadyRegisteredWarning.getText();
	}
	
	public String warningMessageToEnterAllMandatoryFields()
	{
		return warningMessageToEnterAllMandatoryFields.getText();
	}
}
