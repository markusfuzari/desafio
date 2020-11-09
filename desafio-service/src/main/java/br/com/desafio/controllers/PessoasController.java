package br.com.desafio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
import br.com.desafio.models.Pessoa;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "Pessoas", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping(value = "pessoas", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class PessoasController {

	@Autowired
	private PessoasBusiness pessoasBusiness;

	@ApiOperation(value = "Recuperar lista de todos os clientes.")
	@GetMapping
	public ResponseEntity<List<Pessoa>> listarTodos() {
		List<Pessoa> lista = pessoasBusiness.listarTodos();
		return ResponseEntity.ok(lista);
	}

	@ApiOperation(value = "Recuperar lista de todos os clientes por id do setor.")
	@GetMapping("setor/id/{id}")
	public ResponseEntity<List<Pessoa>> listarTodosPorSetorId(
	@ApiParam(value = "id", example = "1", type = "number", required = true)
	@PathVariable(required = true) Long id) {
		List<Pessoa> lista = pessoasBusiness.listarTodosPorSetorId(id);
		return ResponseEntity.ok(lista);
	}

	@ApiOperation(value = "Recuperar lista de todos os clientes por nome do setor.")
	@GetMapping("setor/nome/{nome}")
	public ResponseEntity<List<Pessoa>> listarTodosPorSetorId(
	@ApiParam(value = "nome", example = "Markus Vinicius", type = "string", required = true)
	@PathVariable(required = true) String nome) {
		List<Pessoa> lista = pessoasBusiness.listarTodosPorSetorNome(nome);
		return ResponseEntity.ok(lista);
	}

	@ApiOperation(value = "Cadastrar pessoa.")
	@PostMapping
	public ResponseEntity<Pessoa> salvar(@ApiParam(value = "Pessoa") @RequestBody Pessoa pessoa) {
		pessoa = pessoasBusiness.salvarOuAtualizar(pessoa);
		return ResponseEntity.ok(pessoa);
	}

	@ApiOperation(value = "Alterar cadastro de pessoa.")
	@PutMapping
	public ResponseEntity<Pessoa> atualizar(@ApiParam(value = "Pessoa") @RequestBody Pessoa pessoa) {
		pessoa = pessoasBusiness.salvarOuAtualizar(pessoa);
		return ResponseEntity.ok(pessoa);
	}

	@ApiOperation(value = "Deletar cadastro de pessoa.")
	@DeleteMapping("/{id}")
	public ResponseEntity<Long> deletar(@ApiParam(value = "id", example = "1", type = "number", required = true) @PathVariable Long id) {
		pessoasBusiness.deletar(id);
		return ResponseEntity.ok(id);
	}

}
