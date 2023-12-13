package org.kursovoi.server.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CreateAccountDto {

    private long id;
    private long sum;
    private String dateOfIssue;
    private String currency;
    private String status;
    private String holderUuid;
}
