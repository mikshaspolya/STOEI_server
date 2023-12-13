package org.kursovoi.server.dto;

import lombok.Data;

@Data
public class CreateCardDto {

    private String holderName;
    private String cardIssuer;
    private String type;
    private long idAccount;
}
