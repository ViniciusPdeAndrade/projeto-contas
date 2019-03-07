package br.andrade.vpd.contas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.andrade.vpd.contas.exception.RegistroNaoEncontradoException;
import br.andrade.vpd.contas.model.Tipo;
import br.andrade.vpd.contas.repository.TipoRepository;
import br.andrade.vpd.contas.sistema.ParametroData;

@Service
public class TipoServiceImpl implements TipoService {

	private static final String KEY = "descricao";
	private static final String MSG_ERRO_BUSCA = "Não foi possível recuperar o Tipo";
	
	@Autowired
	private TipoRepository repositorio;

	@Override
	public Tipo find(Long id) throws RegistroNaoEncontradoException {
		Optional<Tipo> op = repositorio.findById(id);
		if (op.isPresent()) {
			return op.get();
		}
		throw new RegistroNaoEncontradoException(MSG_ERRO_BUSCA);
	}

	@Override
	public void save(Tipo tipo) {
			repositorio.save(tipo);
	}

	@Override
	public void update(Tipo tipo) {
		repositorio.save(tipo);
	}

	@Override
	public void remove(Tipo tipo) {
		Optional<Tipo> objDelete = repositorio.findById(tipo.getId());
		repositorio.delete(objDelete.get());
	}
	
	@Override
	public List<Tipo> listAll(){
		return (List<Tipo>) repositorio.findAll();
	}

	@Override
	public Page<Tipo> list(ParametroData entrada) {
		
		return repositorio.listByPage(entrada.getPageable(), entrada.getFiltro(KEY));
	}
	
}
