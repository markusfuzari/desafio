package br.com.desafio.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@ApiModel(value = "Pessoa", description = "Modelo Pessoa")
@Data
@Builder
@JsonPropertyOrder({"name", "title", "description"})
@AllArgsConstructor
@Entity
@Table(name = "PESSOAS")
public class Pessoa {
	
	
	@Id
	@ApiModelProperty(notes = "Id da pessoa", dataType = "number", name="id", value = "12")
	@Column(name = "CD_PESSOAS")
	private Long id;
	
	@ApiModelProperty(notes = "Nome da pessoa", dataType = "string", name="nome", value = "Jefferey Toy")
	@Column(name = "NM_PESSOAS")
	private String nome;
	
	@ApiModelProperty(notes = "Carreira da pessoa", dataType = "string", name="carreira", value = "Human Quality Supervisor")
	@Column(name = "DS_CARREIRA")
	private String carreira;
	
	@ManyToOne
	@JoinColumn(name = "CD_SETOR")
	private Setor setor;
	
	public Pessoa() {
		super();
	}
}
