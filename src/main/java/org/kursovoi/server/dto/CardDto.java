package org.kursovoi.server.dto;

import lombok.Data;

@Data
public class CardDto {

    private long id;
    private String number;
    private String holderName;
    private String dateOfExpire;
    private String cvv;
    private String status;
    private String cardIssuer;
    private String type;
}
