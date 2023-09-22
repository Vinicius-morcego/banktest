package br.com.vinicius.bankapi.domain.model;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.vinicius.bankapi.domain.api.view.MovimentacaoView;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cliente {
	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonView(MovimentacaoView.Resumo.class)
	private String nome;
	
	@JsonView(MovimentacaoView.Resumo.class)
	private String cpf;

}
