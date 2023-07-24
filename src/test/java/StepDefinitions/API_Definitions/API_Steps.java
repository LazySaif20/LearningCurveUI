package StepDefinitions.API_Definitions;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

import Utils.FakeData_Collection;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class API_Steps {
	
	private final String baseURL= "http://localhost:3000";
	private Response response;
	private RequestSpecification reqSpecification;
	private JsonArray jsonArray;
	
	@Given("GET call to {string}")
	public void get_call_to(String url) throws URISyntaxException {
	    // Write code here that turns the phrase above into concrete actions
	   RestAssured.baseURI = baseURL;
	   reqSpecification = RestAssured.given();
	   response = reqSpecification.when().get(new URI(url));
	}

	@Then("response is {string}")
	public void response_is(String Response) {
	    // Write code here that turns the phrase above into concrete actions
		String actualResponse = Integer.toString(response.then().extract().statusCode());
		Assert.assertEquals(Response, actualResponse);
	}
	
	@Then("validate the count returned matches the expected number of records {int}")
	public void validate_the_count_returned_matches_the_expected_number_of_records(int numberOfRecords) {
	    // Write code here that turns the phrase above into concrete actions
		List<String> categoryIds = response.jsonPath().getList("categories.CategoryID");
		Assert.assertEquals(numberOfRecords, categoryIds.size());
	}
	
	@Then("validat the category name of the {int} th category is as expected {string}")
	public void validat_the_category_name_of_the_th_category_is_as_expected(int RecordNumber, String categoryName) {
	    // Write code here that turns the phrase above into concrete actions
		List<List<String>> categoryNames = response.jsonPath().getList("categories.CategoryName");
		Assert.assertEquals(categoryName, categoryNames.get(RecordNumber-1).get(0));
	}
	
	@Then("we have certain data for the users")
	public void we_have_certain_data_for_the_users() {
	    // Write code here that turns the phrase above into concrete actions
		FakeData_Collection.setUserData_User();
		RestAssured.baseURI = baseURL;
		reqSpecification = RestAssured.given()
				.contentType("application/json")
				.body(FakeData_Collection.jObject.toString());
	}
	
	@Then("POST the data to {string}")
	public void POST_the_data_to(String uri) throws URISyntaxException {
	    // Write code here that turns the phrase above into concrete actions
		response = reqSpecification.when().post(new URI(uri));
		Assert.assertEquals(201, response.getStatusCode());
	}
	
	@Then("validate the user data is present {string}")
	public void validate_the_user_data_is_present(String uri) throws URISyntaxException {
	    // Write code here that turns the phrase above into concrete actions
		response = reqSpecification.when().get(new URI(uri));
		Assert.assertEquals(FakeData_Collection.jObject.getString("name"), JsonPath.read(response.body().asString(), "$[-1].name").toString());
	}
	
	
}
