package org.kursovoi.server.util.mapper;

import org.kursovoi.server.dto.AccountDto;
import org.kursovoi.server.dto.CreateAccountDto;
import org.kursovoi.server.model.Account;
import org.kursovoi.server.model.constant.Currency;
import org.kursovoi.server.model.constant.Status;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;

@Mapper(componentModel = "spring")
@Component
public abstract class AccountMapper {

    @Mapping(target = "dateOfIssue", source = "dateOfIssue", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "currency", source = "currency", qualifiedByName = "getCurrencyToString")
    @Mapping(target = "status", source = "status", qualifiedByName = "getStatusToString")
    @Mapping(target = "holderId", source = "holder.id")
    public abstract AccountDto map(Account account);

    @Mapping(target = "dateOfIssue", source = "dateOfIssue", qualifiedByName = "getDateOfIssue")
    @Mapping(target = "currency", source = "currency", qualifiedByName = "getCurrencyToEnum")
    public abstract Account map(CreateAccountDto dto);

    @Named("getCurrencyToString")
    public String getCurrencyToString(Currency currency) {
        return currency.name();
    }

    @Named("getStatusToString")
    public String getStatusToString(Status status) {
        return status.name();
    }

    @Named("getCurrencyToEnum")
    public Currency getCurrencyToEnum(String currency) {
        return Currency.valueOf(currency);
    }

    @Named("getDateOfIssue")
    public LocalDate getDateOfIssue(String dateOfIssue) {
        return LocalDate.now(ZoneId.of("UTC+3"));
    }
}
