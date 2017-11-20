package Project_Utilities;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.time.LocalDate;
//import java.util.Calendar;
//import org.openqa.selenium.JavascriptExecutor;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.sikuli.script.Screen;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import java.time.Instant;
public class Base_Project
{
	public final static DateFormat Calendar = null;
	public static WebDriver driver=null;//
	//public static WebDriver driver;
	public static ExtentReports extent;//
	public static ExtentTest test;
	static Instant instant = Instant.now();
	static long localDate = instant.getEpochSecond();
	public static String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(Calendar.getInstance().getCalendar().getTime());	
	public static Screen screen;
	public static final Logger logger=Logger.getLogger(Base_Project.class.getName());
	public void loadlog4j()
		{
		DOMConfigurator.configure("log4j.xml");
		}

	//Reading and Connection to XML file
	public static String getData (String nodeName) throws ParserConfigurationException, SAXException, IOException
	{
		File fXmlFile = new File("External_Files/XML/Project_Conf.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile); 
		doc.getDocumentElement().normalize();
		return doc.getElementsByTagName(nodeName).item(0).getTextContent();
	}
	
	//************************Reports function*********************************
	public static void  InstanceReports() throws ParserConfigurationException, SAXException, IOException
	{
		extent= new ExtentReports(getData("ReportFilePath") + timeStamp +"-"+localDate+getData("Reporfilename"),true);//Reporfilenamet
	}
	public static void initReportTest(String testName,String testDescription)
	{
		test = extent.startTest(testName, testDescription);
	}
	public static void FinalizedreportTest()
	{
		extent.endTest(test);
	}
	public static void FinalizeExtentReport()
	{
		extent.flush();
		extent.close();
	}
	//*********************end Report function******************************* 

	//Adding report to log and reports when test case start.
	public static void StartTest(String OpeNTest)
	{
		logger.info(OpeNTest);
		test.log(LogStatus.PASS, OpeNTest);
	}
	
	//Screenshot function
	public static String getscreenshot() throws Exception 
	{
		String SsPath=getData("ReportFilePath")+ timeStamp +"-"+localDate+".png";	
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(SsPath));
		return SsPath;
	}

	//Switch Browser
	public static void InitBrowser(String BrowserType) throws ParserConfigurationException, SAXException, IOException
	{
		switch(BrowserType.toLowerCase())
		{
		case "chrome":
			driver = ChromeDriver();
			break;
		case "firefox":
			driver =  initFFDriver();
			break;
		case "ie":
			driver = initIEDriver();
			break;
		}
		logger.info("Open brwoser :"+BrowserType);
		driver.manage().window().maximize();
		driver.get(getData("URL"));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		screen = new Screen();
	}	
	public static WebDriver ChromeDriver() throws ParserConfigurationException, SAXException, IOException
	{
		System.setProperty("webdriver.chrome.driver",getData("ChromeDriverPath"));
		WebDriver driverChrome= new ChromeDriver();
		return driverChrome;
	}
	public static WebDriver initFFDriver() throws ParserConfigurationException, SAXException, IOException
	{
		System.setProperty("webdriver.gecko.driver", getData("FFDriverPath")); 
		WebDriver driverFF= new FirefoxDriver();
		return driverFF;
	}
	public static WebDriver initIEDriver() throws ParserConfigurationException, SAXException, IOException
	{
		System.setProperty("webdriver.ie.driver",getData("IEDriverPath"));
			WebDriver driverIE= new InternetExplorerDriver();
		return driverIE;
	}

}
