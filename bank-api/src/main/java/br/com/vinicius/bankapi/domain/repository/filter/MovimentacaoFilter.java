package br.com.vinicius.bankapi.domain.repository.filter;

import java.time.OffsetDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MovimentacaoFilter {

	@DateTimeFormat(iso = ISO.DATE_TIME)
	private OffsetDateTime dataInicio;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private OffsetDateTime dataFinal;
}
