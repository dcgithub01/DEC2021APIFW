package com.qa.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.qa.gorest.restclient.RestClient;

import io.restassured.response.Response;

public class GetUserDetailsTest {

	public String baseUri="https://gorest.co.in/";
	public String basePath="public/v1/users";
	public String token="04376f78ee207b0fe388ae7ef8e81667bffb1e2b18c4b5e4cc299eebbaf25532";
	
	@Test()
	public void getAllUserData()
	{
		Map<String, String> tokenMap= new HashMap<String, String>();
		tokenMap.put("Authorization", "Bearer " + token);
		
		Response response=RestClient.doGetRequest("JSON", baseUri, basePath, "GET", tokenMap, null, true);
		System.out.println(response.getStatusCode());
		System.out.println(response.getStatusLine());
		System.out.println(response.prettyPrint());
		
	}
	
}
