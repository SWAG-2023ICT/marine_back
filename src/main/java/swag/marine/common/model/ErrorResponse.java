package swag.marine.common.model;

import lombok.Getter;
import swag.marine.common.error.ErrorCode;

@Getter
public class ErrorResponse {
    private final int code;
    private final String message;
    private final String exceptionName;
    private final String exceptionMessage;

    public ErrorResponse(ErrorCode errorCode,Exception exception){
        this.code = errorCode.getStatus();
        this.message = errorCode.getMessage();
        this.exceptionName = exception.getClass().getSimpleName();
        this.exceptionMessage = exception.getMessage();

    }
}
