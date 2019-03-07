package br.andrade.vpd.contas.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

@Entity
public class Rendimento implements Serializable {

	private static final long serialVersionUID = 5351340674130404058L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rendimento_id_seq")
	@SequenceGenerator(name = "rendimento_id_seq", sequenceName = "rendimento_id_seq", initialValue = 1, allocationSize = 1)
	private Long id;

	@Column
	private String descricao;

	@Column
	private BigDecimal valor;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_refencia", nullable = false)
	private Referencia referencia;

	@Version
	private Long version;

	public Rendimento() {

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

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Referencia getReferencia() {
		return referencia;
	}

	public void setReferencia(Referencia referencia) {
		this.referencia = referencia;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

}
