package ru.tinkoff.edu.java.bot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.tinkoff.edu.java.bot.dto.controller.ApiErrorResponse;

import java.util.Arrays;
import java.util.Objects;

@RestControllerAdvice(
        basePackages = "ru.tinkoff.edu.java.bot.controller"
)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private final static String notImplementedResponse = "Error handler is not implemented yet";
    private final static String notImplementedCode = "400";

    @ExceptionHandler(value = {TelegramApiException.class})
    private ResponseEntity<ApiErrorResponse> handleTelegramApiException(TelegramApiException ex) {
        return new ResponseEntity<>(
                new ApiErrorResponse(
                        "Error happened while sending updates",
                        "tg-api",
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