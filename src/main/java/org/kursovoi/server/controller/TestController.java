package org.kursovoi.server.controller;

import lombok.RequiredArgsConstructor;
import org.kursovoi.server.dto.mail.EmailMessage;
import org.kursovoi.server.service.EmailSenderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final EmailSenderService service;

    @PostMapping
    @PermitAll
    public void test() {
        service.sendEmail(EmailMessage.builder().body("hello").sub("TEST").to("kostyashtc@gmail.com").build());
    }
}
