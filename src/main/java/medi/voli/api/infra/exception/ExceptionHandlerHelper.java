package medi.voli.api.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.List;

@RestControllerAdvice
public class ExceptionHandlerHelper {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handle404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handle400(MethodArgumentNotValidException ex){
        List<FieldError> erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosDaException::new).toList());
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity handle500(SQLException ex){
        return ResponseEntity.internalServerError().build();
    }

    private record DadosDaException(String field, String mensagem){

        public DadosDaException(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }

    }
}
