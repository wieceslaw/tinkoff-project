package ru.tinkoff.edu.java.scrapper.controller.dto;

import jakarta.validation.constraints.NotBlank;

import java.net.URI;

public record RemoveLinkRequest(@NotBlank String link) {
}
