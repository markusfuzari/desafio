package br.com.desafio.security;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.desafio.business.SetorBusiness;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
public class SetoresTestes {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private SetorBusiness setorBusiness;
	
	@Test
	void rota1() throws Exception {
		
		
		mockMvc.perform(
				get("/pessoa").contentType("application/json"))
				.andExpect(status().isOk());
	}

}