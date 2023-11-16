package com.karan.CarRentalService.Error;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseStatus
public class Handler extends ResponseEntityExceptionHandler
{
    @ExceptionHandler(CarNotFoundException.class)
    public ResponseEntity<ErrorClass> carNotFoundException(CarNotFoundException exception)
    {
        ErrorClass errorClass = new ErrorClass(HttpStatus.NOT_FOUND,exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorClass);
    }
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorClass> customerNotFoundException(CustomerNotFoundException exception)
    {
        ErrorClass errorClass = new ErrorClass(HttpStatus.NOT_FOUND,exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorClass);
    }
    @ExceptionHandler(CarTakenException.class)
    public ResponseEntity<ErrorClass> carTakenException(CarTakenException exception)
    {
        ErrorClass errorClass = new ErrorClass(HttpStatus.BAD_REQUEST,exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorClass);
    }
    @ExceptionHandler(TransactionFailedException.class)
    public ResponseEntity<ErrorClass> transactionFailedException(TransactionFailedException exception)
    {
        ErrorClass errorClass = new ErrorClass(HttpStatus.PAYMENT_REQUIRED,exception.getMessage());
        return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED).body(errorClass);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorClass> handleValidationException(ConstraintViolationException exception)
    {
        ErrorClass errorClass = new ErrorClass(HttpStatus.BAD_REQUEST,"PARAMETER VALIDATION FAILED!");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorClass);
    }
}
