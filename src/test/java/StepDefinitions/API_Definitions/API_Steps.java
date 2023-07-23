package StepDefinitions.API_Definitions;

import java.net.URI;
import java.net.URISyntaxException;

import org.testng.Assert;

import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class API_Steps {
	
	private final String baseURL= "http://localhost:3000";
	private Response response;
	private Scenario scenario;
	
	
	@Given("get call to {string}")
	public void get_call_to(String url) throws URISyntaxException {
	    // Write code here that turns the phrase above into concrete actions
	   RestAssured.baseURI = baseURL;
	   RequestSpecification reqSpecification = RestAssured.given();
	   response = reqSpecification.when().get(new URI(url));
	}

	@Then("response is {string}")
	public void response_is(String Response) {
	    // Write code here that turns the phrase above into concrete actions
		String actualResponse = Integer.toString(response.then().extract().statusCode());
		Assert.assertEquals(Response, actualResponse);
	}

}
