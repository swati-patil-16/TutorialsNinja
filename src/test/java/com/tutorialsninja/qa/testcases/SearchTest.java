package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;

public class SearchTest extends Base {
	
	SearchPage searchPage;
	HomePage homePage;
	
   public WebDriver driver;

    public SearchTest()
    {
	 super();
    }
	
	@BeforeMethod()
	public void setup()
	{
		 driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
		 homePage = new HomePage(driver);
	}
	
	@AfterMethod
	public void teardown()
	{
		driver.quit();
	}
	
	@Test(priority=1)
	public void verifySearchWithValidProduct()
	{		
		homePage.enterDataInSearchField("HP");
		searchPage = homePage.clickSearchButton();		
		Assert.assertTrue(searchPage.getSearchedProductText(),"Searched Product is not shown");
		
	}
	
	@Test(priority=2)
	public void verifySearchWithInValidProduct()
	{
		homePage.enterDataInSearchField("Honda");
		searchPage = homePage.clickSearchButton();
		String actualSearchMessage =searchPage.getInvalidSearchedElementText();
		
		Assert.assertEquals(actualSearchMessage,"There is noo product that matches the search criteria.","proper message for no product message is not displayed");

	}
	
	@Test(priority=3)
	public void verifySearchWithoutAnyProduct()
	{
		homePage.enterDataInSearchField("");
		searchPage = homePage.clickSearchButton();
		String actualSearchMessage =searchPage.getInvalidSearchedElementText();		
		Assert.assertEquals(actualSearchMessage,"There is no product that matches the search criteria.","proper message for no product message is not displayed");
		
		
	}


}
