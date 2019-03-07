package br.andrade.vpd.contas.service;

import java.util.List;

import org.springframework.data.domain.Page;

import br.andrade.vpd.contas.exception.RegistroNaoEncontradoException;
import br.andrade.vpd.contas.model.Tipo;
import br.andrade.vpd.contas.sistema.ParametroData;


public interface TipoService {
	
	Tipo find(Long id) throws RegistroNaoEncontradoException;

	void save(Tipo tipo);

	void update(Tipo tipo);

	void remove(Tipo tipo);

	List<Tipo> listAll();
	
	Page<Tipo> list(ParametroData entrada);

	
}
