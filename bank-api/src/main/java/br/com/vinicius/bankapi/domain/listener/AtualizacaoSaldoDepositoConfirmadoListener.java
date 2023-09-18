package br.com.vinicius.bankapi.domain.listener;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import br.com.vinicius.bankapi.domain.event.DepositoConfirmadoEvent;
import br.com.vinicius.bankapi.domain.model.Movimentacao;

@Component
public class AtualizacaoSaldoDepositoConfirmadoListener {

	@TransactionalEventListener
	public void aoRealizarDeposito(DepositoConfirmadoEvent event) {
		Movimentacao movimentacao = event.getMovimentacao();
		BigDecimal resultado = movimentacao.getConta().getLimite()
				.add(movimentacao.getConta().getSaldo());
		resultado = resultado.add(movimentacao.getValor());
		movimentacao.getConta().setSaldo(resultado);
	}
}
