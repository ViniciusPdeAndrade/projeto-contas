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
import br.andrade.vpd.contas.model.Conta;
import br.andrade.vpd.contas.model.Referencia;
import br.andrade.vpd.contas.service.ContaService;
import br.andrade.vpd.contas.service.ReferenciaService;
import br.andrade.vpd.contas.sistema.PageData;
import br.andrade.vpd.contas.sistema.Paginador;
import br.andrade.vpd.contas.sistema.ParametroData;

@RestController
@RequestMapping("/conta")
public class ContaController {

	private static final String KEY_CONTA = "Conta";
	private static final String KEY_REFERENCIA = "referencia";

	private static final String EXCLUIDA = "Excluída";
	private static final String ALTERADA = "Alterada";
	private static final String INCLUIDA = "Incluída";

	private static final String MSG_ERRO = "Erro ao executar a operação. Erro[%s]";
	private static final String MSG_SUCESSO = "Conta %s com sucesso";

	@Autowired
	private ContaService service;
	
	@Autowired
	private ReferenciaService referenciaService;

	@PostMapping("/find")
	public PageData find(@RequestBody ParametroData entrada) {
		PageData saida = new PageData();
		try {
			Conta conta = service.find(entrada.getId());
			saida.setEntidade(conta);
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
			Conta conta = entrada.getParametro(KEY_CONTA, Conta.class);
			service.save(conta);
			saida.setMensagem(Helper.getMessage(MSG_SUCESSO, INCLUIDA));
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
			Conta conta = entrada.getParametro(KEY_CONTA, Conta.class);
			service.update(conta);
			saida.setMensagem(Helper.getMessage(MSG_SUCESSO, ALTERADA));
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
			Conta conta = entrada.getParametro(KEY_CONTA, Conta.class);
			service.remove(conta);
			saida.setMensagem(Helper.getMessage(MSG_SUCESSO, EXCLUIDA));
			saida.setErro(false);
		} catch (Exception e) {
			e.printStackTrace();
			saida.setErro(true);
			saida.setMensagem(Helper.getMessage(MSG_ERRO, e.getMessage()));
		}
		return saida;
	}

	@PostMapping("/listAll")
	public PageData listAll(@RequestBody ParametroData entrada) throws RegistroNaoEncontradoException {
		PageData saida = new PageData();
		try {
			List<Conta> list = service.listAll();
			saida.setLista(list);
			saida.setErro(false);
		} catch (Exception e) {
			e.printStackTrace();
			saida.setErro(true);
			saida.setMensagem(Helper.getMessage(MSG_ERRO, e.getMessage()));
		}
		return saida;
	}

	@PostMapping("/list")
	public PageData list(@RequestBody ParametroData entrada) throws RegistroNaoEncontradoException {
		PageData saida = new PageData();
		try {
			Page<Conta> list = service.list(entrada);
			saida.setLista(list.getContent());
			saida.setPaginador(new Paginador(list, entrada));
			saida.setErro(false);
			
			Referencia referencia = referenciaService.find(entrada.getFiltroLong(KEY_REFERENCIA));
			saida.addAuxiliar(KEY_REFERENCIA, referencia);
		} catch (Exception e) {
			e.printStackTrace();
			saida.setErro(true);
			saida.setMensagem(Helper.getMessage(MSG_ERRO, e.getMessage()));
		}
		return saida;

	}
}
