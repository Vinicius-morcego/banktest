package br.com.vinicius.bankapi.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Movimentacao{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	@ManyToOne
	@JoinColumn(name = "conta_id")
	private Conta conta;
	private Integer operacao;
	private BigDecimal valor;
	
	@CreationTimestamp
	private OffsetDateTime data_hora;

	public void realizarSaque() {
		BigDecimal resultado = new BigDecimal(0);
		if(getConta().getSaldo().compareTo(new BigDecimal(0)) == 0) 
			resultado = getConta().getLimite();
		else
			resultado = getConta().getSaldo();
		resultado = resultado.subtract(getValor());
		getConta().setSaldo(resultado);
	}

	public void realizarDeposito() {
		BigDecimal resultado = new BigDecimal(0);
		if(getConta().getSaldo().compareTo(new BigDecimal(0)) == 0) 
			resultado = getConta().getLimite();
		else
			resultado = getConta().getSaldo();
		resultado = resultado.add(getValor());
		getConta().setSaldo(resultado);
	}
}
