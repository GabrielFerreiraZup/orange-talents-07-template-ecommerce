package br.com.zup.mercadolivre.config.validacao;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@RestControllerAdvice
public class ErroHandlerValidation {


    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErroMensagemDto> handle(MethodArgumentNotValidException exception){
        List<ErroMensagemDto> errosDto = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        for (FieldError erro: fieldErrors) {
            String mensagem = erro.getDefaultMessage();
            LocalDateTime now = LocalDateTime.now();
            errosDto.add(new ErroMensagemDto(mensagem,erro.getField(),now));
        }
        return errosDto;

    }


}
