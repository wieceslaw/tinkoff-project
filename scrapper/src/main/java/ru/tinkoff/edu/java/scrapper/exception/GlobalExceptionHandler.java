package ru.tinkoff.edu.java.scrapper.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;
import ru.tinkoff.edu.java.scrapper.controller.dto.ApiErrorResponse;

import java.util.Arrays;
import java.util.Objects;

@RestControllerAdvice(
        basePackages = "ru.tinkoff.edu.java.scrapper.controller"
)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler
    protected ResponseEntity<ApiErrorResponse> handleNullPointerException(Exception ex) {
        // TODO: implement
        return new ResponseEntity<>(
                new ApiErrorResponse(
                    "Not implemented handler",
                    "400",
                    ex.toString(),
                    ex.getMessage(),
                    Arrays.stream(ex.getStackTrace()).map(Objects::toString).toList()
                ),
                HttpStatus.I_AM_A_TEAPOT
        );
    }
}
