package br.com.marceloguedes.todolist.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice //Usado para definir classes globais no momento de tratamento de exceptions, ou seja toda exception que houver vai passar por esse controller
public class ExceptionHandlerController {
    
    @ExceptionHandler(HttpMessageNotReadableException.class) //Annotation que indica qual é o tipo exception que ativa a execução do método
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        //Pegando a mensagem que é jogada para o handler
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMostSpecificCause().getMessage());
    }
}
