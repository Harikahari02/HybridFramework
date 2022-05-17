package com.qa.linkedin.testcases;

import org.testng.annotations.Test;
//kjhljkfdgljkkjpj;lj;lkj

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.qa.linkedin.base.TestBase;
import com.qa.linkedin.pages.LinkedinHomePage;
import com.qa.linkedin.pages.LinkedinLoggedinPage;
import com.qa.linkedin.pages.SearchResultsPage;
import com.qa.linkedin.util.ExcelUtils;

import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;

public class SearchDatadrivenTest extends TestBase{
	private Logger log=Logger.getLogger(SearchDatadrivenTest.class);
	private LinkedinHomePage lHmPg;
	private LinkedinLoggedinPage llpg;
	private SearchResultsPage srpg;
	private String excelPath=System.getProperty("user.dir")+"\\src\\test\\java\\com\\qa\\linkedin\\data\\searchdata.xlsx";
	
	@Test
	public void doLoginTest() throws Exception {
	log.debug("Started executing the doLoginTest()... ");	
	lHmPg.verifyLinkedinHomePageTitle();
	lHmPg.verifyLinkedinLogo();
	lHmPg.clickSigninLink();
	lHmPg.verifyLinkedinSigninPageTitle();
	lHmPg.verifyLinkedinSigninHeaderText();
	llpg=lHmPg.doLogin(readPropertyValue("uname"), readPropertyValue("pwd"));
	llpg.verifylinkedinLoggedinPgTitle();
	
	}
	
	
  @Test(dataProvider = "dp",dependsOnMethods= {"doLoginTest"})
  public void doSearchTest(String s) throws Exception {
  log.debug("started executing the searchTest for:"+s);
  llpg.verifyProfileRailCard();
  srpg=llpg.doPeopleSearch(s);
  Thread.sleep(1000);
  long count=srpg.getResultsCount();
  log.debug("search results count for:"+s+" is:"+count);
  srpg.clickOnHomeTab();
  
  }

  @DataProvider
  public Object[][] dp() throws org.apache.poi.openxml4j.exceptions.InvalidFormatException, IOException  {
    Object[][] data=new ExcelUtils().getTestData(excelPath,"Sheet1");
  return data;
    }
  
  @BeforeClass
  public void initializeObjects() {
	  log.debug("initiliaze all the page classes");
	  lHmPg=new LinkedinHomePage();
	  llpg=new LinkedinLoggedinPage();
	  srpg=new SearchResultsPage();
  }

  @AfterClass
  public void afterClass() throws InterruptedException {
	  log.debug("perform the logout operation from applicatin");
	  llpg.doSignOut();
	 
  }

}
