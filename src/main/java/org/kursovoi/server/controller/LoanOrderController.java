package org.kursovoi.server.controller;

import lombok.RequiredArgsConstructor;
import org.kursovoi.server.dto.CreateLoanOrderDto;
import org.kursovoi.server.dto.LoanOrderDto;
import org.kursovoi.server.dto.UpdateStatusDto;
import org.kursovoi.server.dto.UpdateSumDto;
import org.kursovoi.server.service.LoanOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/loanOrders")
@RequiredArgsConstructor
public class LoanOrderController {

    private final LoanOrderService service;

    @GetMapping
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<List<LoanOrderDto>> findAllLoans() {
        var loanOrders = service.findAllLoans();
        return new ResponseEntity<>(loanOrders, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<LoanOrderDto> findSpecificLoanOrder(@PathVariable long id) {
        var loanOrder = service.findSpecificLoanOrder(id);
        return new ResponseEntity<>(loanOrder, HttpStatus.OK);
    }

    @GetMapping("/pending")
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<List<LoanOrderDto>> findAllPendingLoans() {
        var loanOrders = service.findAllPendingLoans();
        return new ResponseEntity<>(loanOrders, HttpStatus.OK);
    }

    @PutMapping
    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<?> updateStatus(@RequestBody UpdateStatusDto newStatus) {
        service.updateStatus(newStatus);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PatchMapping("/sum")
    @RolesAllowed({"ROLE_USER"})
    public ResponseEntity<?> updateSum(UpdateSumDto dto) {
        service.updateSum(dto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping
    @RolesAllowed({"ROLE_USER"})
    public ResponseEntity<?> createNewLoanOrder(CreateLoanOrderDto dto) {
        service.createNewLoanOrder(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }



}
