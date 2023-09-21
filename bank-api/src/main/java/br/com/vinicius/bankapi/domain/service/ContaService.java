package br.com.vinicius.bankapi.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vinicius.bankapi.StringUtils;
import br.com.vinicius.bankapi.domain.exception.ContaNaoEncontradaException;
import br.com.vinicius.bankapi.domain.exception.NegocioException;
import br.com.vinicius.bankapi.domain.model.Conta;
import br.com.vinicius.bankapi.domain.repository.ContaRepository;
import jakarta.transaction.Transactional;

@Service
public class ContaService {

	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private MovimentacaoService movimentacaoService;
	
	@Transactional
	public Conta salvarConta(Conta conta) {	
		if(validaCadastroConta(conta.getNumero()))
			throw new NegocioException(String.format("Conta '%s' já existente!", conta.getNumero()));
		return contaRepository.save(conta);
	}
	
	@Transactional
	public void deletarConta(Conta conta) {
		if(movimentacaoService.validaContaVinculada(conta.getNumero()))
			throw new NegocioException(String.format("Não é permitido deletar conta de numero '%s' vinculada a uma movimentação.", conta.getNumero()));
		contaRepository.delete(conta);
		contaRepository.flush();
		
	}
	public Boolean validaCadastroConta(String numero) {
		Optional<Conta> conta = contaRepository.findContaByNumero(StringUtils.removeMask(numero));
		return conta.isPresent();
	}
	
	public Boolean validarClienteVinculado(Long clienteId) {
		Optional<Conta> conta = contaRepository.findContaByClienteId(clienteId);
		return conta.isPresent();
	}
	
	public Conta buscarOuFalhar(String numero) {
		Conta conta = contaRepository.findContaByNumero(StringUtils.removeMask(numero)).orElseThrow(() -> 
			new ContaNaoEncontradaException(String.format("Conta '%s' não localizada.", numero)));
		return conta;
	}
	
}
