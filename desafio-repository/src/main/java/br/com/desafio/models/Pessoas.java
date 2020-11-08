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

@Data
@Builder
@ApiModel(value = "Pessoas")
@JsonPropertyOrder({"name", "title", "description"})
@AllArgsConstructor
@Entity
@Table(name = "PESSOAS")
public class Pessoas {
	
	
	@Id
	@ApiModelProperty
	@Column(name = "CD_PESSOAS")
	private Long id;
	
	@ApiModelProperty
	@Column(name = "NM_PESSOAS")
	private String nome;
	
	@ApiModelProperty
	@Column(name = "DS_CARREIRA")
	private String carreira;
	
	@ApiModelProperty
	@ManyToOne
	@JoinColumn(name = "CD_SETOR")
	private Setor setor;
	
	public Pessoas() {
		super();
	}
}
