package com.gottmusig.cucumber.features;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SearchedCharacterFound {

	public final static String PATH_TO_DRIVER = "C:/Users/Tarosso/Desktop";
	
	WebDriver driver;
	
	@Given("^I navigated to the GottMusIg website$")
	public void i_navigated_to_the_GottMusIg_website() throws Throwable {
		System.setProperty("webdriver.gecko.driver", PATH_TO_DRIVER + "/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.navigate().to("http://localhost:8080/frontend/wicket/bookmarkable/com.gottmusig.pages.GearPage");
	}

	@When("^I choose the location \"([^\"]*)\"$")
	public void shouldChooseTheLocationDeDe(String arg1) throws Throwable {
		Select location = new Select(driver.findElement(By.cssSelector("#location-selenium")));
		location.selectByValue("0");
	}

	@When("^I choose the realm \"([^\"]*)\"$")
	public void shouldChooseTheRealmKragJin(String arg1) throws Throwable {
		Select realm = new Select(driver.findElement(By.id("realm-selenium")));
		realm.selectByValue("5");
	}

	@When("^I look for \"([^\"]*)\"$")
	public void shouldLookForTaurosso(String arg1) throws Throwable {
		driver.findElement(By.id("name-selenium")).sendKeys(arg1);
	}

	@When("^I click the \"([^\"]*)\" button$")
	public void shouldClickTheSearchButton(String arg1) throws Throwable {
		driver.findElement(By.id("search-selenium")).click();
	}

	@Then("^I see the character panel$")
	public void checkCharacterPanel() throws Throwable {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		String characterName = driver.findElement(By.id("character-name-selenium")).getText();
		Assert.assertTrue(characterName.contains("Taurosso"));
	}
	
}