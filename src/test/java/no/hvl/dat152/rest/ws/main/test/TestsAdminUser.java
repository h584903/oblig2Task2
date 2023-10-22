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
	
	private String token = "eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJiZXJpdEBlbWFpbC5jb20iLCJpc3MiOiJEQVQxNTItTGVjdHVyZXJAVERPWSIsImZpcnN0bmFtZSI6IkJlcml0IiwibGFzdG5hbWUiOiJKw7hyZ2Vuc2VuIiwicm9sZXMiOlsiVVNFUiIsIlNVUEVSX0FETUlOIiwiQURNSU4iXSwiaWF0IjoxNjk3NzQxNTEyLCJleHAiOjE2OTgxNzM1MTJ9.gBWbP_2_KQPyNuyjFthMgNub9iu72rtm6kWN3jRZbDl6WiIn3YJgl6COicKl_N8TMzQx_J2v62RxMQ_GY8ZQlWXx41Rl9l1hd1iC40VrIxdQaBFyEsmA_05qn8AXkNh001Em4c1m3F1X7Eky5149GohaSPhz_RlxFtD6X9uYaabetRwmhPJYz0qEyjjfD5yGg_RdQPOmvcX7DMEPfGxnDQMYdWbahUo6zhcVPF6hH-ovUO2EIbJWBVY17_KlbcaK8rS1kJwUlC-76rRTb6i89lazeCFhiQt-X1tNeY_P7Avrjz0FekLZQBfBh3bxuD4Cek1aUohrMtShB8W-QaxBfQ";
	
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
