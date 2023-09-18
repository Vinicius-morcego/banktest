package br.com.vinicius.bankapi.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vinicius.bankapi.domain.model.Cliente;
import br.com.vinicius.bankapi.domain.model.Conta;
import br.com.vinicius.bankapi.domain.repository.ClienteRepository;
import br.com.vinicius.bankapi.domain.repository.ContaRepository;

@Service
public class ContaService {

	@Autowired
	private ContaRepository contaRepository;
	
	
	
	public Conta salvarConta(Conta conta) throws Exception {	
		if(validaCadastroConta(conta.getNumero()))
			throw new Exception(String.format("Conta '%s' já existente!", conta.getNumero()));
		return contaRepository.save(conta);
	}
	
	public Boolean validaCadastroConta(String numero) {
		Optional<Conta> conta = contaRepository.findByNumero(numero);
		return conta.isPresent();
	}
	
	public Boolean validarClienteVinculado(Long clienteId) {
		Optional<Conta> conta = contaRepository.findByClienteId(clienteId);
		return conta.isPresent();
	}
	
}
