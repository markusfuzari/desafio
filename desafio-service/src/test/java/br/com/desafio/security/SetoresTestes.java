package br.com.desafio.security;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matcher;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.desafio.business.SetorBusiness;
import br.com.desafio.models.Setor;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SetoresTestes {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	SetorBusiness setorBusiness;
	
	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
	public void rota01GetSetores() throws Exception {
		
		Matcher<String> matcher =
		        allOf(containsString("Baby"),
		        		containsString("Movies"));
		
		mockMvc.perform(
				get("/setores")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(matcher));
	}
		
	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
	public void rota02PostSetores() throws Exception {
		
		Setor setor = Setor.builder().nome("Setor Inserido").build();
		
		mockMvc.perform(
				post("/setores")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(setor)))
				.andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
	public void rota03PutSetores() throws Exception {
		
		Setor setor = setorBusiness.recuperarPorId(1L);
		setor.setNome("Nome Setor Alterado");
		
		Matcher<String> matcher =
        allOf(containsString("Nome Setor Alterado"));
		
		mockMvc.perform(
				post("/setores")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(setor)))
				.andExpect(status().isOk())
				.andExpect(content().string(matcher));
	}
	
	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
	public void rota04DeleteSetores() throws Exception {
		
		mockMvc.perform(
				delete("/setores/{id}",5)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

}