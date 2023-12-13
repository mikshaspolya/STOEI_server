package org.kursovoi.server.dto;

import lombok.Data;
import org.kursovoi.server.model.constant.Status;

@Data
public class UserDto {

    private long id;
    private String login;
    private String name;
    private String surname;
    private String dateOfBirth;
    private String phoneNumber;
    private Status status;
    private String email;
    private String role;
    private String uuid;
}
