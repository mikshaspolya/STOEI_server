package org.kursovoi.server.controller;

import lombok.RequiredArgsConstructor;
import org.kursovoi.server.dto.DepositDto;
import org.kursovoi.server.service.DepositService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/deposits")
@RequiredArgsConstructor
public class DepositController {

    private final DepositService service;

    @GetMapping
    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<List<DepositDto>> findAllDeposits() {
        var deposits = service.findAllDeposits();
        return new ResponseEntity<>(deposits, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<DepositDto> findSpecificDeposit(@PathVariable long id) {
        var deposit = service.findSpecificDepositDto(id);
        return new ResponseEntity<>(deposit, HttpStatus.OK);
    }

    @PostMapping
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<?> createDeposit(@RequestBody DepositDto dto) {
        service.createDeposit(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<?> deleteDeposit(@PathVariable long id) {
        service.deleteDeposit(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
