package org.kursovoi.server.util.mapper;

import org.kursovoi.server.dto.LoanDto;
import org.kursovoi.server.model.Loan;
import org.kursovoi.server.model.constant.Currency;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public abstract class LoanMapper {

    @Named("getCurrencyToString")
    public String getCurrencyToString(Currency currency) {
        return currency.name();
    }

    @Named("getCurrencyToEnum")
    public Currency getCurrencyToEnum(String currency) {
        return Currency.valueOf(currency);
    }

    @Mapping(target = "currency", source = "currency", qualifiedByName = "getCurrencyToString")
    public abstract LoanDto map(Loan loan);

    @Mapping(target = "currency", source = "currency", qualifiedByName = "getCurrencyToEnum")
    public abstract Loan map(LoanDto loan);
}
