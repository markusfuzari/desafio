package br.com.desafio.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafio.generic.GenericBusiness;
import br.com.desafio.models.Pessoas;
import br.com.desafio.repositories.PessoasRepository;

@Service
public class PessoasBusiness extends GenericBusiness<Pessoas, Long> {

	@Autowired
	private PessoasRepository repository;
	
	public PessoasBusiness(PessoasRepository repository) {
		super(repository);
		this.repository = repository;
	}

	public List<Pessoas> listarTodosPorSetorId(Long id){
		return repository.findBySetorId(id);
	}
	
	public List<Pessoas> listarTodosPorSetorNome(String nome){
		return repository.findBySetorNome(nome);
	}
	
}
