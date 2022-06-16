package com.qa.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.gorest.pojo.User;
import com.qa.gorest.restclient.RestClient;
import com.qa.gorest.utils.TokenForImgur;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class UpdateUserTest {
	String userID;
	String baseURI="https://gorest.co.in";
	//String token="04376f78ee207b0fe388ae7ef8e81667bffb1e2b18c4b5e4cc299eebbaf25532";
	Map<String,String> tokenMap= new HashMap<String,String>();
	User userdata;
	
	@BeforeClass
	public  void doSetUpData()
	{
		String basepath="/public/v1/users";
		tokenMap=TokenForImgur.goRestToken();
		//tokenMap.put("Authorization", "Bearer "+token);
		userdata= new User("RohanMalhotra", "rohandd3113@gmail.com", "male", "active");
		
		Response res =RestClient.doPostRequest("JSON", baseURI, basepath, "POST", tokenMap, null, true, userdata);
	 userID=	res.then().extract().jsonPath().getString("data.id");
	System.out.println("userid is  " + userID);
		System.out.println(res.prettyPrint());
		
	 
	}
	
	@Test()
	public void doUpdateUser()
	{
		//int userID=resjson.getJsonObject("data.id");
		// userID=doSetUpData();
		String basepath="/public/v1/users/"+userID;
		//tokenMap.put("Authorization", "Bearer "+token);
		userdata.setGender("female");;
		Response ress=RestClient.doPutRequest("JSON", baseURI, basepath, "PUT", tokenMap, null, true, userdata);
	System.out.println(ress.statusCode());
	System.out.println(ress.prettyPrint());
	
	}
	@Test()
	public void getResponseforuser()
	{
		String basepath="/public/v1/users/"+userID;
		Response res=RestClient.doGetRequest("JSON", baseURI, basepath, "GET", tokenMap, null, true);
		System.out.println(res.prettyPrint());
		String gender=res.then().extract().jsonPath().getString("data.gender");
		Assert.assertEquals(gender, userdata.getGender());
		System.out.println(gender);
	}
	
	

}
