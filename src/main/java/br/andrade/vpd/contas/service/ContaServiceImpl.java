package br.andrade.vpd.contas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.andrade.vpd.contas.exception.RegistroNaoEncontradoException;
import br.andrade.vpd.contas.model.Conta;
import br.andrade.vpd.contas.model.Referencia;
import br.andrade.vpd.contas.model.Tipo;
import br.andrade.vpd.contas.repository.ContaRepository;
import br.andrade.vpd.contas.repository.ReferenciaRepository;
import br.andrade.vpd.contas.repository.TipoRepository;
import br.andrade.vpd.contas.sistema.ParametroData;

@Service
public class ContaServiceImpl implements ContaService {

	private static final String MSG_ERRO_BUSCA = "Não foi possivel recuperar a Conta";

	private static final String KEY = "descricao";
	
	@Autowired
	private TipoRepository tipoRepository;

	@Autowired
	private ReferenciaRepository referenciaRepository;
	
	@Autowired
	private ContaRepository repositorio;

	@Override
	public Conta find(Long id) throws RegistroNaoEncontradoException {
		Optional<Conta> op = repositorio.findById(id);
		if (op.isPresent()) {
			return op.get();
		}
		throw new RegistroNaoEncontradoException(MSG_ERRO_BUSCA);

	}

	@Override
	public void save(Conta conta) {
		prepareConta(conta);
	}

	@Override
	public void update(Conta conta) {
		prepareConta(conta);
	}
	
	private void prepareConta(Conta conta) {
		Referencia referenciaLoad = referenciaRepository.findById(conta.getReferencia().getId()).get();
		conta.setReferencia(referenciaLoad);
		
		Tipo tipoLoad = tipoRepository.findById(conta.getTipo().getId()).get();
		conta.setTipo(tipoLoad);
		conta = repositorio.save(conta);
		
		if(Boolean.TRUE.equals(conta.getPadrao())) {
			repositorio.savePadrao(conta.getId());
			}
		
	}

	@Override
	public void remove(Conta conta) {
		Optional<Conta> objDelete = repositorio.findById(conta.getId());
		repositorio.delete(objDelete.get());
	}

	@Override
	public List<Conta> listAll() {
		return (List<Conta>) repositorio.findAll();
	}

	@Override
	public Page<Conta> list(ParametroData entrada) {
		return repositorio.listByPage(entrada.getPageable(), entrada.getFiltro(KEY), entrada.getFiltroLong("referencia"));
	}
}
