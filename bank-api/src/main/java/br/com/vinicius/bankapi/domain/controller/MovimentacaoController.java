package br.com.vinicius.bankapi.domain.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vinicius.bankapi.StringUtils;
import br.com.vinicius.bankapi.domain.model.Movimentacao;
import br.com.vinicius.bankapi.domain.repository.MovimentacaoRepository;

@RestController
@RequestMapping("/movimentacoes")
public class MovimentacaoController {
	
	@Autowired
	private MovimentacaoRepository movimentacaoRepository;
	
	@GetMapping("{numero}")
	public Optional<Movimentacao> getMovimentacao(@PathVariable String numero, 
			@RequestBody Movimentacao movimentacao) throws Exception{
		Optional<Movimentacao> mResultado = movimentacaoRepository.findByContaNumero(StringUtils.removeMask(numero));
		if(mResultado.isEmpty()) 
			throw new Exception(String.format("Nenhuma movimentação para conta '%s' disponivel", numero));
		
		return mResultado;
	}

}
