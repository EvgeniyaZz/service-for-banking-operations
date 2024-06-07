package com.github.EvgeniyaZz.bank.web;

import com.github.EvgeniyaZz.bank.dto.SignUpRequest;
import com.github.EvgeniyaZz.bank.model.User;
import com.github.EvgeniyaZz.bank.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static com.github.EvgeniyaZz.bank.util.ValidationUtil.checkNew;

@Tag(name = "Регистрация")
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = RegisterController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RegisterController {

    static final String REST_URL = "/api/register";

    private final UserService service;

    @Operation(summary = "Регистрация пользователя")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> createWithLocation(@Valid @RequestBody SignUpRequest request) {
        log.info("register {}", request);
        checkNew(request);
        User created = service.save(request);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL).build().toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}