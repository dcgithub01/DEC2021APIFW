package com.qa.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.gorest.restclient.RestClient;
import com.qa.gorest.utils.TokenForImgur;

import io.restassured.response.Response;

public class UploadImagePostImgur {
	
	public String baseURI="https://api.imgur.com";
	public String basePath="/3/upload";
	public 	Map<String,String> token;
	
	@BeforeMethod
	public void uploadImageSetup()
	{
		//Map<Object,Object>token=TokenForImgur.generateToken();
		token=TokenForImgur.getClientID();
		
	}
	
	
	@Test
public void uploadImageTest()

{
		Map<String,String> formsparam= new HashMap<String,String>();
		formsparam.put("title", "title1");
		formsparam.put("description", "desc1");
		Response response=RestClient.doPostRequest("multipart", baseURI, basePath, "POST", token, null, true, formsparam);
		response.statusCode();
		response.prettyPrint();
		response.statusLine();
}

}
