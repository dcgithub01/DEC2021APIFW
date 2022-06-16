package com.qa.gorest.restclient;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.qa.gorest.utils.ObjectToJsonConverter;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * 
 * @author Deepak
 *
 */

// setBASEURI,CReateREquest--COntentTYpe,HeaderToken,Params,Log
public class RestClient {

	public static Response doGetRequest(String contentType, String baseURI, String Basepath, String httpMethod,
			Map<String, String> token, Map<String, String> params, boolean log) {
		setBaseURL(baseURI);
		RequestSpecification request=createRequest(contentType, token, params, log);
		return getResponse(request, httpMethod, Basepath);
     
	}
	
	public static Response doPostRequest(String contentType, String baseURI, String basepath, String httpMethod,
			Map<String, String>  token, Map<String, String> params, boolean log, Object data)
	{
		setBaseURL(baseURI);
		RequestSpecification request= createRequest(contentType, token, params, log);
		request=attachPayload(request, data);
	return	getResponse(request, httpMethod, basepath);
		
		
	}
	
	public static Response doPutRequest(String contentType,String baseURI,String basepath,String httpMethods,Map<String,String>token,Map<String,String>params,boolean log,Object obj)
	{
		RequestSpecification request= createRequest(contentType, token, params, log);
		request=attachPayload(request, obj);
		return getResponse(request, httpMethods, basepath);
	}
	
	public static Response dodeleteRequest(String contentType,String baseURI,String basePath,String httpMethod,Map<String,String> token,Map<String,String>params,boolean log,Object obj){
		setBaseURL(baseURI);
		RequestSpecification request=createRequest(contentType, token, params, log);
		return getResponse(request, httpMethod, basePath);
	}
	private static RequestSpecification attachPayload(RequestSpecification request,Object obj)
	{
		if(obj!=null)
		{
		if(obj instanceof Map)
		{
			request.formParams((Map<String, String>) obj);
			
		}
		else {
		String bodydata=ObjectToJsonConverter.convertToJson(obj);
		request.body(bodydata);
		}
		}
		return request;
		
	}
	private static void setBaseURL(String baseURl) {
		if (baseURl != null) {
			RestAssured.baseURI = baseURl;
		}

	}

	private static RequestSpecification createRequest(String contenttype, Map<String, String>  token, Map<String, String> params, boolean log) {
		RequestSpecification request=null;

		if (log) {
			request = RestAssured.given().log().all();

		} else {
			request = RestAssured.given();

		}
		if (token!=null)
		{
			//request.header("Authorization", "Bearer " + token);
		request.headers(token);

		}
		if(contenttype!=null)
		{
		if (contenttype.equalsIgnoreCase("JSON"))

		{
			request.contentType(ContentType.JSON);

		}
		if (contenttype.equalsIgnoreCase("XML"))

		{
			request.contentType(ContentType.XML);

		}
		if (contenttype.equalsIgnoreCase("text"))

		{
			request.contentType(ContentType.TEXT);

		}
		if (contenttype.equalsIgnoreCase("multipart"))

		{
			request.multiPart("image", new File("../com.DEC2021APIFW/src/main/java/com/qa/gorest/testdata/images/upload1.jpg"));

		}
		if(!(params==null))
		{
			request.queryParams(params);
		}
		
		}
		return request;
	}
	
	private static Response getResponse(RequestSpecification request, String httpmethod,String basepath)
	{
		return executeAPI(request, httpmethod, basepath);
		
	}
	private static Response executeAPI(RequestSpecification request, String httpmethod,String basepath)
	{
		Response response=null;
		switch (httpmethod) {
		case "GET":
			response=request.get(basepath);
			break;
		case "POST":
			response=request.post(basepath);
			break;
		case "PUT":
			response=request.put(basepath);
			break;
		case "PATCH":
			response=request.patch(basepath);
			break;
		case "DELETE":
			response=request.delete(basepath);
			break;
		default:
			System.out.println("please enter valid httpmethod.The value entered is incorrect  ---->  " + httpmethod);
			break;
		}
		return response;
	}

}