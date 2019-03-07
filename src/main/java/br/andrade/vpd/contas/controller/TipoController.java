package br.andrade.vpd.contas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.andrade.vpd.contas.exception.RegistroNaoEncontradoException;
import br.andrade.vpd.contas.helper.Helper;
import br.andrade.vpd.contas.model.Tipo;
import br.andrade.vpd.contas.service.TipoService;
import br.andrade.vpd.contas.sistema.PageData;
import br.andrade.vpd.contas.sistema.Paginador;
import br.andrade.vpd.contas.sistema.ParametroData;

@RestController
@RequestMapping("/tipo")
public class TipoController {
	
	private static final String KEY_TIPO = "tipo";
	
	private static final String EXCLUIDO = "excluído";
	private static final String ALTERADO = "alterado";
	private static final String INCLUIDO = "incluido";
	
	private static final String MSG_SUCESSO = "Tipo %s com sucesso";
	private static final String MSG_ERRO = "Erro ao executar operação. Erro[%s]";


	@Autowired
	private TipoService service;

	@PostMapping("/find")
	public PageData find(@RequestBody ParametroData entrada) throws RegistroNaoEncontradoException {
		PageData saida = new PageData();
		try {
			Tipo tipo = service.find(entrada.getId());
			saida.setEntidade(tipo);
			saida.setErro(false);
		}catch(Exception e) {
			saida.setErro(true);
			saida.setMensagem(e.getMessage());
		}
		return saida;
	}

	@PostMapping("/save")
	public PageData save(@RequestBody ParametroData entrada) throws RegistroNaoEncontradoException {
		PageData saida = new PageData();
		try {
			Tipo tipo = entrada.getParametro(KEY_TIPO, Tipo.class);
			service.save(tipo);
			saida.setMensagem(Helper.getMessage(MSG_SUCESSO, INCLUIDO));
			saida.setErro(false);
		}catch(Exception e) {
			saida.setErro(true);
			saida.setMensagem(Helper.getMessage(MSG_ERRO, e.getMessage()));
		}
		return saida;
	}

	@PostMapping("/update")
	public PageData update(@RequestBody ParametroData entrada) throws RegistroNaoEncontradoException {
		PageData saida = new PageData();
		try {
			Tipo tipo = entrada.getParametro(KEY_TIPO, Tipo.class);
			service.update(tipo);
			saida.setMensagem(Helper.getMessage(MSG_SUCESSO, ALTERADO));
			saida.setErro(false);
		}catch(Exception e) {
			saida.setErro(true);
			saida.setMensagem(Helper.getMessage(MSG_ERRO, e.getMessage()));
		}
		return saida;
	}

	@PostMapping("/delete")
	public PageData delete(@RequestBody ParametroData entrada) throws RegistroNaoEncontradoException {
		PageData saida = new PageData();
		try {
		Tipo tipo = service.find(entrada.getId());
		service.remove(tipo);
		saida.setMensagem(Helper.getMessage(MSG_SUCESSO, EXCLUIDO));
		saida.setErro(false);		
		}catch(Exception e) {
			saida.setErro(true);
			saida.setMensagem(Helper.getMessage(MSG_ERRO, e.getMessage()));
		}
		return saida;
	}
	@PostMapping("/listAll")
	public PageData listAll(@RequestBody ParametroData entrada) throws RegistroNaoEncontradoException{
		PageData saida = new PageData();
		try {
			List<Tipo> list = service.listAll();
			saida.setLista(list);
			saida.setErro(false);
		}catch(Exception e) {
			e.printStackTrace();
			saida.setErro(true);
			saida.setMensagem(Helper.getMessage(MSG_ERRO, e.getMessage()));
		}
		
		return saida;
	}
	
	
	@PostMapping("/list")
	public PageData list(@RequestBody ParametroData entrada) throws RegistroNaoEncontradoException{
		PageData saida = new PageData();
		try {
			Page<Tipo> list = service.list(entrada);
			saida.setLista(list.getContent());
			saida.setPaginador(new Paginador(list,entrada));
			saida.setErro(false);
		}catch(Exception e) {
			saida.setErro(true);
			saida.setMensagem(Helper.getMessage(MSG_ERRO, e.getMessage()));
		}
		return saida;
	}

}
