package br.andrade.vpd.contas.helper;

public class Helper {

	public  static String getMessage(String mensagem, String parametro) {
		return String.format(mensagem,parametro);
	}
}

