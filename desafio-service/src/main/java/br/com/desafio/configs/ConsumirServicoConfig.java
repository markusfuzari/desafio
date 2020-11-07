package br.com.desafio.configs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import br.com.desafio.business.PessoasBusiness;
import br.com.desafio.business.SetorBusiness;
import br.com.desafio.models.DesafioRest;
import br.com.desafio.models.Pessoas;
import br.com.desafio.models.Setor;

@Configuration
public class ConsumirServicoConfig {


	@Autowired
	private SetorBusiness setorBusiness;

	@Autowired
	PessoasBusiness pessoasBusiness;

	
	@Bean
	public RestTemplate buildRestTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public Object recuperarDados(RestTemplate restTemplate) throws Exception {

		ResponseEntity<List<DesafioRest>> response = restTemplate.exchange(
				"https://5e61af346f5c7900149bc5b3.mockapi.io/desafio03/employer", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<DesafioRest>>() {
				});

		List<DesafioRest> lista = response.getBody();
		lista.forEach(desafioRest -> {
			
			Setor setor = setorBusiness.recuperarPorNome(desafioRest.getDepartment());
			if(ObjectUtils.isEmpty(setor)) {
				setor = Setor.builder()
				.nome(desafioRest.getDepartment())
				.build();
				setor = setorBusiness.salvarOuAtualizar(setor);
			}
			
			Pessoas pessoas = Pessoas.builder()
					.id(desafioRest.getId())
					.nome(desafioRest.getFirst_name().concat(" "+desafioRest.getLast_name()))
					.carreira(desafioRest.getCareer())
					.setor(setor)
					.build();
			
			pessoasBusiness.salvarOuAtualizar(pessoas);
		});

		return lista;
	}
}
