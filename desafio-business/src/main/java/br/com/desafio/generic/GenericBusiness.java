package br.com.desafio.generic;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class GenericBusiness<E, ID extends Serializable> {

	private GenericRepository<E, ID> repository;
	
	@SuppressWarnings("unused")
	private GenericBusiness(){}
	
	public GenericBusiness(GenericRepository<E,ID> repository) {
		this.repository = repository;
	}
	
	public E salvarOuAtualizar(E entity) {
		return repository.save(entity);
	}

	public void deletar(ID id) {
		repository.deleteById(id);
	}
	
	public E recuperarPorId(ID id){
		Optional<E> optional = repository.findById(id);
		return optional.isPresent()?optional.get():null;
	}
	
	public List<E> listarTodos(){
		return (List<E>) repository.findAll();
	}

	public Page<E> listarPaginado(Pageable pageable){
		return repository.findAll(pageable);
	}
}
