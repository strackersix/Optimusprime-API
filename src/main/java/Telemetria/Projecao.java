package Telemetria;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import core.BaseTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Projecao extends BaseTest {
		
	@Test
	public void T_001_NaoDeveAutenticarSemSenha () {
		
		given()
			.log().all()
		.when()
			.get("https://projecoes-dev.zordon.app/list_units")
		.then()
			.log().all()
			.statusCode(401)
			.body("html.body", is("Unauthorized Access"))
			
		;
				
	}
	
	@Test
	public void T_002_ListUnits () {
				
		given()
			.log().all()
		.when()
			.get("/list_units")
		.then()
			.log().all()
			.statusCode(200)
			.body("status", is("ok"))
			.body("units.000084", hasItems("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "64", "65", "66", "67", "68", "63", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79"))
			.body("units.000086", hasItems("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14"))
			.body("units.000385", hasItems("1"))
			.body("units.000683", hasItems("2", "3", "4"))
			.body("units.000756", hasItems("2"))
			.body("units.000889", hasItems("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "19", "20", "21", "26", "29", "30", "31", "33", "34", "35", "36", "37", "38", "39", "41", "42", "43", "46", "47", "48", "50", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72" ,"73", "76", "77", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87", "74", "75"))
			.body("units.001092", hasItems("1", "2"))
			.body("units.001147", hasItems("1"))
			.body("units.001201", hasItems("1"))
			.body("units.001345", hasItems("1"))
			.body("units.001466", hasItems("1"))
			.body("units.001562", hasItems("1"))
			.body("units.001589", hasItems("1", "2"))
			.body("units.001608", hasItems("5", "1", "2", "3", "4"))
			.body("units.001961", hasItems("5", "1", "2", "3", "4", "5", "6"))
			.body("units.002390", hasItems("1"))
			.body("units.002418", hasItems("1"))
			.body("units.002434", hasItems("1"))
			.body("units.010286", hasItems("1"))
			.body("units.013603", hasItems("1"))
			.body("units.014459", hasItems("1"))
			.body("units.037047", hasItems("1"))
			
		;
				 
	}
	 
	@Test
	public void T_003_Insert_Points () {
		
		given()
			.log().all()
			.contentType(Content_TYPE)
			.body("data=[{\"code\":\"000889\",\"store\":\"1\",\"Data\":\"20190101\",\"Hora\":\"0015\",\"TipoMedicao\":\"Q\",\"Posto\":\"Q\",\"EnergiaAtiva\":123.21,\"EnergiaReativa\":321.21},"
				+ " {\"code\" : \"000889\",	\"store\" : \"1\", \"Data\": \"20190701\", \"Hora\": \"2345\", \"TipoMedicao\": \"T\", \"Posto\": \"F\", \"EnergiaAtiva\": 679, \"EnergiaReativa\": 126}]")
		.when()
			.post("/insert_points")
		.then()
			.log().all()
			.statusCode(200)
			.body("status", is("ok"))
		
		;
		
	}
	
	@Test
	public void T_004_Medidas () {

		given()
			.log().all()
			.contentType(Content_TYPE)
			.body("inicio=2020-05-01&fim=2020-05-30&unidades={\"000889\":[\"1\"]}")
		.when()
			.post("/medidas/")
		.then()
			.log().all()
			.statusCode(200)
			.body("metadata.included_count", is(1))
			.body("metadata.included_units.000889", contains("1"))
			.body("metadata.missing_count", is(0))
			.body("metadata.total_count", is(1))
			.body("projetado.legenda", hasItem("MWm"))
			.body("projetado_acumulado.legenda", hasItem("MWm"))
			.body("realizado.legenda", hasItem("MWm"))
			.body("realizado_acumulado.legenda", hasItem("MWm"))
			
		;
		
	}
	
	@Test
	public void T_005_Medidas_CSV () {
		
		String csv =
		
		given()
			.log().all()
			.body("inicio=2020-05-01&fim=2020-05-30&unidades={ \"000889\" : [\"1\"]}")
		.when()
			.post("/medidas_csv/")
		.then()
			.log().all()
			.statusCode(200)
			.extract().toString();
		
		Assert.assertFalse(csv.isEmpty());
			
	}
			
}

