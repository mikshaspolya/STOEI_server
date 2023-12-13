package org.kursovoi.server.util.mapper;

import org.kursovoi.server.dto.OperationDto;
import org.kursovoi.server.model.Operation;
import org.kursovoi.server.model.constant.OperationType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Mapper(componentModel = "spring")
public abstract class OperationMapper {

    @Mapping(target = "type", source = "type", qualifiedByName = "getTypeToString")
    @Mapping(target = "idUser", source = "user.id")
    @Mapping(target = "timestamp", source = "timestamp", qualifiedByName = "getTimeToString")
    public abstract OperationDto map(Operation model);

    @Mapping(target = "type", source = "type", qualifiedByName = "getTypeToEnum")
    public abstract Operation map(OperationDto dto);

    @Named("getTypeToString")
    public String getTypeToString(OperationType model) {
        return model.name();
    }

    @Named("getTypeToEnum")
    public OperationType getTypeToEnum(String dto) {
        return OperationType.valueOf(dto);
    }

    @Named("getTimeToString")
    public String getTimeToString(LocalDateTime timestamp) {
        return timestamp.format(DateTimeFormatter.RFC_1123_DATE_TIME);
    }
}
