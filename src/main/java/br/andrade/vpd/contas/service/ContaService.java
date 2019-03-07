package br.andrade.vpd.contas.service;

import java.util.List;

import org.springframework.data.domain.Page;

import br.andrade.vpd.contas.exception.RegistroNaoEncontradoException;
import br.andrade.vpd.contas.model.Conta;
import br.andrade.vpd.contas.sistema.ParametroData;


public interface ContaService {
	
	Conta find(Long id) throws RegistroNaoEncontradoException;

	void save(Conta conta);

	void update(Conta conta);

	void remove(Conta conta);

	List<Conta> listAll();
	
	Page<Conta> list(ParametroData entrada);

}
