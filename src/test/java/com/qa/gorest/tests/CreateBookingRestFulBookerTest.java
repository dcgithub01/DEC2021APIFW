package com.qa.gorest.tests;

import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.gorest.pojo.BookingDates;
import com.qa.gorest.pojo.BookingFinalDetails;
import com.qa.gorest.pojo.TravellerDetails;
import com.qa.gorest.restclient.RestClient;
import com.qa.gorest.utils.ObjectToJsonConverter;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;



public class CreateBookingRestFulBookerTest {

	private String baseURI="https://restful-booker.herokuapp.com";
	private String basePath="/booking";
	//private Map<String, String> hookerToken;
	
//	@BeforeMethod
//	public void setupBooking()
//	{
//	hookerToken=Token.getHeroBookerToken();
//	System.out.println(hookerToken);
//	}
	
	
	@Test
	public void createBooking()
	{
	BookingDates bookingdates= new BookingDates("2021-12-11", "2021-12-12");
	TravellerDetails td= new TravellerDetails("Rohans", "Gavaskars", 100, true, "brkfst", bookingdates);
	//BookingFinalDetails finald = new BookingFinalDetails(td);
	
	//String jsoncreated=ObjectToJsonConverter.convertToJson(td);
	
	//System.out.println(jsoncreated);
	
//	Response res=RestAssured.given().contentType(ContentType.JSON).body(jsoncreated).when().post("https://restful-booker.herokuapp.com/booking");
//	System.out.println(res.prettyPrint());
//	System.out.println(res.getStatusCode());
	System.out.println("**********************************************");
	Response response=RestClient.doPostRequest("JSON", baseURI, basePath, "POST", null, null, true, td);
System.out.println(response.statusCode());
System.out.println(response.getStatusLine());
System.out.println(response.prettyPrint());
}
}
 