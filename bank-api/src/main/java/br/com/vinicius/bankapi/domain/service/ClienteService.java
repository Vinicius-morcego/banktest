package br.com.vinicius.bankapi.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public Cliente salvaCliente(Cliente cliente) throws Exception {		
		if(validaCadastroCliente(cliente.getCpf()))
			throw new Exception(String.format("CPF '%s' já cadastrado.", cliente.getCpf()));
		return clienteRepository.save(cliente);
	}
	
	@Transactional
	public void deletarCliente(Long id) throws Exception {
		if(contaService.validarClienteVinculado(id)) 
			throw new Exception(String.format(
					"Não é permitido remover cliente de id %d vinculado a uma conta.", id));
		clienteRepository.deleteById(id);
	}
	
	public Boolean validaCadastroCliente(String cpf) {
		Optional<Cliente> mCliente = clienteRepository.findByCpf(cpf);
		return mCliente.isPresent();
	}
	
}
