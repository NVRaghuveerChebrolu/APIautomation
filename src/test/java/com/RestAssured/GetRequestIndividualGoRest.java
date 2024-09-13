package com.RestAssured;

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


public class GetRequestIndividualGoRest extends Library{
	/*
	given()->Pre-requisite or precondition (request headers, payload, query or path parameters,auth 
	when()->API request (GET,POST,PUT,DELETE)
	then()->validation (status code,message, time, response payload
	*/
	
	@Test()
	public void GetRequest() {
		Response Res= RestAssured
				.given()
				.when()
				.auth().oauth2(ObjProp.getProperty("TokenOfGoRestAPI"))
				.get(ObjProp.getProperty("GoRestIndUser"));
		
		Assert.assertEquals(Res.getStatusCode(), Integer.parseInt(ObjProp.getProperty("SuccessResponseStatusCode")));
		
		ResponseBody resBody = Res.getBody();
		String ResponseFromGET_APIcall = resBody.asString();
		System.out.println("ResponseFromGET_APIcall:"+ResponseFromGET_APIcall);
		
		JsonPath jsnPath = Res.jsonPath();
		String Name = jsnPath.get("name");
		String Gender = jsnPath.get("gender");
		System.out.println("Name:"+Name);
		System.out.println("Gender:"+Gender);
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
	//	LibraryFunctions.LaunchBrowser();
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
