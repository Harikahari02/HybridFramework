package com.qa.linkedin.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class LinkedinLoggedinPage extends BasePageWeb{
	
	private Logger log=Logger.getLogger(LinkedinLoggedinPage.class);
	
	public LinkedinLoggedinPage() {
		PageFactory.initElements(driver, this);
	}
@FindBy(css="div[class*='feed-identity-module']")
private WebElement profileRailCard;

@FindBy(css="img[class*=global-nav__me-photo]")
private WebElement profileImageIcon;

@FindBy(xpath="//a[@class='global-nav__secondary-link mv1'][contains(.,'Sign Out')]")
private WebElement signOutLink;

@FindBy(xpath="//a[//input[contains(@class,'search-global-typeahead__input')]")
private WebElement searchEditBox;

String linkedinLoggedinPgTitle="Feed | LinkedIn";

public void verifylinkedinLoggedinPgTitle() {
	log.debug("verify the logged in page title:"+linkedinLoggedinPgTitle);
	wait.until(ExpectedConditions.titleContains(linkedinLoggedinPgTitle));
	Assert.assertTrue(driver.getTitle().contains(linkedinLoggedinPgTitle));
}

public void verifyProfileRailCard() {
	log.debug("wait and verify the linkedin loggedin page profile railcaard");;
	wait.until(ExpectedConditions.visibilityOf(profileRailCard));
	Assert.assertTrue(profileRailCard.isDisplayed());
	
}

public void doSignOut() throws InterruptedException {
	log.debug("performing the doSignout action....");
	wait.until(ExpectedConditions.visibilityOf(profileImageIcon));
	click(profileImageIcon);
	log.debug("click on Signout link");
	wait.until(ExpectedConditions.visibilityOf(signOutLink));
	click(signOutLink);
}

public SearchResultsPage doPeopleSearch(String keyword) throws InterruptedException {
	log.debug("performing the people search for:"+keyword);
	clearText(searchEditBox);
	log.debug("Type the search :"+keyword+" in searcheditbox");
	sendKey(searchEditBox,keyword);
	log.debug("press the ENTER key");
	searchEditBox.sendKeys(Keys.ENTER);
	Thread.sleep(2000);
	return new SearchResultsPage();
}



}


