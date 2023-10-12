package swag.marine.common.model;

import lombok.Getter;
import swag.marine.common.error.ErrorCode;

@Getter
public class ErrorResponse {
    private final int code;
    private final String message;
    private final String exception;

    public ErrorResponse(ErrorCode errorCode,Exception exception){
        this.code = errorCode.getStatus();
        this.message = errorCode.getMessage();
        this.exception = exception.getMessage();

    }
}
