package br.com.vinicius.bankapi.domain.controller;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vinicius.bankapi.StringUtils;
import br.com.vinicius.bankapi.domain.model.Cliente;
import br.com.vinicius.bankapi.domain.model.Conta;
import br.com.vinicius.bankapi.domain.repository.ClienteRepository;
import br.com.vinicius.bankapi.domain.repository.ContaRepository;
import br.com.vinicius.bankapi.domain.service.ContaService;

@RestController
@RequestMapping("/contas")
public class ContaController {

	@Autowired
	private ContaService contaService;
	
	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping("/getContas")
	public Collection<Conta> getContas(){
		return contaRepository.findAll();
	}
	
	@PostMapping("/salvar/{clienteId}")
	public Conta salvarConta(@PathVariable Long clienteId, @RequestBody Conta conta) {
		Optional<Cliente> cliente = clienteRepository.findById(clienteId);
		if(cliente.isPresent()) conta.setCliente(cliente.get());
		conta.setNumero(StringUtils.removeMask(conta.getNumero()));		
		return contaService.salvarConta(conta);
	}
	
}
