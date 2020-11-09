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

import br.com.desafio.business.SetorBusiness;
import br.com.desafio.models.Setor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "Setores", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping(value = "setores")
public class SetorController {

	@Autowired
	private SetorBusiness setorBusiness;

	@ApiOperation(value = "Recuperar lista de todos os clientes.")
	@GetMapping
	public ResponseEntity<List<Setor>> listarTodos() {
		List<Setor> lista = setorBusiness.listarTodos();
		return ResponseEntity.ok(lista);
	}

	@ApiOperation(value = "Cadastrar setor.")
	@PostMapping
	public ResponseEntity<Setor> salvar(@ApiParam(value = "Setor") @RequestBody Setor setor) {
		setor = setorBusiness.salvarOuAtualizar(setor);
		return ResponseEntity.ok(setor);
	}

	@ApiOperation(value = "Alterar cadastro de setor.")
	@PutMapping
	public ResponseEntity<Setor> atualizar(@ApiParam(value = "Setor") @RequestBody Setor setor) {
		setor = setorBusiness.salvarOuAtualizar(setor);
		return ResponseEntity.ok(setor);
	}

	@ApiOperation(value = "Deletar cadastro de setor.")
	@DeleteMapping("/{id}")
	public ResponseEntity<Long> deletar(@ApiParam(value = "id", example = "1", type = "number", required = true) @PathVariable Long id) {
		setorBusiness.deletar(id);
		return ResponseEntity.ok(id);
	}

}
