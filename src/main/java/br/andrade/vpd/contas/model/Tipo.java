package br.andrade.vpd.contas.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

@Entity
public class Tipo implements Serializable {

	private static final long serialVersionUID = -4416156188375026706L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipo_id_seq")
	@SequenceGenerator(name = "tipo_id_seq", sequenceName = "tipo_id_seq", initialValue = 1, allocationSize = 1)
	private Long id;

	@Version
	private Long version;

	@Column
	private String descricao;

	public Tipo() {
	}

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

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

}
