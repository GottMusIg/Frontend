package com.gottmusig.cucumber.features;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SearchedCharacterFound {

	public final static String PATH_TO_DRIVER = "";
	
	WebDriver driver;
	
	@Given("^I navigated to the GottMusIg website$")
	public void shouldNavigatedToGottMusIgSite() throws Throwable {
		System.setProperty("webdriver.gecko.driver", PATH_TO_DRIVER + "/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.navigate().to("http://localhost:8080/frontend/");
	}

	@When("^I click to the \"([^\"]*)\" link$")
	public void shouldClickToTheGearLink(String arg1) throws Throwable {
//		driver.findElement(By.id("gear")).click();
	}

	@When("^I choose the location \"([^\"]*)\"$")
	public void shouldChooseTheLocationDeDe(String arg1) throws Throwable {
	}

	@When("^I choose the realm \"([^\"]*)\"$")
	public void shouldChooseTheRealmKragJin(String arg1) throws Throwable {
	}

	@When("^I look for \"([^\"]*)\"$")
	public void shouldLookForTaurosso(String arg1) throws Throwable {
	}

	@When("^I click the \"([^\"]*)\" button$")
	public void shouldClickTheSearchButton(String arg1) throws Throwable {
	}

	@Then("^I see the character panel$")
	public void checkCharacterPanel() throws Throwable {
	}
	
}