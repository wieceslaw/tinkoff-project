package ru.tinkoff.edu.java.scrapper.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;
import ru.tinkoff.edu.java.scrapper.dto.controller.ApiErrorResponse;

import java.util.Arrays;
import java.util.Objects;

@RestControllerAdvice(
        basePackages = "ru.tinkoff.edu.java.scrapper.controller"
)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private static final String INPUT_ERROR_CODE = "tg-api";
    private static final String SERVER_ERROR_CODE = "server";

    private static final String INPUT_ERROR_DESCRIPTION = "Error happened while sending updates";
    private static final String SERVER_ERROR_DESCRIPTION = "Internal error";

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<ApiErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        return buildError(
                ex,
                HttpStatus.BAD_REQUEST,
                INPUT_ERROR_CODE,
                INPUT_ERROR_DESCRIPTION
        );
    }

    @ExceptionHandler
    protected ResponseEntity<ApiErrorResponse> handleOtherErrors(Exception ex) {
        return buildError(
                ex,
                HttpStatus.INTERNAL_SERVER_ERROR,
                SERVER_ERROR_CODE,
                SERVER_ERROR_DESCRIPTION
        );
    }

    private ResponseEntity<ApiErrorResponse> buildError(
            Exception exception,
            HttpStatus httpStatus,
            String code,
            String description
    ) {
        return new ResponseEntity<>(
                new ApiErrorResponse(
                        description,
                        code,
                        exception.toString(),
                        exception.getMessage(),
                        Arrays.stream(exception.getStackTrace())
                                .map(Objects::toString)
                                .toList()
                ),
                httpStatus
        );
    }
}
