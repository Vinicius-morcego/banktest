package br.com.vinicius.bankapi.domain.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vinicius.bankapi.domain.model.Conta;
import br.com.vinicius.bankapi.domain.model.Movimentacao;
import br.com.vinicius.bankapi.domain.repository.MovimentacaoRepository;
import br.com.vinicius.bankapi.domain.service.ContaService;
import br.com.vinicius.bankapi.domain.service.MovimentacaoService;

@RestController
@RequestMapping("/movimentacoes")
public class MovimentacaoController {
	
	@Autowired
	private ContaService contaService;
	
	@Autowired
	private MovimentacaoService movimentacaoService;
	
	@Autowired
	private MovimentacaoRepository movimentacaoRepository;
	
	@GetMapping("{numero}")
	public Optional<Movimentacao> getMovimentacao(@PathVariable String numero, 
			@RequestBody Movimentacao movimentacao) throws Exception{
		Conta mConta = contaService.buscarOuFalhar(numero);		
		Optional<Movimentacao> buscaMovimentacao = movimentacaoRepository.findMovimentacaoByNumeroConta(mConta.getNumero());		
		if(buscaMovimentacao.isEmpty()) throw new Exception(String.format(
				"Nenhuma movimentação para conta de numero '%s' disponivel", buscaMovimentacao.get().getConta().getNumero()));
		return buscaMovimentacao;
	}
	
	@PostMapping("/salvar/{numero}")
	public Movimentacao salvarMovimentacao(@PathVariable String numero, @RequestBody Movimentacao movimentacao) throws Exception {
		Conta mConta = contaService.buscarOuFalhar(numero);		
		movimentacao.setConta(mConta);
		if(movimentacao.getOperacao() == 0)
			movimentacaoService.realizarSaque(movimentacao);
		else if(movimentacao.getOperacao() == 1)
			movimentacaoService.realizarDeposito(movimentacao);
		else
			throw new Exception("Operação inválida");
			
		return movimentacaoService.salvarMovimentacao(movimentacao);
	}
}
