package swag.marine.common.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import swag.marine.common.error.CommonErrorCode;
import swag.marine.common.model.ErrorResponse;

import java.sql.SQLException;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponse> notFoundHandler(Exception exception){
        ErrorResponse response = new ErrorResponse(CommonErrorCode.NOT_FOUND,exception);
        exception.printStackTrace();
        return ResponseEntity.status(response.getCode()).body(response);
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> serverErrorHandler(Exception exception){
        ErrorResponse response = new ErrorResponse(CommonErrorCode.Server_ERROR,exception);
        exception.printStackTrace();
        return ResponseEntity.status(response.getCode()).body(response);
    }
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ErrorResponse> sqlExceptionHandler(SQLException exception){
        ErrorResponse response = new ErrorResponse(CommonErrorCode.SQL_ERROR,exception);
        exception.printStackTrace();
        return ResponseEntity.status(response.getCode()).body(response);
    }
}