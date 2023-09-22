package br.com.vinicius.bankapi.domain.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.JsonMappingException.Reference;

import br.com.vinicius.bankapi.domain.exception.EntidadeNaoEncontradaException;
import br.com.vinicius.bankapi.domain.exception.NegocioException;
import lombok.extern.log4j.Log4j2;

@Log4j2
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{

	public static final String MSG_ERRO_INTERNO = "Erro interno, se o problema persistir contate o administrador do sistema.";
	
	@Autowired
	MessageSource messageSource;
	
	private String joinPath(List<Reference> references) {
		return references.stream().map(ref -> ref.getFieldName())
				.collect(Collectors.joining("."));
	}
	
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex, WebRequest request){
		HttpStatusCode status = HttpStatusCode.valueOf(404);
		ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
		
		String detail = ex.getMessage();
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(detail)
				.build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleExceptionGenerica(Exception ex, WebRequest request){
		log.error(ex.getMessage(), ex);
		HttpStatusCode status = HttpStatusCode.valueOf(500);
		ProblemType problemType = ProblemType.ERRO_DE_SISTEMA;
		
		String detail = MSG_ERRO_INTERNO;
		ex.printStackTrace();
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(detail)
				.build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> handleNegocioException(NegocioException ex, WebRequest request){
		
		HttpStatusCode status = HttpStatusCode.valueOf(400);
		ProblemType problemType = ProblemType.ERRO_DE_NEGOCIO;
		String detail = ex.getMessage();
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(detail)
				.build();
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request); 
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatusCode status,
			WebRequest request){
		if(body == null) {
			body = Problem.builder().timestamp(OffsetDateTime.now())
					.status(status.value())
					.title(HttpStatus.valueOf(status.value()).getReasonPhrase())
					.userMessage(MSG_ERRO_INTERNO).build();
		}else if(body instanceof String) {
			body = Problem.builder().timestamp(OffsetDateTime.now())
					.status(status.value())
					.title((String) body).userMessage(MSG_ERRO_INTERNO).build();
		}
		
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
	private Problem.ProblemBuilder createProblemBuilder(HttpStatusCode status, ProblemType problemType, String detail){
		return Problem.builder()
				.status(status.value())
				.timestamp(OffsetDateTime.now())
				.type(problemType.getUri())
				.title(problemType.getTitulo())
				.detail(detail);
	}
	
}
