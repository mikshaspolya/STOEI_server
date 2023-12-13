package org.kursovoi.server.controller;

import lombok.RequiredArgsConstructor;
import org.kursovoi.server.dto.AccountDto;
import org.kursovoi.server.dto.CreateAccountDto;
import org.kursovoi.server.dto.TransactionDto;
import org.kursovoi.server.dto.UpdateStatusDto;
import org.kursovoi.server.service.AccountService;
import org.kursovoi.server.util.keycloak.RoleMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService service;

    @GetMapping
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        var accounts = service.getAllAccounts();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<AccountDto> getSpecificAccount(@PathVariable long id) {
        var account = service.getSpecificAccountDto(id);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @PostMapping
    @RolesAllowed({"ROLE_USER"})
    public ResponseEntity<?> createAccount(@RequestBody CreateAccountDto dto) {
        service.createAccount(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/transaction")
    @RolesAllowed({"ROLE_USER"})
    public ResponseEntity<?> makeTransaction(@RequestBody TransactionDto dto) {
        service.makeTransaction(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed({"ROLE_USER"})
    public ResponseEntity<?> deleteAccount(@PathVariable long id) {
        service.deleteAccount(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<?> updateStatusOfAccount(@RequestBody UpdateStatusDto dto) {
        service.updateStatusOfAccount(dto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
