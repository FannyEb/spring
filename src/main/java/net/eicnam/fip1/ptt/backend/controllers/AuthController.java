package net.eicnam.fip1.ptt.backend.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

import net.eicnam.fip1.ptt.backend.bodyrequest.LoginBody;
import net.eicnam.fip1.ptt.backend.services.AuthService;

import static net.eicnam.fip1.ptt.backend.utils.Namespace.JSON;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api")
public class AuthController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService;

    @PostMapping(value = "/login", produces = JSON)
    public String postLogin(@RequestBody LoginBody loginBody) {
        LOGGER.info("Request incoming: login");

        final String token = authService.login(loginBody.getEmail(), loginBody.getPassword());

        return "{\"token\": \"" + token + "\"}";
    }
}
