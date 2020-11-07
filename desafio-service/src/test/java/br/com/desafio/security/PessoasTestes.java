package br.com.desafio.security;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PessoasTestes {
	private static final ObjectMapper om = new ObjectMapper();
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void testCustomerList() throws Exception {
		
		ResponseEntity<List> response = restTemplate
				.withBasicAuth("admin", "admin")
				.getForEntity("/pessoas", List.class);

		printJSON(response);

		assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		

	}
	private static void printJSON(Object object) {
		String result;
		try {
			result = om.writerWithDefaultPrettyPrinter().writeValueAsString(object);
			System.out.println(result);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
}