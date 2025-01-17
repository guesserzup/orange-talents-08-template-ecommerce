package br.com.zupacademy.gabriel.ecommerce.validacao;

import org.springframework.beans.NotReadablePropertyException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public RetornaErro validacao (MethodArgumentNotValidException exception){

        List<ObjectError> globalErrors = exception.getBindingResult().getGlobalErrors();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        RetornaErro erros = new RetornaErro();

        globalErrors.forEach(erro -> erros.AddError(erro.getDefaultMessage()));
        fieldErrors.forEach(erro -> erros.addErrorField(erro.getField(),erro.getRejectedValue(),erro.getDefaultMessage()));

        return erros;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotReadablePropertyException.class)
    public RetornaErro validacao2(NotReadablePropertyException exception){
        RetornaErro erros = new RetornaErro();

        erros.AddError(exception.getLocalizedMessage());

        return erros;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RegraNegocioException.class)
    public RetornaErro validacaoRegraDeNegocio(RegraNegocioException erro){
        return erro.getReturnError();
    }
}
