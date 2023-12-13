package org.kursovoi.server.controller;

import lombok.RequiredArgsConstructor;
import org.kursovoi.server.dto.CreateDepositDto;
import org.kursovoi.server.dto.DepositOrderDto;
import org.kursovoi.server.dto.UpdateStatusDto;
import org.kursovoi.server.dto.UpdateSumDto;
import org.kursovoi.server.service.DepositOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/depositOrders")
@RequiredArgsConstructor
public class DepositOrderController {

    private final DepositOrderService service;

    @GetMapping
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<List<DepositOrderDto>> findAllDepositOrders() {
        var depositOrders = service.findAllDepositOrders();
        return new ResponseEntity<>(depositOrders, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<DepositOrderDto> findSpecificOrder(@PathVariable long id) {
        var depositOrder = service.findDepositOrder(id);
        return new ResponseEntity<>(depositOrder, HttpStatus.OK);
    }

    @GetMapping("/pending")
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<List<DepositOrderDto>> findAllPendingDeposits() {
        var depositOrders = service.findAllPendingDeposits();
        return new ResponseEntity<>(depositOrders, HttpStatus.OK);
    }

    @PutMapping
    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<?> updateStatus(UpdateStatusDto dto) {
        service.updateStatus(dto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping
    @RolesAllowed({"ROLE_USER"})
    public ResponseEntity<?> createDepositOrder(CreateDepositDto dto) {
        service.createDepositOrder(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/sum")
    @RolesAllowed({"ROLE_USER"})
    public ResponseEntity<?> updateSum(UpdateSumDto dto) {
        service.updateSum(dto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
