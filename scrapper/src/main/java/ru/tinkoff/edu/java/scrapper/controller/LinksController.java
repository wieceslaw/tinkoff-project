package ru.tinkoff.edu.java.scrapper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.edu.java.scrapper.controller.dto.AddLinkRequest;
import ru.tinkoff.edu.java.scrapper.controller.dto.LinkResponse;
import ru.tinkoff.edu.java.scrapper.controller.dto.ListLinksResponse;
import ru.tinkoff.edu.java.scrapper.controller.dto.RemoveLinkRequest;
import ru.tinkoff.edu.java.scrapper.service.StackOverflowService;

import java.util.ArrayList;

@Validated
@RequestMapping("/links")
@RestController
public class LinksController {
    @Autowired
    private StackOverflowService service;

    @PostMapping(
            path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public LinkResponse create(@PathVariable("id") Long id,
                               @RequestBody AddLinkRequest request) {
        // TODO: implement
        return new LinkResponse(1L, "cool.url.com/path");
    }


    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ListLinksResponse getAll(@PathVariable("id") Long id) {
        // TODO: implement
        System.out.println(service.fetchQuestion(9706688).block());
        return new ListLinksResponse(new ArrayList<>(), 0);
    }

    @DeleteMapping(
            path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public LinkResponse delete(@PathVariable("id") Long id,
                               @RequestBody RemoveLinkRequest request) {
        // TODO: implement
        return new LinkResponse(1L, "cool.url.com/path");
    }
}
