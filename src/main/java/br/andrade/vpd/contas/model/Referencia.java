package br.andrade.vpd.contas.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Referencia implements Serializable {


	private static final long serialVersionUID = 4293928276386557999L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "referencia_id_seq")
	@SequenceGenerator(name = "referencia_id_seq", sequenceName = "referencia_id_seq")
	private Long id;
	
	@Column
	private String descricao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


}
