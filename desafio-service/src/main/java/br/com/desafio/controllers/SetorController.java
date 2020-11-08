package br.com.desafio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.business.SetorBusiness;
import br.com.desafio.models.Setor;

@RestController
@RequestMapping(value = "setores")
public class SetorController {

	@Autowired
	private SetorBusiness setorBusiness;

	@GetMapping
	public ResponseEntity<List<Setor>> listarTodos() {
		List<Setor> lista = setorBusiness.listarTodos();
		return ResponseEntity.ok(lista);
	}

	@GetMapping("page")
	public ResponseEntity<Page<Setor>> listarTodosPaginado(Pageable pageable) {
		Page<Setor> page = setorBusiness.listarPaginado(pageable);
		return ResponseEntity.ok(page);
	}

	@PostMapping
	public ResponseEntity<Setor> salvar(@RequestBody Setor setor) {
		setor = setorBusiness.salvarOuAtualizar(setor);
		return ResponseEntity.ok(setor);
	}

	@PutMapping
	public ResponseEntity<Setor> atualizar(@RequestBody Setor setor) {
		setor = setorBusiness.salvarOuAtualizar(setor);
		return ResponseEntity.ok(setor);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Long> deletar(@PathVariable Long id) {
		setorBusiness.deletar(id);
		return ResponseEntity.ok(id);
	}

}
