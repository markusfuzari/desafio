package br.com.desafio.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.desafio.generic.GenericRepository;
import br.com.desafio.models.Pessoas;

@Repository
public interface PessoasRepository extends GenericRepository<Pessoas, Long> {

	public List<Pessoas> findBySetorId(Long id);
	
	public List<Pessoas> findBySetorNome(String nome);

}
