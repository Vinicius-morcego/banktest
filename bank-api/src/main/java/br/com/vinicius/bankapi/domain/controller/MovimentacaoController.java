package br.com.vinicius.bankapi.domain.controller;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.vinicius.bankapi.domain.api.view.MovimentacaoView;
import br.com.vinicius.bankapi.domain.exception.ContaNaoEncontradaException;
import br.com.vinicius.bankapi.domain.model.Conta;
import br.com.vinicius.bankapi.domain.model.Movimentacao;
import br.com.vinicius.bankapi.domain.repository.MovimentacaoRepository;
import br.com.vinicius.bankapi.domain.repository.MovimentacaoSpecs;
import br.com.vinicius.bankapi.domain.repository.filter.MovimentacaoFilter;
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
	
	@JsonView(MovimentacaoView.Resumo.class)
	@GetMapping("/getMovimentacao/{numero}")
	public Collection<Movimentacao> getMovimentacao(@PathVariable String numero){		
		return movimentacaoRepository.findMovimentacaoByNumeroConta(numero);	
	}
	
	@GetMapping
	public Collection<Movimentacao> getMovimentacaoPorPeriodo(@RequestParam OffsetDateTime dataInicial, 
			@RequestParam OffsetDateTime dataFinal){
		//return movimentacaoRepository.findAll(MovimentacaoSpecs.usandoFiltros(filtro));
		return movimentacaoRepository.findMovimentacaoByPeriodo(dataInicial, dataFinal);
	}
	
	@PostMapping("/salvar/{numero}")
	public Movimentacao salvarMovimentacao(@PathVariable String numero, @RequestBody Movimentacao movimentacao) throws Exception {			
		Conta mConta = contaService.buscarOuFalhar(numero);		
		movimentacao.setConta(mConta);
		return movimentacaoService.salvarMovimentacao(movimentacao);			
	}
}
