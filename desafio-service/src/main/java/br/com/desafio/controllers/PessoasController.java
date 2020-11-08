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

import br.com.desafio.business.PessoasBusiness;
import br.com.desafio.models.Pessoas;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

@Api(value = "Pessoas", authorizations = @Authorization(value="ADMIN", scopes = {}))
@ApiResponses(value = { 
		@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
		@ApiResponse(code = 500, message = "Foi gerada uma exceção"), 
})
@RestController
@RequestMapping(value = "pessoas")
public class PessoasController {

	@Autowired
	private PessoasBusiness pessoasBusiness;

	@ApiOperation(value = "Retorna uma lista de pessoas")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna a lista de pessoas") })
	@GetMapping
	public ResponseEntity<List<Pessoas>> listarTodos() {
		List<Pessoas> lista = pessoasBusiness.listarTodos();
		return ResponseEntity.ok(lista);
	}

	@ApiOperation(value = "Retorna uma lista paginada de pessoas")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna uma lista paginada de pessoas") })
	@GetMapping("page")
	public ResponseEntity<Page<Pessoas>> listarTodosPaginado(Pageable pageable) {
		Page<Pessoas> page = pessoasBusiness.listarPaginado(pageable);
		return ResponseEntity.ok(page);
	}

	@ApiOperation(value = "Retorna uma lista de pessoas pelo 'id' do setor")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna a lista de pessoas pelo 'id' do setor") })
	@GetMapping("setor/id/{id}")
	public ResponseEntity<List<Pessoas>> listarTodosPorSetorId(@ApiParam(name =  "id", 
	value = "Cógido do setor", example = "3"  , required = true) @PathVariable(required = true) Long id) {
		List<Pessoas> lista = pessoasBusiness.listarTodosPorSetorId(id);
		return ResponseEntity.ok(lista);
	}

	@ApiOperation(value = "Retorna uma lista de pessoas pelo 'nome' do setor")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna a lista de pessoas pelo 'id' do setor") })
	@GetMapping("setor/nome/{nome}")
	public ResponseEntity<List<Pessoas>> listarTodosPorSetorId(@ApiParam(name =  "id",
	value = "Nome do setor",example = "Industrial", required = true) @PathVariable(required = true) String nome) {
		List<Pessoas> lista = pessoasBusiness.listarTodosPorSetorNome(nome);
		return ResponseEntity.ok(lista);
	}

	/*
	 * Cadastrar Pessoas
	 */
	@PostMapping
	public ResponseEntity<Pessoas> salvar(@RequestBody Pessoas pessoas) {
		pessoas = pessoasBusiness.salvarOuAtualizar(pessoas);
		return ResponseEntity.ok(pessoas);
	}

	@PutMapping
	public ResponseEntity<Pessoas> atualizar(@RequestBody Pessoas pessoas) {
		pessoas = pessoasBusiness.salvarOuAtualizar(pessoas);
		return ResponseEntity.ok(pessoas);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Long> deletar(@PathVariable Long id) {
		pessoasBusiness.deletar(id);
		return ResponseEntity.ok(id);
	}

}
