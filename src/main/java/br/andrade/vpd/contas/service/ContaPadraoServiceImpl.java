package br.andrade.vpd.contas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.andrade.vpd.contas.exception.RegistroNaoEncontradoException;
import br.andrade.vpd.contas.model.ContaPadrao;
import br.andrade.vpd.contas.repository.ContaPadraoRepository;
import br.andrade.vpd.contas.sistema.ParametroData;

@Service
public class ContaPadraoServiceImpl implements ContaPadraoService {

	private static final String KEY = "descricao";
	private static final String MSG_ERRO_BUSCA = "Não foi possível recuperar a conta padrão";
	
	@Autowired
	private ContaPadraoRepository repositorio;
	
	@Override
	public ContaPadrao find(Long id) throws RegistroNaoEncontradoException {
		Optional<ContaPadrao> op = repositorio.findById(id);
		if(op.isPresent()) {
			return op.get();
		}
		throw new RegistroNaoEncontradoException(MSG_ERRO_BUSCA);
	}

	@Override
	public void save(ContaPadrao contaPadrao) {
		repositorio.save(contaPadrao);
	}

	@Override
	public void update(ContaPadrao contaPadrao) {
		repositorio.save(contaPadrao);
	}

	@Override
	public void remove(ContaPadrao contaPadrao) {
		Optional<ContaPadrao> objDelete = repositorio.findById(contaPadrao.getId());
		repositorio.delete(objDelete.get());
	}
	
	@Override
	public List<ContaPadrao> listAll(){
		return (List<ContaPadrao>) repositorio.findAll();
	}

	@Override
	public Page<ContaPadrao> list(ParametroData entrada) {
		return repositorio.listByPage(entrada.getPageable(), entrada.getFiltro(KEY));
	}

}
