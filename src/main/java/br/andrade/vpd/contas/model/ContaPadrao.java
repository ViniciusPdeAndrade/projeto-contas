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
public class ContaPadrao implements Serializable {

	private static final long serialVersionUID = 3281986368634833731L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "conta_padrao_id_seq")
	@SequenceGenerator(name = "conta_padrao_id_seq", sequenceName = "conta_padrao_id_seq", initialValue = 1, allocationSize = 1)
	private Long id;

	@Column
	private String descricao;

	@Column
	private Conta Conta;
	
	@Version
	private Long version;

	public ContaPadrao() {

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

	public Conta getConta() {
		return Conta;
	}

	public void setConta(Conta conta) {
		Conta = conta;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

}
