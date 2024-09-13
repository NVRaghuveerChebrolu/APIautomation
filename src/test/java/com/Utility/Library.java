package com.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class Library {
	public Properties ObjProp;
	
	public void ReadPropertiesFile() throws IOException {
		File objfile = new File(System.getProperty("user.dir")+"//src//test//resources//propertiesInfo//project.properties");
		FileInputStream objFileInput= new FileInputStream(objfile);
		ObjProp = new Properties();
		ObjProp.load(objFileInput);
		System.out.println("GoRestIndUser: "+ObjProp.getProperty("GoRestIndUser"));
		System.out.println("CreatedResponseStatusCode: "+ObjProp.getProperty("CreatedResponseStatusCode"));
	}
	
	public void getAllUsers() {
		Response Res= RestAssured
				.given()
				.when()
				.auth().oauth2(ObjProp.getProperty("TokenOfGoRestAPI"))
				.get(ObjProp.getProperty("GoRestAllV2Users"));
		System.out.println("Status code of Get Call:"+Res.getStatusCode());
		Assert.assertEquals(Res.getStatusCode(), Integer.parseInt(ObjProp.getProperty("SuccessResponseStatusCode")));
		ResponseBody resBody = Res.getBody();
		String ResponseFromGET_APIcall = resBody.asString();
		System.out.println("ResponseFromGET_APIcall:"+ResponseFromGET_APIcall);
	}

}
