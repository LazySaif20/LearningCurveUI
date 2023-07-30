package StepDefinitions.API_Definitions;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.testng.Assert;

import com.google.gson.JsonArray;
import com.jayway.jsonpath.JsonPath;

import Utils.FakeData_Collection;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class API_Steps {

	private final String baseURL = "http://localhost:3000";
	private Response response;
	private RequestSpecification reqSpecification;
	private JsonArray jsonArray;

	@When("GET call to {string}")
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
		Assert.assertEquals(categoryName, categoryNames.get(RecordNumber - 1).get(0));
	}

	@Then("we have certain data for the users")
	public void we_have_certain_data_for_the_users() {
		// Write code here that turns the phrase above into concrete actions
		FakeData_Collection.setUserData_User();
		RestAssured.baseURI = baseURL;
		reqSpecification = RestAssured.given().contentType(ContentType.JSON)
				.body(FakeData_Collection.jObject.toString());
	}

	@When("POST the data to {string}")
	public void POST_the_data_to(String uri) throws URISyntaxException {
		// Write code here that turns the phrase above into concrete actions
		response = reqSpecification.when().post(new URI(uri));
		Assert.assertEquals(201, response.getStatusCode());
	}

	@Then("validate the user data is present {string}")
	public void validate_the_user_data_is_present(String uri) throws URISyntaxException {
		// Write code here that turns the phrase above into concrete actions
		response = reqSpecification.when().get(new URI(uri));
		Assert.assertEquals(FakeData_Collection.jObject.getString("name"),
				JsonPath.read(response.body().asString(), "$[-1].name").toString());
	}

	@When("PUT the data to the expected user {int}, {string}")
	public void put_the_data_to_the_expected_user(int userID, String uri) throws URISyntaxException {
		// Write code here that turns the phrase above into concrete actions
		response = reqSpecification.when().put(new URI(uri + userID));
	}

	@When("validate the updated data is present for the user {int}, {string}")
	public void validate_the_updated_data_is_present_for_the_user(int userID, String uri) throws URISyntaxException {
		// Write code here that turns the phrase above into concrete actions
		if (userID == 11) {
			response = reqSpecification.when().get(new URI(uri + userID));
			String updatedName = JsonPath.read(response.body().asString(), "$.name").toString();
			String updatedColor = JsonPath.read(response.body().asString(), "$.color").toString();
			String updatedYear = JsonPath.read(response.body().asString(), "$.year").toString();
			String updatedPV = JsonPath.read(response.body().asString(), "$.pantone_value").toString();
			String updatedCatId = JsonPath.read(response.body().asString(), "$.categories[0].CategoryID").toString();
			String updatedCatName = JsonPath.read(response.body().asString(), "$.categories[0].CategoryName")
					.toString();

			Assert.assertEquals(FakeData_Collection.jObject.getString("name"), updatedName);
			Assert.assertEquals(FakeData_Collection.jObject.getInt("color"), Integer.parseInt(updatedColor));
			Assert.assertEquals(FakeData_Collection.jObject.getInt("year"), Integer.parseInt(updatedYear));
			Assert.assertEquals(FakeData_Collection.jObject.getString("pantone_value"), updatedPV);
			Assert.assertEquals(FakeData_Collection.jArray.getJSONObject(0).getInt("CategoryID"),
					Integer.parseInt(updatedCatId));
			Assert.assertEquals(FakeData_Collection.jArray.getJSONObject(0).getString("CategoryName"), updatedCatName);
		} else {
			String updatedName = JsonPath.read(response.body().asString(), "$.name").toString();
			Assert.assertEquals(FakeData_Collection.jObject.getString("name"), updatedName);
		}
	}

	@When("PATCH the data to the expected user {int}, {string}")
	public void patch_the_data_to_the_expected_user(int userID, String uri) throws URISyntaxException {
		// Write code here that turns the phrase above into concrete actions
		response = reqSpecification.when().patch(new URI(uri + userID));
	}
	
	@Given("get the data to be updated for the user")
	public void get_the_data_to_be_updated_for_the_user() throws URISyntaxException {
		// Write code here that turns the phrase above into concrete actions
		FakeData_Collection.setUserData_Name();
		RestAssured.baseURI = baseURL;
		reqSpecification = RestAssured.given().contentType(ContentType.JSON)
				.body(FakeData_Collection.jObject.toString());
	}
	
	@When("GET call is made with Query as {string} and Path as {string} Params")
	public void get_call_is_made_with_query_and_path_params(String query, String path) throws URISyntaxException {
		// Write code here that turns the phrase above into concrete actions
		RestAssured.baseURI = baseURL;
		String[] qparamArray = query.split(",");
		reqSpecification = RestAssured.given()
				.pathParam("path", path)
				.queryParam(qparamArray[0], qparamArray[1]);
		response = reqSpecification.when().get("{path}");
	
	}
	
	@Then("validate the user details are as expected as {int}, {int}, {string}, {int}, {string}, {int} and {string}")
	public void validate_the_user_details_are_as_expected_as(int color, int year, String name, int id, String pantone_value, int CategoryID, String CategoryName) throws URISyntaxException {
		// Write code here that turns the phrase above into concrete actions

		List<String> responseName = JsonPath.read(response.body().asString(), "$[*].name");
		List<String> responsePantone_value = JsonPath.read(response.body().asString(), "$[*].pantone_value");
		List<Integer> responseColor = JsonPath.read(response.body().asString(), "$..color");
		List<Integer> responseYear = JsonPath.read(response.body().asString(), "$..year");
		List<Integer> responseId = JsonPath.read(response.body().asString(), "$..id");
		List<Integer> responseCategoryId = JsonPath.read(response.body().asString(), "$..categories[0].CategoryID");
		List<String> responseCategoryName = JsonPath.read(response.body().asString(), "$..categories[0].CategoryName");
		
		Assert.assertEquals(color, responseColor.get(0));
		Assert.assertEquals(year, responseYear.get(0));
		Assert.assertEquals(name, responseName.get(0));
		Assert.assertEquals(id, responseId.get(0));
		Assert.assertEquals(pantone_value, responsePantone_value.get(0));
		Assert.assertEquals(CategoryID, responseCategoryId.get(0));
		Assert.assertEquals(CategoryName, responseCategoryName.get(0));
		
	}
	
	@Then("print all the member names")
	public void print_all_the_member_names() {
		// Write code here that turns the phrase above into concrete actions
		List<String> names = JsonPath.read(response.body().asString(), "$[*]..name");
		System.out.println(names);
	}
	
	@When("GET call to {string} with id as {int}")
	public void get_call_to_url_with_id(String url, int id) throws URISyntaxException {
		// Write code here that turns the phrase above into concrete actions
		RestAssured.baseURI = baseURL;
		reqSpecification = RestAssured.given();
		response = reqSpecification.get(new URI(url+"?id="+id));
	}
	
	@Then("validate the json response schema is as expected")
	public void validate_the_json_response_schema_is_as_expected() {
		// Write code here that turns the phrase above into concrete actions
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File("C:\\Users\\mdsai\\LearningCurve\\LearningCurveUI\\JsonSchema.json")));
	}
}
