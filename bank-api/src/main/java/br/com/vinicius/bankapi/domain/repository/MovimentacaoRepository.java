package br.com.vinicius.bankapi.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.vinicius.bankapi.domain.model.Movimentacao;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long>{

	@Query(name="Movimentacao.existsContaMovimentacao")
	Optional<Movimentacao> findMovimentacaoByNumeroConta(String numero);
}
