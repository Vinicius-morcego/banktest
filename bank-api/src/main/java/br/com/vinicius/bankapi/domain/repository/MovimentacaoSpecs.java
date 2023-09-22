package br.com.vinicius.bankapi.domain.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.domain.Specification;

import br.com.vinicius.bankapi.domain.model.Movimentacao;
import br.com.vinicius.bankapi.domain.repository.filter.MovimentacaoFilter;
import jakarta.persistence.criteria.Predicate;

public class MovimentacaoSpecs {

	public static Specification<Movimentacao> usandoFiltros(MovimentacaoFilter filtro){
		
		return(root, query, builder) -> {
			root.fetch("conta");
			
			var predicates = new ArrayList<Predicate>();
			if(filtro.getDataInicio() != null) {
				predicates.add(builder.greaterThanOrEqualTo(root.get("data_hora"), filtro.getDataInicio()));
			}
			if(filtro.getDataFinal() != null) {
				predicates.add(builder.lessThanOrEqualTo(root.get("data_hora"), filtro.getDataFinal()));
			}
			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}
}
