package org.kursovoi.server.util.mapper;

import org.kursovoi.server.dto.LoanOrderDto;
import org.kursovoi.server.model.LoanOrder;
import org.kursovoi.server.model.constant.LoanOrderStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public abstract class LoanOrderMapper {

    @Mapping(target = "dateOfIssue", source = "dateOfIssue", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "status", source = "status", qualifiedByName = "getStatusToString")
    @Mapping(target = "idLoan", source = "loan.id")
    @Mapping(target = "idUser", source = "user.id")
    @Mapping(target = "dateOfEnd", source = "dateOfEnd", dateFormat = "yyyy-MM-dd")
    public abstract LoanOrderDto map(LoanOrder model);

    @Named("getStatusToString")
    public String getStatusToString(LoanOrderStatus model) {
        return model.name();
    }
}
