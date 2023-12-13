package org.kursovoi.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DepositDto {

    private long id;
    private double interest;
    private long monthsToReturn;
    private String currency;
}
