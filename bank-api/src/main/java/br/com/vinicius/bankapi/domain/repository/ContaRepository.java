package br.com.vinicius.bankapi.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.vinicius.bankapi.domain.model.Cliente;
import br.com.vinicius.bankapi.domain.model.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long>{

	Optional<Conta> findContaByNumero(String numero);
	Optional<Conta> findByClienteId(Long clienteId);
}
