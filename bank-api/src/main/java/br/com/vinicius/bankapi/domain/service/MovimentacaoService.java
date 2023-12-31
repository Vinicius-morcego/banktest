package br.com.vinicius.bankapi.domain.service;

import java.math.BigDecimal;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vinicius.bankapi.domain.exception.NegocioException;
import br.com.vinicius.bankapi.domain.model.Conta;
import br.com.vinicius.bankapi.domain.model.Movimentacao;
import br.com.vinicius.bankapi.domain.repository.MovimentacaoRepository;
import jakarta.transaction.Transactional;

@Service
public class MovimentacaoService {
	
	@Autowired
	private MovimentacaoRepository movimentacaoRepository;
	
	@Transactional
	public Movimentacao salvarMovimentacao(Movimentacao movimentacao) throws Exception {
		if(movimentacao.getOperacao() == 0)
			realizarSaque(movimentacao);
		else if(movimentacao.getOperacao() == 1)				
			realizarDeposito(movimentacao);
		else throw new NegocioException("Operação inválida");
		return movimentacaoRepository.save(movimentacao);
	}
	
	@Transactional
	public void realizarSaque(Movimentacao movimentacao) throws Exception {
		if(validaOperacaoSaque(movimentacao.getValor(), movimentacao.getConta()))
			throw new NegocioException(String.format("Operação inválida, saldo indisponivel para conta %s.", 
					movimentacao.getConta().getNumero()));		
		movimentacao.realizarSaque();
	}
	
	@Transactional
	public void realizarDeposito(Movimentacao movimentacao) throws Exception {
		movimentacao.realizarDeposito();		
	}
	
	private Boolean validaOperacaoSaque(BigDecimal valor, Conta conta) throws Exception {
		return valor.compareTo(conta.getSaldo()) > 0; 
	}
	
	public Boolean validaContaVinculada(String numero) {
		Collection<Movimentacao> movimentacao = movimentacaoRepository.findMovimentacaoByNumeroConta(numero);
		return !movimentacao.isEmpty();
	}
}
