package no.hvl.dat152.rest.ws.main.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import io.restassured.RestAssured;
import io.restassured.response.Response;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class TestsAdminUser {
	
	private String API_ROOT = "http://localhost:8090/elibrary/api/v1/admin";
	
	private String token = "eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJiZXJpdEBlbWFpbC5jb20iLCJpc3MiOiJEQVQxNTItTGVjdHVyZXJAVERPWSIsImZpcnN0bmFtZSI6IkJlcml0IiwibGFzdG5hbWUiOiJKw7hyZ2Vuc2VuIiwicm9sZXMiOlsiVVNFUiIsIkFETUlOIiwiU1VQRVJfQURNSU4iXSwiaWF0IjoxNjk3OTY5NzAzLCJleHAiOjE2OTg0MDE3MDN9.FRNQpvyiKttZJJOurJkLfyltpa-DL9SiCE9CrYn4gpHnZrCTZH6W0S3Aem48ne_tZ9TujS2XjKTjFQi4rc-hNCM9qzwcx7E9Lm-MVMOaUD-ztPQ1mIAT9mG2WkDrcHX4bzPemWPm0zCsO8H5QjmGIPsCj7IK7pSKB30E8OWoMqv4GULwNH2ep4umoxOjtru6l0etGSaWzXxtskSCRHhU6iFy_RM8MSq3sSzn7m0RuXKVRFm6_3eTY3lC6Fwcz3Ui9RyhQBIjNciumc8_YepDqbDlbgDnmHIKZ3RUFbOopXJPLsfZK_orlza4-_WUzirMHteu6QMZjfplV0EvGMMjZA";
	
	@DisplayName("JUnit test for @PutMapping(/users/{id}) endpoint")
	@Test
	public void AupdateUserRole_thenOK() {
		
		Response response = RestAssured.given()
				.header("Authorization", "Bearer "+ token)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.queryParam("role", "ADMIN")
				.put(API_ROOT+"/users/{id}", 1);
	    
	    assertEquals(HttpStatus.OK.value(), response.getStatusCode());
	    assertEquals("robert@email.com", response.jsonPath().get("email"));
	}
	
	@DisplayName("JUnit test for @DeleteMapping(/users/{id}) endpoint")
	@Test
	public void BdeleteUserRole_thenOK() {
		
		Response response = RestAssured.given()
				.header("Authorization", "Bearer "+ token)
				.queryParam("role", "USER")
				.delete(API_ROOT+"/users/{id}", 1);
	    
	    assertEquals(HttpStatus.OK.value(), response.getStatusCode());
	    assertFalse(response.jsonPath().getList("roles").get(0).toString().contains("USER"));
	}

}
