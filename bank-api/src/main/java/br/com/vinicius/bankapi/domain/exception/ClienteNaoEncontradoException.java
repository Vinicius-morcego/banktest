package br.com.vinicius.bankapi.domain.exception;

public class ClienteNaoEncontradoException extends EntidadeNaoEncontradaException{
	
	private static final long serialVersionUID = 1L;

	public ClienteNaoEncontradoException(String mensagem) {
		super(mensagem);		
	}
	
	public ClienteNaoEncontradoException(Long clienteId) {
		this(String.format("Cliente de id %d não encontrado", clienteId));
	}


}
