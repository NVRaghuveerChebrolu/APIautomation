package com.RestAssured;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

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

import com.Utility.Library;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;


public class PUTRequestUpdateUserInGoRest extends Library {
	@Test()
	public void PutRequest() throws FileNotFoundException {
		
		FileInputStream objFileInput = new FileInputStream(new String(System.getProperty("user.dir") + "//src//test//resources//PayloadsForRest//UpdateUserInGoRest.txt"));
		
	Response Res= RestAssured
			.given()
			.header("Content-type", "application/json")
			.and()
			.body(objFileInput)
			.when()
			.auth().oauth2(ObjProp.getProperty("TokenOfGoRestAPI"))
			.put(ObjProp.getProperty("GorestUpdateUser"));
		
	Assert.assertEquals(Res.getStatusCode(), Integer.parseInt(ObjProp.getProperty("SuccessResponseStatusCode")));
	ResponseBody resBody = Res.getBody();
	String ResponseFromPut_APIcall = resBody.asString();
	System.out.println("ResponseFromPut_APIcall:"+ResponseFromPut_APIcall);
	
	JsonPath jsnPath = Res.jsonPath();
	String Name = jsnPath.get("name");
	String Gender = jsnPath.get("gender");
	String status = jsnPath.get("status");
	System.out.println("Name:"+Name);
	System.out.println("Gender:"+Gender);
	System.out.println("status:"+status);
	//updated name and status are hard coded below
	Assert.assertEquals(Name, "Sanjay Datta 1234", "User name not updated Successfully");
	Assert.assertEquals(status, "inactive", "User status not updated Successfully");
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
