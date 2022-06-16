package com.qa.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.gorest.pojo.User;
import com.qa.gorest.restclient.RestClient;
import com.qa.gorest.utils.ExcelUtil;
import com.qa.gorest.utils.TokenForImgur;

import io.restassured.response.Response;
import junit.framework.Assert;

public class CreateUserTest {

	public String baseURI="https://gorest.co.in/";
	public String basePath="public/v1/users";
	public String token="04376f78ee207b0fe388ae7ef8e81667bffb1e2b18c4b5e4cc299eebbaf25532";
	public String sheetname="UserData";
	public String id;
	public 	User user;
	
	@Test()
	public void createUser()
	{
		Map<String, String> tokenMap= new HashMap<String, String>();
		tokenMap.put("Authorization", "Bearer " + token);
		 user= new User("testingdc", "direct26@gmail.com", "male", "inactive");
	Response res=	RestClient.doPostRequest("JSON", baseURI, basePath, "POST", tokenMap, null, true, user);
		System.out.println(res.statusCode());
		System.out.println(res.prettyPrint());
		id=res.then().extract().jsonPath().getString("data.id");
	}
	
	@Test()
	public void getUserDetails()
	{
		Map<String, String> tokenMap= TokenForImgur.goRestToken();
		String basePathh="public/v1/users/"+id;
		Response res=RestClient.doGetRequest("JSON", baseURI, basePathh, "GET", tokenMap, null, true);
		String actualEmail=res.then().extract().jsonPath().getString("data.email");
		Assert.assertEquals(actualEmail, user.getEmail());
		System.out.println(res.statusCode());
		System.out.println(res.prettyPrint());
		System.out.println("Getting user email id   "+user.getEmail());
		System.out.println("getting ID " +id);
		
		
		
	}
	
	@DataProvider()
	public Object[][] loadData()
	{
		return ExcelUtil.getDataFromExcel(sheetname);
	}
	@Test(dataProvider="loadData", enabled=false)
	public void createMultipleUserUsingExcel(String name,String gender,String email,String status)
	{
		Map<String, String> tokenMap= new HashMap<String, String>();
		tokenMap.put("Authorization", "Bearer " + token);
		
		User user1= new User(name, email, gender, status);
		Response response =RestClient.doPostRequest("JSON", baseURI, basePath, "POST", tokenMap, null, true, user1);
		System.out.println(response.statusCode());
		System.out.println(response.statusLine());
		System.out.println(response.prettyPrint());
	}
	
}
