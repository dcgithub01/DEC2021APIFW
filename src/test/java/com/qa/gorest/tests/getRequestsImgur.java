package com.qa.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.gorest.restclient.RestClient;
import com.qa.gorest.utils.TokenForImgur;

import io.restassured.response.Response;

public class getRequestsImgur {
	Map<Object, Object> getJson;
	String token;
	String accountName;
	Map<String,String> AuthToken= new HashMap<String, String>();

	@BeforeMethod
	public void setUp()
	{
		getJson=TokenForImgur.generateToken();
	 AuthToken=	TokenForImgur.getAuthToken();
		token=getJson.get("access_token").toString();
		accountName=getJson.get("account_username").toString();
		//return authToken;
	}
	
	@Test
	public void getBlockedAccountStatus()
	{
		String baseURI="https://api.imgur.com";
		String basePath="account/v1/"+accountName+"/block";
		Response response=RestClient.doGetRequest(null, baseURI, basePath, "GET", AuthToken, null, true);
		System.out.println(response.statusCode());
		System.out.println(response.statusLine());
		System.out.println(response.prettyPrint());
	}

}
