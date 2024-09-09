package com.RestAssured;

import org.testng.annotations.Test;

import com.Utility.Library;

import org.testng.annotations.BeforeMethod;

import java.io.IOException;

import org.testng.annotations.AfterMethod;

public class GoRestAllv2Users extends Library {
	
  @Test
  public void ValidateGoRestIndV2AllUsers() {
	  System.out.println("ValidateGoRestIndV2AllUsers");
  }
  
  @BeforeMethod
  public void beforeMethod() {
	  try {
		ReadPropertiesFile();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }

  @AfterMethod
  public void afterMethod() {
	  System.out.println("inside after method");
  }

}
