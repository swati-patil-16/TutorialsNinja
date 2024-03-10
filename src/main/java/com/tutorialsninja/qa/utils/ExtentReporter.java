package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;

import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {

	Properties prop;

	public static ExtentReports generateExtentReporter() 
	{
		ExtentReports extentReport = new ExtentReports();
		File extentReportFile = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\ExtentReport.html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
		
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("Tutorials Ninja Test Automation Results");
        sparkReporter.config().setDocumentTitle("TN Automation Framework");		
        sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
        extentReport.attachReporter(sparkReporter);
        
        Properties prop = new Properties();
		File file = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");
		
		try
		{
			FileInputStream fis = new FileInputStream(file);
			prop.load(fis);	
		}
		
		catch(Throwable t)
		{
			t.printStackTrace();
		}

        extentReport.setSystemInfo("Application URL: ", prop.getProperty("url"));
        extentReport.setSystemInfo("Browsername: ", prop.getProperty("browser"));
        extentReport.setSystemInfo("Email: ", prop.getProperty("username"));
        extentReport.setSystemInfo("Password: ", prop.getProperty("password"));
        extentReport.setSystemInfo("OS Name: ", System.getProperty("os.name"));
        extentReport.setSystemInfo("User Name Of Machine: ", System.getProperty("user.name"));
        extentReport.setSystemInfo("Java Version: ", System.getProperty("java.version"));
        
        return extentReport;
        
	}

}
