package br.com.vinicius.bankapi.domain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vinicius.bankapi.domain.exception.NegocioException;
import br.com.vinicius.bankapi.domain.model.Conta;
import br.com.vinicius.bankapi.domain.model.Movimentacao;
import br.com.vinicius.bankapi.domain.service.ContaService;
import br.com.vinicius.bankapi.domain.service.MovimentacaoService;

@RestController
@RequestMapping("/movimentacoes")
public class MovimentacaoController {
	
	@Autowired
	private ContaService contaService;
	
	@Autowired
	private MovimentacaoService movimentacaoService;
	
	@GetMapping("{numero}")
	public Movimentacao getMovimentacao(@PathVariable String numero, @RequestBody Movimentacao movimentacao){
		Conta mConta = contaService.buscarOuFalhar(numero);		
		movimentacao.setConta(mConta);
		return movimentacao;
	}
	
	@PostMapping("/salvar/{numero}")
	public Movimentacao salvarMovimentacao(@PathVariable String numero, @RequestBody Movimentacao movimentacao) throws Exception {
			
				if(movimentacao.getOperacao() == 0)
					movimentacaoService.realizarSaque(movimentacao);
				else if(movimentacao.getOperacao() == 1)				
					movimentacaoService.realizarDeposito(movimentacao);
				else throw new NegocioException("Operação inválida");
				
				Conta mConta = contaService.buscarOuFalhar(numero);		
				movimentacao.setConta(mConta);
				return movimentacaoService.salvarMovimentacao(movimentacao);
			
	}
}
