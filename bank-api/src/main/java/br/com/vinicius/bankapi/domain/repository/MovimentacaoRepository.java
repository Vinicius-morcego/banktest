package br.com.vinicius.bankapi.domain.repository;

import java.time.OffsetDateTime;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.vinicius.bankapi.domain.model.Movimentacao;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long>,
	JpaSpecificationExecutor<Movimentacao>{

	@Query(name="Movimentacao.existsContaMovimentacao")
	Collection<Movimentacao> findMovimentacaoByNumeroConta(String numero);
	
	@Query(name="Movimentacao.porPeriodo")
	Collection<Movimentacao> findMovimentacaoByPeriodo(OffsetDateTime dataInicial, 
			OffsetDateTime dataFinal);
}
