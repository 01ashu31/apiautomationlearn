package com.learnapi.automation;


import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class RestAssuredAPITest {
	
	@Test (description="testing get api")
	public void GetBookDetails() {
		
		RestAssured.baseURI="https://demoqa.com/BookStore/v1/Books";
		RequestSpecification httpRequest= RestAssured.given();
//		Response response = httpRequest.request(Method.GET,"");
		Response response = httpRequest.get();
		int statuscode= response.getStatusCode();
		Assert.assertEquals(statuscode,  200);
//		System.out.println("status received =>" + response.getStatusLine());
//		System.out.println("Response = > " + response.asString());
		
	}
	
	@Test (description="To validate response status to find error status code")
	public void GetPetDetails() {
		RestAssured.baseURI="https://demoqa.com/Account/v1/User/";
		RequestSpecification httRequest= RestAssured.given();
		Response response= httRequest.get("test");
		int statusCode= response.getStatusCode();
		Assert.assertEquals(statusCode, 401);
	}
	
	@Test (description ="to validate response status with the sttausline")
	public void GetBookDetails2() {
		
		RestAssured.baseURI="https://demoqa.com/BookStore/v1/Books";
		RequestSpecification httpRequest= RestAssured.given();
		Response response = httpRequest.get();
		String statusLine= response.getStatusLine();
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		
	}
	
	@Test (description ="to validate headers ")
	public void IteratingHeaders() {
		
		RestAssured.baseURI="https://demoqa.com/BookStore/v1/Books";
		RequestSpecification httpRequest= RestAssured.given();
		Response response = httpRequest.get();
		Headers allHeaders= response.headers();
		for (Header header : allHeaders ) {
			System.out.println("key: " + header.getName() + "Value:" + header.getValue());
		}
	}
	
	@Test (description="to validate the body")
	public void WeatherMessageBody() {
		RestAssured.baseURI="https://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpsRequest= RestAssured.given();
		Response response=	httpsRequest.get("/Hyderabad");
		ResponseBody body= response.getBody();
		System.out.println("Response body is:" + body.asString());
	}
	
	@Test
	public void queryParameter() {
		RestAssured.baseURI="https://bookstore.toolsqa.com/BookStore/v1";
		RequestSpecification httpRequest= RestAssured.given();
		Response response= httpRequest.queryParam("ISBN", "9781449325862").get("/Book");
		ResponseBody body= response.body();
		String rbdy= body.asString();
		JsonPath jpath= new JsonPath(rbdy);
		String title= jpath.getString("title");
		System.out.println("The book title is" + title);
	}
	
	

}
