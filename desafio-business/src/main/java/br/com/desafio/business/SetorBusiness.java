package br.com.desafio.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafio.generic.GenericBusiness;
import br.com.desafio.models.Setor;
import br.com.desafio.repositories.SetorRepository;

@Service
public class SetorBusiness extends GenericBusiness<Setor, Long> {

	@Autowired
	private SetorRepository repository;

	public SetorBusiness(SetorRepository repository) {
		super(repository);
		this.repository = repository;
	}
	
	public Setor recuperarPorNome(String nome) {
		return this.repository.findByNome(nome);
	}

}
