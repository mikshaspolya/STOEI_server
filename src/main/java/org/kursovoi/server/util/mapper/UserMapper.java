package org.kursovoi.server.util.mapper;

import org.kursovoi.server.dto.CreateUserDto;
import org.kursovoi.server.dto.UserDto;
import org.kursovoi.server.model.Role;
import org.kursovoi.server.model.User;
import org.kursovoi.server.model.constant.Status;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Mapping(target = "dateOfBirth", source = "dateOfBirth", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "status", source = "status", qualifiedByName = "getStatus")
    @Mapping(target = "role", source = "role", qualifiedByName = "getRole")
    public abstract UserDto map(User user);

    @Mapping(target = "dateOfBirth", source = "dateOfBirth", qualifiedByName = "getDateToLocalDate")
    public abstract User map(CreateUserDto dto);

    @Named("getRole")
    public String getRole(Role role) {
        return role.getRoleName();
    }

    @Named("getStatus")
    public String getStatus(Status status) {
        return status.name();
    }

    @Named("getDateToLocalDate")
    public LocalDate getDateToLocalDate(String dto) {
        return LocalDate.parse(dto, DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
