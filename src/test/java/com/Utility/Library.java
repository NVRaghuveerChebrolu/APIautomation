package com.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

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

}
