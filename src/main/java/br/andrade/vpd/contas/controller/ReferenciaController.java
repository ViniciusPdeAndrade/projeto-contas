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
import br.andrade.vpd.contas.model.Referencia;
import br.andrade.vpd.contas.service.ReferenciaService;
import br.andrade.vpd.contas.sistema.PageData;
import br.andrade.vpd.contas.sistema.Paginador;
import br.andrade.vpd.contas.sistema.ParametroData;

@RestController
@RequestMapping("/referencia")
public class ReferenciaController {
	
	private static final String KEY_REFERENCIA = "referencia";
	
	private static final String INCLUIDA = "incluida";
	private static final String ALTERADA = "alterada";
	private static final String EXCLUIDA = "excluída";
	
	private static final String MSG_SUCESSO = "Refênrencia %s com sucesso";
	private static final String MSG_ERRO = "Erro ao executar a operação. Erro[%s]";
	
	@Autowired
	private ReferenciaService service;
	
	@PostMapping("/find")
	public PageData find(@RequestBody ParametroData entrada) throws RegistroNaoEncontradoException {
		PageData saida = new PageData();
		try {
			Referencia referencia = service.find(entrada.getId());
			saida.setEntidade(referencia);
			saida.setErro(false);
		}catch(Exception e) {
			saida.setErro(true);
			saida.setMensagem(e.getMessage());
		}
		return saida;
		
	}
	
	@PostMapping("/save")
	public PageData save(@RequestBody ParametroData entrada) throws RegistroNaoEncontradoException{
		PageData saida = new PageData();
		try {
			Referencia referencia = entrada.getParametro(KEY_REFERENCIA, Referencia.class);
			service.save(referencia);
			saida.setMensagem(Helper.getMessage(MSG_SUCESSO, INCLUIDA));
			saida.setErro(false);
			}catch(Exception e) {
				saida.setErro(true);
				saida.setMensagem(Helper.getMessage(MSG_ERRO, e.getMessage()));
			}
		return saida;
	}
	
	@PostMapping("/update")
	public PageData update(@RequestBody ParametroData entrada) throws RegistroNaoEncontradoException{
		PageData saida = new PageData();
		try {
			Referencia referencia = entrada.getParametro(KEY_REFERENCIA, Referencia.class);
			service.update(referencia);
			saida.setMensagem(Helper.getMessage(MSG_SUCESSO, ALTERADA));
			saida.setErro(false);
		}catch(Exception e) {
			saida.setErro(true);
			saida.setMensagem(Helper.getMessage(MSG_ERRO, e.getMessage()));
		}
		return saida;
	}
	@PostMapping("/delete")
	public PageData delete(@RequestBody ParametroData entrada) throws RegistroNaoEncontradoException{
		PageData saida = new PageData();
		try {
			Referencia referencia = service.find(entrada.getId());
			service.update(referencia);
			saida.setMensagem(Helper.getMessage(MSG_SUCESSO, EXCLUIDA));
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
			List<Referencia> list = service.listAll();
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
			Page<Referencia> list = service.list(entrada);
			saida.setLista(list.getContent());
			saida.setPaginador(new Paginador(list, entrada));
			saida.setErro(false);
		}catch(Exception e) {
			e.printStackTrace();
			saida.setErro(true);
			saida.setMensagem(Helper.getMessage( MSG_ERRO,e.getMessage()));
		}
		return saida;
	
	}
}
