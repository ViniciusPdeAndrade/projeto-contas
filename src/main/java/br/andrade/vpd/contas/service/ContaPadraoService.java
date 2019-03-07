package br.andrade.vpd.contas.service;

import java.util.List;

import org.springframework.data.domain.Page;

import br.andrade.vpd.contas.exception.RegistroNaoEncontradoException;
import br.andrade.vpd.contas.model.ContaPadrao;
import br.andrade.vpd.contas.sistema.ParametroData;

public interface ContaPadraoService {

	ContaPadrao find(Long id) throws RegistroNaoEncontradoException;

	void save(ContaPadrao contaPadrao);

	void update(ContaPadrao contaPadrao);

	void remove(ContaPadrao contaPadrao);

	List<ContaPadrao> listAll();
	
	Page<ContaPadrao> list(ParametroData entrada);
}
