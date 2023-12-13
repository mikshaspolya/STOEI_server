package org.kursovoi.server.util.mapper;

import org.kursovoi.server.dto.DepositOrderDto;
import org.kursovoi.server.model.DepositOrder;
import org.kursovoi.server.model.constant.DepositOrderStatus;
import org.kursovoi.server.model.constant.Status;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public abstract class DepositOrderMapper {

    @Named("getStatusToString")
    public String getStatusToString(DepositOrderStatus status) {
        return status.name();
    }

    @Mapping(target = "dateOfIssue", source = "dateOfIssue", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "status", source = "status", qualifiedByName = "getStatusToString")
    @Mapping(target = "idDeposit", source = "deposit.id")
    @Mapping(target = "idUser", source = "user.id")
    @Mapping(target = "dateOfEnd", source = "dateOfEnd", dateFormat = "yyyy-MM-dd")
    public abstract DepositOrderDto map(DepositOrder model);
}
