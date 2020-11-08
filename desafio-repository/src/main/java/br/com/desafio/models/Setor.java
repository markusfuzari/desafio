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
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@ApiModel(value = "Setor")
@JsonPropertyOrder({"name", "title", "description"})
@Entity
@Table(name = "SETOR")
public class Setor {

	
	@Id
	@ApiModelProperty
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CD_SETOR")
	private Long id;

	@ApiModelProperty
	@Column(name = "NM_SETOR")
	private String nome;

	@JsonIgnore
	@ApiModelProperty
	@OneToMany(mappedBy = "setor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Pessoas> pessoas;

	public Setor() {
		super();
	}
}
