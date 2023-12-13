package org.kursovoi.server.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.kursovoi.server.dto.LoanDto;
import org.kursovoi.server.model.Loan;
import org.kursovoi.server.repository.LoanRepository;
import org.kursovoi.server.util.exception.ModelNotFoundException;
import org.kursovoi.server.util.mapper.LoanMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;
    private final LoanMapper mapper;

    @Transactional
    public List<LoanDto> getAllLoans() {
        return loanRepository.findAll().stream().map(mapper::map).collect(Collectors.toList());
    }

    @Transactional
    public LoanDto findSpecificLoanDto(long id) {
        return mapper.map(findSpecificLoan(id));
    }

    @Transactional
    public void saveLoan(LoanDto dto) {
        loanRepository.save(mapper.map(dto));
    }

    @Transactional
    public void deleteLoan(long id) {
        loanRepository.delete(findSpecificLoan(id));
    }

    Loan findSpecificLoan(long id) {
        return loanRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Loan with id: " + id + " - not found"));
    }
}
