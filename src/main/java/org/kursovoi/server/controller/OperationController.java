package org.kursovoi.server.controller;

import lombok.RequiredArgsConstructor;
import org.kursovoi.server.dto.OperationDto;
import org.kursovoi.server.service.OperationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/operations")
@RequiredArgsConstructor
public class OperationController {

    private final OperationService service;

    @GetMapping
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<List<OperationDto>> getAllOperations(
            @RequestParam(required = false) String filterByType,
            @RequestParam(required = false) String filterByStartDate,
            @RequestParam(required = false) String filterByEndDate) {
        var operations = service.getAllOperations(filterByType, filterByStartDate, filterByEndDate);
        return new ResponseEntity<>(operations, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<OperationDto> getSpecificOperationDto(@PathVariable long id) {
        var operation = service.getSpecificOperationDto(id);
        return new ResponseEntity<>(operation, HttpStatus.OK);
    }

    @PostMapping
    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<?> createOperation(@RequestBody OperationDto dto) {
        service.createOperation(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
