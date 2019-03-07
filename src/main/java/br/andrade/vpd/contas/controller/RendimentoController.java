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
import br.andrade.vpd.contas.model.Rendimento;
import br.andrade.vpd.contas.service.RendimentoService;
import br.andrade.vpd.contas.sistema.PageData;
import br.andrade.vpd.contas.sistema.Paginador;
import br.andrade.vpd.contas.sistema.ParametroData;

@RestController
@RequestMapping("/rendimento")
public class RendimentoController {

	private static final String KEY_RENDIMENTO = "rendimento";

	private static final String INCLUIDO = "incluido";
	private static final String ALTERADO = "alterado";
	private static final String EXCLUIDO = "excluído";

	private static final String MSG_SUCESSO = "Rendimento %s com sucesso";
	private static final String MSG_ERRO = "Erro ao executar a operação. Erro[%s]";

	@Autowired
	private RendimentoService service;

	@PostMapping("/find")
	public PageData find(@RequestBody ParametroData entrada) {
		PageData saida = new PageData();
		try {
			Rendimento rendimento = service.find(entrada.getId());
			saida.setEntidade(rendimento);
			saida.setErro(false);
		} catch (Exception e) {
			e.printStackTrace();
			saida.setErro(true);
			saida.setMensagem(e.getMessage());
		}
		return saida;
	}

	@PostMapping("/save")
	public PageData save(@RequestBody ParametroData entrada) {
		PageData saida = new PageData();
		try {
			Rendimento rendimento = entrada.getParametro(KEY_RENDIMENTO, Rendimento.class);
			service.save(rendimento);
			saida.setMensagem(Helper.getMessage(MSG_SUCESSO, INCLUIDO));
			saida.setErro(false);
		} catch (Exception e) {
			e.printStackTrace();
			saida.setErro(true);
			saida.setMensagem(Helper.getMessage(MSG_ERRO, e.getMessage()));
		}
		return saida;
	}

	@PostMapping("/update")
	public PageData update(@RequestBody ParametroData entrada) {
		PageData saida = new PageData();
		try {
			Rendimento rendimento = entrada.getParametro(KEY_RENDIMENTO, Rendimento.class);
			service.update(rendimento);
			saida.setMensagem(Helper.getMessage(MSG_SUCESSO, ALTERADO));
			saida.setErro(false);
		} catch (Exception e) {
			e.printStackTrace();
			saida.setErro(true);
			saida.setMensagem(Helper.getMessage(MSG_ERRO, e.getMessage()));
		}
		return saida;
	}

	@PostMapping("/delete")
	public PageData delete(@RequestBody ParametroData entrada) {
		PageData saida = new PageData();
		try {
			Rendimento rendimento = entrada.getParametro(KEY_RENDIMENTO, Rendimento.class);
			service.remove(rendimento);
			saida.setMensagem(Helper.getMessage(MSG_SUCESSO, EXCLUIDO));
			saida.setErro(false);
		} catch (Exception e) {
			e.printStackTrace();
			saida.setErro(true);
			saida.setMensagem(Helper.getMessage(MSG_ERRO, e.getMessage()));
		}
		return saida;
	}

	@PostMapping("/listAll")
	public PageData listAll(@RequestBody ParametroData entrada) throws RegistroNaoEncontradoException{
		PageData saida = new PageData();
		try {
			List<Rendimento> list = service.listAll();
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
			Page<Rendimento> list = service.list(entrada);
			saida.setLista(list.getContent());
			saida.setPaginador(new Paginador(list, entrada));
			saida.setErro(false);
		}catch(Exception e) {
			e.printStackTrace();
			saida.setErro(true);
			saida.setMensagem(Helper.getMessage(MSG_ERRO, e.getMessage()));
		}
		return saida;
	
	}
}
