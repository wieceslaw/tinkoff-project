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
    private final static String notImplementedResponse = "Error handler is not implemented yet";
    private final static String notImplementedCode = "500";

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<ApiErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(
                new ApiErrorResponse(
                        "Wrong arguments input",
                        "input-error",
                        ex.toString(),
                        ex.getMessage(),
                        Arrays.stream(ex.getStackTrace()).map(Objects::toString).toList()
                ),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler
    protected ResponseEntity<ApiErrorResponse> handleNullPointerException(Exception ex) {
        return new ResponseEntity<>(
                new ApiErrorResponse(
                        notImplementedResponse,
                        notImplementedCode,
                        ex.toString(),
                        ex.getMessage(),
                        Arrays.stream(ex.getStackTrace()).map(Objects::toString).toList()
                ),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
