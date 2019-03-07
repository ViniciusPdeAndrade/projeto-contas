package br.andrade.vpd.contas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.andrade.vpd.contas.exception.RegistroNaoEncontradoException;
import br.andrade.vpd.contas.model.Rendimento;
import br.andrade.vpd.contas.repository.RendimentoRepository;
import br.andrade.vpd.contas.sistema.ParametroData;

@Service
public class RendimentoServiceImpl implements RendimentoService {

	private static final String KEY = "descricao";
	private static final String MSG_ERRO_BUSCA = "Não foi possível recuperar o Rendimento";
	
	@Autowired
	private RendimentoRepository repositorio;

	@Override
	public Rendimento find(Long id) throws RegistroNaoEncontradoException {
		Optional<Rendimento> op = repositorio.findById(id);
		if (op.isPresent()) {
			return op.get();
		}
		throw new RegistroNaoEncontradoException(MSG_ERRO_BUSCA);
	}

	@Override
	public void save(Rendimento rendimento) {
		repositorio.save(rendimento);

	}

	@Override
	public void update(Rendimento rendimento) {
		repositorio.save(rendimento);
		
	}

	@Override
	public void remove(Rendimento rendimento) {
		Optional<Rendimento> objDelete = repositorio.findById(rendimento.getId());
		repositorio.delete(objDelete.get());
		
	}
	@Override
	public List<Rendimento> listAll(){
		return (List<Rendimento>) repositorio.findAll();
	}

	@Override
	public Page<Rendimento> list(ParametroData entrada) {
		return repositorio.listByPage(entrada.getPageable(), entrada.getFiltro(KEY), entrada.getFiltroLong("referencia"));
	}

}
