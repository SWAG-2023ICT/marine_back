package swag.marine.common.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import swag.marine.common.error.CommonErrorCode;
import swag.marine.common.model.ErrorResponse;


@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponse> notFoundHandler(Exception exception){
        ErrorResponse response = new ErrorResponse(CommonErrorCode.Not_Found,exception);
        return ResponseEntity.status(response.getCode()).body(response);
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> serverErrorHandler(Exception exception){
        ErrorResponse response = new ErrorResponse(CommonErrorCode.Server_Error,exception);
        return ResponseEntity.status(response.getCode()).body(response);
    }
}