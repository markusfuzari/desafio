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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

@Api(value = "Setores", authorizations = @Authorization(value="USER, ADMIN", scopes = {}))
@ApiResponses(value = { 
		@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
		@ApiResponse(code = 500, message = "Foi gerada uma exceção"), 
})
@RestController
@RequestMapping(value = "setores")
public class SetorController {

	@Autowired
	private SetorBusiness setorBusiness;

	@ApiOperation(value = "Listar todos os setores")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna a lista de setores") })
	@GetMapping
	public ResponseEntity<List<Setor>> listarTodos() {
		List<Setor> lista = setorBusiness.listarTodos();
		return ResponseEntity.ok(lista);
	}

	@ApiOperation(value = "Listar os setores paginado")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna a lista paginada de setores") })
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
