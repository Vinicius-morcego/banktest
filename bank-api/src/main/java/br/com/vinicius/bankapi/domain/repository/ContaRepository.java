package br.com.vinicius.bankapi.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.vinicius.bankapi.domain.model.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long>{

	@Query(name="Conta.consultarPorNumero")
	Optional<Conta> findContaByNumero(String numero);
	
	@Query(name="Conta.existsResponsavel")
	Optional<Conta> findContaByClienteId(Long clienteId);
}
