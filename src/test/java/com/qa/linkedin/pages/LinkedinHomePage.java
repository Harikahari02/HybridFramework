package com.qa.linkedin.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class LinkedinHomePage extends BasePageWeb{

	private Logger log=Logger.getLogger(LinkedinHomePage.class);
	
			//create a constructor
			public LinkedinHomePage() {
		PageFactory.initElements(driver, this);
	}
			
			@FindBy(css="a.nav__logo-link")
			private WebElement linkedinLogo;
	
	
	@FindBy(css="a.nav__button-secondary")
	private WebElement signInLink;
	
	@FindBy(css="h1.header__content__heading ")
	private WebElement signInHeaderText;


    @FindBy(id="username")
    private WebElement emailEditbox;
	

    @FindBy(id="password")
    private WebElement passwordEditbox;
	

    @FindBy(xpath="//button[@type='submit']")
    private WebElement signInBtn;
	
	
	String linkedinHomePageTitle="LinkedIn: Log In or Sign Up";
	String linkedinSigninPageTitle="LinkedIn Login, Sign in | LinkedIn";
	
	public void verifyLinkedinHomePageTitle() {
		log.debug("verify the linkedinhomepagetitle:"+linkedinHomePageTitle);
		wait.until(ExpectedConditions.titleIs(linkedinHomePageTitle));
		Assert.assertEquals(driver.getTitle(), linkedinHomePageTitle);
	}
	
	public void verifyLinkedinSigninPageTitle() {
		log.debug("verify linked in home page title:"+linkedinSigninPageTitle);
		wait.until(ExpectedConditions.titleIs(linkedinSigninPageTitle));
		Assert.assertEquals(driver.getTitle(), linkedinSigninPageTitle);
		}
	
	public void verifyLinkedinLogo() {
		log.debug("wait and verify the linkedin logo");
		wait.until(ExpectedConditions.visibilityOf(linkedinLogo));
		Assert.assertTrue(linkedinLogo.isDisplayed());
	}
	
	public void verifyLinkedinSigninHeaderText() {
		log.debug("wait and verify linkedin sign in header text");
		wait.until(ExpectedConditions.visibilityOf(signInHeaderText));
		Assert.assertTrue(signInHeaderText.isDisplayed());
	}
	
	public void clickSigninLink() throws InterruptedException {
		log.debug("ckicking on signin link");
        click(signInLink);
	
	}
	
	public void clickSigninButton() throws InterruptedException  {
		log.debug("clickin on signin button");
		click(signInBtn);
	}
	
	public LinkedinLoggedinPage doLogin(String uname,String pwd) throws Exception {
		log.debug("performing the login operation");
		log.debug("clear the content in username editbox");
		clearText(emailEditbox);
		log.debug("type the "+uname+" in emaileditbox");
		sendKey(emailEditbox,uname);
		log.debug("clear the content in password edit box");
		clearText(passwordEditbox);
		log.debug("type the "+pwd+" in passwordeditbox");
		sendKey(passwordEditbox,pwd);
		clickSigninButton();
		return new LinkedinLoggedinPage();
		
		
	}
			
			
			
			
}
