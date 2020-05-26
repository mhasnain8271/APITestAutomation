
package com.qa.apiTest;

import org.testng.Assert;
import org.testng.annotations.*;


import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class TC001_Get_All_Employees{
	public RequestSpecification httpRequest;
	public Response response; 
	TC001_Get_All_Employees(){
		super();
	}
		
	
	@BeforeClass
	void getAllEmployees() throws InterruptedException
	{

		
	RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
	httpRequest = RestAssured.given();
	response = httpRequest.request(Method.GET,"/employees");
	
	Thread.sleep(500);
	}
			
	@Test(priority=1)
	void checkResposeBody()
	{
	
		String responseBody = response.getBody().asString();
	
		Assert.assertTrue(responseBody!=null);
		
	}
		
	@Test(priority=2)
	void checkStatusCode()
	{

		int statusCode = response.getStatusCode(); // Gettng status code
		
		Assert.assertEquals(statusCode, 200);
		
	}
		
	@Test(priority=3)
	void checkResponseTime()
	{
		
		long responseTime = response.getTime(); // Getting status Line
	
		
		if(responseTime>2000)
		
		Assert.assertTrue(responseTime<10000);
		
			
	}
	
	@Test(priority=4)
	void checkstatusLine()
	{
		
		String statusLine = response.getStatusLine(); // Getting status Line
	
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	
		
	}
	
	
	@Test(priority=5)
	void checkContentType()
	{
		
		String contentType = response.header("Content-Type");
	
		Assert.assertEquals(contentType, "application/json;charset=utf-8");
	}

	@Test(priority=6)
	void checkserverType()
	{
	
		
		String serverType = response.header("Server");
	 
		Assert.assertEquals(serverType, "nginx/1.16.0");
	
	}

	@Test(priority=7)
	void checkcontentEncoding()
	{
	
		
		String contentEncoding = response.header("Content-Encoding");
	 
		Assert.assertEquals(contentEncoding, "gzip");
		
		
	}

		
		
	@AfterClass
	void tearDown()
	{
		System.out.println("End of Test");
	}
				 	
}
