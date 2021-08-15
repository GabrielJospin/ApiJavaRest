package com.gvendas.gestaovendas.excecao;

import com.gvendas.gestaovendas.entidades.Categoria;
import org.hibernate.validator.constraints.Length;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class GestaoVendasExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String LENGTH = "Length";
    private static final String MIN = "Min";
    private static final String NOT_BLANK = "NotBlank";
    private static final String NOT_NULL = "NotNull";
    private static final String PATTERN = "Pattern";

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<Erro> erroList =  gerarListaDeErros(ex.getBindingResult());

        return handleExceptionInternal(ex,erroList,headers,HttpStatus.BAD_REQUEST,request);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex,WebRequest request){

        String msgUsuario = "Recurso não encontrado";
        String msgDesenvolvedor = ex.toString();
        List<Erro> erroList = Arrays.asList(new Erro(msgUsuario,msgDesenvolvedor));

        return handleExceptionInternal(ex,erroList,new HttpHeaders(),HttpStatus.NOT_FOUND,request);
    }

    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<Object> handleRegraNegocioException(RegraNegocioException ex,WebRequest request){

        String msgUsuario = ex.getMessage();
        String msgDesenvolvedor = ex.getMessage();
        List<Erro> erroList = Arrays.asList(new Erro(msgUsuario,msgDesenvolvedor));

        return handleExceptionInternal(ex,erroList,new HttpHeaders(),HttpStatus.BAD_REQUEST,request);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request){

        String msgUsuario = "Recurso não encontrado";
        String msgDesenvolvedor = ex.getMessage();
        List<Erro> erroList = Arrays.asList(new Erro(msgUsuario,msgDesenvolvedor));

        return handleExceptionInternal(ex,erroList,new HttpHeaders(),HttpStatus.BAD_REQUEST,request);
    }

    private List<Erro> gerarListaDeErros(BindingResult bindingResult) {
        List<Erro> erroList = new ArrayList<Erro>();

        bindingResult.getFieldErrors().forEach(fieldError -> {
            String msgUsuario = tratarMensagemDeErroParaUsuario(fieldError);
            String msgDesenvolvedor = fieldError.toString();
            erroList.add(new Erro(msgUsuario,msgDesenvolvedor));
        });

        return erroList;
    }

    private String tratarMensagemDeErroParaUsuario(FieldError fieldError) {
        if(fieldError.getCode().equals(NOT_BLANK) ||
                fieldError.getCode().equals(NOT_NULL)){
            return fieldError.getDefaultMessage().concat(" é obrigatório");
        }
        if(fieldError.getCode().equals(MIN)){
            String error = String.format("O valor deve ser maior ou igual a %d",fieldError.getArguments()[1]);

            return fieldError.getDefaultMessage().concat(error);
        }
        if(fieldError.getCode().equals(LENGTH)){
            String error = String.format(" deve ter entre %s e %s caracteres", Categoria.MIN_CHAR,Categoria.MAX_CHAR);

            return fieldError.getDefaultMessage().concat(error);
        }
        if(fieldError.getCode().equals(PATTERN)){
            String error = String.format("formato inválido");

            return fieldError.getDefaultMessage().concat(error);
        }

        return fieldError.toString();
    }
}
