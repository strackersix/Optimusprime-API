package core;

import io.restassured.http.ContentType;

public interface Constantes {

	String Url = "https://powerview:comerc123@projecoes-dev.zordon.app";
	Integer Port_HTTP = 80; 
	Integer Port_HTTPS = 443; 
	String Base_PATH = "";

	ContentType Content_TYPE = ContentType.URLENC;
	Long Max_Timout = 20000L;
	
	
}
