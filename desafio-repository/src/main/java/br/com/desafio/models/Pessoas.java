package br.com.desafio.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "PESSOAS")
@ApiModel(value = "Pessoas")
public class Pessoas {
	
	@ApiModelProperty(value = "Código da pessoa")
	@Id
	@Column(name = "CD_PESSOAS")
	private Long id;
	
	@ApiModelProperty(value = "Nome da pessoa")
	@Column(name = "NM_PESSOAS")
	private String nome;
	
	@ApiModelProperty(value = "Carreira da pessoa")
	@Column(name = "DS_CARREIRA")
	private String carreira;
	
	@ApiModelProperty(value = "Setor da pessoa")
	@ManyToOne
	@JoinColumn(name = "CD_SETOR")
	private Setor setor;
	
	public Pessoas() {
		super();
	}
}
