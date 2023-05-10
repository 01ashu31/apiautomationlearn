package com.learnapi.automation;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class RestAssuredPostMethod {
	
	@Test (description="test")
	public void bookStorePostMethod() {
		RestAssured.baseURI="https://demoqa.com/BookStore/v1/Books";
		RequestSpecification httpsRequest= RestAssured.given();
		JSONObject requestparam= new JSONObject();
    	requestparam.put("UserName", "test_rest");
		requestparam.put("Password","Testrest@123");
		httpsRequest.body(requestparam.toJSONString());
		Response response= httpsRequest.put("/User");
		ResponseBody body= response.getBody();
		System.out.println(response.getStatusLine());
		System.out.println(body.asString());
		
	}
	
	@Test(description="Serialization process")
	public void UserSerelization() {
		RestAssured.baseURI="https://demoqa.com";
		RequestSpecification request= new RestAssured().given();
		JSONObject requestparam= new JSONObject();
		requestparam.put("UserName", "test_rest");
		requestparam.put("password", "rest@123");
		request.body(requestparam.toJSONString());
		Response response= request.post("/Account/v1/User");
		ResponseBody body= response.getBody();
		JSONSuccessResponse responseBody=body.as(JSONSuccessResponse.class);
	}

}
