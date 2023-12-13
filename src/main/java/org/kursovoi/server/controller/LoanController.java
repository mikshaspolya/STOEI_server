package org.kursovoi.server.controller;

import lombok.RequiredArgsConstructor;
import org.kursovoi.server.dto.LoanDto;
import org.kursovoi.server.service.LoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/loans")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService service;

    @GetMapping
    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<List<LoanDto>> getAllLoans() {
        var loans = service.getAllLoans();
        return new ResponseEntity<>(loans, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<LoanDto> findSpecificLoanDto(@PathVariable long id) {
        var loan = service.findSpecificLoanDto(id);
        return new ResponseEntity<>(loan, HttpStatus.OK);
    }

    @PostMapping
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<?> saveLoan(@RequestBody LoanDto dto) {
        service.saveLoan(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<?> deleteLoan(@PathVariable long id) {
        service.deleteLoan(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


}
