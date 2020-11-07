package br.com.desafio.generic;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface GenericRepository<T,ID>  extends PagingAndSortingRepository<T, ID>  {

}
