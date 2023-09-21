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

import br.com.vinicius.bankapi.StringUtils;
import br.com.vinicius.bankapi.domain.model.Cliente;
import br.com.vinicius.bankapi.domain.model.Conta;
import br.com.vinicius.bankapi.domain.repository.ContaRepository;
import br.com.vinicius.bankapi.domain.service.ClienteService;
import br.com.vinicius.bankapi.domain.service.ContaService;

@RestController
@RequestMapping("/contas")
public class ContaController {

	@Autowired
	private ContaService contaService;
	
	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping("/getContas")
	public Collection<Conta> getContas(){
		return contaRepository.findAll();
	}
	
	@PostMapping("/salvar/{clienteId}")
	public Conta salvarConta(@PathVariable Long clienteId, @RequestBody Conta conta) {
		Cliente cliente = clienteService.buscarOuFalhar(clienteId);
		conta.setCliente(cliente);		
		conta.setNumero(StringUtils.removeMask(conta.getNumero()));
		return contaService.salvarConta(conta);
	}
	
	@DeleteMapping("/deletar/{numero}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarConta(@PathVariable String numero) {
		Conta conta = contaService.buscarOuFalhar(StringUtils.removeMask(numero));
		contaService.deletarConta(conta);
	}
	
}
