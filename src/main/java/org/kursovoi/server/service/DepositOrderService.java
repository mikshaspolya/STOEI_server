package org.kursovoi.server.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.kursovoi.server.dto.*;
import org.kursovoi.server.model.DepositOrder;
import org.kursovoi.server.model.User;
import org.kursovoi.server.model.constant.DepositOrderStatus;
import org.kursovoi.server.model.constant.OperationType;
import org.kursovoi.server.model.constant.Status;
import org.kursovoi.server.repository.DepositOrderRepository;
import org.kursovoi.server.util.exception.AccessDeniedException;
import org.kursovoi.server.util.exception.IncorrectStatusException;
import org.kursovoi.server.util.exception.ModelNotFoundException;
import org.kursovoi.server.util.keycloak.TokenUtil;
import org.kursovoi.server.util.keycloak.RoleMapping;
import org.kursovoi.server.util.mapper.DepositOrderMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
@RequiredArgsConstructor
public class DepositOrderService {

    private final DepositOrderRepository depositOrderRepository;
    private final DepositService depositService;
    private UserService userService;
    private final DepositOrderMapper mapper;
    private final TokenUtil tokenUtil;
    private final OperationService operationService;


    @Transactional
    public List<DepositOrderDto> findAllDepositOrders() {
        return depositOrderRepository.findAll().stream().map(mapper::map).collect(Collectors.toList());
    }

    @Transactional
    public List<DepositOrderDto> findDepositOrdersOfUser(User user) {
        return depositOrderRepository.findByUser(user).stream().map(mapper::map).collect(Collectors.toList());
    }

    @Transactional
    public List<DepositOrderDto> findAllPendingDeposits() {
        return depositOrderRepository
                .findByStatus(DepositOrderStatus.PENDING).stream().map(mapper::map).collect(Collectors.toList());
    }

    @Transactional
    public void updateStatus(UpdateStatusDto dto) {
        var depositOrder = getDepositOrder(dto.getId());
        depositOrder.setStatus(DepositOrderStatus.valueOf(dto.getNewStatus()));
        depositOrderRepository.save(depositOrder);
    }

    @Transactional
    public DepositOrderDto findDepositOrder(long id) {
        var depositOrder = mapper.map(getDepositOrder(id));
        if(tokenUtil.hasRole(RoleMapping.USER)
                && !userService.getUser(depositOrder.getIdUser()).getUuid()
                .equals(tokenUtil.getUUIDUser())) {
            throw new AccessDeniedException("Access denied for resource");
        }
        return depositOrder;
    }


    @Transactional
    public void createDepositOrder(CreateDepositDto dto) {
        var user = userService.getUser(dto.getUuidUser());
        var deposit = depositService.findSpecificDeposit(dto.getIdDeposit());
        var depositOrder = DepositOrder.builder()
                .deposit(deposit)
                .user(user)
                .dateOfIssue(LocalDate.now(ZoneId.of("UTC-3")))
                .sum(dto.getSum())
                .status(DepositOrderStatus.PENDING)
                .build();
        depositOrder.setDateOfEnd(depositOrder.getDateOfIssue().plusMonths(deposit.getMonthToExpire()));
        depositOrderRepository.save(depositOrder);
        logOperation(OperationDescription.NEW_DEPOSIT_ORDER, OperationType.ORDER, user.getId());
    }

    @Transactional
    public void updateSum(UpdateSumDto dto) {
        var depositOrder = getDepositOrder(dto.getIdEntity());
        if (!depositOrder.getStatus().equals(DepositOrderStatus.APPROVED) ||
                !depositOrder.getUser().getStatus().equals(Status.ACTIVE)) {
            throw new IncorrectStatusException("Forbidden due to status of user or order of deposit");
        }
        depositOrder.setSum(depositOrder.getSum() + dto.getSum());
        depositOrderRepository.save(depositOrder);
    }

    DepositOrder getDepositOrder(long id) {
        return depositOrderRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Deposit order with id: " + id + " - not found!"));
    }

    private void logOperation(OperationDescription description, OperationType type, long idUser) {
        operationService.createOperation(OperationDto.builder()
                .type(type.name())
                .description(description.getMessage())
                .idUser(idUser).build());
    }
}
