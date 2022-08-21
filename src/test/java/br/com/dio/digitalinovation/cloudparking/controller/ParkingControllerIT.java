package br.com.dio.digitalinovation.cloudparking.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import br.com.dio.digitalinovation.cloudparking.controller.dto.ParkingCreateDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ParkingControllerIT {
	
	@LocalServerPort
	private int randomPort;
	
	@BeforeEach
	public void setUpTest() {
		System.out.println("PORTA:"+randomPort);
	RestAssured.port = randomPort;	
		
	}
	
	@Test
	void whenFindAllThnCheckResult() {
		RestAssured.given()
//		.header("Authorization", "Basic dXNlcjpkaW9AMTIzNDU2")
		.auth().basic("user", "dio@123456")
		.when()
		.get("/parking")
		.then().statusCode(200)
		.body("license[0]",Matchers.equalTo("MDS-111"));
		
	}
	
	@Test
	void whenCreateThenChecInsCreated() {
		
		var createDTO = new ParkingCreateDTO();
		createDTO.setColor("Amarelo");
		createDTO.setLicense("JUN-1150");
		createDTO.setModel("SIENA");
		createDTO.setState("MG");
			
		
		RestAssured.given()//
		.auth().basic("user", "dio@123456")
		.when()
		 //.header("Authorization", "Basic dXNlcjpkaW9AMTIzNDU2")
		.contentType(ContentType.JSON)
		.body(createDTO)
		.post("/parking")
		.then().statusCode(201)
		.body("license",Matchers.equalTo("JUN-1150"));
		
		
	  
	}

}
