package br.com.vinicius.bankapi.domain.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.vinicius.bankapi.StringUtils;
import br.com.vinicius.bankapi.domain.model.Conta;
import br.com.vinicius.bankapi.domain.model.Movimentacao;
import br.com.vinicius.bankapi.domain.repository.ContaRepository;
import br.com.vinicius.bankapi.domain.repository.MovimentacaoRepository;
import br.com.vinicius.bankapi.domain.service.MovimentacaoService;

@RestController
@RequestMapping("/movimentacoes")
public class MovimentacaoController {
	
	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private MovimentacaoService movimentacaoService;
	
	@Autowired
	private MovimentacaoRepository movimentacaoRepository;
	
	@GetMapping("{numero}")
	public Optional<Movimentacao> getMovimentacao(@PathVariable String numero, 
			@RequestBody Movimentacao movimentacao) throws Exception{
		Optional<Conta> mConta = contaRepository.findContaByNumero(StringUtils.removeMask(numero));
		if(mConta.isEmpty()) 
			throw new Exception(String.format("Nenhuma conta de numero '%s' disponivel", numero));
		Optional<Movimentacao> buscaMovimentacao = movimentacaoRepository.findMovimentacaoByContaId(mConta.get().getId());		
		if(buscaMovimentacao.isEmpty()) throw new Exception(String.format(
				"Nenhuma movimentação disponivel para conta de numero '%s' disponivel", buscaMovimentacao.get().getConta().getNumero()));
		return buscaMovimentacao;
	}
	
	@PostMapping("/salvar/{numero}")
	public Movimentacao salvarMovimentacao(@PathVariable String numero, @RequestBody Movimentacao movimentacao) throws Exception {
		Optional<Conta> mConta = contaRepository.findContaByNumero(StringUtils.removeMask(numero));
		if(mConta.isEmpty()) 
			throw new Exception(String.format("Nenhuma conta de numero '%s' disponivel", numero));
		movimentacao.setConta(mConta.get());
		if(movimentacao.getOperacao() == 0)
			movimentacaoService.realizarSaque(movimentacao);
		else 
			movimentacaoService.realizarDeposito(movimentacao);
			
		return movimentacaoService.salvarMovimentacao(movimentacao);
	}
}
