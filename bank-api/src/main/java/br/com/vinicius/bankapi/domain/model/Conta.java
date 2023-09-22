package br.com.vinicius.bankapi.domain.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.vinicius.bankapi.domain.api.view.MovimentacaoView;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class Conta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@JsonView(MovimentacaoView.Resumo.class)
	private String numero;
	private BigDecimal limite;
	
	@JsonView(MovimentacaoView.Resumo.class)
	private BigDecimal saldo;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	@JsonView(MovimentacaoView.Resumo.class)
	private Cliente cliente;
	
	
	

}
