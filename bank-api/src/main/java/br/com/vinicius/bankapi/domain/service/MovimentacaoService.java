package br.com.vinicius.bankapi.domain.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vinicius.bankapi.domain.model.Conta;
import br.com.vinicius.bankapi.domain.model.Movimentacao;
import br.com.vinicius.bankapi.domain.repository.ContaRepository;
import br.com.vinicius.bankapi.domain.repository.MovimentacaoRepository;
import jakarta.transaction.Transactional;

@Service
public class MovimentacaoService {

	@Autowired 
	private MovimentacaoRepository movimentacaoRepository;
	
	@Autowired
	private ContaRepository contaRepository;
	
	@Transactional
	public Movimentacao salvarMovimentacao(Movimentacao movimentacao) {		
		return movimentacaoRepository.save(movimentacao);
	}	
	
	private Boolean validaCadastroMovimentacao(String numero) {
		Optional<Movimentacao> movimentacao = movimentacaoRepository.findByContaNumero(numero);
		return movimentacao.isPresent();
	}
	
	private Boolean validaOperacaoSaque(BigDecimal valor, Movimentacao movimentacao) {
		Optional<Conta> conta = contaRepository.findById(movimentacao.getConta().getId());
		BigDecimal resultado = conta.get().getLimite().add(conta.get().getSaldo());
		return resultado.compareTo(valor) > 0;
	}
}
