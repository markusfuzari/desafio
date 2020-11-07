package br.com.desafio.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "SETOR")
@ApiModel(value = "Setor")
public class Setor {

	
	@Id
	@ApiModelProperty(value = "Código do setor")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CD_SETOR")
	private Long id;

	@ApiModelProperty(value = "Nome do setor")
	@Column(name = "NM_SETOR")
	private String nome;

	@JsonIgnore
	@ApiModelProperty(value = "Lista de pessoa pertencentes ao setor")
	@OneToMany(mappedBy = "setor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Pessoas> pessoas;

	public Setor() {
		super();
	}
}
