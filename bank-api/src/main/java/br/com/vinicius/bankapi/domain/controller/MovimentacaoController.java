package br.com.vinicius.bankapi.domain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.vinicius.bankapi.domain.api.view.MovimentacaoView;
import br.com.vinicius.bankapi.domain.exception.ContaNaoEncontradaException;
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
	
	@GetMapping("/getMovimentacao/{numero}")
	@JsonView(MovimentacaoView.Resumo.class)
	public Movimentacao getMovimentacao(@PathVariable String numero){		
		return movimentacaoRepository.findMovimentacaoByNumeroConta(numero)
				.orElseThrow(() -> new ContaNaoEncontradaException(String.format("Movimentação não disponivel para conta %s", numero)));
	}
	
	@PostMapping("/salvar/{numero}")
	public Movimentacao salvarMovimentacao(@PathVariable String numero, @RequestBody Movimentacao movimentacao) throws Exception {			
		Conta mConta = contaService.buscarOuFalhar(numero);		
		movimentacao.setConta(mConta);
		return movimentacaoService.salvarMovimentacao(movimentacao);			
	}
}
