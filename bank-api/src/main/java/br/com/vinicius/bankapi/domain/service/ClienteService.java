package br.com.vinicius.bankapi.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.vinicius.bankapi.domain.exception.ClienteNaoEncontradoException;
import br.com.vinicius.bankapi.domain.exception.NegocioException;
import br.com.vinicius.bankapi.domain.model.Cliente;
import br.com.vinicius.bankapi.domain.repository.ClienteRepository;
import jakarta.transaction.Transactional;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ContaService contaService;

	@Transactional
	public Cliente salvaCliente(Cliente cliente) {		
		if(validaCadastroCliente(cliente.getCpf()))
			throw new NegocioException(String.format("CPF '%s' já cadastrado.", cliente.getCpf()));
		return clienteRepository.save(cliente);
	}
	
	@Transactional
	public void deletarCliente(Cliente cliente) {
		try {			
			if(contaService.validarClienteVinculado(cliente.getId())) 
				throw new NegocioException(String.format(
						"Não é permitido remover cliente de id %d vinculado a uma conta.", cliente.getId()));
			clienteRepository.deleteById(cliente.getId());
			clienteRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new ClienteNaoEncontradoException(cliente.getId());
		} 
	}
	
	public Boolean validaCadastroCliente(String cpf) {
		Optional<Cliente> mCliente = clienteRepository.findByCpf(cpf);
		return mCliente.isPresent();
	}

	public Cliente buscarOuFalhar(Long clienteId) {
		Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(() -> new ClienteNaoEncontradoException(clienteId));
		return cliente;
	}
	
}
