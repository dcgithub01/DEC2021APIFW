package com.qa.gorest.tests;

import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.gorest.pojo.User;
import com.qa.gorest.restclient.RestClient;
import com.qa.gorest.utils.TokenForImgur;

import io.restassured.response.Response;
import junit.framework.Assert;

public class DeleteUserTest {

	public String baseURI="https://gorest.co.in";
	public User user;
	public String id;
	

	@BeforeMethod
public void setUp()
{
	String basePath="/public/v1/users";
	Map<String ,String> token=TokenForImgur.goRestToken();
	user= new User("karthikAryan", "findyourself2@gmail.com", "male", "active");
	Response res=RestClient.doPostRequest("JSON", baseURI, basePath, "POST", token, null, true, user);
	System.out.println(res.prettyPrint());
	System.out.println(res.statusCode());
	 id=res.then().extract().jsonPath().getString("data.id");
	
	
	
}

@Test()
public void deleteUser(){
	
	Map<String,String>token=TokenForImgur.goRestToken();
	String basePath="/public/v1/users/"+ id;
	Response res=RestClient.dodeleteRequest("JSON", baseURI, basePath, "DELETE", token, null, true, null);
	
	System.out.println(res.statusCode());
	System.out.println(res.prettyPrint());
	
}

	
	
}


