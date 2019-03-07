package br.andrade.vpd.contas.service;

import java.util.List;

import org.springframework.data.domain.Page;

import br.andrade.vpd.contas.exception.RegistroNaoEncontradoException;
import br.andrade.vpd.contas.model.Rendimento;
import br.andrade.vpd.contas.sistema.ParametroData;

public interface RendimentoService {

	Rendimento find(Long id) throws RegistroNaoEncontradoException;

	void save(Rendimento rendimento);

	void update(Rendimento rendimento);

	void remove(Rendimento rendimento);

	List<Rendimento> listAll();

	Page<Rendimento> list(ParametroData entrada);
}
