package org.kursovoi.server.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.kursovoi.server.dto.*;
import org.kursovoi.server.model.LoanOrder;
import org.kursovoi.server.model.User;
import org.kursovoi.server.model.constant.LoanOrderStatus;
import org.kursovoi.server.model.constant.OperationType;
import org.kursovoi.server.model.constant.Status;
import org.kursovoi.server.repository.LoanOrderRepository;
import org.kursovoi.server.util.exception.AccessDeniedException;
import org.kursovoi.server.util.exception.IncorrectStatusException;
import org.kursovoi.server.util.exception.ModelNotFoundException;
import org.kursovoi.server.util.exception.TransactionSumTooLargeException;
import org.kursovoi.server.util.keycloak.TokenUtil;
import org.kursovoi.server.util.keycloak.RoleMapping;
import org.kursovoi.server.util.mapper.LoanOrderMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Data
public class LoanOrderService {

    private final LoanOrderRepository loanOrderRepository;
    private final LoanService loanService;
    private UserService userService;
    private final LoanOrderMapper mapper;
    private final TokenUtil tokenUtil;
    private final OperationService operationService;

    @Transactional
    public List<LoanOrderDto> findAllLoans() {
        return loanOrderRepository.findAll().stream().map(mapper::map).collect(Collectors.toList());
    }

    @Transactional
    public List<LoanOrderDto> findLoansOrdersOfUser(User user) {
        return loanOrderRepository.findByUser(user).stream().map(mapper::map).collect(Collectors.toList());
    }

    @Transactional
    public List<LoanOrderDto> findAllPendingLoans() {
        return loanOrderRepository
                .findByStatus(LoanOrderStatus.PENDING).stream().map(mapper::map).collect(Collectors.toList());
    }

    @Transactional
    public LoanOrderDto findSpecificLoanOrder(long id) {
        var loanOrder = mapper.map(findLoanOrder(id));
        if(tokenUtil.hasRole(RoleMapping.USER)
                && !userService.getUser(loanOrder.getIdUser()).getUuid()
                .equals(tokenUtil.getUUIDUser())) {
            throw new AccessDeniedException("Access denied for resource");
        }
        return loanOrder;
    }

    @Transactional
    public void updateStatus(UpdateStatusDto newStatus) {
        var loanOrder = findLoanOrder(newStatus.getId());
        loanOrder.setStatus(LoanOrderStatus.valueOf(newStatus.getNewStatus()));
        loanOrderRepository.save(loanOrder);
    }

    @Transactional
    public void createNewLoanOrder(CreateLoanOrderDto dto) {
        var loan = loanService.findSpecificLoan(dto.getIdLoan());
        var user = userService.getUser(dto.getUuidUser());
        var newLoanOrder = LoanOrder.builder()
                .loan(loan)
                .user(user)
                .dateOfIssue(LocalDate.now(ZoneId.of("UTC+3")))
                .status(LoanOrderStatus.PENDING)
                .sum(dto.getSum())
                .build();
        newLoanOrder.setDateOfEnd(newLoanOrder.getDateOfIssue().plusMonths(loan.getMonthsToReturn()));
        loanOrderRepository.save(newLoanOrder);
        logOperation(OperationDescription.NEW_LOAN_ORDER, OperationType.ORDER, user.getId());
    }

    @Transactional
    public void updateSum(UpdateSumDto dto) {
        var loanOrder = findLoanOrder(dto.getIdEntity());
        if (!loanOrder.getStatus().equals(LoanOrderStatus.APPROVED) ||
                !loanOrder.getUser().getStatus().equals(Status.ACTIVE)) {
            throw new IncorrectStatusException("Forbidden due to status of user or order of deposit");
        }
        if (loanOrder.getSum() < dto.getSum()) {
            throw new TransactionSumTooLargeException("Balance of loan: " + loanOrder.getSum() + " - is less than transaction");
        }
        loanOrder.setSum(loanOrder.getSum() - dto.getSum());
        loanOrderRepository.save(loanOrder);
    }

    LoanOrder findLoanOrder(long id) {
        return loanOrderRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Loan order with id: " + id + " - not found!"));
    }

    private void logOperation(OperationDescription description, OperationType type, long idUser) {
        operationService.createOperation(OperationDto.builder()
                .type(type.name())
                .description(description.getMessage())
                .idUser(idUser).build());
    }

}
