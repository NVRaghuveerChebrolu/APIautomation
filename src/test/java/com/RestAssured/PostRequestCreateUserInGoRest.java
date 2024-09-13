package com.RestAssured;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import com.Utility.Library;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;


public class PostRequestCreateUserInGoRest extends Library{
	@Test()
	public void PostRequest() throws FileNotFoundException {	
	System.out.println("inside PostRequest");
	FileInputStream objFileInput = new FileInputStream(new String(System.getProperty("user.dir") + "//src//test//resources//PayloadsForRest//CreateUserInGoRest.txt"));
	Response Res= RestAssured
			.given()
			.header("Content-type", "application/json")
			.and()
			.body(objFileInput)
			.when()
			.auth().oauth2(ObjProp.getProperty("TokenOfGoRestAPI"))
			.post(ObjProp.getProperty("GoRestCreateUser"));
	Assert.assertEquals(Res.getStatusCode(), Integer.parseInt(ObjProp.getProperty("CreatedResponseStatusCode")));
	ResponseBody resBody = Res.getBody();
	String ResponseFromPost_APIcall = resBody.asString();
	System.out.println("ResponseFromPost_APIcall:"+ResponseFromPost_APIcall);
	
	JsonPath jsnPath = Res.jsonPath();
	String Name = jsnPath.get("name");
	String Gender = jsnPath.get("gender");
	System.out.println("Name:"+Name);
	System.out.println("Gender:"+Gender);
	Assert.assertEquals(Name, ObjProp.getProperty("PostRequestUserNameCreated"), "User not created Successfully with expected name");
	}
	
	@Test
	public void postUsingPOJO() {
		System.out.println("Post using POJO");
		POJOdata data = new POJOdata();
		data.setName("Aravind");
		data.setJob("Developer");
		given().header("Content-type", "application/json").body(data).
		//given below POST end point URL is hard coded
		when().post("https://reqres.in/api/users").
		then().
		statusCode(201).
		body("name",equalTo("Aravind")).
		log().all();
	}
	
	@Test
	public void testPOJOResponse() {
		System.out.println("Post using POJO");
		
//		HashMap<String, String> user = new HashMap<>();
//      user.put("name", "John Doe");
//      user.put("job", "Automation Tester");
		
		POJOdata data = new POJOdata();
		data.setName("Govind");
		data.setJob("Tester");
		Response res = given().
		header("Content-type", "application/json").
		body(data).
		//body(user).
		when().
		post("https://reqres.in/api/users");
		System.out.println("Response form testPOJO:");
		System.out.println(res.asPrettyString());
		String name = res.jsonPath().getString("name");
		//below Govind is hard coded
		Assert.assertEquals(name, "Govind");
	
	}
	

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("inside beforeMethod");
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("inside afterMethod");
	}

	@BeforeClass
	public void beforeClass() {
		System.out.println("inside beforeClass");
	}

	@AfterClass
	public void afterClass() {
		System.out.println("inside afterClass");
	}

	@BeforeTest
	public void beforeTest() {
		System.out.println("inside beforeTest");
	//	LaunchBrowser();
	}

	@AfterTest
	public void afterTest() {
		System.out.println("inside afterTest");
	}

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("inside beforeSuite");
		try {
			ReadPropertiesFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("inside afterSuite");
	}
	
}
