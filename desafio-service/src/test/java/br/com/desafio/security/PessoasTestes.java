package br.com.desafio.security;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
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

import br.com.desafio.business.PessoasBusiness;
import br.com.desafio.models.Pessoas;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PessoasTestes {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	PessoasBusiness pessoasBusiness;
	
	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
	public void rota01GetPessoas() throws Exception {
		
		Matcher<String> matcher =
		        allOf(containsString("National Assurance Architect"),
		        		containsString("Chief Branding Executive"));
		
		mockMvc.perform(
				get("/pessoas")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(matcher));
	}
	
	@Test
	@WithMockUser(username = "desafio", password = "desafio", roles = "USER")
	public void rota02GetPessoasSemPermissao() throws Exception {
		
		mockMvc.perform(
				get("/pessoas")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isForbidden());
	}
	
	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
	public void rota03GetPessoasPorNomeSetor() throws Exception {
		
		Matcher<String> matcher =
		        allOf(containsString("National Assurance Architect"),
		        		not(containsString("Chief Branding Executive")));
		
		mockMvc.perform(
				get("/pessoas/setor/nome/{nome}","Games")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(matcher));
	}
	
	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
	public void rota04PostPessoas() throws Exception {
		
		Pessoas pessoas = Pessoas.builder().id(350L).build();
		
		mockMvc.perform(
				post("/pessoas")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(pessoas)))
				.andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
	public void rota05PutPessoas() throws Exception {
		
		Pessoas pessoas = pessoasBusiness.recuperarPorId(71L);
		pessoas.setNome("Nome Alterado");
		
		Matcher<String> matcher =
        allOf(containsString("Nome Alterado"),
        		not(containsString("Frankie Spencer")));
		
		mockMvc.perform(
				post("/pessoas")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(pessoas)))
				.andExpect(status().isOk())
				.andExpect(content().string(matcher));
	}
	
	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
	public void rota06DeletePessoas() throws Exception {
		
		mockMvc.perform(
				delete("/pessoas/{id}",5)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

}