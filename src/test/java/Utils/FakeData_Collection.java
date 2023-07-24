package Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import com.github.javafaker.Faker;

public class FakeData_Collection {
	
	
	static final String name = "";
	static final int id = 0;
	static final int year = 0;
	static final String color = "";
	static final String pantone_value = "";
	static final int CategoryID = 0;
	static final String CategoryName = "";
	
	//LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
	//LinkedHashMap<String, Object> catMap = new LinkedHashMap<String, Object>();
	//List<LinkedHashMap<String, Object>> categories = new ArrayList<LinkedHashMap<String, Object>>();
	public static JSONObject jObject = new JSONObject();
	public static JSONArray jArray = new JSONArray();
	//static JSONObject catObject = new JSONObject();
	
	static Faker faker = new Faker();
	
	public static String setName() {
		String firstName = faker.name().fullName();
		return firstName;
	}
	
	public static int setId() {
		int id = Integer.parseInt(faker.number().digits(2));
		return id;
	}
	
	public static int setYear() {
		int year = faker.number().numberBetween(1900, 2023);
		return year;
	}
	
	public static int setColor() {
		int color = faker.color().hashCode();
		return color;
	}
	
	public static String setPantoneValue() {
		String pantoneValue = faker.starTrek().character();
		return pantoneValue;
	}
	
	public static int setCategoryID() {
		int catId = Integer.parseInt(faker.number().digits(2));
		return catId;
	}
	
	public static String setCategoryName() {
		String catName = faker.company().name();
		return catName;
	}
	
//	public void setUserData() {
//		map.put("id", FakeData_Collection.setId());
//		map.put("name", FakeData_Collection.setName());
//		map.put("year", FakeData_Collection.setYear());
//		map.put("color", FakeData_Collection.setColor());
//		map.put("pantone_value", FakeData_Collection.setPantoneValue());
//		catMap.put("CategoryID", FakeData_Collection.setCategoryID());
//		catMap.put("CategoryName", FakeData_Collection.setCategoryName());
//		categories.add(catMap);
//		map.put("categories1", categories);
	
	//catObject.put("CategoryID", FakeData_Collection.setCategoryID());
			//catObject.put("CategoryName", FakeData_Collection.setCategoryName());
			//jArray.put(new JSONObject().put("CategoryName", FakeData_Collection.setCategoryName()));
//	}
	
	public static void setUserData_User() {
		
		jObject.put("id", FakeData_Collection.setId());
		jObject.put("name", FakeData_Collection.setName());
		jObject.put("year", FakeData_Collection.setYear());
		jObject.put("color", FakeData_Collection.setColor());
		jObject.put("pantone_value", FakeData_Collection.setPantoneValue());
		jArray.put(new JSONObject().put("CategoryName", FakeData_Collection.setCategoryName()).put("CategoryID", FakeData_Collection.setCategoryID()));
		//jArray.put(new JSONObject().put("CategoryID", FakeData_Collection.setCategoryID()));
		jObject.put("categories", jArray);
	}

}
