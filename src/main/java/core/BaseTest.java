package core;

import org.hamcrest.Matchers;
import org.junit.BeforeClass;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;

public class BaseTest implements Constantes {

	@BeforeClass
	public static void Setup () {
		
		RestAssured.baseURI = Url;
		RestAssured.port = Port_HTTPS;
		
		RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
		reqBuilder.setContentType(Content_TYPE);
		RestAssured.requestSpecification = reqBuilder.build();
				
		ResponseSpecBuilder resBuilder = new ResponseSpecBuilder();
		resBuilder.expectResponseTime(Matchers.lessThan(Max_Timout));
		RestAssured.responseSpecification = resBuilder.build();
						
		
	}
	
	
}
