package org.kursovoi.server.util.mapper;

import org.kursovoi.server.dto.CardDto;
import org.kursovoi.server.dto.CreateCardDto;
import org.kursovoi.server.model.Card;
import org.kursovoi.server.model.constant.CardIssuer;
import org.kursovoi.server.model.constant.CardType;
import org.kursovoi.server.model.constant.Status;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;

@Mapper(componentModel = "spring")
@Component
public abstract class CardMapper {

    @Mapping(target = "dateOfExpire", source = "dateOfExpire", dateFormat = "MM/yyyy")
    @Mapping(target = "status", source = "status", qualifiedByName = "getStatusToString")
    @Mapping(target = "cardIssuer", source = "cardIssuer", qualifiedByName = "getCardIssuerToString")
    @Mapping(target = "type", source = "type", qualifiedByName = "getTypeToString")
    public abstract CardDto map(Card card);

    @Mapping(target = "dateOfExpire", source = "idAccount", qualifiedByName = "getDateOfExpire")
    @Mapping(target = "cardIssuer", source = "cardIssuer",qualifiedByName = "getCardIssuerToEnum")
    @Mapping(target = "type", source = "type", qualifiedByName = "getTypeToEnum")
    public abstract Card map(CreateCardDto card);

    @Named("getStatusToString")
    public String getStatusToString(Status status) {
        return status.name();
    }

    @Named("getCardIssuerToString")
    public String getCardIssuerToString(CardIssuer issuer) {
        return issuer.name();
    }

    @Named("getTypeToString")
    public String getTypeToString(CardType type) {
        return type.name();
    }

    @Named("getDateOfExpire")
    public LocalDate getDateOfExpire(long idAccount) {
        return LocalDate.now(ZoneId.of("UTC+3")).plusYears(4);
    }

    @Named("getTypeToEnum")
    public CardType getTypeToEnum(String dto) {
        return CardType.valueOf(dto);
    }

    @Named("getCardIssuerToEnum")
    public CardIssuer getCardIssuerToEnum(String dto) {
        return CardIssuer.valueOf(dto);
    }
}
