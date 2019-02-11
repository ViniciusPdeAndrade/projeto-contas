package br.andrade.vpd.contas.service;

import java.util.List;

import org.springframework.data.domain.Page;

import br.andrade.vpd.contas.exception.RegistroNaoEncontradoException;
import br.andrade.vpd.contas.model.Referencia;
import br.andrade.vpd.contas.sistema.ParametroData;

public interface ReferenciaService {
	
	Referencia find(Long id) throws RegistroNaoEncontradoException;

	void save(Referencia referencia);

	void update(Referencia referencia);

	void remove(Referencia referencia);

	List<Referencia> listAll();
	
	Page<Referencia> list(ParametroData entrada);
	

}
