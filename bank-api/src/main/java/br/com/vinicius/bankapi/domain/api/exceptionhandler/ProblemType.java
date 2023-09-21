package br.com.vinicius.bankapi.domain.api.exceptionhandler;

import lombok.Getter;


@Getter
public enum ProblemType {

	ERRO_DE_DIGITACAO("/erro-de-digitacao", "Erro de digitação"),
	ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de sistema"),
	ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
	ERRO_DE_NEGOCIO("/erro-de-negocio", "Erro de negócio"),
	RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado");

	private String titulo;
	private String uri;
	ProblemType(String path, String titulo) {

		this.titulo = titulo;
		this.uri = "https://bancktest.com.br" + path;
	}
	
}
