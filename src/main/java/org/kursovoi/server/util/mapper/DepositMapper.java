package org.kursovoi.server.util.mapper;

import org.kursovoi.server.dto.DepositDto;
import org.kursovoi.server.model.Deposit;
import org.kursovoi.server.model.constant.Currency;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public abstract class DepositMapper {

    @Mapping(target = "currency", source = "currency", qualifiedByName = "getCurrencyToString")
    @Mapping(target = "monthsToReturn", source = "monthToExpire")
    public abstract DepositDto map(Deposit deposit);

    @Mapping(target = "currency", source = "currency", qualifiedByName = "getCurrencyToEnum")
    @Mapping(target = "monthToExpire", source = "monthsToReturn")
    public abstract Deposit map(DepositDto deposit);

    @Named("getCurrencyToString")
    public static String getCurrencyToString(Currency deposit) {
        return deposit.name();
    }

    @Named("getCurrencyToEnum")
    public static Currency getCurrencyToEnum(String dto) {
        return Currency.valueOf(dto);
    }
}
