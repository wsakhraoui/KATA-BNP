package fr.bnp.bank.si.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler({InvalidRequestPersonException.class})
    public ResponseEntity<String> handleCustomerNotFoundException(Exception re) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(re.getMessage());
    }

    
}
