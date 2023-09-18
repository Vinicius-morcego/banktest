package br.com.vinicius.bankapi.domain.event;

import br.com.vinicius.bankapi.domain.model.Movimentacao;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SaqueConfirmadoEvent {

	private Movimentacao movimentacao;
}
