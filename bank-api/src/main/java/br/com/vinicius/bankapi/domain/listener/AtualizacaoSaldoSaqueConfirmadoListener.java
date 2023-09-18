package br.com.vinicius.bankapi.domain.listener;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import br.com.vinicius.bankapi.domain.event.SaqueConfirmadoEvent;
import br.com.vinicius.bankapi.domain.model.Movimentacao;

@Component
public class AtualizacaoSaldoSaqueConfirmadoListener {

	@TransactionalEventListener
	public void aoRealizarSaque(SaqueConfirmadoEvent event) {
		Movimentacao movimentacao = event.getMovimentacao();
		BigDecimal resultado = movimentacao.getConta().getLimite()
				.add(movimentacao.getConta().getSaldo());
		resultado = resultado.subtract(movimentacao.getValor());
		movimentacao.getConta().setSaldo(resultado);
	}
}
