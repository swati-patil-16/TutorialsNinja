package com.tutorialsninja.qa.testcases;
import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegisterTest extends Base{
	
	AccountSuccessPage accountSuccessPage ;
	
	RegisterPage registerPage;
	WebDriver driver;

	public RegisterTest()
	{
		super();
	}
	
	
	@BeforeMethod()
	public void setup()
	{
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
		HomePage homePage = new HomePage(driver);
		registerPage = homePage.navigateToRegisterPage();
	}
	
	@AfterMethod
	public void teardown()
	{
		driver.quit();
	}	
	@Test(priority=1)
	public void verifyRegisterinAnAccountWithMandatoryFields()
	{		
		accountSuccessPage =registerPage.enterDetailsOnRegister("itaws","litap",Utilities.generateTimeStamp(),"12345678","1234","1234");
		String ActualText = accountSuccessPage.getregistrationConfirmationText();
		Assert.assertEquals(ActualText, "Your Account Has Been Created!","Account success message not displayed");
		driver.quit();
	}
	
	@Test(priority=2)
	public void verifyRegisteringAnAccountByEnteringAllFields()
	{
		accountSuccessPage =registerPage.enterDetailsOnRegister("itaws","litap",Utilities.generateTimeStamp(),"12345678","1234","1234");

		String ActualText = accountSuccessPage.getregistrationConfirmationText();
		Assert.assertEquals(ActualText, "Your Account Has Been Created!","Account success message not displayed");
		driver.quit();
	}
	
	@Test(priority=3)
	public void verifyRegisteringAccountWithExistingEmailAddress()
	{
		
		registerPage.enterDetailsOnRegister("itaws","litap",prop.getProperty("username"),"12345678",prop.getProperty("password"),"1234");
		registerPage.enterFirstNameOnRegister("itaws");
		registerPage.enterLastNameOnRegister("litap");
		registerPage.enteremailOnRegister(prop.getProperty("username"));
		registerPage.enterTelephoneOnRegister("12345678");
		registerPage.enterPasswordOnRegister(prop.getProperty("password"));
		registerPage.enterconfirmOnRegister("1234");
		registerPage.selectNewsLetterOnRegister();
		registerPage.selectAgreeOnRegister();
		registerPage.selectContinueOnRegister();
		
		String ActualWarning = registerPage.emailAddressAlreadyRegisteredWarning();
		System.out.println(ActualWarning);
		Assert.assertTrue(ActualWarning.contains("Warning: E-Mail Address is already registered!"),"Warning message is not correct");
		driver.quit();
	}
	
	@Test(priority=4)
	public void verifyRegisteringAccountWithoutProvidingAnyDetails()
	{
		registerPage.selectContinueOnRegister();
		
		String ActualWarning = registerPage.warningMessageToEnterAllMandatoryFields();
		Assert.assertTrue(ActualWarning.contains("Warning: You must agree to the Privacy Policy!"),"Privacy Warning message is not correct");
		driver.quit();
	}
	
}

