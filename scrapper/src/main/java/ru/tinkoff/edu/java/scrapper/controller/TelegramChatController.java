package ru.tinkoff.edu.java.scrapper.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
public class TelegramChatController {
    @PostMapping(
            path = "/tg-chat/{id}"
    )
    public void create(@PathVariable("id") Long id) {
        // TODO: implement
    }

    @DeleteMapping(
            path = "/tg-chat/{id}"
    )
    public void delete(@PathVariable("id") Long id) {
        // TODO: implement
    }
}
