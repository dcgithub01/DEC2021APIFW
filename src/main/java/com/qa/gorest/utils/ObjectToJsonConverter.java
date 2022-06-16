package com.qa.gorest.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectToJsonConverter {
	
	
	public static String convertToJson(Object obj)
	{
		String jsonbody=null;
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			jsonbody=mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonbody;
	}

}
