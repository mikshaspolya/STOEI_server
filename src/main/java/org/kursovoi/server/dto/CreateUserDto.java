package org.kursovoi.server.dto;

import lombok.Data;

@Data
public class CreateUserDto {

    private String login;
    private String uuid;
    private String name;
    private String surname;
    private String dateOfBirth;
    private String phoneNumber;
    private String email;
    private String password;
}
