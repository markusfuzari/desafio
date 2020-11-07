package br.com.desafio.repositories;

import org.springframework.stereotype.Repository;

import br.com.desafio.generic.GenericRepository;
import br.com.desafio.models.Setor;

@Repository
public interface SetorRepository extends GenericRepository<Setor, Long> {
	public Setor findByNome(String nome);
}
