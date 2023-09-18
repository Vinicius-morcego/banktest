package br.com.vinicius.bankapi.domain.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.vinicius.bankapi.domain.model.Cliente;
import br.com.vinicius.bankapi.domain.repository.ClienteRepository;
import br.com.vinicius.bankapi.domain.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired 
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping("/getClientes")
	public Collection<Cliente> getClientes(){
		return clienteRepository.findAll();
	}
	
	@PostMapping("/salvar")
	public Cliente salvar(@RequestBody Cliente cliente) throws Exception{		
		return clienteService.salvaCliente(cliente);
	}
	
	@DeleteMapping("/{clienteId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarCliente(@PathVariable Long clienteId) throws Exception {
		clienteService.deletarCliente(clienteId);
	}
}
