package br.com.vinicius.bankapi.domain.exception;

public class ContaNaoEncontradaException extends EntidadeNaoEncontradaException{
	
	private static final long serialVersionUID = 1L;
	
	public ContaNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
}
