package br.com.desafio.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.desafio.generic.GenericRepository;
import br.com.desafio.models.Pessoa;

@Repository
public interface PessoasRepository extends GenericRepository<Pessoa, Long> {

	public List<Pessoa> findBySetorId(Long id);
	
	public List<Pessoa> findBySetorNome(String nome);

}
