package org.opengroup.osdu.unitservice.middleware;

import org.opengroup.osdu.unitservice.util.AppError;
import org.opengroup.osdu.unitservice.util.AppException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionMapper extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AppException.class)
    protected ResponseEntity<AppError> handleAppException(AppException e) {
        return this.getErrorResponse(e);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<AppError> handleGeneralException(Exception e) {
        return this.getErrorResponse(
                new AppException(INTERNAL_SERVER_ERROR.value(), "Server error.",
                        "An unknown error has occurred."));
    }

    private ResponseEntity<AppError> getErrorResponse(AppException e) {
        logger.error(e);
        AppError appError = e.getError();
        return new ResponseEntity<>(appError, resolve(appError.getCode()));
    }
}
