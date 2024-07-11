package one.digitalinnovation.gof.handlers;

import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

/**
 * O uso de uma classe personalizada para exceções {@link ClienteInsertionException}
 * e a criação de um handler global para exceções {@link GlobalExceptionHandler}
 * são práticas recomendadas para o gerenciamento de erros. Isso pode ser associado
 * ao padrão <b>Chain of Responsibility</b>, onde diferentes tipos de exceções
 * são tratados por diferentes manipuladores.
 *
 * @author Kaio Abreu
 */

/*
  Observer Pattern - O Uso de anotações como @ControllerAdvice e @ExceptionHandler no Spring é uma implementação
  do padrão Observer. Aqui, o handler global observa as exceções
  lançadas pelos controladores e toma as ações apropriadas.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OpenApiResourceNotFoundException.class)
    public ResponseEntity<ResponseError> handleResourceNotFoundException(OpenApiResourceNotFoundException e) {
        ResponseError responseError = new ResponseError(
                new Date(),
                "error",
                HttpStatus.NOT_FOUND.value(),
                "Erro ao encontrar Cliente! " + e.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseError);
    }

    @ExceptionHandler(ClienteInsertionException.class)
    public ResponseEntity<ResponseError> handleClienteInsertionException(ClienteInsertionException e) {
       ResponseError responseError = new ResponseError(
               new Date(),
               "error",
               HttpStatus.BAD_REQUEST.value(),
               "Erro ao inserir Cliente: " + e.getMessage()
       );
       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseError> handleGeneralException(Exception e) {
        ResponseError responseError = new ResponseError(
                new Date(),
                "error",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro interno do servidor: " + e.getMessage()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseError);
    }
}
