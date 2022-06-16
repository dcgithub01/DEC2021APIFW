package com.qa.gorest.utils;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
public class TokenForImgur {
	public static Map<String,String> mapparams;
	public static Map<Object,Object> jsonmap;
	public static Map<String,String> tokenmapGoRest;
	public static String clientID="56762092f783adf";
	public static String tokenid="04376f78ee207b0fe388ae7ef8e81667bffb1e2b18c4b5e4cc299eebbaf25532";
	
	
	public static Map<Object, Object> generateToken()
	{
		mapparams= new HashMap<>();
		mapparams.put("client_id", "56762092f783adf");
		mapparams.put("client_secret", "023f74a0d2bc668ed6a5a0ed12bd9afe1ce14066");
		mapparams.put("refresh_token", "6a14935d5152d007f1ea55e6694a6c9225f09afd");
		mapparams.put("grant_type", "refresh_token");
		RequestSpecification req=RestAssured.given();
		req.formParams(mapparams);
		Response res =req.post("https://api.imgur.com/oauth2/token");
		JsonPath js=res.then().extract().jsonPath();
		jsonmap=js.getMap("");
		System.out.println(jsonmap);
		return jsonmap;
	}
	
	
	public static Map<String, String> getAuthToken()
	{
	    String accessToken=jsonmap.get("access_token").toString();
		Map<String, String> authtokenMap= new HashMap<String,String>();
		authtokenMap.put("Authorization", "Bearer " + accessToken);
		return authtokenMap;
	}

	public static Map<String, String> getClientID()
	{

		Map<String, String> clientIDMap= new HashMap<String,String>();
		clientIDMap.put("Authorization", "Client-ID " + clientID);
		return clientIDMap;
	}
	public static Map<String, String> goRestToken()
	{
		
		tokenmapGoRest = new HashMap<String,String>();
		tokenmapGoRest.put("Authorization", "Bearer " + tokenid);
		return tokenmapGoRest;
	}
	

}
