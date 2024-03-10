package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;



public class LoginTest extends Base {
	
	WebDriver driver;
	LoginPage loginPage;
	
	public LoginTest()
	{
		super();
	}
	
	@BeforeMethod()
	public void setup()
	{
		
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
		System.out.println("driver value" + driver.getCurrentUrl());
		try {
		HomePage homePage = new HomePage(driver);
		loginPage = homePage.navigateToLoginPage();
		}
		catch (Throwable ite) {
            Throwable underlyingException = ite.getCause();
            underlyingException.printStackTrace();
        } 
		
	}
	
	@AfterMethod
	public void teardown()
	{
		driver.quit();
	}	
	
	@DataProvider(name="LoginCredentials")
	public Object[][] supplyData()
	{
		Object[][] obj = Utilities.getDataFromExel("Login");
		return obj;
	}
	
	@Test(priority=1,dataProvider="LoginCredentials")
	public void verifyLoginWithValidCredentials(String email, String Password)

	{
		AccountPage accountPage = loginPage.login(email, Password);
		Assert.assertTrue(accountPage.verifyDisplayedInfoofEditYourAccount());
	}
	
	@Test(priority=2)
	public void verifyLoginWithInvalidCredentials()
	{		
		loginPage.login(Utilities.generateTimeStamp(), "sjp981");
		String actualWarningMessage =  loginPage.retrieveEmailPassWordNotMatchingWarningText();
		String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
        Assert.assertTrue(actualWarningMessage.contains(expectedWarning),"Expected Warning message is not displayed. Expected Message is" + expectedWarning);
	
	}
	
	@Test(priority=3)
	public void verifyLoginWithValidEmailAndInvalidPassword()
	{	
		loginPage.login(prop.getProperty("username"), "sjp981");
		String actualWarningMessage =  loginPage.retrieveEmailPassWordNotMatchingWarningText();
		String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
        Assert.assertTrue(actualWarningMessage.contains(expectedWarning),"Expected Warning message is not displayed. Expected Message is" + expectedWarning);
		
	}
	
	@Test(priority=4)
	public void verifyLoginWithInvalidEmailAndValidPassword()
	{	
		loginPage.login(Utilities.generateTimeStamp(), prop.getProperty("password"));
		String actualWarningMessage =  loginPage.retrieveEmailPassWordNotMatchingWarningText();
		String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
        Assert.assertTrue(actualWarningMessage.contains(expectedWarning),"Expected Warning message is not displayed. Expected Message is" + expectedWarning);		
	}
	
	
	@Test(priority=5)
	public void verifyLoginWithoutProvidingCredentials()
	{
		loginPage.clickOnLoginButton();
		String actualWarningMessage =  loginPage.retrieveEmailPassWordNotMatchingWarningText();
		String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
        Assert.assertTrue(actualWarningMessage.contains(expectedWarning),"Expected Warning message is not displayed. Expected Message is" + expectedWarning);
	
	}
	

	
}



