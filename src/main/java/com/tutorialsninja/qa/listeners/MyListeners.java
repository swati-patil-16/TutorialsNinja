package com.tutorialsninja.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.qa.utils.ExtentReporter;
import com.tutorialsninja.qa.utils.Utilities;

public class MyListeners implements ITestListener{

	ExtentReports extentReporter;
	ExtentTest eTest;
	
	@Override
	public void onStart(ITestContext context) {
      extentReporter = ExtentReporter.generateExtentReporter();
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		eTest = extentReporter.createTest(result.getName());
		eTest.log(Status.INFO, result.getName()+" started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		eTest.log(Status.PASS, result.getName()+" Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		WebDriver driver = null;
		try {
			 driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String destinationScreenshotPath = Utilities.captureScreenshot(driver, result.getName());
		
		eTest.addScreenCaptureFromPath(destinationScreenshotPath);
		eTest.log(Status.FAIL, result.getName() + " failed");		
		eTest.log(Status.INFO, result.getThrowable());
		driver.close();
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		eTest.log(Status.INFO, result.getThrowable());
		eTest.log(Status.SKIP, result.getName() + " skipped");
	}

	
	@Override
	public void onFinish(ITestContext context) {
		extentReporter.flush();
		String extentReportPath = System.getProperty("user.dir")+"\\test-output\\ExtentReports\\ExtentReport.html" ;
		File eReport = new File(extentReportPath);
		try {
			Desktop.getDesktop().browse(eReport.toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
