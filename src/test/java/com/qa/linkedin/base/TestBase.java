package com.qa.linkedin.base;

import org.testng.annotations.AfterMethod;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
public static WebDriver driver=null;
public WebDriverWait wait=null;
private Logger log=Logger.getLogger(TestBase.class);

/**
 *Read the propertieas from properties file 
 *
 * @throws IOException
 */

public String readPropertyValue(String key) throws IOException {
	log.info("Create Object for Properties class");
	Properties prop = new Properties();
	log.info("Read the properties file");
	try {
		FileInputStream fis = new FileInputStream(new File(System.getProperty("user.dir")
				+ "\\src\\test\\java\\com\\qa\\linkedin\\config\\config.properties"));
		prop.load(fis);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return prop.getProperty(key);
}

@BeforeSuite
public void setup() throws IOException{
log.debug("started executing the @BeforeSuite");
String browserName=readPropertyValue("browser");
log.info("i am launching the browser:"+browserName);
if(browserName.equalsIgnoreCase("chrome")) {
	WebDriverManager.chromedriver().setup();
	driver=new ChromeDriver();
}else if(browserName.equalsIgnoreCase("firefox")) {
	WebDriverManager.firefoxdriver().setup();
	FirefoxOptions opt=new FirefoxOptions();
	opt.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
	//interface refvar=new implementedclass():
	driver=new FirefoxDriver(opt);
	
}else if(browserName.equalsIgnoreCase("edge")) {
	WebDriverManager.edgedriver().setup();
	driver=new EdgeDriver();
}
log.info("maximize the window");
driver.manage().window().maximize();

log.info("add implicit wait");
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
log.info("create object for WebDriverWait class");
wait=new WebDriverWait(driver,Duration.ofSeconds(30));
log.info("launch the application url:"+readPropertyValue("appUrl"));
driver.get(readPropertyValue("appUrl"));
}

@AfterMethod
@AfterSuite
public void tearDown() {
	log.info("closing the browser");
    if(driver!=null) {
	driver.quit();
	


}
}














	
}
	
	

